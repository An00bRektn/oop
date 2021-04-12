/*
 * This program is a sandbox for development
 * Credit to Eric Pogue for HttpRequest.java
 * 
 * Currently Supported Features:
 *     Help, HttpRequest, HttpRequestIndex, HttpValidateIndex,
 *     Sleep, SleepFast, SleepFastImplementsRunnable,
 * 
 */
public class sak{
    public static void main(String[] args){
        long startTime, endTime;
        
        startTime = System.currentTimeMillis();
        printHeader();

        System.out.println();
        if(args.length < 1){ // no arguments
            System.out.println("Usage: java sak [Options] [URL]");
            System.out.println("Use \"-Help\" for further detail.");
        }
        // Help, alias 'h'
        else if(args[0].equalsIgnoreCase("-Help") || args[0].equalsIgnoreCase("-H")){
            System.out.println("Displaying further help...");
            Help.printHelp();
        }
        
        // HttpRequest, alias 'g', because "GET"
        else if(args[0].equalsIgnoreCase("-HttpRequest") || args[0].equalsIgnoreCase("-G")){
            System.out.println("Executing HttpRequest...");
            if(args.length != 2){
                System.out.println("Argument requires valid URL to proceed.");
            }
            else{
                String usrURL = args[1];
				HttpRequest request = new HttpRequest();
				if (request.readURL(usrURL)) {
					System.out.println(request);
				}
            }
        }
        // HttpRequestIndex, alias 'r', because recursive (even though it isn't)
        else if(args[0].equalsIgnoreCase("-HttpRequestIndex") || args[0].equalsIgnoreCase("-R")){
            System.out.println("Executing HttpRequestIndex...");
            if(args.length != 2){
                System.out.println("Argument requires valid URL to proceed.");
            }
            else{
                String usrURL = args[1];
                HttpRequestIndex hri = new HttpRequestIndex();
                if(hri.readURLIndex(usrURL)){
                    System.out.println(hri);
                }
            }
        }
        // HttpValidateIndex, alias 'v', because validate
        else if(args[0].equalsIgnoreCase("-HttpValidateIndex") || args[0].equalsIgnoreCase("-V")){
            System.out.println("Executing HttpValidateIndex...");
            if(args.length != 2){
                System.out.println("Argument requires valid URL to proceed.");
            }
            else{
                String usrURL = args[1];
                HttpValidateIndex hvi = new HttpValidateIndex();
                if(hvi.validateURLIndex(usrURL)){
                    System.out.println(hvi);
                }
            }
        }
        // HttpValidateIndexThreaded, alias 'vt', because validate threaded
        else if(args[0].equalsIgnoreCase("-HttpValidateIndexThreaded") || args[0].equalsIgnoreCase("-VT")){
            System.out.println("Executing HttpValidateIndexThreaded...");
            if(args.length != 2){
                System.out.println("Argument requires valid URL to proceed.");
            }
            else{
                String usrURL = args[1];
                HttpValidateIndexThreaded hvit = new HttpValidateIndexThreaded();
                if(hvit.validateURLIndex(usrURL)){
                    System.out.println(hvit);
                }
            }
        }
        // Sleep, alias 's'
        else if(args[0].equalsIgnoreCase("-Sleep") || args[0].equalsIgnoreCase("-S")){
            Sleep.main();
        }
        // SleepFast, alias 'sf'
        else if(args[0].equalsIgnoreCase("-SleepFast") || args[0].equalsIgnoreCase("-sf")){
            SleepFast.main();
        }
        // SleepFastImplementsRunnable, alias 'sfr'
        else if(args[0].equalsIgnoreCase("-SleepFastImplementsRunnable") || args[0].equalsIgnoreCase("-sfr")){
            SleepFastImplementsRunnable.main();
        }
        // In case of straight up incorrect usage
        else{
            System.out.println("Invalid arguments");
            System.out.println("Usage: java sak [Options] [URL]");
            System.out.println("Use \"-Help\" for further detail.");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime-startTime) + " ms");
    }

    private static void printHeader(){
        System.out.println(" ---------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          SwissArmyKnife         |");
        System.out.println("|       \"We learned to sew?\"      |");
        System.out.println("|_________________________________|");
        System.out.println("|            Version: 2.0         |");
        System.out.println(" ---------------------------------");
    }
}