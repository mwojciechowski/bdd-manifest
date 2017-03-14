package com.hsbc.fsa.bdd.manifest.graph

import com.hsbc.fsa.bdd.manifest.fmc.FMCEdge

import scalax.collection.GraphEdge.DiEdge
import scalax.collection.mutable.Graph

/**
  * Created by michal on 11.03.17.
  */
case




class BDDGraph(fmcEgdes : Seq[FMCEdge]) {
  def create(e : FMCEdge) : List[DiEdge[Node]] = {
    if("level2".equals(e.level)
      && e.isInput && e.file != null) {
      val data = DatasetNode("")
      val ing = IngestNode("")
      List(DiEdge(data, ing), DiEdge(ing, e.start), DiEdge(e.start, e.end))
    } else {
      List(DiEdge(e.start, e.end))
    }
  }

  val edges = fmcEgdes.flatMap(create(_))
  val graph  = Graph(edges)
}
