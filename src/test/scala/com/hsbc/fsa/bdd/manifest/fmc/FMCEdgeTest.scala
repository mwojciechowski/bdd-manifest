package com.hsbc.fsa.bdd.manifest.fmc

import com.hsbc.fsa.bdd.manifest.graph.{BDDGraph, Node}
import org.junit.Assert._
import org.junit.Test

import scalax.collection.Graph
import scalax.collection.GraphEdge.DiEdge

/**
  * Created by michal on 17.03.17.
  */

@Test
class FMCEdgeTest  {

  @Test
  def testAcyclicGraph = {
    val maps = List(
      Map("feature" -> "f1", "dataset" -> "ds1", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f1", "dataset" -> "ds2", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f1", "dataset" -> "ds3", "input" -> "output").withDefault(s => ""),
      Map("feature" -> "f2", "dataset" -> "ds3", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f2", "dataset" -> "ds4", "input" -> "output").withDefault(s => ""),

      Map("feature" -> "f3", "dataset" -> "ds5", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f3", "dataset" -> "ds6", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f3", "dataset" -> "ds7", "input" -> "output").withDefault(s => ""),
      Map("feature" -> "f4", "dataset" -> "ds7", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f4", "dataset" -> "ds8", "input" -> "output").withDefault(s => ""),

      Map("feature" -> "f5", "dataset" -> "ds4", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f5", "dataset" -> "ds8", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f5", "dataset" -> "ds9", "input" -> "output").withDefault(s => "")
    )
    val graph = new BDDGraph( maps.map(m => new FMCEdge(m)))
    assert(graph.nodes.size == 14)
    assert(graph.edges.size == 13)
    assert(graph.isAcyclic)

    val edge = new FMCEdge(Map("feature" -> "f5", "dataset" -> "ds4", "input" -> "input").withDefault(s => ""))
    val node = edge.start
    val traverser = graph.traverser(node)
    val g = Graph.from[Node, DiEdge](Nil, traverser)
    System.out.println(g.edges.toOuter.mkString(" "))

  }

  @Test
  def testCyclicGraph = {
    val maps = List(
      Map("feature" -> "f1", "dataset" -> "ds1", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f1", "dataset" -> "ds2", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f1", "dataset" -> "ds3", "input" -> "output").withDefault(s => ""),
      Map("feature" -> "f2", "dataset" -> "ds3", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f2", "dataset" -> "ds4", "input" -> "output").withDefault(s => ""),

      Map("feature" -> "f3", "dataset" -> "ds5", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f3", "dataset" -> "ds6", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f3", "dataset" -> "ds7", "input" -> "output").withDefault(s => ""),
      Map("feature" -> "f4", "dataset" -> "ds7", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f4", "dataset" -> "ds8", "input" -> "output").withDefault(s => ""),

      Map("feature" -> "f4a", "dataset" -> "ds8", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f4a", "dataset" -> "ds5", "input" -> "output").withDefault(s => ""),

      Map("feature" -> "f5", "dataset" -> "ds4", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f5", "dataset" -> "ds8", "input" -> "input").withDefault(s => ""),
      Map("feature" -> "f5", "dataset" -> "ds9", "input" -> "output").withDefault(s => "")
    )
    val graph = new BDDGraph( maps.map(m => new FMCEdge(m)))
    assert(graph.nodes.size == 15)
    assert(graph.edges.size == 15)
    val optCycle = graph.getCycle
    assert(optCycle.isDefined)
    val cycle = optCycle.get.toList
    assert(cycle.size == 13)
  }

  def test1 = {
    Map("featurefile" -> "f3a", "dataset" -> "ds2a", "File Pattern" -> "fileA", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3a", "dataset" -> "ds2b", "File Pattern" -> "fileB", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3b", "dataset" -> "ds2b", "File Pattern" -> "fileB", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3b", "dataset" -> "ds2c", "File Pattern" -> "fileC", "datasource" -> "level2", "input/output" -> "input")

    Map("featurefile" -> "f3a", "dataset" -> "ds3a", "datasource" -> "level3", "input/output" -> "output")
    Map("featurefile" -> "f3b", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "output")
    Map("featurefile" -> "f3b", "dataset" -> "ds3c", "datasource" -> "level3", "input/output" -> "output")

    Map("featurefile" -> "f4a", "dataset" -> "ds3a", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4a", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4b", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4b", "dataset" -> "ds3c", "datasource" -> "level3", "input/output" -> "input")

    Map("featurefile" -> "f4a", "dataset" -> "ds4a", "datasource" -> "level4", "input/output" -> "output")
    Map("featurefile" -> "f4b", "dataset" -> "ds4b", "datasource" -> "level4", "input/output" -> "output")

    Map("featurefile" -> "f5", "dataset" -> "ds4a", "datasource" -> "level4", "input/output" -> "input")
    Map("featurefile" -> "f5", "dataset" -> "ds4b", "datasource" -> "level4", "input/output" -> "input")

    Map("featurefile" -> "f5", "dataset" -> "ds5", "datasource" -> "level5", "input/output" -> "output")

  }

  def test2 = {
    Map("featurefile" -> "f3a", "dataset" -> "ds2a", "File Pattern" -> "fileA", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3a", "dataset" -> "ds2b", "File Pattern" -> "fileB", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3b", "dataset" -> "ds2b", "File Pattern" -> "fileB", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3b", "dataset" -> "ds2c", "File Pattern" -> "fileC", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3c", "dataset" -> "ds2c", "File Pattern" -> "fileC", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3c", "dataset" -> "ds2d", "File Pattern" -> "fileD", "datasource" -> "level2", "input/output" -> "input")
    Map("featurefile" -> "f3c", "dataset" -> "ds2e", "File Pattern" -> "fileE", "datasource" -> "level2", "input/output" -> "input")

    Map("featurefile" -> "f3a", "dataset" -> "ds3a", "datasource" -> "level3", "input/output" -> "output")
    Map("featurefile" -> "f3b", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "output")
    Map("featurefile" -> "f3c", "dataset" -> "ds3c", "datasource" -> "level3", "input/output" -> "output")
    Map("featurefile" -> "f3c", "dataset" -> "ds3d", "datasource" -> "level3", "input/output" -> "output")

    Map("featurefile" -> "f4d", "dataset" -> "ds3a", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4a", "dataset" -> "ds3a", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4a", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4b", "dataset" -> "ds3b", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4b", "dataset" -> "ds3c", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4c", "dataset" -> "ds3c", "datasource" -> "level3", "input/output" -> "input")
    Map("featurefile" -> "f4c", "dataset" -> "ds3d", "datasource" -> "level3", "input/output" -> "input")

    Map("featurefile" -> "f4d", "dataset" -> "ds4a", "datasource" -> "level4", "input/output" -> "output")
    Map("featurefile" -> "f4a", "dataset" -> "ds4b", "datasource" -> "level4", "input/output" -> "output")
    Map("featurefile" -> "f4b", "dataset" -> "ds4c", "datasource" -> "level4", "input/output" -> "output")
    Map("featurefile" -> "f4c", "dataset" -> "ds4d", "datasource" -> "level4", "input/output" -> "output")

    Map("featurefile" -> "f5a", "dataset" -> "ds4a", "datasource" -> "level4", "input/output" -> "input")
    Map("featurefile" -> "f5a", "dataset" -> "ds4b", "datasource" -> "level4", "input/output" -> "input")
    Map("featurefile" -> "f5a", "dataset" -> "ds4c", "datasource" -> "level4", "input/output" -> "input")
    Map("featurefile" -> "f5b", "dataset" -> "ds4c", "datasource" -> "level4", "input/output" -> "input")
    Map("featurefile" -> "f5b", "dataset" -> "ds4d", "datasource" -> "level4", "input/output" -> "input")

    Map("featurefile" -> "f5a", "dataset" -> "ds5a", "datasource" -> "level5", "input/output" -> "output")
    Map("featurefile" -> "f5b", "dataset" -> "ds5b", "datasource" -> "level5", "input/output" -> "output")
  }
}
