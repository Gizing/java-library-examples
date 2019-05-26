package max_min_heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class HeapExample
{

    public static void main(String[] args)
    {
        // 最小堆
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(9);
        minHeap.offer(8);
        minHeap.offer(5);
        minHeap.offer(6);
        System.out.print("最小堆：");
        while (!minHeap.isEmpty())
        {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // 最大堆
        Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) ->
        {
            if (o1 < o2)
                return 1;
            else if (o1 == o2)
                return 0;
            else
                return -1;
        });// 用lambda
        maxHeap.offer(9);
        maxHeap.offer(8);
        maxHeap.offer(5);
        maxHeap.offer(6);
        System.out.print("最大堆：");
        while (!maxHeap.isEmpty())
        {
            System.out.print(maxHeap.poll() + " ");
        }
    }

}
