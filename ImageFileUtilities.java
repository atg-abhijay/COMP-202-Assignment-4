import java.io.*;
import java.util.Scanner;
public class ImageFileUtilities {
  public static Image read(String filename) throws IOException {
    File inputFile = new File(filename);
    Scanner reader = new Scanner(inputFile);
    String format = reader.next();
    /* if the format of the image is not P3 or P2,
     * we throw an exception saying that the format
     * for the image is not correct */
    if(!(format.equals("P3")) && !(format.equals("P2"))) {
      throw new IOException("The format code is not right!");
    }
    /* we create a String which will store all of
     * the comments that are present in the image.
     * while the scanner object does not obtain an
     * int, we keep reading the input and appending
     * it to our existing String variable. 
     * 
     * as soon as we get an int, it means that there are no
     * more comments and hence we stop appending the
     * string. in this way, we obtain all of the comments */
    String metadata = "";
      while(!reader.hasNextInt()) {
        metadata += reader.nextLine() + "\n";
      }
    //System.out.println(metadata);
    
    int width = reader.nextInt();
    int height = reader.nextInt();
    int upperBound = reader.nextInt();
    /* System.out.println("Width: " + width);
    System.out.println("Height: " + height);
    System.out.println("Max Range: " + upperBound); */
    
    /* we create a new Pixel[][] which will
     * store the pixels of our input image */
    Pixel[][] pixelsOfImage = new Pixel[height][width];
    /* if the format of the image is P3, we will read
     * the numbers in groups of 3 for red, green and blue */
    if(format.equals("P3")) {
      int red = 0;
      int green = 0;
      int blue = 0;
      /* we need to read the numbers from the
       * input image (height * width) number of
       * times to complete our Pixel[][] */
      for(int i = 0; i < height; i++) {
        for(int j = 0; j < width; j++) {
        red = reader.nextInt();
        green = reader.nextInt();
        blue = reader.nextInt();
        /* we create a new Pixel object based on
         * the colours that we read from the input
         * image and assign that pixel to our Pixel[][] */
        Pixel pixel = new Pixel(red, green, blue);
        pixelsOfImage[i][j] = pixel;
        }
      }
    }
    
    /* if the format of the image is P2, we will read
     * the numbers individually and use only one number
     * to assign the red, green and blue values for the
     * pixel since all the 3 colours have the same value */
    else{
      int intensity = 0;
      /* we need to read the numbers from the
       * input image (height * width) number of
       * times to complete our Pixel[][] */
      for(int i = 0; i < height; i++) {
        for(int j = 0; j < width; j++) {
          intensity = reader.nextInt();
          /* we create a new Pixel object based on
           * the value of intensity that we read
           * from the input image and assign
           * that pixel to our Pixel[][] */
          Pixel pixel = new Pixel(intensity);
          pixelsOfImage[i][j] = pixel;
        }
      }
    }
    
    /* we create a new Image object using the metadata, maxRange
     * and Pixel[][] that we have extracted from the input image */
    Image createdImage = new Image(metadata, upperBound, pixelsOfImage);
    reader.close();
    return createdImage;
  }
  
  public static void writePnm(Image sampleImage, String filename) throws IOException {
    FileWriter fw = new FileWriter(filename);
    BufferedWriter bw = new BufferedWriter(fw);
    /* the first thing that we are supposed to write to
     * the file is the format which in this case is P3 */
    bw.write("P3\n");
    /* since sampleImage is an object of type Image, it
     * has methods getMetadata(), getWidth(), getHeight(),
     * getMaxRange() defined on it. we call these methods on
     * it to get the metadata, width, height and maximum range
     * of the image and we write these to the file */
    bw.write(sampleImage.getMetadata() + "\n");
    bw.write(sampleImage.getWidth() + " " + sampleImage.getHeight() + "\n");
    bw.write(sampleImage.getMaxRange() + "\n");
    /* we have to write (height * width) number of
     * triplets to the file to make our PNM image */
    for(int i = 0; i < sampleImage.getHeight(); i++) {
      for(int j = 0; j < sampleImage.getWidth(); j++) {
        /* sampleImage is an object of type Image. it has
         * a method getPixel(i,j) defined on it. when we call
         * this method on sampleImage we get an object of type Pixel.
         * on this Pixel, there are methods getRed(), getGreen(), getBlue()
         * defined. when we call these methods on the Pixel
         * that we obtained, we obtain the values for our
         * red, green and blue components */
        bw.write(sampleImage.getPixel(i,j).getRed() + " ");
        bw.write(sampleImage.getPixel(i,j).getGreen() + " ");
        bw.write(sampleImage.getPixel(i,j).getBlue() + "\n");
      }
    }
    bw.close();
    fw.close();
  }
  
  public static void writePgm(Image sampleImage, String filename) throws IOException {
    FileWriter fw = new FileWriter(filename);
    BufferedWriter bw = new BufferedWriter(fw);
    /* the first thing that we are supposed to write to
     * the file is the format which in this case is P2 */
    bw.write("P2\n");
    bw.write(sampleImage.getMetadata() + "\n");
    bw.write(sampleImage.getWidth() + " " + sampleImage.getHeight() + "\n");
    bw.write(sampleImage.getMaxRange() + "\n");
    /* we have to write (height * width) number
     * of values to the file to make our PGM image */
    for(int i = 0; i < sampleImage.getHeight(); i++) {
      for(int j = 0; j < sampleImage.getWidth(); j++) {
        /* sampleImage is an object of type Image. it has
         * a method getPixel(i,j) defined on it. when we call
         * this method on sampleImage, we get an object of
         * type Pixel. a Pixel has a method grey() defined on
         * it. grey() returns the average of the red, green and
         * blue components. we write this average value, which is
         * representative of the intensity of the colour grey, to the file */
        bw.write(sampleImage.getPixel(i,j).grey() + "\n");
      }
    }
    bw.close();
    fw.close();
  }
}