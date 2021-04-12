import java.util.ArrayList;

public class SleepFastImplementsRunnable {
    public static void main() {
        System.out.println("Starting Sleep...");

        OneSecondSleeperRunnable sleeper0 = new OneSecondSleeperRunnable(0);
        OneSecondSleeperRunnable sleeper1 = new OneSecondSleeperRunnable(1);

        System.out.println("\nNon-threaded Sleep");
        long start = System.currentTimeMillis();
        sleeper0.sleep();
        sleeper1.sleep();
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

        System.out.println("\nThreaded Sleep");
        start = System.currentTimeMillis();

        Thread t0 = new Thread(sleeper0);
        Thread t1 = new Thread(sleeper1);

        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

        // Sleeping ArrayList
        ArrayList<OneSecondSleeperRunnable> sleeperList = new ArrayList<OneSecondSleeperRunnable>();
        for (int i=2; i<12; i++) {
            sleeperList.add(new OneSecondSleeperRunnable(i));
        }

        System.out.println("\nNon-threaded ArrayList sleep:");
        start = System.currentTimeMillis();
        for (OneSecondSleeperRunnable s: sleeperList) {
            s.sleep();
        }
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));

        System.out.println("\nThreaded ArrayList sleep:");
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        for (OneSecondSleeperRunnable s: sleeperList) {
            threadList.add(new Thread(s));
        }

        start = System.currentTimeMillis();
        for (Thread t: threadList) {
            t.start();
        }

        try {
            for (Thread t: threadList) {
                t.join();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Elapsed time = "+(System.currentTimeMillis()-start));
    }
}