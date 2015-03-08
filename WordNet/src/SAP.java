import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SAP {
    Digraph mDigraph;
    HashMap<Pair<String, String>, SAPResult> mCache;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        mDigraph = G;
        mCache = new HashMap<Pair<String, String>, SAPResult>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return calculateResult(v, w).getLength();
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return calculateResult(v, w).getAncestor();
    }

    private SAPResult calculateResult(int v, int w) {
        if (mCache.containsKey(new Pair(v, w))) return mCache.get(new Pair(v, w));
        validateInput(v, w);
        BreadthFirstDirectedPaths vbfdp = new BreadthFirstDirectedPaths(mDigraph, v);
        BreadthFirstDirectedPaths wbfdp = new BreadthFirstDirectedPaths(mDigraph, w);
        int shortestDist = -1;
        int shortestAncestor = -1;
        for (int ancestor = 0; ancestor < mDigraph.V(); ancestor++) {
            if (vbfdp.distTo(ancestor) < Integer.MAX_VALUE && wbfdp.distTo(ancestor) < Integer.MAX_VALUE) {
                int calcDist = vbfdp.distTo(ancestor) + wbfdp.distTo(ancestor);
                if (shortestDist < 0 || calcDist < shortestDist) {
                    shortestDist = calcDist;
                    shortestAncestor = ancestor;
                }
            }
        }
        SAPResult result = new SAPResult(shortestAncestor, shortestDist);
        mCache.put(new Pair(v, w), result);
        return result;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return calculateResult(v, w).getLength();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return calculateResult(v, w).getAncestor();
    }

    private SAPResult calculateResult(Iterable<Integer> v, Iterable<Integer> w) {
        if (mCache.containsKey(new Pair(v, w))) return mCache.get(new Pair(v, w));
        validateInput(v, w);
        BreadthFirstDirectedPaths vbfdp = new BreadthFirstDirectedPaths(mDigraph, v);
        BreadthFirstDirectedPaths wbfdp = new BreadthFirstDirectedPaths(mDigraph, w);
        int shortestDist = -1;
        int shortestAncestor = -1;
        for (int ancestor = 0; ancestor < mDigraph.V(); ancestor++) {
            if (vbfdp.distTo(ancestor) < Integer.MAX_VALUE && wbfdp.distTo(ancestor) < Integer.MAX_VALUE) {
                int calcDist = vbfdp.distTo(ancestor) + wbfdp.distTo(ancestor);
                if (shortestDist < 0 || calcDist < shortestDist) {
                    shortestDist = calcDist;
                    shortestAncestor = ancestor;
                }
            }
        }
        SAPResult result = new SAPResult(shortestAncestor, shortestDist);
        mCache.put(new Pair(v, w), result);
        return result;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("data/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
            ArrayList<Integer> v2 = new ArrayList<Integer>();
            v2.add(3);
            v2.add(9);
            ArrayList<Integer> w2 = new ArrayList<Integer>();
            w2.add(11);
//            int length   = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
            int length   = sap.length(v2, w2);
            int ancestor = sap.ancestor(v2, w2);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
    }

    private void validateInput(int v, int w) throws IndexOutOfBoundsException {
        if (v < 0 || v > mDigraph.V() || w < 0 || w > mDigraph.V()) throw new IndexOutOfBoundsException();
    }

    private void validateInput(Iterable<Integer> v, Iterable<Integer> w) throws IndexOutOfBoundsException {
        for (Integer vertex : v)
            if (vertex < 0 || vertex > mDigraph.V()) throw new IndexOutOfBoundsException();

        for (Integer vertex : w)
            if (vertex < 0 || vertex > mDigraph.V()) throw new IndexOutOfBoundsException();
    }

    private class SAPResult {
        private int mAncestor = -1;
        private int mLength = -1;

        public SAPResult(int ancestor, int length) {
            mAncestor = ancestor;
            mLength = length;
        }

        public int getAncestor() {
            return mAncestor;
        }

        public int getLength() {
            return mLength;
        }
    }
}
