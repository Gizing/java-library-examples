package producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;


public class ProducerConsumerExample
{

    public static void main(String[] args)
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        new Thread(consumer).start();
        new Thread(producer).start();
    }

}

class Consumer implements Runnable
{
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            synchronized (queue)
            {
                if (queue.size() < 10)
                {
                    queue.notifyAll();
                }
            }
            try
            {
                Integer task = queue.take();
                if (task != null)
                    System.out.println(task);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }
}

class Producer implements Runnable
{
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                synchronized (queue)
                {
                    while (queue.size() > 12)
                        queue.wait();
                }
                int task = ThreadLocalRandom.current().nextInt();

                queue.put(task); // 本来就是同步阻塞队列不需要额外的操作
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}