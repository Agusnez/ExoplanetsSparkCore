import org.apache.spark.{SparkContext, SparkConf}
object Exoplanets {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val inputfile = sc.textFile("src/data/exoplanets_confirmed.csv")
    val countExoplanets = inputfile.count()
    println("Hello world!")
    println("There are " + countExoplanets + " exoplanets in our dataset.")
  }
}