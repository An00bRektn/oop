import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpRequestIndex extends HttpRequest {
    protected ArrayList<String> urls;
    protected ArrayList<ArrayList<String>> allUrlContent;
    protected String mainURL;
    private int urlNum = 0;

    HttpRequestIndex(){
        super();
        allUrlContent = new ArrayList<ArrayList<String>>();
        urls = new ArrayList<String>();
    }

    HttpRequestIndex(String urlIn) {
        super(urlIn);
        allUrlContent = new ArrayList<ArrayList<String>>();
        urls = new ArrayList<String>();
    }

    @Override
    public Boolean readURL(String urlIn) {
        requestURL = urlIn;
        Boolean returnValue = false;
        allUrlContent.add(new ArrayList<String>()); // adds new list to store info
 
        try {
            URL myURL = new URL(requestURL);
            URLConnection myConnection = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                allUrlContent.get(urlNum).add(inputLine);
                returnValue = true;
            }
            in.close();
        }
        catch (Exception e) {
            returnValue = false;
        }
        
        return returnValue;
    }

    public Boolean readURLIndex(){
        return readURLIndex(requestURL);
    }

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
            for (String url : urls) { // take the list of urls
                urlNum++;
                readURL(url); // and read each url
            }
            returnValue = true;
        } 
        catch (Exception e) {
            returnValue = false;
        }
        
        
        return returnValue;
    }

    @Override
    public String toString() {
        String returnValue = "URL: "+ mainURL + "\n";
        int i = -1;
        for (ArrayList<String> a : allUrlContent) {
            if(i!=-1){ returnValue = returnValue + "URL: " + urls.get(i) + "\n"; }
            i++;
            for (String s : a) {
                returnValue = returnValue + s + "\n";
            }
            
        } 
        return returnValue;
    }
    
    public static void main(String[] args){
        HttpRequestIndex hri = new HttpRequestIndex();
        if(hri.readURLIndex("https://thunderbird-index.azurewebsites.net/w0a6zk195d.json")){
            System.out.println(hri);
        }
    }
}
