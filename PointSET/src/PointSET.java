import java.util.ArrayList;

public class PointSET {
   private SET<Point2D> mSet;
   
   public PointSET() {
     // construct an empty set of points
     mSet = new SET<Point2D>();
   }

   public boolean isEmpty() {
     // is the set empty?
     return mSet.isEmpty();
   }

   public int size() {
     // number of points in the set
     return mSet.size();
   }

   public void insert(Point2D p) {
     // add the point p to the set (if it is not already in the set)
     mSet.add(p);
   }
   
   public boolean contains(Point2D p) {
     // does the set contain the point p?
     return mSet.contains(p);
   }

   public void draw() {
     // draw all of the points to standard draw
     int count = 0;
     for (Point2D p : mSet) {
       StdDraw.setPenRadius(.01);
       StdDraw.point(p.x(), p.y());
     }
   }

   public Iterable<Point2D> range(RectHV rect) {
     // all points in the set that are inside the rectangle
     ArrayList<Point2D> list = new ArrayList<Point2D>();
     for (Point2D p : mSet) {
       if (rect.contains(p)) list.add(p); 
     }
     return list;
   }

   public Point2D nearest(Point2D p) {
     // a nearest neighbor in the set to p; null if set is empty
     double dist = 0;
     double minDist = 100;
     Point2D minPoint = null;
     for (Point2D p2 : mSet) {
       dist = p2.distanceTo(p);
       if (dist < minDist) {
         minDist = dist;
         minPoint = p2;
       }
     }
     return minPoint;
   }
   
//   public static void main(String[] args) {
//     PointSET set = new PointSET();
//     set.insert(new Point2D(0.5, 0.5));
//     set.insert(new Point2D(0.25, 0.5));
//     set.insert(new Point2D(0.75, 0.75));
//     set.draw();
//   }
}