import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by edwardweng on 7/30/14.
 */
public class KdTreeTest {
    @Test
    public void testIsEmpty() {
        KdTree tree = new KdTree();
        assertEquals(true, tree.isEmpty());
    }

    @Test
    public void testInsertDuplicates() {
        KdTree tree = new KdTree();
        assertEquals(0, tree.size());
        tree.insert(new Point2D(0,0));
        assertEquals(1, tree.size());
        tree.insert(new Point2D(0,0));
        assertEquals(1, tree.size());
    }

    @Test
    public void testContains() {
        KdTree tree = new KdTree();
        assertEquals(false, tree.contains(new Point2D(0,0)));
        tree.insert(new Point2D(0,0));
        tree.insert(new Point2D(0,0.5));
        tree.insert(new Point2D(0,1));
        tree.insert(new Point2D(1,0.5));
        assertEquals(4, tree.size());
        assertEquals(true, tree.contains(new Point2D(0,0)));
        assertEquals(true, tree.contains(new Point2D(0,0.5)));
        assertEquals(true, tree.contains(new Point2D(0,1)));
        assertEquals(true, tree.contains(new Point2D(1,0.5)));
        assertEquals(false, tree.contains(new Point2D(1,0.75)));
    }


}
