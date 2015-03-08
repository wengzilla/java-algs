/**
 * Created by edwardweng on 7/29/14.
 */
public class KdTreeFileVisualizer {

    public static void main(String[] args) {

        String filename = "sample_input/input800K.txt";
        In in = new In(filename);

        StdDraw.show(0);

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        kdtree.draw();

        StdDraw.show(0);
    }
}
