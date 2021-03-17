import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

class Oval extends JPanel {

    private final Color DARK_BLUE = new Color(0,0,255); // the blue-est blue known to computer kind
    
    // myColor + setters and getters - self-explanatory
    private Color myColor;
    public void setColor(int red, int green, int blue) {
        myColor = new Color(red,green,blue);
    }
    public void setColor(int red, int green, int blue, int alpha) {
        myColor = new Color(red,green,blue,alpha);
    }
    public Color getColor() {
        return myColor;
    }

    // Default Oval(): Makes red oval
    Oval() {
        setColor(255,0,0);
    }

    // Oval(): Makes oval with specific color
    Oval(int red, int green, int blue) {
        setColor(red,green,blue);       
    }

    // paintComponent(): Does the drawing
    // No clue what Graphics object is actually getting passed lol
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        // Rectangle
        /*
         * Does this violate the open-closed principle?
         * Yeah.
         * Do I wish I could figure out how the JLayeredPane
         * worked so I could maybe put the rectangle stuff
         * somewhere else?
         * Yeah.
         */
        g.setColor(DARK_BLUE);
        g.fillRect(0, 0, panelWidth, panelHeight);

        // Oval
        g.setColor(myColor);
        g.fillOval(0,0,panelWidth,panelHeight);
        
    }

}
