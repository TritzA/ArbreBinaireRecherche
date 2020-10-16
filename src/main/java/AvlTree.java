import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {

    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){

    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        return false;
    }

    /** TODO Worst case : O( 1 )
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        return -1;
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        return null;
    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() { return new LinkedList<>(); }

    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        return new LinkedList<>();
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {

    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){

    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){

    }

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}
