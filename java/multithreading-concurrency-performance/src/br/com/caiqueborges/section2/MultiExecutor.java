package br.com.caiqueborges.section2;

import java.util.List;

public class MultiExecutor {
    private List<Runnable> tasks;

    // Add any necessary member variables here

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Runnable task : tasks) {
            new Thread(task).start();
        }
    }
}
