package net.jgp.labs.photo.lab100_read_exif;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Displays the EXIF attributes of a photo or photos.
 * 
 * @author jgp
 */
public class ReadExifApp {

  /**
   * main() is your entry point to the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    ReadExifApp app = new ReadExifApp();
    app.start();
  }

  /**
   * The processing code.
   */
  private void start() {
    // Creates a session on a local master
    SparkSession spark = SparkSession.builder()
        .appName("CSV to Dataset")
        .master("local")
        .getOrCreate();

    // Reads a CSV file with header, called books.csv, stores it in a
    // dataframe
    Dataset<Row> df = spark.read().format("csv")
        .option("header", "true")
        .load("data/books.csv");

    // Shows at most 5 rows from the dataframe
    df.show(5);
  }
}
