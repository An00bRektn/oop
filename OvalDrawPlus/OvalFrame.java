import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


class OvalFrame extends JFrame {
    
    // Default OvalFrame(): establishes the frame (profound, I know)
    OvalFrame() {
        setTitle("The *B E S T* OvalDrawPlus program on the internet");
        setBounds(250,150,400,500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // allows program to respond to close

        Oval myOval = new Oval(204, 100, 245); //purple oval
        Container contentPane = getContentPane();
        contentPane.add(myOval);

        // Credit to user9643348 for the solution
        // https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
        // Note: I do understand how this works, I did not copy and paste
        // blindly
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Closing OvalDrawPlus...");
                //THEN you can exit the program
                System.exit(0);
            }
        });
    }
    
}
