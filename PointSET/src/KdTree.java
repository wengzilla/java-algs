import java.util.ArrayList;

public class KdTree {

    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;

    private Node mRoot;
    private int mSize = 0;

    private static class Node {
        private Point2D p; // the point
        private RectHV rect;
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree
        private boolean type;

        public Node(Point2D p, RectHV rect, Node lb, Node rt, boolean type) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.type = type;
        }

        public int compareTo(Point2D comparePoint) {
            if (p.equals(comparePoint)) return 0;
            if (type == VERTICAL) {
                if (p.x() <= comparePoint.x()) return -1;
                else return 1;
            } else {
                if (p.y() <= comparePoint.y()) return -1;
                else return 1;
            }
        }

        public String toString() {
            return "(" + p.x() + "," + p.y() + ")";
        }
    }

    public KdTree() {
        // construct an empty set of points
    }

    public boolean isEmpty() {
        // is the set empty?
        return (mRoot == null);
    }

    public int size() {
        // number of points in the set
        return mSize;
    }

    public void insert(Point2D p) {
        // add the point p to the set (if it is not already in the set)
        if (mRoot == null) {
            mSize++;
            mRoot = new Node(p, new RectHV(0, 0, 1, 1), null, null, VERTICAL);
        } else {
            mRoot = put(mRoot, p, null);
        }
    }

    private Node put(Node root, Point2D p, Node parent) {
        if (root == null) {
            mSize++;
            return new Node(p, getRectHV(p, parent), null, null, !parent.type);
        }
        int cmp = root.compareTo(p);

        // don't add point to set if it's already in the set.
        if (cmp < 0) root.lb = put(root.lb, p, root);
        else if (cmp > 0) root.rt = put(root.rt, p, root);

        return root;
    }

    private RectHV getRectHV(Point2D p, Node parent) {
        RectHV rect;
        if (parent.type == VERTICAL) {
            if (parent.p.x() > p.x()) {
                rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
            } else {
                rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
            }
        } else {
            if (parent.p.y() > p.y()) {
                rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
            } else {
                rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
            }
        }
        return rect;
    }

    public boolean contains(Point2D p) {
        return get(mRoot, p) != null;
    }

    private Node get(Node node, Point2D p) {
        if (node == null) return null;
        int cmp = node.compareTo(p);

        if (cmp < 0) return get(node.lb, p);
        else if (cmp > 0) return get(node.rt, p);
        else return node;
    }

    public void draw() {
        // draw all of the points to standard draw

        StdDraw.setPenRadius(.01);
        for (Node n : nodes()) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(n.p.x(), n.p.y());

            StdDraw.setPenRadius(.005);
            if (n.type == VERTICAL) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points in the set that are inside the rectangle
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        range(mRoot, list, rect);
        return list;
    }

    private Iterable<Node> nodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        nodes(mRoot, list);
        return list;
    }

    private void nodes(Node node, ArrayList<Node> list) {
        if (node == null) return;
        nodes(node.lb, list);
        list.add(node);
        nodes(node.rt, list);
    }

    private void range(Node node, ArrayList<Point2D> list, RectHV rect) {
        if (node == null || !rect.intersects(node.rect)) return;
        range(node.lb, list, rect);
        if (rect.contains(node.p)) list.add(node.p);
        range(node.rt, list, rect);
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to p; null if set is empty
        return nearest(mRoot, p, null);
    }

    private Point2D nearest(Node root, Point2D p, Point2D closest) {
        if (root == null) return closest;
        if (closest != null && closest.distanceSquaredTo(p) < root.rect.distanceSquaredTo(p)) return closest;

        Point2D newClosest = closest;

        int cmp = root.compareTo(p);
        if (cmp > 0) newClosest = nearest(root.rt, p, newClosest);
        else newClosest = nearest(root.lb, p, newClosest);

        if (newClosest == null || newClosest.distanceSquaredTo(p) > root.p.distanceSquaredTo(p)) newClosest = root.p;

        if (cmp > 0) newClosest = nearest(root.lb, p, newClosest);
        else newClosest = nearest(root.rt, p, newClosest);

        return newClosest;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
