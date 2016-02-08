package com.professionalscala.ch11

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object Spark extends App {
  val conf = new SparkConf().setAppName("Simple Application").setMaster("local[4]")
  val sc = new SparkContext(conf)
  val readme: RDD[String] = sc.textFile("README.md").cache()
  val numAs = readme.filter(line => line.contains("a")).count()
  val numBs = readme.filter(line => line.contains("b")).count()
  println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
}

object SparkSql extends App{
val conf = new SparkConf().setAppName("Simple Application").setMaster("local[4]")
val sc = new SparkContext(conf)
val sqlContext = new SQLContext(sc)
val df = sqlContext.read
  .format("com.databricks.spark.csv")
  .option("header", "true") // Use first line of all files as header
  .option("inferSchema", "true") // Automatically infer data types
  .load("src/main/resources/users.csv")

df.show() // will show current DataFrame as a table

df.registerTempTable("users")
val adults = sqlContext.sql("SELECT name FROM users WHERE age > 18")
adults.show()

}
