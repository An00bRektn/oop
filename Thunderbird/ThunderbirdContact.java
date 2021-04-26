/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file is licensed under the BSD-3-Clause
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 *****************************************************************************/

class ThunderbirdContact extends HttpRequest implements Runnable {
    
    private String authorName;
    public String getAuthorName() { return authorName; }

    private String authorEmail;
    public String getAuthorEmail() { return authorEmail; }

    private String firstName;
    public String getFirstName() { return firstName; }

    private String lastName;
    public String getLastName() {return lastName; }
    
    private String preferredName;
    public String getPreferredName() { return preferredName; }

    private String favoriteHobby;
    public String getFavoriteHobby() { return favoriteHobby;}

    private String email;
    public String getEmail() { return email; }

    private String city;
    public String getCity() { return city; }

    private String state;
    public String getState() { return state; }

    private int seatLocation; 
    public int getSeat() { return seatLocation; }

    ThunderbirdContact(String urlIn) {
        super(urlIn);

        authorName = "Unknown";
        authorEmail = "Unknown";

        firstName = "";
        lastName = "";
        preferredName = "";
        favoriteHobby = "";
        email = "";
        city = "";
        state = "";

        seatLocation = 0;

        // Todo: Add additional fields.
        // RS: Added fields that existed in most of the JSON files.  
    }

    ThunderbirdContact(String urlIn, String authorNameIn, String authorEmailIn) {
        super(urlIn);

        authorName = authorNameIn;
        authorEmail = authorEmailIn;

        firstName = "";
        lastName = "";
        preferredName = "";
        favoriteHobby = "";
        email = "";
        city = "";
        state = "";

        seatLocation = 0;
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            // Todo: Parse for additional fields.
            // RS: Added fields that existed in most of the JSON files. 
            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("preferredName")){
                    preferredName = subString[3];
                }
                if (subString[1].equals("favoriteHobby")){
                    favoriteHobby = subString[3];
                }
                if (subString[1].equals("email")){
                    email = subString[3];
                }
                if (subString[1].equals("city")){
                    city = subString[3];
                }
                if (subString[1].equals("state")){
                    state = subString[3];
                }
                if (subString[1].equals("seatLocation")) {
                    try {
                        seatLocation = 0; 
                        if (!subString[3].equals("")) {
                            seatLocation = Integer.parseInt(subString[3]);
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }    
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
            return; // Returning from the middle of a method is controversial.
        }

        // Todo: Add author's name and email address to failed messages.
        // RS: Passed author name and email address through constructor. 
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.format("    Author: %s, Email: %s\n", authorName, authorEmail);
            System.out.println("    **Failed**: First Name (\"firstName\") required but not found\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("Validating: " + requestURL);
            System.out.format("    Author: %s, Email: %s\n", authorName, authorEmail);
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
    }

    public String toString() {
        // Todo: Add additional fields to returnString. 
        // RS: Added relevant fields.
        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "preferredName: " + preferredName + "\n";
        returnString = returnString + "favoriteHobby: " + favoriteHobby + "\n";
        returnString = returnString + "email: " + email + "\n";
        returnString = returnString + "city: " + city + "\n";
        returnString = returnString + "state: " + state + "\n";
        returnString = returnString + "seatNumber: " + seatLocation + "\n";
        returnString = returnString + super.toString();

        return returnString;
    }

    // RS: The fact that traditional cocatenation doesn't work, but a StringBuilder does
    //     baffles me.
    public String toHTML(){
        StringBuilder buff = new StringBuilder();
        buff.append("<html>");
        buff.append("firstName: " + firstName + "<br>");
        buff.append("lastName: " + lastName + "<br>");
        buff.append("preferredName: " + preferredName + "<br>");
        buff.append("favoriteHobby: " + favoriteHobby + "<br>");
        buff.append("email: " + email + "<br>");
        buff.append("city: " + city + "<br>");
        buff.append("state: " + state + "<br>");
        buff.append("seatNumber: " + seatLocation + "<br>");
        buff.append("</html>");
        return buff.toString();
    }

    public void run() {
        Load();
    }
}