import java.io.*;
public class Comp202Photoshop {
  public static void main(String[] args) {
    /* we print an error message if the number
     * of arguments inputted is less than 4 */
    if(args.length < 4) {
      System.out.println("You must give 4 arguments (or 8 arguments if you want to crop the image!)");
      return;
    }
    
    String inputFile = args[0];
    String outputFile = args[1];
    
    String format = args[2];
    /* we print an error message if the
     * format doesn't match pgm or pnm */
    if(!(format.equals("pgm")) && !(format.equals("pnm"))) {
      System.out.println("The output format for the image is invalid!");
      return;
    }
    
    String operation = args[3];
    /* we print an error message if the operation
     * doesn't match -fh or -fv or -gs or -cr */
    if(!(operation.equals("-fh")) && !(operation.equals("-fv")) && !(operation.equals("-gs")) && !(operation.equals("-cr"))) {
      System.out.println("The operation requested does not exist!");
      return;
    }
    
    int startX = 0;
    int startY = 0;
    int endX = 0;
    int endY = 0;
    if(operation.equals("-cr")) {
    /* we print an error message if the operation
     * requested is crop but the coordinates for
     * cropping have not been provided */
      if(args.length < 8) {
        System.out.println("You haven't given the coordinates for cropping!");
        return;
      }
      startX = Integer.parseInt(args[4]);
      startY = Integer.parseInt(args[5]);
      endX = Integer.parseInt(args[6]);
      endY = Integer.parseInt(args[7]);
    }
    
    try{
      /* we first use the read() method from ImageFileUtilities
       * to obtain our input image */
      Image inputImage = ImageFileUtilities.read(inputFile);
      /* when the output format requested is pnm, we use
       * the writePnm() method to write our images
       * 
       * we flip the image horizontally or vertically by using
       * the flip() method (from the Image class) and providing
       * it a boolean which tells the method whether to do a
       * horizontal flip or a vertical flip
       * 
       * we crop the image by using the crop() method from the
       * Image class and providing it the coordinates for cropping
       * 
       * for the conversion of the image to greyscale,
       * we call the method writePgm instead of writePnm. */
      if(format.equals("pnm")) {
        if(operation.equals("-fh")) {
          inputImage.flip(true);
          ImageFileUtilities.writePnm(inputImage, outputFile);
        }
        if(operation.equals("-fv")) {
          inputImage.flip(false);
          ImageFileUtilities.writePnm(inputImage, outputFile);
        }
        if(operation.equals("-gs")) {
          ImageFileUtilities.writePgm(inputImage, outputFile);
        }
        if(operation.equals("-cr")) {
          inputImage.crop(startX, startY, endX, endY);
          ImageFileUtilities.writePnm(inputImage, outputFile);
        }
      }
      
      /* when the output format requested is pgm, we use
       * the writePgm() method to write our images */
      else {
        if(operation.equals("-fh")) {
          inputImage.flip(true);
          ImageFileUtilities.writePgm(inputImage, outputFile);
        }
        if(operation.equals("-fv")) {
          inputImage.flip(false);
          ImageFileUtilities.writePgm(inputImage, outputFile);
        }
        if(operation.equals("-gs")) {
          ImageFileUtilities.writePgm(inputImage, outputFile);
        }
        if(operation.equals("-cr")) {
          inputImage.crop(startX, startY, endX, endY);
          ImageFileUtilities.writePgm(inputImage, outputFile);
        }
      }
    }
    
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
}