
public class HttpRequestThread extends HttpRequest implements Runnable {
    private int tid;

    HttpRequestThread(int tidIn){
        super();
        tid = tidIn;
    }
    HttpRequestThread(int tidIn, String urlIn){
        super(urlIn);
        tid = tidIn;
    }

    @Override
    public void run() {
        readURL(requestURL);
    }
    
}
