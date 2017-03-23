package com.hsbc.fsa.bdd.manifest.graph

import com.hsbc.fsa.bdd.manifest.fmc.FMCEdge

import scalax.collection.GraphEdge._
import scalax.collection.GraphPredef._
import scalax.collection.GraphTraversal.{BreadthFirst, Parameters, Predecessors}
import scalax.collection.mutable.Graph

/**
  * Created by michal on 11.03.17.
  */
class BDDGraph(fmcEgdes : Traversable[FMCEdge]) {
  def create(e : FMCEdge) : List[DiEdge[Node]] = {
    if("level2".equals(e.level)
      && e.isInput && e.file != null) {
      //val dataset = DatasetNode(e.catalogueKey)
      val datafile = DatafileNode(e.catalogueKey)
      val ing = IngestNode(e.feature, e.catalogueKey)

      List(datafile ~> ing, ing ~> e.start, e.start ~> e.end)
    } else {
      List(e.start ~> e.end)
    }
  }

  val edges = fmcEgdes.flatMap(create)

  val graph = Graph.from(Nil, edges)

  val nodes = graph.nodes.toList

  def updateTransf(features : Map[String, String]) : Map[Node, String] =
    nodes.map( n => n.value match {
      case TransfNode(f, j) => n.value -> features(f)
    } ).toMap
  def isAcyclic: Boolean = graph.isAcyclic

  def getCycle = graph.findCycle

  def traverser(root : Node) = graph.outerEdgeTraverser(graph.get(root), Parameters.Bfs(Predecessors))

}
