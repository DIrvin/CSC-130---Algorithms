// Red Black Insertion Tree
// Assignment 2
// CSC 130 Professor Ouyang
// Programmed By Derek Irvin
// march 31st, 2014
// The following is a red black tree using insertion and with Three Arrays(Ascending Descending Array) to test the time complexity between AVL and RB Trees
public class RedBlackBST<Key extends Comparable<Key>, Value>
{
private static final boolean RED = true;     // Boolean Red Value = True
private static final boolean BLACK = false;  // Boolean Black Value = False

private Node root;   

private class Node
{
   int key; // key/Value
   Node left;  // left subtree
   Node right; // right subtree
   int N;      // # of Nodes in the subtree
   boolean color; // Red or Black
   
   Node(int key, int N, boolean color)
   {  
      this.key = key;   // The Value
      this.N = N;       // # of Nodes 
      this.left = null; // Initialize left child of the system to null
      this.right = null; // initialize the right child of the system to null
      this.color = color;  // color
   }
}

private boolean isRed(Node x)
{
   if (x == null) return false;  // if the value is null return false
   return x.color == RED;  // else return the value as red since all inserted values are red
}

// Rotation to the left 
Node rotateLeft(Node h)
{
   Node x = h.right; // Node x equal to the right 
   h.right = x.left; // the right is equal to the left
   x.left = h;    // h is now the left
   x.color = h.color;   // swap the colors
   h.color = RED; // change the colors of H to red
   x.N = h.N;
   h.N = 1 + size(h.left)
           + size(h.right);
   return x;
}

// Roation to the right
Node rotateRight(Node h)
{
   Node x = h.left;  // Node x equal to the left value
   h.left = x.right; // left height equal to the right
   x.right = h;      // right now equal h
   x.color = h.color;   // swap colors and such
   h.color = RED;
   x.N = h.N;
   h.N = 1 + size(h.left)
           + size(h.right);
   return x;
}

void flipColors(Node h)
{
   h.color = RED; // swap color of base
   h.left.color = BLACK;   // left child black
   h.right.color = BLACK;  // right child black
}

private int size()
{
   return size(root);   // get size
}

private int size(Node x)
{
   if (x == null) return 0;   // check if size is equal to null
   else           return x.N;
}

public void put(int key)
{
   root = put(root, key);
   root.color = BLACK;  // set root color to Black
}

private Node put(Node h, int key)
{
   if (h == null)
      return new Node(key, 1, RED);
      
   if    (key < h.key) h.left = put(h.left, key);
   else if (key > h.key) h.right = put(h.right, key);
   
   if (isRed(h.right) && !isRed(h.left))  h = rotateLeft(h);   // Red Child not leaning to the left
   if (isRed(h.left) && isRed(h.left.left)) h= rotateRight(h); // Two red Children in a line
   if (isRed(h.left) && isRed(h.right))   flipColors(h); // Two Red Children
   
   h.N = size(h.left) + size(h.right) + 1;
   return h;
}

public void traverseInOrder() {
        System.out.println (root.key);
        inorder(root);
        System.out.println();
    }

private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key);
            inorder(node.right);
        }
    }

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
        int[] arrayOne = new int[10000];
        for(int i = 0; i < arrayOne.length; i++)
        arrayOne[1] = 1 + 1;
      
      // Descending
         int[] arrayTwo = new int[10000];
         for(int i = 0; i < arrayTwo.length; i++)
         arrayTwo[i] = arrayTwo.length - i;
         
       // random access memory
         int[] arrayThree = new int[10000];
         for(int i = 0; i < arrayThree.length; i++)
         arrayThree[i] = i + 1;
         shuffleArray(arrayThree);
        
         // fill the tree with ascending array and collect time
        RedBlackBST rbtFirstTree = new RedBlackBST();
        
       long startTime1 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayOne.length; i++)
       rbtFirstTree.put((arrayOne[1]));
       
       long endTime1 = System.currentTimeMillis();
       long totalTime1 = endTime1 - startTime1;
       System.out.println("The Ascending Time Was: ");
       System.out.println(totalTime1 + "\n");
       
       // descending 
       RedBlackBST rbtSecondTree = new RedBlackBST();
        
       long startTime2 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayTwo.length; i++)
       rbtSecondTree.put((arrayTwo[1]));
       
       long endTime2 = System.currentTimeMillis();
       long totalTime2 = endTime2 - startTime2;
       System.out.println("The Descending Time Was: ");
       System.out.println(totalTime2 + "\n");

        // Random
      RedBlackBST rbtThirdTree = new RedBlackBST();
        
       long startTime3 = System.currentTimeMillis();
       
       for(int i = 0; i < arrayThree.length; i++)
       rbtThirdTree.put((arrayThree[1]));
       
       long endTime3 = System.currentTimeMillis();
       long totalTime3 = endTime3 - startTime3;
       System.out.println("The Random Time Was: ");
       System.out.println(totalTime3 + "\n");
        
        }
}
   