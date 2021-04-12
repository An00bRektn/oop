/*
 * This class just holds help info. Yup.
 */

public class Help {

    public static void printHelp(){
        System.out.println("Usage: java sak [Options] [URL]\n");

        System.out.println("-Help,H                               Displays Help information");
        System.out.println(">> java sak -help\n");

        System.out.println("-HttpRequest,G [URL]                  Returns information from URL to terminal");
        System.out.println(">> java sak -httprequest http://www.ismycomputeron.com/\n");

        System.out.println("-HttpRequestIndex,R                   Returns information from URL, and from URLs within URL");
        System.out.println(">> java sak -httprequestindex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json\n");

        System.out.println("-HttpValidateIndex,V                  Returns information from URL, and from URLs within URL, and validates info files");
        System.out.println(">> java sak -httpvalidateindex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json\n");

        System.out.println("-HttpValidateIndexThreaded,VT         HttpValidateIndex, but faster(?)");
        System.out.println(">> java sak -httpvalidateindexthreaded https://thunderbird-index.azurewebsites.net/w0a6zk195d.json\n");

        System.out.println("-Sleep,S                              Sleeps. Sometimes programs get tired.");
        System.out.println(">> java sak -Sleep\n");

        System.out.println("-SleepFast,SF                         If only real-life sleep could be multi-threaded...");
        System.out.println(">> java sak -SleepFast\n");

        System.out.println("-SleepFastImplementsRunnable,SFR      SleepFast, but a mouthful.");
        System.out.println(">> java sak -SleepFastImplementsRunnable\n");
    }
}
