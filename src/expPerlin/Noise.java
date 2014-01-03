package expPerlin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Noise {



  public static void main(String[] args) {
    final int WIDTH = 900, HEIGHT = 600;

    double[] data = new double[WIDTH * HEIGHT];
    int count = 0;

    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        data[count++] = PerlinNoise.perlinNoise(20.0* x / WIDTH, 10.0* y / HEIGHT, 0.0);
      }
    }

    double minValue = data[0], maxValue = data[0];
    for (int i = 0; i < data.length; i++) {
      minValue = Math.min(data[i], minValue);
      maxValue = Math.max(data[i], maxValue);
    }

    int[] pixelData = new int[WIDTH * HEIGHT];
    for (int i = 0; i < data.length; i++) {
      pixelData[i] = (int) (255 * (data[i] - minValue) / (maxValue - minValue));
    }

    BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
    img.getRaster().setPixels(0, 0, WIDTH, HEIGHT, pixelData);

    File output = new File("image.jpg");
    try {
      ImageIO.write(img, "jpg", output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}