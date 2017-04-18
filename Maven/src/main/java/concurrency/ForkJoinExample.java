package concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinExample extends RecursiveAction {
    private int from; //1
    private int to;   //100
    protected static int threshold = 25;

    public ForkJoinExample(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void compute() {
        if (to - from > threshold) {
            int split = (to - from) / 2; //49
            invokeAll(new ForkJoinExample(from, from + split), //1,50
                    new ForkJoinExample(from + split + 1, to)); //51,100
        } else {
            System.out.printf("%d to %d in %s%n", from, to, Thread.currentThread().getName());

        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinExample task = new ForkJoinExample(1, 100);
        pool.invoke(task); //blocks until concurrency completes
        System.out.printf("%d processors, %d milliseconds",
                pool.getParallelism(), //available processors
                System.currentTimeMillis() - start);
    }
}