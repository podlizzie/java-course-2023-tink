package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {
    private final List<Worker> workers = new ArrayList<>();
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();

    public FixedThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            workers.add(new Worker());
        }
    }

    @Override
    public void start() {
        workers.forEach(Thread::run);
    }

    public static FixedThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void execute(Runnable runnable) {
        workQueue.add(runnable);
    }

    @Override
    public void close() {
        workers.forEach(worker -> {
            try {
                worker.interrupt();
                worker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private final class Worker extends Thread {
        @Override
        public void run() {
            Runnable nextWork;
            while (true) {
                synchronized (workQueue) {
                    if (workQueue.isEmpty()) {
                        break;
                    }
                    nextWork = workQueue.poll();
                }
                nextWork.run();
            }
        }
    }
}
