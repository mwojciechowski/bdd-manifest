package com.hsbc.fsa.bdd.manifest.graph

import com.hsbc.fsa.bdd.manifest.catalogue.DataCatalogueKey

/**
  * Created by michal on 12.03.17.
  */
case class DatasetNode(key : DataCatalogueKey) extends Node(key.id)

case class DatafileNode(key : DataCatalogueKey) extends Node(key.id)

