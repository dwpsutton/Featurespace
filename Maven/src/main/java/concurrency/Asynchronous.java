package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class Asynchronous {
	public static void main(String[] args) {
		System.out.printf("main running in %s%n",Thread.currentThread().getName());
		Supplier<Long> supplier = () ->
		{
			System.out.printf("supplier running in %s%n",Thread.currentThread().getName());
			return  LongStream.range(2, 10000).filter(p -> !LongStream.range(2, p).anyMatch(n -> p % n == 0)).count();
		};
		Consumer<Long> consumer = s ->
		{
			System.out.printf("consumer running in %s%n",Thread.currentThread().getName());
			System.out.println(s);
		};
		CompletableFuture.supplyAsync(supplier).thenAccept(consumer);
		System.out.println("a callback has been set up");
		try {
			ForkJoinPool.commonPool().awaitTermination(60, TimeUnit.SECONDS); //prevents main method exiting
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("exit");
	}
}
