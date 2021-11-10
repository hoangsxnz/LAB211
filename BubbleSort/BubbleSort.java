/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BubbleSort;

import Utility.GetValidInput;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author hoangson
 */
public class BubbleSort {

    public static void main(String[] args) {
        // Step 1: decide if need to check the given test case or not
        boolean checkTestCase = false;
        // if needed, create the array given by guidelines
//        int[] arr = mockTest();

        // Step 2: create array with input from user
        int[] arr = createArray();
        // Step 3: generate random elements for created array
        randomArray(arr);
        // Step 4: display array before sorted
        display("Unsorted array: ", arr, checkTestCase);

        // Step 5: sort the array using bubble sort
        bubbleSort(arr, checkTestCase);

        // Step 6: display array after sorted
        display("\nSorted array: ", arr, checkTestCase);
    }

    // test case given by guidelines
    private static int[] mockTest() {
        int[] arr = {5, 1, 12, -5, 16};
        return arr;
    }

    // create an array based on input size
    private static int[] createArray() {
        int size = GetValidInput.getInt("Enter number of array: \n",
                "Input can't be a string!", 1, Integer.MAX_VALUE);
        int[] arr = new int[size];
        return arr;
    }

    // generate random elements for an array
    private static void randomArray(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; ++i) {
            // random int value in range [0, arr.length-1]
            arr[i] = rand.nextInt(arr.length);
        }
    }

    // print the array to screen
    private static void display(String msg, int[] arr, boolean check) {
        System.out.print(msg + Arrays.toString(arr));
    }

    // sort the array using Bubble Sort algorithm
    private static void bubbleSort(int[] arr, boolean check) {
        int n = arr.length;
        if (check == true) {
            System.out.println("\n");
        }
        // loop to reach all elements of array in forward order
        for (int i = 0; i < n - 1; ++i) {
            // loop from the first element to the element before the element that has just been sorted
            for (int j = 0; j < n - 1 - i; ++j) {
                // print out the step if needed
                if (check == true) {
                    System.out.print(Arrays.toString(arr));
                }
                // check if 2 adjacent elements are not in ascending order
                if (arr[j] > arr[j + 1]) {
                    // print out the step if needed
                    if (check == true) {
                        System.out.println("    " + arr[j] + " > " + arr[j + 1] + ", swap");
                    }
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                } else if (arr[j] < arr[j + 1]) {
                    // print out the step if needed
                    if (check == true) {
                        System.out.println("    " + arr[j] + " < " + arr[j + 1] + ", ok");
                    }
                } else {
                    // print out the step if needed
                    if (check == true) {
                        System.out.println("    " + arr[j] + " = " + arr[j + 1] + ", ok");
                    }
                }
            }
        }
    }
}
