// AVL Insertion Tree
// Assignment 2 
// Programmed By Derek Irvin
// March 31st, 2014
// The following is a AVL tree using insertion and with Three Arrays(Ascending Descending Array) to test the time complexity between AVL and RB Trees
public class AVLTree {

    private static class Node {
        Node left;   // left child
        Node right;  // right child
        Node parent; // parent 
        int value ;
        int height = 0; // initial height set to 0

        public Node(int data, Node parent) {
            this.value = data;
            this.parent = parent;
        }
        
        public String toString() {
            return value + " height " + height + " parent " + (parent == null ?
                    "NULL" : parent.value) + " | ";
        }

        void setLeftChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.left = child;
        }

        void setRightChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.right = child;
        }
    }

    private Node root = null;

    public void insert(int data) {
        insert(root, data);
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private void insert(Node node, int value) {
    // If the root is null, therefore the tree is empty, the inserted value is the root node
        if (root == null) {
            root = new Node(value, null);
            return;
        }
   
     // if the value is less than that of the node value pointed at
        if (value < node.value) {
            if (node.left != null) {   // and the node to the left of the value is not null
                insert(node.left, value); // insert the value into the node
            } else {
                node.left = new Node(value, node);
            }

            // comparing the height of the left subtree and the right subtree to test if it is balanced
            if (height(node.left) - height(node.right) == 2) { // if the Left Subtree - the Right Subtree is more than 2
                if (value < node.left.value) {  
                    rotateRight(node); // rotate to the right meaning your left is more than your right subtree to balance it out
                } else {
                    LeftRightRotation(node);  // Else double rotation
                }
            }
        } else if (value > node.value) {  // else check if the value is greater than that off the node and if it is greater it will go in the right subtree
            if (node.right != null) {     // check the right subtree for a value is null and if it is, then insert the value into the right subtree
                insert(node.right, value);
            } else {
                node.right = new Node(value, node);   // else new Node value
            }

            if (height(node.right) - height(node.left) == 2) { // compare the value to see if the righ subtree is greater then that of the left subtree
                if (value > node.right.value)
                    rotateLeft(node);
                else {
                   rightLeftRotation(node);
                }
            }
        }

        returnHeight(node);
    }

// Rotate to the right
    private void rotateRight(Node pivot) {
        Node parent = pivot.parent; // parent node is the pivot node
        Node leftChild = pivot.left;   // left child is pivot left
        Node rightChildOfLeftChild = leftChild.right; // the right child is now the left child
        pivot.setLeftChild(rightChildOfLeftChild);
        leftChild.setRightChild(pivot);
        if (parent == null) {
            this.root = leftChild;
            leftChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(leftChild);
        } else {
            parent.setRightChild(leftChild);
        }

        returnHeight(pivot);
        returnHeight(leftChild);
    }

// rotate to the left
    private void rotateLeft(Node pivot) {
        Node parent = pivot.parent; // the parent node is now the pivot node
        Node rightChild = pivot.right; // the right child is now the right pivot
        Node leftChildOfRightChild = rightChild.left; // the left child is now the right child
        pivot.setRightChild(leftChildOfRightChild);
        rightChild.setLeftChild(pivot);
        if (parent == null) {
            this.root = rightChild;
            rightChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(rightChild);
        } else {
            parent.setRightChild(rightChild);
        }

        returnHeight(pivot);
        returnHeight(rightChild);
    }

    private void returnHeight(Node node) { // compare height of the two nodes
        node.height = Math.max(height(node.left), height(node.right)) + 1; // find the max height of the left and right subtrees
    }


// double rotations

// Left Right Rotation
    private void LeftRightRotation(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

// Right Left Rotation
    private void rightLeftRotation(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    private int calDifference(Node node) {
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight - leftHeight;
    }

    private void balanceTree(Node node) {
        int difference = calDifference(node);
        Node parent = node.parent;
        if (difference == -2) {
            if (height(node.left.left) >= height(node.left.right)) {
                rotateRight(node);
            } else {
                LeftRightRotation(node);
            }
        } else if (difference == 2) {
            if (height(node.right.right) >= height(node.right.left)) {
                rotateLeft(node);
            } else {
                rightLeftRotation(node);
            }
        }

        if (parent != null) {
            balanceTree(parent);
        }

        returnHeight(node);
    }

    public Node search(int key) {
        return binarySearch(root, key);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) return null;

        if (key == node.value) {
            return node;
       }

        if (key < node.value && node.left != null) {
            return binarySearch(node.left, key);
        }

        if (key > node.value && node.right != null) {
            return binarySearch(node.right, key);
        }

        return null;
    }    

// Random Shuffle

  static void shuffleArray(int [] ar)
{
   java.util.Random rnd = new java.util.Random();
   for (int i = ar.length - 1; i > 0; i--)
   {
      int index = rnd.nextInt(i + 1);
   // simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
      }
}

public static void main(String[] args) 
{
      // Ascending
        int[] arrayOne = new int[10000000];
        for(int i = 0; i < arrayOne.length; i++)
        arrayOne[1] = 1 + 1;
      
      // Descending
         int[] arrayTwo = new int[10000000];
         for(int i = 0; i < arrayTwo.length; i++)
         arrayTwo[i] = arrayTwo.length - i;
         
       // random access memory
         int[] arrayThree = new int[10000000];
         for(int i = 0; i < arrayThree.length; i++)
         arrayThree[i] = i + 1;
         shuffleArray(arrayThree);
        
         // fill the tree with ascending array and collect time
        AVLTree AVLFirstTree = new AVLTree();
        
       long startTime1 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayOne.length; i++)
       AVLFirstTree.insert((arrayOne[1]));
       
       long endTime1 = System.currentTimeMillis();
       long totalTime1 = endTime1 - startTime1;
       System.out.println("The Ascending Time Was: ");
       System.out.println(totalTime1 + "\n");
       
       // descending 
       AVLTree AVLSecondTree = new AVLTree();
        
       long startTime2 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayTwo.length; i++)
       AVLSecondTree.insert((arrayTwo[1]));
       
       long endTime2 = System.currentTimeMillis();
       long totalTime2 = endTime2 - startTime2;
       System.out.println("The Descending Time Was: ");
       System.out.println(totalTime2 + "\n");

        // Random
       AVLTree AVLThirdTree = new AVLTree();
        
       long startTime3 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayThree.length; i++)
       AVLThirdTree.insert((arrayThree[1]));
       
       long endTime3 = System.currentTimeMillis();
       long totalTime3 = endTime3 - startTime3;
       System.out.println("The Random Time Was: ");
       System.out.println(totalTime3 + "\n");
        
        }
}