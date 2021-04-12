import java.util.ArrayList;

// TODO: Ask what is necessary here
public class SleepFast {
    public static void main(){
        System.out.println("Starting SleepFast...");

        System.out.println("\nSingle-Threaded Sleep");
        OneSecondSleeper sleeper0 = new OneSecondSleeper(0);
        OneSecondSleeper sleeper1 = new OneSecondSleeper(1);
        
        long start = System.currentTimeMillis();
        sleeper0.sleep();
        sleeper1.sleep();
        System.out.println("Elapsed Time = " + (System.currentTimeMillis() - start));
        
        System.out.println("\nThreaded Sleep");
        start = System.currentTimeMillis();
        sleeper0.start();
        sleeper1.start();

        try{
            sleeper0.join();
            sleeper1.join();
        } catch(Exception e){
            System.out.println("Exception = " + e);
        }
        System.out.println("Elapsed Time = " + (System.currentTimeMillis() - start));

        // Sleeping ArrayList
        ArrayList<OneSecondSleeper> sleeperList = new ArrayList<OneSecondSleeper>();
        for (int i=2; i<12; i++) {
            sleeperList.add(new OneSecondSleeper(i));
        }

        System.out.println("\nNon-threaded ArrayList sleep:");
        start = System.currentTimeMillis();
        for (OneSecondSleeper s: sleeperList) {
            s.sleep();
        }
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));

        System.out.println("\nThreaded ArrayList sleep:");
        start = System.currentTimeMillis();
        for (OneSecondSleeper s: sleeperList) {
            s.start();
        }

        try {
            for (OneSecondSleeper s: sleeperList) {
                s.join();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Elapsed time = "+(System.currentTimeMillis()-start));
    }
}
