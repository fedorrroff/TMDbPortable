package com.fedorrroff.utils.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadUtil {

    private static int processorsNumber =  Runtime.getRuntime().availableProcessors() + 1;
    private static ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(processorsNumber);

    public static <T> T runOnBackground (Callable<T> task) {
        final Future<T> future = pool.submit(task);

        T result = null;
            try {
                result = future.get();
            } catch (ExecutionException | InterruptedException e ) {
                e.printStackTrace();
            }

        return result;
    }
}
