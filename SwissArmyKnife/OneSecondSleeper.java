class OneSecondSleeper extends Thread{
    private int sleepNum;

    OneSecondSleeper(int sleepNumIn){
        sleepNum = sleepNumIn;
    }
    public void sleep(){
        System.out.println(sleepNum + " - Going to sleep");
        try {
            Thread.sleep(1000); // Sleep for one second
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        } 

        System.out.println("... " + sleepNum + " - Done sleeping");
    }
    
    @Override
    public void run(){
        sleep();
    }
}