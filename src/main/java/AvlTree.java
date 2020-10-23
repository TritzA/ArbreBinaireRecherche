import org.w3c.dom.Node;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class AvlTree<ValueType extends Comparable<? super ValueType>> {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() {
    }

    /**
     * TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {
        BinaryNode<ValueType> node = root;
        boolean findPlace = false;
        if (root == null) {//put the value at the root if the tree is empty
            root = new BinaryNode<ValueType>(value, null);
        } else {

            do {
                if (value.compareTo(node.value) < 0) {//or <= and value!=node.value //search the variable by going to the left
                    if (node.left == null) {
                        node.left = new BinaryNode<>(value, node);
                        findPlace = true;
                    } else {
                        node = node.left;
                    }
                } else if (value.compareTo(node.value) > 0) {//search the variable by going to the right
                    if (node.right == null) {
                        node.right = new BinaryNode<>(value, node);
                        findPlace = true;
                    }
                    node = node.right;
                } else {
                    return;
                }
            } while (!findPlace);
        }
    }

    /**
     * TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value) {
        BinaryNode<ValueType> node = root.left;
        boolean find = false;
        boolean lastLeft = true;
        if (root.value.equals(value)) {//if the root is the value, remove it
            root = null;
        } else {
            while (!find) {
                if (node.value.equals(value)) {//if we find the value, remove it
                    System.out.println("ici" + node.value);
                    /*if( node.left != null && node.right != null ) // Cas deux enfants
                    {
                        node.parent = findMin( node.right ).element;
                        node.right = remove( node.element, node.right );
                    }
                    else // Cas trivial
                        node = ( node.left != null ) ? node.left : node.right;
                    */
                    if (lastLeft)
                        node.parent.left = null;
                    else {
                        node.parent.right = null;
                    }
                    find = true;

                } else if (node == null) {//get potential error
                    System.out.println("Error : Try to remove an element missing from the tree");
                    return;

                } else if (value.compareTo(node.value) < 0) {//search the variable by going to the left
                    node = node.left;
                    lastLeft = true;

                } else if (value.compareTo(node.value) > 0) {//search the variable by going to the right
                    node = node.right;
                    lastLeft = false;
                }


            }
        }
    }

    /**
     * TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Verifies if the tree contains value
     *
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        BinaryNode<ValueType> node = root;

        while (true) {
            if (node == null) {//if we finally get a null
                return false;

            } else if (value.compareTo(node.value) < 0) {//search the variable by going to the left
                node = node.left;

            } else if (value.compareTo(node.value) > 0) {//search the variable by going to the right
                node = node.right;

            } else {//final case (else if (node.value == value))
                return true;
            }
        }
    }

    /**
     * TODO Worst case : O( 1 )
     * Returns the max level contained in our root tree
     *
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        return heightNode(root);//O ( log n )??
    }

    /**
     * TODO Worst case : O( 1 )
     * Returns the level of the current node
     *
     * @param node current node
     * @return height of the current node
     */
    private int heightNode(BinaryNode node) {
        if (node == null) {
            return -1;
        } else {
            if (heightNode(node.left) > heightNode(node.right)){//optimizable ?
                return heightNode(node.left) + 1;
            }else{
                return heightNode(node.right) + 1;
            }
        }
    }

    /**
     * TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Returns the node which has the minimal value contained in our root tree
     *
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        BinaryNode<ValueType> node = this.root;

        if (this.root == null) {//if the tree is empty
            return null;
        }

        do {
            if (node.left == null)
                return node.value;
            node = node.left;
        } while (node.left != null);

        return node.value;
    }


    /**
     * TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     *
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        LinkedList<ValueType> oC = new LinkedList();
        AvlTree<ValueType> t = this;
        ValueType min;
        while(t.root != null) {
            min = t.findMin();

            oC.add(min);
            t.remove(min);
        }
        return oC;
    }

    /**
     * TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Returns all values contained in the root tree in level order from top to bottom
     *
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder() {
        return new LinkedList<>();
    }

    /**
     * TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Balances the whole tree
     *
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {

    }

    /**
     * TODO Worst case : O ( 1 )
     * <p>
     * Single rotation to the left child, AVR Algorithm
     *
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1) {
        ValueType nP = node1.value;
        node1.value = node1.left.value;
        node1.left.value = nP;
    }

    /**
     * TODO Worst case : O ( 1 )
     * <p>
     * Single rotation to the right, AVR Algorithm
     *
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1) {
        ValueType nP = node1.value;
        node1.value = node1.right.value;
        node1.right.value = nP;
    }

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        BinaryNode(ValueType value, BinaryNode<ValueType> parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
