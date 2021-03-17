import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

class FacePanel extends JPanel{
	
    ArrayList<Face> faces = new ArrayList<Face>();
    Random rand = new Random();

	public FacePanel(){
        int numFaces = rand.nextInt(8) + 3; // random int from 3-10
        
        // Generating random parameters for each face, and printing the toString()
        for(int i=0; i<numFaces; i++){
            int randX = rand.nextInt(401) + 75;
            int randY = rand.nextInt(401) + 33;
            int randW = rand.nextInt(101) + 50;
            int randH = rand.nextInt(101) + 50;
            faces.add(new Face(randX, randY, randW, randH));

            System.out.println(faces.get(i).toString());
        }
	}
    public FacePanel(int appWidth, int appHeight){
        int numFaces = rand.nextInt(8) + 3; // random int from 3-10
        
        int appWidthRange = (int) (appWidth * 0.66);
        int appHeightRange = (int) (appHeight * 0.66);

        // Generating random parameters for each face, and printing the toString()
        for(int i=0; i<numFaces; i++){
            int randX = rand.nextInt(appWidthRange) + (int) (appWidth * 0.1);
            int randY = rand.nextInt(appHeightRange) + (int) (appHeight * 0.1);
            int randW = rand.nextInt((int) (appWidthRange * 0.2)) + (int) (appWidth * 0.1);
            int randH = rand.nextInt((int)(appHeightRange * 0.2)) + (int) (appHeight * 0.1);
            faces.add(new Face(randX, randY, randW, randH));

            System.out.println(faces.get(i).toString());
        }
	}
    // displays each face 
	public void paint(Graphics g){
		super.paintComponent(g);
        for(Face face : faces){
            face.paintComponent(g);
        }
	}
}
