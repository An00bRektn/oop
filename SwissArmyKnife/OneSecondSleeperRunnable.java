public class OneSecondSleeperRunnable implements Runnable {
    private int sleepNumber;
    OneSecondSleeperRunnable(int sleepNumberIn) {
        sleepNumber = sleepNumberIn;
    }

    public void sleep() {
        System.out.println(sleepNumber + " - Going to sleep");
        try {
            Thread.sleep(1000); // Sleep for one second. 
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("... " + sleepNumber + " - Done sleeping");
    }

    public void run() {
        sleep();
    }
}
