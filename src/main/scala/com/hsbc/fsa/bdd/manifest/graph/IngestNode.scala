package com.hsbc.fsa.bdd.manifest.graph

import com.hsbc.fsa.bdd.manifest.catalogue.DataCatalogueKey

/**
  * Created by michal on 12.03.17.
  */
case class IngestNode(feature : String, key : DataCatalogueKey) extends Node(toString) {
  override
  def toString = String.format("%s%s", feature, key.usid)
}
