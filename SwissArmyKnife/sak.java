/*
 * This program is a sandbox for development
 * Credit to Eric Pogue for HttpRequest.java
 * 
 * Currently Supported Features:
 *     Help, HttpRequest, HttpRequestIndex
 * 
 */
public class sak{
    public static void main(String[] args){
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
        // In case something happens
        else{
            System.out.println("Invalid arguments");
            System.out.println("Usage: java sak [Options] [URL]");
            System.out.println("Use \"-Help\" for further detail.");
        }
    }

    private static void printHeader(){
        System.out.println(" ---------------------------------");
        System.out.println("|                                 |");
        System.out.println("|          SwissArmyKnife         |");
        System.out.println("|        \"cURL, but worse!\"       |");
        System.out.println("|_________________________________|");
        System.out.println("|            Version: 1.0         |");
        System.out.println(" ---------------------------------");
    }
}