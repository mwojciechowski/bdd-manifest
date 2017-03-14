package com.hsbc.fsa.bdd.manifest.catalogue

import com.hsbc.fsa.bdd.manifest.graph.DatasetNode

/**
  * Created by michal on 12.03.17.
  */
case class DataCatalogueKey(val dataset : String,
                            val system: String,
                            val region : String,
                            val level : String) {

  override
  def toString = String.format("%s:%s:%s:%s", dataset, system, region, level)
}
