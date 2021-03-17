import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JPanel;

// Outlines code for each tile
class Tile extends JPanel implements MouseListener{

    private int red, green, blue;
    private int shape; // determines circle or rect (0-1)
    private String letter; 

    private int expression; // expression of face (1-3)
    private boolean isFace = false;
    public void setFace(boolean state) {
        isFace = state;
    }
    public boolean getFace(){return isFace;}

    // stores total number of tiles to generate tid
    private static int totalTiles = 0; 
    private int tid = 0; // stores id of tile

    Tile(){
        super();
        SetRandomValue();
        addMouseListener(this);
        tid = totalTiles++;
    }

    Tile(int rIn, int gIn, int bIn){
        red = rIn;
        green = gIn;
        blue = bIn;
        shape = GetNumberBetween(0, 1);
        letter = String.valueOf((char) GetNumberBetween(65, 90));
        tid = totalTiles++;
    }

    // Randomizes attributes of Tile
    final public void SetRandomValue(){
        red = GetNumberBetween(0, 255);
        blue = GetNumberBetween(0, 255);
        green = GetNumberBetween(0, 255);
        expression = GetNumberBetween(1, 3);
        shape = GetNumberBetween(0,1);
        letter = String.valueOf((char) GetNumberBetween(65, 90));
    }

    // Helper function, credit Eric Pogue, makes more intuitive rng
    private static int GetNumberBetween(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt(max-min+1);
    }

    // Helper function, credit Eric Pogue, makes letter with color 
    // aesthetically pleasing
    private static int GetContrastingColor(int colorIn){
        return((colorIn+128)%256);
    }

    // Draws tiles or faces
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        if(tid == 143){ System.out.println("Start paint***"); }
        if(!isFace){
            /* 
             * --------------------------
             * DRAWS TILES
             * --------------------------
             */
            g.setColor(new Color(red, blue, green));
            if(shape == 0){
                g.fillRect(0, 0, panelWidth, panelHeight);
            }
            else{
                g.fillOval(0, 0, panelWidth, panelHeight);
            }

            g.setColor(new Color(GetContrastingColor(red), GetContrastingColor(blue), GetContrastingColor(green)));
            
            // Font settings, very finnicky
            final int fontSize = (((int) (0.3 * panelWidth)) + ((int) (0.3 * panelHeight)) / 2);
            g.setFont(new Font("Arial", Font.PLAIN, fontSize));
            int stringX = (panelWidth/2)-((int) (0.3 * fontSize));
            int stringY = (panelHeight/2)+((int) (0.3 * fontSize));
            g.drawString(letter, stringX, stringY);

            System.out.println(this.toString());
        }
        else{
             /* 
             * --------------------------
             * DRAWS FACES
             * --------------------------
             */
            g.drawOval(0, 0, panelWidth, panelHeight); // face
            g.drawOval(panelWidth/4, panelHeight/4, panelWidth/6, panelHeight/6); // left eye
            g.drawOval((2 * panelWidth / 3), panelHeight/4, panelWidth/6, panelHeight/6); // right eye
            switch(expression){
                case 1: // frown
                    g.drawArc(panelWidth/5, 2 * panelHeight / 3, 
                              2 * panelWidth / 3, 2 * panelHeight / 3, 45, 90); 
                    break;
                case 2: // straight
                    g.drawLine((panelWidth / 5), (2 * panelHeight / 3), 
                                (4 * panelWidth / 5), (2 * panelHeight / 3)); 
                    break; 
                case 3: // smile
                    g.drawArc((panelWidth / 5), (panelHeight/8), 
                              2 * panelWidth / 3, 2 * panelHeight / 3, 225, 90); 
                    break;
            }
        }
        
    }
    
    public String toString(){
        String shapeStr;
        if (shape == 1) { shapeStr = "circle"; }
        else { shapeStr = "square"; }
        return String.format("JPanel.Tile[id=%d, shape=%s, letter=%s, r=%d, g=%d, b=%d]",
                                          tid, shapeStr, letter, red, green, blue);
    }
    
    public void mouseClicked(MouseEvent e) {
        System.out.format("id=%d, isFace=%b\n", tid, isFace);
        if(getFace()){ setFace(false); }
        else{ setFace(true); }
        repaint();
    }

    // Unused MouseListener methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e) {}
}
