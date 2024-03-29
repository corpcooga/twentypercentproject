package corpcooga.components;

import corpcooga.canvas.DrawingSurface;

import java.awt.Color;
import processing.core.PApplet;

public class Button extends GraphicsElement
{
//	Fields
	
	private String text;
	private Color fillColor, borderColor, textColor;
	private int borderWidth, borderRound, textSize;
	
	
//	Constructors
	
	public Button(String text, int x, int y, int width, int height,
			int bWidth, int bRound, int tSize, Color fCol, Color bCol, Color tCol)
	{
		super(x, y, width, height);
		this.text = text;
		borderWidth = bWidth;
		borderRound = bRound;
		textSize = tSize;
		textColor = tCol;
		fillColor = fCol;
		borderColor = bCol;
	}
	
	public Button(String text, int x, int y, int width, int height, int bWidth, int bRound, int tSize)
	{
		this(text, x, y, width, height, bWidth, bRound, tSize, Color.white, Color.black, Color.black);
	}
	
	public Button(String text, int x, int y, int width, int height, Color fCol, Color bCol, Color tCol)
	{
		this(text, x, y, width, height, (int)(width / 20), (int)(height / 3), (int)(height * 0.5), fCol, bCol, tCol);
	}
	
	public Button(String text, int x, int y, int width, int height)
	{
		this(text, x, y, width, height, (int)(width / 20), (int)(height / 3), (int)(height * 0.5));
	}
	
	public Button()
	{
		this("", 0, 0, 0, 0);
	}
	
	
//	Methods
	
	public void draw(PApplet p)
	{
		p.push();
		
//		Button body
		p.strokeWeight(borderWidth);
		p.fill(fillColor.getRGB());
		p.stroke(borderColor.getRGB());
		if (pointOver(p.mouseX * DrawingSurface.DRAWING_WIDTH / p.width, 
				p.mouseY * DrawingSurface.DRAWING_HEIGHT / p.height)) {
			p.fill(fillColor.getRed() * .8f, fillColor.getGreen() * .8f, fillColor.getBlue() * .8f);
			if (p.mousePressed)
				p.fill(fillColor.getRed() * .5f, fillColor.getGreen() * .5f, fillColor.getBlue() * .5f);
		}
		p.rect(getX(), getY(), getWidth(), getHeight(), borderRound);
		
//		Button text
		p.textSize(textSize);
		p.textAlign(PApplet.CENTER, PApplet.CENTER);
		p.fill(textColor.getRGB());
		p.text(text, getX() + getWidth() / 2, getY() + getHeight() / 2);
		
		p.pop();
	}
	
	public boolean pointOver(double x, double y)
	{
		if (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight())
			return true;
		return false;
	}
	
	public String toString()
	{
		return "text: " + text + "\ncoordinates: ("+getX()+", "+getY()+")"
				+ "\ndimensions: " + getWidth() + "x" + getHeight() + "\nborderWidth: " + borderWidth
				+ "\nborderRound: " + borderRound + "\ntextSize: " + textSize + "\nfillColor: "
				+ fillColor + "\nborderColor: " + borderColor + "\ntextColor: " + textColor;
	}
	
}
