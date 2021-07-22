package heap;

public class TestMain {
    public static void main(String[] args) {
        int [] array = {27,15,19,18,28,34,65,49,25,37};
        TestHeap testHeap = new TestHeap();

        testHeap.createHeap(array);
        System.out.println();

        testHeap.push(80);
        System.out.println();
        System.out.println(testHeap.poll());

      testHeap.heapSort();

    }
}
