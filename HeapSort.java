public class HeapSort {
    public void heapify(int[] arr, int i) {
        int n = arr[0];
        int smallest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= n && arr[left] < arr[smallest])
            smallest = left;

        if (right <= n && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, smallest);
        }
    }

    public void buildHeap(int[] arr) {
        int n = arr[0];
        for (int i = n / 2; i >= 1; i--)
            heapify(arr, i);
    }

    public void heapSort(int[] arr) {
        buildHeap(arr);
        int originalSize = arr[0];

        for (int i = arr[0]; i > 1; i--) {
            swap(arr, 1, i);
            arr[0]--;
            heapify(arr, 1);
        }

        // Restore the original size
        arr[0] = originalSize;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void printArray(int[] arr) {
        for (int i = 1; i <= arr[0]; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();

        int[] arr = new int[16];  // 15 elements + 1 for length
        arr[0] = 15;  // Set the length
        int[] values = {23, 17, 14, 6, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0, 11};
        for (int i = 0; i < values.length; i++) {
            arr[i + 1] = values[i];
        }

        System.out.println("Original array:");
        hs.printArray(arr);

        hs.buildHeap(arr);
        System.out.println("Array after converting to min-heap:");
        hs.printArray(arr);

        hs.heapSort(arr);
        System.out.println("Array after heap sort (descending order):");
        hs.printArray(arr);
    }
}
