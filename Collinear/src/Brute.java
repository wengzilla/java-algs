import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Brute {
  public static void main(String[] args) {
    String fileName = args[0];
    String line;
    int numberOfLines;
    int counter = 0;
    Point[] points;
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      numberOfLines = Integer.parseInt(br.readLine());
      points = new Point[numberOfLines];

      while ((line = br.readLine()) != null) {
        Scanner s = new Scanner(line).useDelimiter("\\W+");
        if (s.hasNext()) {
          points[counter] = new Point(s.nextInt(), s.nextInt());
          points[counter].draw();
          counter++;
        }
      }
      
      br.close();

      Arrays.sort(points);
      for (int i = 0; i < points.length - 3; i++) {
        for (int j = i + 1; j < points.length - 2; j++) {
          for (int k = j + 1; k < points.length - 1; k++) {
            for (int l = k + 1; l < points.length; l++) {
              if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                points[i].drawTo(points[l]);
                System.out.println(points[i] + "->" + points[j] + "->" + points[k] + "->" + points[l]);
              }
            }
          }
        }
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
