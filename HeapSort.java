public class HeapSort {
    
    /**
     * Performs min-heapify operation to maintain min-heap property
     * @param arr Array where arr[0] contains the heap size
     * @param i Index to heapify from (1-indexed)
     */
    public void heapify(int[] arr, int i) {
        if (arr == null || arr.length < 2 || arr[0] == 0) {
            return;
        }
        
        int n = arr[0];
        int smallest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        // Find smallest among parent, left child, and right child
        if (left <= n && arr[left] < arr[smallest])
            smallest = left;

        if (right <= n && arr[right] < arr[smallest])
            smallest = right;

        // If smallest is not the parent, swap and continue heapifying
        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, smallest);
        }
    }

    /**
     * Builds a min-heap from an unsorted array
     * @param arr Array where arr[0] contains the heap size
     */
    public void buildHeap(int[] arr) {
        if (arr == null || arr.length < 2 || arr[0] == 0) {
            return;
        }
        
        int n = arr[0];
        // Start from last non-leaf node and heapify all nodes
        for (int i = n / 2; i >= 1; i--) {
            heapify(arr, i);
        }
    }

    /**
     * Sorts array in descending order using min-heap
     * @param arr Array where arr[0] contains the heap size
     */
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2 || arr[0] <= 1) {
            return;
        }
        
        // Build min-heap
        buildHeap(arr);
        int originalSize = arr[0];

        // Extract minimum elements and place at the end
        for (int i = arr[0]; i > 1; i--) {
            // Move current minimum (root) to end of heap
            swap(arr, 1, i);
            arr[0]--; // Reduce heap size
            heapify(arr, 1); // Re-heapify the root
        }

        // Restore the original size for array printing
        arr[0] = originalSize;
    }

    /**
     * Alternative sorting method for ascending order using max-heap
     * @param arr Array where arr[0] contains the heap size
     */
    public void heapSortAscending(int[] arr) {
        if (arr == null || arr.length < 2 || arr[0] <= 1) {
            return;
        }
        
        buildMaxHeap(arr);
        int originalSize = arr[0];

        for (int i = arr[0]; i > 1; i--) {
            swap(arr, 1, i);
            arr[0]--;
            maxHeapify(arr, 1);
        }

        arr[0] = originalSize;
    }

    /**
     * Max-heapify for ascending order sort
     */
    private void maxHeapify(int[] arr, int i) {
        int n = arr[0];
        int largest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= n && arr[left] > arr[largest])
            largest = left;

        if (right <= n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest);
        }
    }

    /**
     * Builds max-heap for ascending order sort
     */
    private void buildMaxHeap(int[] arr) {
        int n = arr[0];
        for (int i = n / 2; i >= 1; i--) {
            maxHeapify(arr, i);
        }
    }

    /**
     * Swaps two elements in the array
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Prints the array contents (1-indexed portion)
     */
    public void printArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            System.out.println("Empty array");
            return;
        }
        
        for (int i = 1; i <= arr[0]; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Validates if array represents a valid min-heap
     */
    public boolean isMinHeap(int[] arr) {
        if (arr == null || arr.length < 2) return true;
        
        int n = arr[0];
        for (int i = 1; i <= n / 2; i++) {
            int left = 2 * i;
            int right = 2 * i + 1;
            
            if (left <= n && arr[i] > arr[left]) return false;
            if (right <= n && arr[i] > arr[right]) return false;
        }
        return true;
    }

    /**
     * Creates array from regular int array for heap operations
     */
    public static int[] createHeapArray(int[] values) {
        if (values == null) return null;
        
        int[] heapArray = new int[values.length + 1];
        heapArray[0] = values.length;
        System.arraycopy(values, 0, heapArray, 1, values.length);
        return heapArray;
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();

        // Create heap array: first element stores size, rest store values
        int[] values = {23, 17, 14, 6, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0, 11};
        int[] arr = createHeapArray(values);

        System.out.println("=== HeapSort Demo (Descending Order) ===");
        System.out.println("Original array:");
        hs.printArray(arr);

        // Build min-heap
        hs.buildHeap(arr);
        System.out.println("Array after converting to min-heap:");
        hs.printArray(arr);
        System.out.println("Is valid min-heap: " + hs.isMinHeap(arr));

        // Sort in descending order
        hs.heapSort(arr);
        System.out.println("Array after heap sort (descending order):");
        hs.printArray(arr);

        System.out.println("\n=== Alternative: Ascending Order Sort ===");
        // Reset array for ascending sort demo
        int[] arr2 = createHeapArray(values);
        System.out.println("Original array:");
        hs.printArray(arr2);

        hs.heapSortAscending(arr2);
        System.out.println("Array after heap sort (ascending order):");
        hs.printArray(arr2);

        System.out.println("\n=== Algorithm Explanation ===");
        System.out.println("Min-heap + extract minimum → Descending order");
        System.out.println("Max-heap + extract maximum → Ascending order");
    }
}
