import java.awt.Graphics;

/*
 * Credit to Eric Pogue for ShapesLibrary.java
 * Specifically implementation of Oval class
 */

class Face extends OvalDraw{
	
    private OvalDraw eyeL;
    private OvalDraw eyeR;

	// 1: frown, 2: straight, 3: smile
    private int expression = (int) (Math.random() * 3) + 1;
	public void setExpression(int expressionIn){
		if(expressionIn > 3 || expressionIn < 1){
			System.out.println("Expression out of range.");
		}
		else{ expression = expressionIn; }
	}
	public int getExpression(){ return expression; }

	// Default, creats nothing
	public Face(){
		super(0,0,0,0);
		eyeL = new OvalDraw(0,0,0,0);
        eyeR = new OvalDraw(0,0,0,0);
	}

	// Makes a circle
	public Face(int positionXIn, int positionYIn){
		super(positionXIn, positionYIn, 75, 75);

		int eyePositionXL = positionXIn + (75 / 4);
        int eyePositionXR = positionXIn + (2 * 75 / 3);
		int eyePositionY = positionYIn + (75 / 4);

		eyeL = new OvalDraw(eyePositionXL,eyePositionY,38,13);
        eyeR = new OvalDraw(eyePositionXR,eyePositionY,38,13);
	}

	// Assigns all parameters and calculates features appropriately
	public Face(int positionXIn, int positionYIn, int widthIn, int heightIn){
		super(positionXIn, positionYIn, widthIn, heightIn);

		int eyeHeight = heightIn / 6; 
		int eyeWidth = eyeHeight / 2; 
		int eyePositionXL = positionXIn + (widthIn / 4);
        int eyePositionXR = positionXIn + (2 * widthIn / 3);
		int eyePositionY = positionYIn + (heightIn / 4);

		eyeL = new OvalDraw(eyePositionXL, eyePositionY, eyeWidth, eyeHeight);
        eyeR = new OvalDraw(eyePositionXR, eyePositionY, eyeWidth, eyeHeight);

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		eyeL.paintComponent(g);
        eyeR.paintComponent(g);
        
		// Determining expression
        switch(expression){
		case 1: // frown
            g.drawArc(getPositionX() + (getWidth() / 5), getPositionY()+(2 * getHeight() / 3), 
                      2 * getWidth() / 3, 2 * getHeight() / 3, 45, 90); 
            break;
        case 2: // straight
            g.drawLine(getPositionX() + (getWidth() / 5), getPositionY()+(2 * getHeight() / 3), 
                       getPositionX() + (4 * getWidth() / 5), getPositionY()+(2 * getHeight() / 3)); 
            break; 
        case 3: // smile
            g.drawArc(getPositionX() + (getWidth() / 5), getPositionY() + (getHeight()/8), 
                      2 * getWidth() / 3, 2 * getHeight() / 3, 225, 90); 
            break;
        }
	}

	public String toString(){
		String s = "Shape.Oval.OvalDraw.Face[Expression=";
		switch(expression){
			case 1: s += "frown, "; break;
			case 2: s += "straight, "; break;
			case 3: s += "smile, "; break;
			default: s += "n/a, ";
		}
		s += String.format("x=%d, y=%d, width=%d, height=%d]", 
							getPositionX(), getPositionY(), getWidth(), getHeight());
		return s;
	}
}