import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        int compareTo;
        if (root == null) {//put the value at the root if the tree is empty
            root = new BinaryNode<ValueType>(value, null);
        } else {
            do {
                compareTo = value.compareTo(node.value);
                if (compareTo < 0) {//search the variable by going to the left
                    if (node.left == null) {
                        node.left = new BinaryNode<>(value, node);
                        findPlace = true;
                    } else {
                        node = node.left;
                    }
                } else if (compareTo > 0) {//search the variable by going to the right
                    if (node.right == null) {
                        node.right = new BinaryNode<>(value, node);
                        findPlace = true;
                    } else {
                        node = node.right;
                    }
                } else {
                    return;// if the element is already in the tree
                }
            } while (!findPlace);
            updateHeightAdd(node);//add one to parent height
            balance(node);//balance the tree if he needs rotations
            if (node.parent != null) {
                desperateMeasureAdd(node);
            }
        }
    }

    private void desperateMeasureAdd(BinaryNode node) {
        if (node.parent.left != null && node.parent.right != null) {
            if (node.value != node.parent.left.value && node.value != node.parent.right.value) {
                node = node.parent;
                while (node != null) {
                    node.height--;
                    node = node.parent;
                }
            }
        } else if (node.height + 1 != node.parent.height) {
            node = node.parent;
            while (node != null) {
                node.height--;
                node = node.parent;
            }
        }
    }


    private void updateHeightAdd(BinaryNode node) {
        int i = 1;
        while (node != null) {
            node.height = i;
            node = node.parent;
            i++;
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
        BinaryNode<ValueType> node = this.root;
        if (root == null)
            return;
        boolean find = false;
        boolean lastLeft = true;
        int compareTo;
        if (this.root.value.equals(value)) {//if the root is the value, remove it
            this.root = null;
        } else {
            while (!find) {
                if (node == null) {
                    return;
                }
                compareTo = value.compareTo(node.value);
                if (compareTo == 0) {//if we find the value, remove it
                    if (lastLeft) {
                        if (node.left != null || node.right != null)
                            node.parent.left.value = reassignOnRemove(node);
                        else {
                            node.parent.left = null;
                        }
                    } else {
                        if (node.left != null || node.right != null)
                            node.parent.right.value = reassignOnRemove(node);
                        else {
                            node.parent.right = null;
                        }
                    }
                    find = true;

                } else if (compareTo < 0) {//search the variable by going to the left
                    node = node.left;
                    lastLeft = true;

                } else if (compareTo > 0) {//search the variable by going to the right
                    node = node.right;
                    lastLeft = false;
                }


            }
            updateHeightRemove(node);
            balance(node.parent);
            updateHeightRemove(node);
        }
    }

    private void updateHeightRemove(BinaryNode node) {
        boolean a;
        do {
            a = false;
            if (node.parent == null) {
                return;
            }
            if (node.parent.left != null) {
                if (node.parent.height - 1 == node.parent.left.height)
                    a = true;
            }
            if (node.parent.right != null) {
                if (node.parent.height - 1 == node.parent.right.height)
                    a = true;
            }
            if (!a) {
                if (node.parent.height != 0)
                    node.parent.height -= 1;
            }
            node = node.parent;
        } while (true);
    }

    private ValueType reassignOnRemove(BinaryNode node) {
        if (node.left != null && node.right != null) {
            node = node.right;

            while (node.left != null) {//going to the left util we got a null
                node = node.left;
            }
            ValueType n = (ValueType) node.value;
            node.parent.left = null;
            return n;
        } else if (node.left != null) {

            while (node.left != null) {//going to the left util we got a null
                node = node.left;
            }
            ValueType n = (ValueType) node.value;
            node.parent.left = null;
            return n;
        } else if (node.right != null) {

            while (node.right != null) {//going to the left util we got a null
                node = node.right;
            }
            ValueType n = (ValueType) node.value;
            node.parent.right = null;
            return n;
        }
        return null;
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
        int compareTo;

        while (true) {

            if (node == null) {//if we finally get a null
                return false;

            }

            compareTo = value.compareTo(node.value);
            if (compareTo < 0) {//search the variable by going to the left
                node = node.left;

            } else if (compareTo > 0) {//search the variable by going to the right
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
        if (root == null)
            return -1;
        return root.height;
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
        LinkedList<ValueType> listValue = new LinkedList<ValueType>();
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode node = this.root;

        while (node != null || stack.size() != 0) {//going through the tree

            while (node != null) {//going to the left util we got a null
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            listValue.add((ValueType) node.value);
            node = node.right;
        }

        return listValue;
    }

    /**
     * TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Returns all values contained in the root tree in level order from top to bottom
     *
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder() {
        BinaryNode<ValueType> node;
        LinkedList<BinaryNode> listNode = new LinkedList<BinaryNode>();
        LinkedList<ValueType> listValue = new LinkedList<ValueType>();

        int n = getHeight();
        int maxNbElem = ((n) * (2 * n + 1) * (n + 1)) / (6);//sum SIGMA(n^2) form [0; height]

        listNode.add(this.root);
        listValue.add(this.root.value);

        for (int i = 0; i < maxNbElem; i++) {
            node = listNode.get(i);//with this node, we will add his 2 children

            if (node.left == null) {
                return listValue;//it ends if we find a null
            } else {
                listNode.add(node.left);//left child
                listValue.add(node.left.value);
            }

            if (node.right == null) {
                return listValue;//it ends if we find a null
            } else {
                listNode.add(node.right);//right child
                listValue.add(node.right.value);
            }
        }
        return listValue;
    }

    /**
     * TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * <p>
     * Balances the whole tree
     *
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {
        int nL = -1, nR = -1, nLL = -1, nLR = -1, nRR = -1, nRL = -1;

        //set all values
        while (node != null) {
            if (node.left != null) {
                nL = node.left.height;
                if (node.left.right != null) {
                    nLR = node.left.right.height;
                }
                if (node.left.left != null) {
                    nLL = node.left.left.height;
                }
            }
            if (node.right != null) {
                nR = node.right.height;
                if (node.right.right != null) {
                    nRR = node.right.right.height;
                }
                if (node.right.left != null) {
                    nRL = node.right.left.height;
                }
            }

            // checking for rotation
            if (nL - nR > 1) {
                if (nLL < nLR) {
                    rotateLeft(node.left);
                }
                rotateRight(node);
            } else if (nR - nL > 1) {
                if (nRR < nRL) {
                    rotateRight(node.right);
                }
                rotateLeft(node);
            }
            node = node.parent;
        }
    }


    /**
     * TODO Worst case : O ( 1 )
     * <p>
     * Single rotation to the left child, AVR Algorithm
     *
     * @param node1 Node to become child of its right child
     */
    private void rotateLeft(BinaryNode<ValueType> node1) {
        if (node1.left == null)
            node1.left = new BinaryNode<>(node1.value, node1);
        else {
            BinaryNode n = node1.left;
            node1.left = new BinaryNode<>(node1.value, node1);
            node1.left.left = n;
            node1.left.left.parent = node1.left;
            node1.left.height = n.height + 1;
        }
        node1.value = node1.right.value;
        if (node1.right.left == null) {
            node1.right = node1.right.right;
            node1.right.parent = node1;
        } else {
            BinaryNode n = node1.right.left;
            node1.right = node1.right.right;
            node1.right.parent = node1;
            node1.left.right = n;
            node1.left.right.parent = node1.left;

            if (node1.left.height < n.height + 1)
                node1.left.height = n.height + 1;
        }

    }

    /**
     * TODO Worst case : O ( 1 )
     * <p>
     * Single rotation to the right, AVR Algorithm
     *
     * @param node1 Node to become child of its left child
     */
    private void rotateRight(BinaryNode<ValueType> node1) {
        if (node1.right == null)
            node1.right = new BinaryNode<>(node1.value, node1);
        else {
            BinaryNode n = node1.right;
            node1.right = new BinaryNode<>(node1.value, node1);
            node1.right.right = n;
            node1.right.right.parent = node1.right;

            node1.right.height = n.height + 1;
        }
        node1.value = node1.left.value;
        if (node1.left.right == null) {
            node1.left = node1.left.left;
            node1.left.parent = node1;
        } else {
            BinaryNode n = node1.left.right;
            node1.left = node1.left.left;
            node1.left.parent = node1;
            node1.right.left = n;
            node1.right.left.parent = node1.right;
            if (node1.right.height < n.height + 1)
                node1.right.height = n.height + 1;
        }

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
