import java.util.Scanner;

/*
 * Ryan Syed
 * CPSC 24500, Spring 2021
 * Matchmaker Program
 * 
 * This program determines whether or not the user is my
 * "true love" using 5 questions of various weights.
 * 
 */

public class MatchMaker{

    final static int FRIENDS = 69; // min score for friends
    final static int TRUE_LOVE = 90; // min score for True Love
    
    static String[] questions = {"Chicago is a neat city",
                          "JRR Tolkien set the bar too high for future high fantasy novelists",
                          "The Portillo's Cake Shake is not good. \n(For the uninitiated, it's exactly what it sounds like)",
                          "Ya like jazz (but, like, unironically)?",
                          "The Nintendo Switch is a good console"};
    static int[] weights = {2, 3, 1, 3, 1}; // How much each question should be valued
    static int[] myAns = {4, 5, 2, 5, 1}; // The objectively correct answers
    static int[] usrAns = new int[5]; // stores user ans

    // printHeader(): Prints opening messages
    static void printHeader(){

        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.println("|          Matchmaker 9000          |");
        
        // generates flavor text
        switch((int) (Math.random() * 6) + 1){
            case 1: System.out.println("|         \"What\'s popping?\"         |"); break;
            case 2: System.out.println("|50% of the time, it works everytime|"); break;
            case 3: System.out.println("|    Not your mom\'s matchmaker!    |"); break;
            case 4: System.out.println("|  There\'s a reason this is \'9000\'  |"); break;
            case 5: System.out.println("|         Success Rate: n/a         |"); break; // personal favorite
            case 6: System.out.println("|     100% Backed by \"Science\"      |"); break;
            default: System.out.println("|         [Insert Random Quip]        |"); // redundant
        }
        System.out.println("|           Notas Cam Inc           |");
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n");
        
        // Instructions
        System.out.println("This program will figure out if we are");
        System.out.println("meant to be. You will be presented with");
        System.out.println("five statements, and will answer on a scale");
        System.out.println("of 1-5, where 1 is Strongly Disagree, 5");
        System.out.println("is Strongly Agree, and 3 is neutral.\n");

        System.out.println("Good luck! I definitely won't hold any of these answers against you!\n");
    }
    
    // askQ(): Asks the question from the questions[] array
    static void askQ(int index){
        System.out.println("<3<3<3<3<3<3<3<3<3<3<3<3");
        System.out.println("        Question " + (index+1));
        System.out.println("<3<3<3<3<3<3<3<3<3<3<3<3 \n");

        System.out.print(questions[index] + ": ");
    }

    // validate(): Checks if user input is valid
    static boolean validate(String token){
        int tokenInt = 0;

        try{ // try to parse int from input
            tokenInt = Integer.parseInt(token);
        }
        catch(Exception e){ // if anything is wrong, it is either too many inputs, or non-int inputs
            System.out.println("Error 1337: Please enter ONE (actual) integer. \n");
            return false;
        }
        if(tokenInt < 1 || tokenInt > 5){ // out of range error
            System.out.println("Error 9999: Please enter an integer in the valid range. \n");
            return false;
        }
        return true;
    }

    // calculateScore(): Takes user answers and applies the following jank formula
    // Let W: 1x5 matrix of weights, D: 5x1 matrix of the diffs between myAns[] and usrAns[]
    // (100 - (W * D * 1.66)) + 3 = score
    static int calculateScore(int ans[]){
        int score = 100;
        double[] weighted = new double[5];
        for(int i=0; i<ans.length; i++){
            weighted[i] = Math.abs(myAns[i]-ans[i]);
            weighted[i] *= weights[i] * 1.66;
            score -= weighted[i];
        }
        if(score == 100){ // keeping max at 100
            return score;
        }
        else{ // the +3 acts more like a curve if anything
            return score + 3;
        }  
    }

    // displayBreakdown(): displayEnd() was getting long, so I put the breakdown of the scores here
    static void displayBreakdown(int ans[]){
        int[] weighted = new int[5];
        int[] unweight = new int[5];

        // Calculates compatipbility scores (weighted and unweighted)
        // Mostly arbitrary since these exact numbers were not used in the actual calculation
        // but still serve an okay purpose
        for(int i=0; i<ans.length; i++){
            weighted[i] = Math.abs(myAns[i]-ans[i]);
            unweight[i] = Math.abs(myAns[i]-ans[i]);
            unweight[i] *= 1.66;
            weighted[i] *= weights[i] * 1.66;
        }
        System.out.println(" <3 <3 <3 <3 <3 <3 <3");
        // Loop to display the scores
        for(int i=0; i<5; i++){
            System.out.print("      Q" + (i+1) + "  ");
            System.out.print(100 - (unweight[i] * 5));
            System.out.print("/");
            System.out.print(100 - (weighted[i] * 5));
            System.out.print("    ");
            System.out.println();
        }
        System.out.println(" <3 <3 <3 <3 <3 <3 <3");

        System.out.println();
        System.out.println("The first percentage in each box is the unweighted score.");
        System.out.println("The second percentage is the weighted score.\n");

        System.out.println("Until next time!");
    }

    // displayEnd(): Prints out end message dependent on score
    static void displayEnd(int score){
        Scanner tmp = new Scanner(System.in);
        String ans; // answer to see breakdown
        System.out.println("Your score was " + score + "%");
        System.out.println();

        if(score < TRUE_LOVE){
            if(score < FRIENDS){ // worst ending
                System.out.println("Nothing against you, but, really?"); 
                System.out.println("I mean, not the greatest takes...");
            }
            else{
                System.out.println("You seem like a neat person."); // friend ending
                System.out.println("Not true love, but maybe a true homie?");
            }
        } 
        else{
            System.out.println("Please send your contact info to NotasCam@defoscam.net"); // true love ending
            System.out.println("You recieved an \"Error\" if you catch my drift...");
        }
        System.out.println();
        System.out.print("See a more detailed breakdown? (y/n): ");
        ans = tmp.next().toLowerCase();

        System.out.println();

        if(ans.equals("y") || ans.equals("yes")){
            displayBreakdown(usrAns);
        }
        else{
            System.out.println("Until next time! \n");
        }
        tmp.close();
    }
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String usrInput;
        int score;
        
        printHeader();

        for(int i=0; i<questions.length; i++){ // asks each question with validation
            do {
                askQ(i);
                usrInput = scan.nextLine();
                System.out.println();
            }while(!validate(usrInput));
            usrAns[i] = Integer.parseInt(usrInput);
            System.out.println();
        }
        
        score = calculateScore(usrAns);
        displayEnd(score);
        scan.close();
    }
}