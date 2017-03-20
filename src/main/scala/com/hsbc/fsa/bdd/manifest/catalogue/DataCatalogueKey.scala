package com.hsbc.fsa.bdd.manifest.catalogue

/**
  * Created by michal on 12.03.17.
  */
case class DataCatalogueKey(dataset : String, system: String, region : String,
                            entity : String, dtype : String, level : String) {

  def id = String.format("%s:%s:%s:%s:%s:%s", dataset, system, region, entity, dtype, level)

  def usid = String.format("%s_%s_%s_%s_%s_%s", dataset, system, region, entity, dtype, level)
}
