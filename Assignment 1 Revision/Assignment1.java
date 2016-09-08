// Programmed By: Derek Irvin
// Professor Ouyang
// Revision April 22nd, 2014
// CSC 130 Assignment 1

import java.util.Scanner;

class GetInputFromUser

{

 public static void main(String args[])

 {

 int arraySize; // Size of the Array
 int nthValue;  // Nth value we are looking for
 
 Scanner in = new Scanner(System.in);

 // get the size of the array
 System.out.println("what is the size of your arrays?");

 arraySize = in.nextInt();

 // Filling the arrays
 int[] arrayA = new int[arraySize];

 System.out.println("Please fill the first array: ");

 for(int j=0; j<arraySize; j++)

	 arrayA[j]=in.nextInt();

// Testing that the elements are filling the array

// System.out.println("Array A elements are: ");

// for(int i=0; i<arraySize; i++)

// System.out.println(arrayA[i]+",");


 int[] arrayB = new int[arraySize];

  System.out.println("Please fill the second array: ");

 for(int j=0; j<arraySize; j++)

	 arrayB[j]=in.nextInt();

 // Find the Nth value we are looking for
 System.out.println("Choose the Nth Value we are looking for: ");
 nthValue=in.nextInt();

 // Basic Case Check comparison of Nth Value we are searching for and the size of the array
 if(nthValue>arraySize)
 {
	 System.out.println("The Nth value you are looking for is outside the bounds of the given array");
 }
 else
 {
	 // Call for the the conquered value with array A, array B, and the Nth Value
	 // we will not need the array size since the array is sorted it can only be in the first N values
	 // of the array. 
	 System.out.println("The " + arraySize + "th largest number is: " + conqueredValue(arrayA, arrayB, nthValue));
 }

 }

public static int conqueredValue(int[] arrayA, int[] arrayB, int nthValue) 
{
	// base case of array size 1 in which case the greater value is the value 
	if(nthValue == 1)
	{
		if (arrayA[0] > arrayB[0])
			return arrayB[0];
		else
			return arrayA[0];
	}
	else
	{
		// divide the middle of the array
		 int middleValue = nthValue/2;
		 int newSize;
		 
		 // check if the value is an even or an odd number
		 if(nthValue%2==0)
		 {
			 newSize = nthValue/2;
		 }
		 else
		 {
			 newSize = nthValue/2+1;
		 }
		 
		 // Division of the arrays (DIVIDE and conquer)
		 int[] dividedArrayA = new int[newSize];

		 int[] dividedArrayB = new int[newSize];
		 
		 // See Design document for complete logic of split
		 if(arrayA[middleValue] > arrayB[middleValue])
		 {
			 for(int i = 0; i<newSize; i++)
			 {
				 dividedArrayA[i]= arrayA[i];
			 }
			 for(int i = 0; i<newSize; i++)
			 {
				 dividedArrayB[i] = arrayB[i+middleValue];
			 }
		 }
		 else
		 {
			 for(int i = 0; i<newSize; i++)
			 {
				 dividedArrayA[i]= arrayA[i+middleValue];
			 }
			 for(int i = 0; i<newSize; i++)
			 {
				 dividedArrayB[i] = arrayB[i];
			 }
		 }
		 // recursive call with the new size of the arrays
		 return conqueredValue(dividedArrayA, dividedArrayB, newSize);
	}
}
	
}


