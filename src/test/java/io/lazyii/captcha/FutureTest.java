package io.lazyii.captcha;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020/8/12 15:04:18.
 */
public class FutureTest {

    @Test
    public void completableFutureTest() {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Runnable x = () -> {
            try {
                System.out.println(Thread
                        .currentThread()
                        .getName() + " start " + counter.getAndIncrement() + "  " + LocalDateTime.now());
                URL url = new URL("https://www.google.com");
                url.getContent();
//                TimeUnit.SECONDS.sleep(2);
                
                System.out.println(Thread
                        .currentThread()
                        .getName() + " end " + counter.get() + "  " + LocalDateTime.now());
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    
        for (int i = 0; i < 1000; i++) {
            CompletableFuture.completedFuture(1).thenRunAsync(x, executorService);
        }
        try {
            System.out.println(LocalDateTime.now());
            TimeUnit.SECONDS.sleep(50);
            System.out.println(LocalDateTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        System.out.println("hahahaha");;
    }
}
