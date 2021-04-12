/*
 * Each personal information file referenced in the index file exists
   The "firstName" and "lastName" fields exist and have a length of 2 to 16 characters (inclusive)
   If the optional "preferredName" field exists, it meets the same length requirements as "firstName" and "lastName"
 */

import java.util.ArrayList;

public class JSONParse {
    public String checkNames(ArrayList<String> content){
        // forms bitstring that represent areas of issue
        String[] errorBits = new String[6]; 
        for (String s : content) {
            if(s.contains("firstName")){
                errorBits[0] = "1";
                if(checkNameLength(s)){
                    errorBits[1] = "1";
                }
            }
            if(s.contains("lastName")){
                errorBits[2] = "1";
                if(checkNameLength(s)){
                    errorBits[3] = "1";
                }
            }
            if(s.contains("preferredName")){
                errorBits[4] = "1";
                if(checkNameLength(s)){
                    errorBits[5] = "1";
                }
            }
        }
        // fills in holes with 0's, unless optional
        for(int i=0; i<6; i++){
            if(errorBits[i] == null){
                if(i == 4 || i == 5){
                    errorBits[i] = "1";  // in case preferred name is not there  
                }
                else{
                    errorBits[i] = "0"; 
                }  
            }
        }
        
        return stringArrayToString(errorBits);
    }

    private Boolean checkNameLength(String s){
        String[] parsed = s.split("\"");
        if(parsed[3].length() < 2 || parsed[3].length() > 16){
            return false;
        }
        else{
            return true;
        }
    }

    private static String stringArrayToString(String[] strArr){
        String str = "";
        for (String s : strArr) {
            str += s;
        }
        return str;
    }

}
