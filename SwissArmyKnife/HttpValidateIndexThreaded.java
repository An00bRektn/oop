import java.util.ArrayList;

public class HttpValidateIndexThreaded extends HttpRequestIndex{

    HttpValidateIndexThreaded(){
        super();
    }
    HttpValidateIndexThreaded(String urlIn){
        super(urlIn);
    }

    @Override
    public Boolean readURLIndex(String urlIn){
        requestURL = urlIn;
        mainURL = requestURL;
        Boolean returnValue = false; 
        try {
            readURL(requestURL); // read the url
            for (String line : allUrlContent.get(0)) { // for every line you pull from the url
                if(line.contains("http")){ // if that line contains http
                    String[] parts = line.split("\""); // split up the line by quotes
                    urls.add(parts[11]); // add it to the list of urls
                }
            }
            int i = 0;
            ArrayList<HttpRequestThread> requests = new ArrayList<HttpRequestThread>();
            ArrayList<Thread> threads = new ArrayList<Thread>();
            for (String url : urls) { // take the list of urls
                requests.add(new HttpRequestThread(i++, url)); // make new requester
            }
            i = 0;
            for(int j=0; j<requests.size(); j++){
                threads.add(new Thread(requests.get(j))); // turn each requester into thread
            }
            for (Thread t : threads) {
                t.start(); // start the requests
            }
            
            try {
                for (HttpRequestThread r : requests) {
                    allUrlContent.add(r.urlContent); // get url content from each requester
                }
                for (Thread t : threads) {
                    t.join();
                }
                
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            returnValue = true;
        } 
        catch (Exception e) {
            returnValue = false;
        }
        return returnValue;
    }
    public Boolean validateURLIndex(){
        return validateURLIndex(requestURL);
    }

    public Boolean validateURLIndex(String urlIn){
        requestURL = urlIn;
        String returnMessage;
        try {
            readURLIndex(requestURL);
            JSONParse parser = new JSONParse();
            
            for(int i=1; i<allUrlContent.size(); i++){
                returnMessage = parser.checkNames(allUrlContent.get(i));
                if(returnMessage.contains("0")){   
                    allUrlContent.get(i).add(generateErrorMessage(returnMessage));
                }
                else{
                    allUrlContent.get(i).add("passed\n");
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String generateErrorMessage(String bits){
        String failed = "";
        if(bits.charAt(0) == '0'){ failed += "failed - missing firstName field\n";}
        if(bits.charAt(1) == '0'){ failed += "failed - invalid firstName length\n";}
        if(bits.charAt(2) == '0'){ failed += "failed - missing lastName field\n";}
        if(bits.charAt(3) == '0'){ failed += "failed - invalid lastName length\n";}
        if(bits.charAt(4) == '0'){ failed += "failed - invalid preferredName field\n";}
        if(bits.charAt(5) == '0'){ failed += "failed - invalid prefferedName length\n";}

        return failed;
    }
}
