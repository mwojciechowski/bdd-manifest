package com.hsbc.fsa.bdd.manifest.graph

/**
  * Created by michal on 12.03.17.
  */
case class TransfNode(feature : String, job : String) extends Node(toString) {
  override
  def toString = String.format("%s%s", feature, job)
}
