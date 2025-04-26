import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount {
  def main(args: Array[String]): Unit = {
    // Set up Spark configuration
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]") // Use all available CPU cores locally

    // Create Spark context
    val sc = new SparkContext(conf)

    // Read input file (args(0) is the input file path)
    val textFile = sc.textFile(args(0))

    // Process word counts
    val counts = textFile
      .flatMap(line => line.split("\\s+")) // Split lines into words
      .map(word => (word.toLowerCase, 1))  // Convert to lowercase, map each word to //(word,1)
      .reduceByKey(_ + _)                  // Sum counts for each word

    // Save output to file (args(1) is the output directory)
    counts.saveAsTextFile(args(1))

    // Stop Spark context
    sc.stop()
  }
}
