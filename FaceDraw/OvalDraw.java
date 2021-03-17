import java.awt.Graphics;

// Draws oval. I'm not sure what else you expected.
class OvalDraw extends Oval{

	public OvalDraw(){
		super(0,0,0,0);
	}

	public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn){
		super(positionXIn, positionYIn, widthIn, heightIn);

	}

	public void paintComponent(Graphics g){
		g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());

	}
}