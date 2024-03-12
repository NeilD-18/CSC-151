package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj5.BSTNode;
import proj5.BinarySearchTree;

/** 
 * Testing class for Binary Search Tree Abstract Data Type (ADT)
 * 
 * @author Neil Daterao
 * @version 2/29/2024
 */
public class BST_Tester {
    
	@Rule
    public Timeout timeout = Timeout.millis(100);
	
    private BinarySearchTree<String> bst; 
    private BinarySearchTree<String> largeBst; 
    private BinarySearchTree<Integer> intBst;
    
    @Before
    public void setUp() throws Exception {
        bst = new BinarySearchTree<>();
        intBst = new BinarySearchTree<>();
    }

    @After
    public void tearDown() throws Exception {
        bst = null; 
        intBst = null;
        largeBst = null;
    }

    private void createLargeBST() { 
        largeBst = new BinarySearchTree<>();
        largeBst.insert("D");
        largeBst.insert("H");
        largeBst.insert("C");
        largeBst.insert("J");
        largeBst.insert("M");
        largeBst.insert("E");
        largeBst.insert("A");
        largeBst.insert("F");
        largeBst.insert("G");
        largeBst.insert("K");
        largeBst.insert("I");
        largeBst.insert("L");
        
    }


    @Test
    public void testToString() { 
        bst.insert("B");
        bst.insert("A");
        bst.insert("C");
        assertEquals("should be (( A ) B ( C ))", "(( A ) B ( C ))", bst.toString()); 
    }

    @Test
    public void testToStringEmptyTree() {
        assertEquals("", bst.toString());
    }

    @Test
    public void testToStringOneNode() {
        bst.insert("B");
        assertEquals("( B )", bst.toString());
    }

    @Test
    public void testToStringMultipleNodes() {
        bst.insert("B");
        bst.insert("A");
        bst.insert("C");
        bst.insert("E");
        bst.insert("D");
        assertEquals("(( A ) B ( C (( D ) E )))", bst.toString());
    }

    @Test
    public void testSizeEmptyTree() {
        assertEquals(0, bst.size());
    }

    @Test
    public void testSizeOneNode() {
        bst.insert("B");
        assertEquals(1, bst.size());
    }

    @Test
    public void testSizeMultipleNodes() {
        bst.insert("B");
        bst.insert("A");
        bst.insert("C");
        assertEquals(3, bst.size());
        
        bst.insert("E");
        bst.insert("D");
        assertEquals(5, bst.size());
    }

    @Test
    public void testInsert_M() {
        bst.insert("M");
        assertEquals("( M )", bst.toString());
    }

    @Test
    public void testInsert_MV() {
        bst.insert("M");
        bst.insert("V");
        assertEquals("( M ( V ))", bst.toString());
    }

    @Test
    public void testInsert_MVP() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        assertEquals("( M (( P ) V ))", bst.toString());
    }

    @Test
    public void testInsert_MVPDN() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        bst.insert("D");
        bst.insert("N");
        assertEquals("(( D ) M ((( N ) P ) V ))", bst.toString());
    }

    @Test
    public void testInsert_MVPNDF() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        bst.insert("D");
        bst.insert("N");
        bst.insert("F");
        assertEquals("(( D ( F )) M ((( N ) P ) V ))", bst.toString());
    }

    @Test
    public void testInsert_MVPDNFB() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        bst.insert("D");
        bst.insert("N");
        bst.insert("F");
        bst.insert("B");
        assertEquals("((( B ) D ( F )) M ((( N ) P ) V ))", bst.toString());
    }

    @Test
    public void testInsert_MVPDNFBC() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        bst.insert("D");
        bst.insert("N");
        bst.insert("F");
        bst.insert("B");
        bst.insert("C");
        assertEquals("((( B ( C )) D ( F )) M ((( N ) P ) V ))", bst.toString());
    }

    @Test
    public void testInsert_MVPDNFBCQR() {
        bst.insert("M");
        bst.insert("V");
        bst.insert("P");
        bst.insert("D");
        bst.insert("N");
        bst.insert("F");
        bst.insert("B");
        bst.insert("C");
        bst.insert("Q");
        bst.insert("R");
        assertEquals("((( B ( C )) D ( F )) M ((( N ) P ( Q ( R ))) V ))", bst.toString());
    }

    @Test
    public void testSearchExistingValue() {
        createLargeBST();
        assertTrue("Value 'D' should exist in the tree", largeBst.search("D"));
    }

    @Test
    public void testSearchNonExistingValue() {
        createLargeBST();
        assertFalse("Value 'X' should not exist in the tree", largeBst.search("X"));
    }

    @Test
    public void testSearchRootValue() {
        createLargeBST();
        assertTrue("Root value 'H' should exist in the tree", largeBst.search("H"));
    }

    @Test
    public void testSearchLeafs() {
        createLargeBST();
        assertTrue("Last node value 'A' should exist in the tree", largeBst.search("A"));
        assertTrue("Last node value 'C' should exist in the tree", largeBst.search("C"));
        assertTrue("Last node value 'M' should exist in the tree", largeBst.search("M"));
    }

    @Test
    public void testSearchMiddleNodeValue() {
        createLargeBST();
        assertTrue("Middle node value 'L' should exist in the tree", largeBst.search("L"));
    }

    @Test 
    public void testSearchEmptyTree() { 
        assertFalse("Empty Tree", bst.search("A"));
       
    }

    @Test
    public void testDeleteLeafNode() {
        intBst.insert(5);
        intBst.insert(3);
        intBst.insert(7);

        intBst.delete(3);
        assertFalse(intBst.search(3));
        assertEquals("( 5 ( 7 ))", intBst.toString());
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        intBst.insert(5);
        intBst.insert(3);
        intBst.insert(7);
        intBst.insert(6);

        intBst.delete(7);
        assertFalse("7 should no longer be in the BST",intBst.search(7));
        assertTrue("Should not delete child", intBst.search(6));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        intBst.insert(5);
        intBst.insert(3);
        intBst.insert(7);
        intBst.insert(6);
        intBst.insert(8);

        intBst.delete(7);
        assertFalse("7 should not be in the BST", intBst.search(7));
        assertTrue("Should not delete child", intBst.search(8));
        assertTrue("Should not delete child", intBst.search(6));
    }

    @Test
    public void testDeleteRootNode() {
        intBst.insert(5);
        intBst.insert(3);
        intBst.insert(7);
        intBst.insert(6);
        intBst.insert(8);

        intBst.delete(5);
        assertFalse("5 should not be in BST", intBst.search(5));
        assertTrue("Should not delete child", intBst.search(3));
        assertTrue("Should not delete child", intBst.search(7));
    }

    @Test
    public void testDeleteForItemNotThere() {
        intBst.insert(5);
        intBst.insert(3);
        intBst.insert(7);
        intBst.insert(6);
        intBst.insert(8);

        intBst.delete(9);
        assertEquals("(( 3 ) 5 (( 6 ) 7 ( 8 )))", intBst.toString());
        
    }

    @Test
    public void testGetExistingElement() {
        intBst.insert(10);
        intBst.insert(8);
        intBst.insert(4);
        
        Integer target = 8;
        BSTNode<Integer> resultNode = intBst.get(target);
        assertNotNull(resultNode); // Check that the result is not null
        assertEquals(target, resultNode.data); // Check that the correct node is returned
    }

    @Test
    public void testGetNonExistingElement() {
        intBst.insert(10);
        intBst.insert(8);
        intBst.insert(4);
        
        Integer target = 2;
        BSTNode<Integer> resultNode = intBst.get(target);
        assertNull(resultNode); // Check that the result is not null

    }
}





