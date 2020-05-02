import org.apache.spark.{SparkContext, SparkConf}

object Exoplanets {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("WordCount")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val inputfile = sc.textFile("src/data/exoplanets_confirmed.csv")

    // Read CSV file
    val rdd = inputfile.map(f=>{
      f.split(",")
    })

    // Clean the data by keeping the planets that have value on the columns that we chose
    // Also we keep in mind not to retrieve the header data of the dataset.
    val cleanRdd = rdd.filter(line => {
      line(19) != "" && line(19) != "pl_radj" && line(32) != "pl_insol" && line(32) != "" && line(68) != "" && line(68) != "sy_dist"
    })

    // Mapping that performs the computation of the Earth Similarity Index (ESI)
    // Planet name , ESI
    val esiPlanets = cleanRdd.map(p => {

      val s = p(32).toDouble
      val se = 1.0

      val r = p(19).toDouble * 11.209
      val re = 1.0

      val esi = 1.0 - scala.math.sqrt(
        0.5 *
          (scala.math.pow((s-se)/(s+se),2) + scala.math.pow((r-re)/(r+re),2))
      )

      (p(0),esi,p(68))
    }).filter(l => l._2 > 0.6)

    esiPlanets.saveAsTextFile("./tmp/esi")

  }

}