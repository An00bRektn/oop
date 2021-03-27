import java.util.ArrayList;

public class HttpRequestIndex extends HttpRequest {
    private ArrayList<String> urls;

    HttpRequestIndex(){
        super();
        urls = new ArrayList<String>();
    }

    HttpRequestIndex(String urlIn) {
        super(urlIn);
        urls = new ArrayList<String>();
    }

    public Boolean readURLIndex(){
        return readURLIndex(requestURL);
    }

    public Boolean readURLIndex(String urlIn){
        requestURL = urlIn;
        Boolean returnValue = false; 
        try {
            readURL(requestURL); // read the url
            for (String line : urlContent) { // for every line you pull from the url
                if(line.contains("http")){ // if that line contains http
                    String[] parts = line.split("\""); // split up the line by quotes
                    urls.add(parts[11]); // add it to the list of urls
                }
            }
            
            for (String url : urls) { // take the list of urls
                urlContent.add("NEXT"); // makes output easier given implementation
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
        String returnValue = "URL: "+requestURL + "\n";
        int counter = 0;
        for (String s : urlContent) {
            if(s.equals("NEXT")){ // replaces NEXT with url
                returnValue = returnValue + "URL: "+ urls.get(counter) + "\n";
                counter++;
            }
            else{
                returnValue = returnValue + s + "\n";
            }
        } 
        return returnValue;
    }
}
