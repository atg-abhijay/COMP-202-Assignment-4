public class Image {
  private String metadata;
  private int maxRange;
  private Pixel[][] data;
  
  public Image(String information, int upperBound, Pixel[][] pixels) {
    if(upperBound < 0) {
      throw new IllegalArgumentException("The maximum range cannot be negative!");
    }
    this.metadata = information;
    this.maxRange = upperBound;
    this.data = new Pixel[pixels.length][pixels[0].length];
    /* copying and storing the inputted
     * pixel array into a new array */
    for(int i = 0; i < this.data.length; i++) {
      for(int j = 0; j < this.data[0].length; j++) {
        this.data[i][j] = pixels[i][j];
      }
    }
  }
  
  public String getMetadata() {
    return this.metadata;
  }
  
  public int getMaxRange() {
    return this.maxRange;
  }
  
  public int getWidth() {
    /* the length of the sub-array of
     * data is the width of the image */
    return this.data[0].length;
  }
  
  public int getHeight() {
    /* the length of the data array
     * is the height of the image */
    return this.data.length;
  }
  
  public Pixel getPixel(int i, int j) {
    /* returning the (i,j)th element of
     * the data array will return the pixel
     * at the (i,j)th position */
    return this.data[i][j];
  }
  
  public void flip(boolean horizontal) {
    /* we create a new Pixel[][] which will
     * store the pixels which result from
     * the horizontal flip or vertical flip */
    Pixel[][] flipped = new Pixel[this.data.length][this.data[0].length];
    /* when we flip horizontally, the 1st column
     * becomes the last column, the 2nd column
     * becomes the second last one and so on */
    if(horizontal) {
      for(int i = 0; i < flipped.length; i++) {
        for(int j = 0; j < flipped[0].length; j++) {
          flipped[i][flipped[0].length - j - 1] = this.data[i][j];
        }
      }
    }
    /* when we flip vertically, the 1st row
     * becomes the last row, the 2nd row
     * becomes the second last one and so on */
    else{
      for(int i = 0; i < flipped.length; i++) {
        for(int j = 0; j < flipped[0].length; j++) {
          flipped[flipped.length - i -1][j] = this.data[i][j];
        }
      }
    }
    /* we now update the entries in the original 2D
     * array of Pixels with the flipped entries */
    for(int i = 0; i < flipped.length; i++) {
      for(int j = 0; j < flipped[0].length; j++) {
        this.data[i][j] = flipped[i][j];
      }
    }
  }
  
  
  
  public void toGrey() {
    /* we iterate through the 2D array of Pixels of
     * the image. this.data[i][j] refers to a Pixel and
     * each Pixel has the method grey() defined on it. we
     * call the method grey() on each of the pixels and update
     * the pixels to store (or refer to) these new greyscale values */
    for(int i = 0; i < this.data.length; i++) {
      for(int j = 0; j < this.data[0].length; j++) {
        this.data[i][j] = new Pixel(this.data[i][j].grey());
      }
    }
  }
  
  
  public void crop(int startX, int startY, int endX, int endY) {
    if(startX < 0 || startY < 0 || endX > this.data[0].length || endY > this.data.length || endX < startX || endY < startY) {
      throw new IllegalArgumentException("The coordinates for cropping are invalid!");
    }
    /* creating a new 2D array of Pixels which will
     * store the pixels which we wish to crop 
     * 
     * the 'height' or the number or rows in this new
     * 2D array of Pixels will be the difference of the
     * y coordinates and the 'width' or number of columns
     * will be the difference of the x coordinates */
    Pixel[][] pixels = new Pixel[endY - startY][endX - startX];
    /* the integers 'row' and 'column' will
     * act as indices for our new pixels array
     * 
     * if 'column' reaches the end value, then we reset column
     * to be equal to zero and we update 'row' by 1 */
    int row = 0;
    int column = 0;
    for(int i = startY; i < endY; i++) {
      for(int j = startX; j < endX; j++) {
        pixels[row][column] = this.data[i][j];
        column++;
        if(column == endX - startX) {
          row++;
          column = 0;
        }
      }
    }
    /* in the end, we update this.data
     * to our new 2D array of Pixels */
    this.data = pixels;
  }
}