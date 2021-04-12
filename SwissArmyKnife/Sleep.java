public class Sleep {
    public static void main(){
        System.out.println("Starting Sleep...");

        System.out.println("\nSleep");
        OneSecondSleeper sleeper0 = new OneSecondSleeper(0);

        long start = System.currentTimeMillis();
        sleeper0.sleep();
        System.out.println("Elapsed Time = " + (System.currentTimeMillis() - start));
    }
}
