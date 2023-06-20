public static int[] mergeMaxHeaps(int[] heap1, int[] heap2) {
        // 두 힙의 크기를 합친 크기의 배열을 생성한다.
        int[] mergedHeap = new int[heap1.length + heap2.length];

        // 첫 번째 힙을 복사한다.
        System.arraycopy(heap1, 0, mergedHeap, 0, heap1.length);

        // 두 번째 힙을 첫 번째 힙 다음부터 복사한다.
        System.arraycopy(heap2, 0, mergedHeap, heap1.length, heap2.length);

        // 병합된 배열을 최대 힙으로 구성한다.
        buildMaxHeap(mergedHeap);

        return mergedHeap;
        }

private static void buildMaxHeap(int[] arr) {
        // 배열의 중간부터 시작하여 배열 전체를 최대 힙으로 구성한다.
        for (int i = arr.length / 2; i >= 0; i--) {
        maxHeapify(arr, i, arr.length);
        }
        }

private static void maxHeapify(int[] arr, int i, int heapSize) {
        // 현재 노드(i)와 자식 노드들의 값을 비교하여 최대 힙 구성한다.
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int largest = i;

        if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
        largest = leftChild;
        }
        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
        largest = rightChild;
        }

        if (largest != i) {
        // 현재 노드와 자식 노드를 교환하고,
        // 교환된 자식 노드에 대해서 다시 최대 힙을 구성한다.
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
        maxHeapify(arr, largest, heapSize);
        }
        }
