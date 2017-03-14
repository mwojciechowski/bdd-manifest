package com.hsbc.fsa.bdd.manifest.fmc

import org.apache.commons.csv.{CSVParser, CSVRecord}
import scala.collection.JavaConversions._
import scala.collection.Map


class FMCParser(parser : CSVParser) {

  def parse : List[FMCEdge] = {
    val header = parser.getHeaderMap.mapValues(a => a)
    parser.toList.map(toFMCEdge(header))
  }

  private def toFMCEdge(header : Map[String, Integer])(record: CSVRecord) = {
    val map = header.toMap.map(e => (e._1.toLowerCase,record.get(e._2)))
    new FMCEdge(map)
  }
}
