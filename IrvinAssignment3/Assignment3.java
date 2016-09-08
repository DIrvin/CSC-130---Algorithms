//	Program By: Derek Irvin
// Professor: Ouyang
// Date: April 22nd, 2014
// CSC 131 
// Assignment 3
// Heaps

import java.io.*;
import java.util.*;

public class Assignment3
{


   int max = 101;	// Maxsize of the heap is 101

   private NODE [] heap = new NODE[max];


   private int heapSize = 0; // initial heap size

   // Node Class for Values in the Heap
   private class NODE
   {
   	int niceValue;
   	int processID;
   }

//what will be in each node that is added to that heap
private NODE createNode(int nVal, int pID)
{
	NODE n = new NODE();
	n.niceValue = nVal;
	n.processID = pID;
	return n;
}
   
//this is code for insertion
private void insert(NODE n)
{
   
   if(this.heapSize == max){
	//checks if heapsize is 101, if it is then print msg and return
   System.out.println("Heap is full.");
   return;
   }
   int hole = ++heapSize;
	//if the heapsize is not 101 we are going to work with the hole and percolateup
   for(;hole > 1 && n.niceValue < this.heap[hole/2].niceValue; hole/=2)	
	{    
		this.heap[hole] = this.heap[hole/2];                                    
	}
	
	this.heap[hole] = n;                                                 
}
   
//code for deleteMin
private NODE deleteMin()
{
   if(this.heapSize == 0){
   //if heapsize is 0 nothing will happen
   System.out.println("Heap is empty.");
   return createNode(-1, -1);
   }
   NODE minItem = this.heap[1];
   //if it isn't 0 then we will shrink the heapsize and percolate down
   heap[1] = this.heap[heapSize--];
   percDown(1);
   return minItem;
   
}
   
//builds the heap
private void buildHeap()
{
   for(int j = heapSize/2; j>0;j--)
      percDown(j);
}

//percolate down 
private void percDown(int hole)
{
   int child;
   NODE tmp = this.heap[hole];
   for(; hole*2 <= this.heapSize; hole = child){
   child = hole*2;
   if(child!=this.heapSize 
   &&(this.heap[child+1].niceValue < this.heap[child].niceValue))
   child++;
   if(this.heap[child].niceValue < tmp.niceValue)
      this.heap[hole] = this.heap[child];
   else
      break;
  }

  this.heap[hole] = tmp;
}

//to print the heaps
private void printHeap()
{
   for(int i = 1; i < this.max; i++){
   System.out.print("NV:" + this.heap[i].niceValue + " PID:" + this.heap[i].processID + " ");
   }
   System.out.println();
}

//to fill the heap with random numbers
private void fillHeap()
{
   Random rand = new Random();
   for(int j = 1; j < this.max; j++)
   {
      this.heapSize++;
      this.heap[j] = createNode(rand.nextInt(40), this.heapSize);
   }


}

//driver
public static void main(String[]args)
{
   Scanner read = new Scanner(System.in);
   Random rand = new Random();
   
   Assignment3 heap = new Assignment3();
   Assignment3 heap2 = new Assignment3();
    
   //fills the the first heap
   heap.fillHeap();
   
   heap.printHeap();
   
   heap.buildHeap();
   
   heap.printHeap();
   
   for(int i = 1; i < heap.max; i++){
   heap2.insert(heap.deleteMin());
   }
   heap2.printHeap();
   }

}