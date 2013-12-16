import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Fast {
  private ArrayList<Point[]> mCollinearPoints = new ArrayList<Point[]>();

  public static void main(String[] args) {
    String fileName = args[0];
    String line;
    int numberOfLines;
    int counter = 0;
    Point[] originalPoints;
    Point[] points;
    Fast instance = new Fast();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));

      numberOfLines = Integer.parseInt(br.readLine());
      originalPoints = new Point[numberOfLines];
      points = new Point[numberOfLines];

      while ((line = br.readLine()) != null) {
        Scanner s = new Scanner(line).useDelimiter("\\W+");
        if (s.hasNext()) {
          originalPoints[counter] = new Point(s.nextInt(), s.nextInt());
          points[counter] = originalPoints[counter];

          originalPoints[counter].draw();
          counter++;
        }
      }

      br.close();
      Arrays.sort(originalPoints);
      
      for (int i = 0; i < originalPoints.length; i++) {
        points = new Point[originalPoints.length];

        for (int z = 0; z < originalPoints.length; z++) {
          points[z] = originalPoints[z];
        }

        Arrays.sort(points, originalPoints[i].SLOPE_ORDER);
        
        ArrayList<Point> potentialLine = new ArrayList<Point>();
        // add the current point we are examining.
        double slope = 0;

        for (int j = 0; j < points.length - 1; j++) {  
          if (potentialLine.size() == 0) {
            //if (points[j + 1].y == 2986) System.out.println("ADDING POINT" + points[j]);
            slope = points[0].slopeTo(points[j]);
            potentialLine.add(points[0]);
            potentialLine.add(points[j]);
          }
          
          if (slope == points[0].slopeTo(points[j + 1])) {
            //if (points[j + 1].y == 2986) System.out.println("ADDING POINT" + points[j + 1]);
            potentialLine.add(points[j + 1]);
          }
          else {
            // IF NEW SLOPE, BUT ALREADY FOUND > 3 POINTS ON A LINE, ADD TO FOUND ARRAY
            if (potentialLine.size() > 3) {
              //System.out.println("SIZE" + potentialLine.size());
              instance.foundLine(potentialLine);
            }
            potentialLine = new ArrayList<Point>();
          }
        }
        
        // IF THERE ARE NO MORE POINTS LEFT AND SIZE >= 3, ADD TO FOUND ARRAY
        if (potentialLine.size() > 3) {
          instance.foundLine(potentialLine);
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

  private void foundLine(ArrayList<Point> points) {
    Point[] pointsArray = points.toArray(new Point[points.size()]);
    if (pointsArray[0].compareTo(pointsArray[1]) > 0) return;

    //if (!hasLine(pointsArray)) {
    if(true) {
      mCollinearPoints.add(pointsArray);

      for (int i = 0; i < pointsArray.length - 1; i++) {
        System.out.print(pointsArray[i] + " -> ");
      }
      System.out.println(pointsArray[pointsArray.length - 1]);
      
      pointsArray[0].drawTo(pointsArray[pointsArray.length - 1]);
    }
  }
}
