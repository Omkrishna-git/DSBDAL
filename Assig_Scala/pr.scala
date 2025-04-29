import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]") 

    val sc = new SparkContext(conf)
    val textFile = sc.textFile(args(0))

    val counts = textFile
      .flatMap(line => line.split("\\s+")) 
      .map(word => (word.toLowerCase, 1)) 
      .reduceByKey(_ + _)                  

    counts.saveAsTextFile(args(1))

    sc.stop()
  }
}
