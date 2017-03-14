package com.hsbc.fsa.bdd.manifest.fmc

import com.hsbc.fsa.bdd.manifest.catalogue.DataCatalogueKey
import com.hsbc.fsa.bdd.manifest.graph.{DatasetNode, TransfNode}

class FMCEdge(val map : Map[String, String]) {

  def get(column : String) = map(column)

  def vertexes = Map(true -> datasetNode, false -> jobNode)

  def start = vertexes(isInput)

  def end = vertexes(isOutput)

  val isInput = "input" equals input

  val isOutput = !isInput

  def input = map("input")

  def datasetNode = DatasetNode(DataCatalogueKey(dataset, system, region, level))

  def jobNode = TransfNode(feature, job)

  def feature = map("feature")

  def job = map("job")

  def dataset = map("dataset")

  def system = map("system")

  def region = map("region")

  def level = map("level")

  def file = map("file")
}
