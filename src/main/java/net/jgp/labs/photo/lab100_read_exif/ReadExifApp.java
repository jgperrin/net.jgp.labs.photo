package net.jgp.labs.photo.lab100_read_exif;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * Displays the EXIF attributes of a specific photo.
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
    File file = new File("data/obelix-clio-orig.jpeg");

    try {
      Metadata metadata = JpegMetadataReader.readMetadata(file);

      print(metadata, "Using JpegMetadataReader");
    } catch (JpegProcessingException e) {
      print(e);
    } catch (IOException e) {
      print(e);
    }
  }

  private static void print(Metadata metadata, String method) {
    System.out.println();
    System.out.println("-------------------------------------------------");
    System.out.print(' ');
    System.out.print(method);
    System.out.println("-------------------------------------------------");
    System.out.println();

    // A Metadata object contains multiple Directory objects
    for (Directory directory : metadata.getDirectories()) {
      // Each Directory stores values in Tag objects
      for (Tag tag : directory.getTags()) {
        System.out.println(tag);
      }

      // Each Directory may also contain error messages
      for (String error : directory.getErrors()) {
        System.err.println("ERROR: " + error);
      }
    }
  }

  private static void print(Exception exception) {
    System.err.println("EXCEPTION: " + exception);
  }
}
