package com.hsbc.fsa.bdd.manifest.fmc

import com.hsbc.fsa.bdd.manifest.graph.BDDGraph
import org.junit.Assert._
import org.junit.Test

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
    assert(graph.nodes.size == 15)
    assert(graph.edges.size == 15)
    assert(graph.isAcyclic)
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
}
