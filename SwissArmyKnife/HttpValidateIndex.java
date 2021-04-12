public class HttpValidateIndex extends HttpRequestIndex{
    
    HttpValidateIndex(){
        super();
    }

    HttpValidateIndex(String urlIn){
        super(urlIn);
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

    // Returns fail info for each fault
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
