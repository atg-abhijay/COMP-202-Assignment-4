public class Pixel {
  private int red;
  private int green;
  private int blue;
  
  public Pixel(int redColour, int greenColour, int blueColour) {
    /* throwing an exception if any of the
     * colours are out of the range [0,255] */
    if(redColour < 0 || redColour > 255 || greenColour < 0 || greenColour > 255 || blueColour < 0 || blueColour > 255) {
      throw new IllegalArgumentException("The values for the colours are invalid! They should be between 0 and 255");
    }
    this.red  = redColour;
    this.green = greenColour;
    this.blue = blueColour;
  }
  
  public Pixel(int intensity) {
    /* throwing an exception if the intensity
     * is outside of the designated range [0,255] */
    if(intensity < 0 || intensity > 255) {
      throw new IllegalArgumentException("The value for the intensity should be between 0 and 255!");
    }
    /* setting all the colours to the same intensity
     * for the purpose of a greyscale image */
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }
  
  public int getRed() {
    return this.red;
  }
  
  public int getGreen() {
    return this.green;
  }
  
  public int getBlue() {
    return this.blue;
  }
  
  public int grey() {
    /* this method finds the average of the 3
     * colours and returns that value. this 
     * value will be used for the purpose of making
     * the pixel grey */
    return (int) ((this.red + this.green + this.blue)/3);
  }
  
}