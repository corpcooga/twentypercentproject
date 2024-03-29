package corpcooga.components;

import java.awt.Color;
import processing.core.PApplet;

public class Text extends GraphicsElement
{
//	Fields
	
	private String text;
	private int textSize, textAlign;
	private Color textColor;
	
	
//	Constructors
	
	public Text(String text, Color textColor, int... settings)
	{
		super(settings[0], settings[1]);
		
		this.text = text;
		this.textColor = textColor;
		
		if (settings.length == 6) {
//			6 specified settings
			setWidth(settings[2]);
			setHeight(settings[3]);
			textSize = settings[4];
			textAlign = settings[5];
		} else {
//			4 specified settings
			setWidth(0);
			setHeight(0);
			textSize = settings[2];
			textAlign = settings[3];
		}
	}
	
	public Text()
	{
		text = "";
	}
	
	
//	Methods
	
	public void draw(PApplet p)
	{
		p.push();
		p.textSize(textSize);
		p.textAlign(textAlign);
		p.fill(textColor.getRGB());
		
		if (getWidth() == 0 && getHeight() == 0)
//			unbounded text
			p.text(text, getX(), getY());
		else
//			bounded text
			p.text(text, getX(), getY(), getWidth(), getHeight());
		
		p.pop();
	}
	
	public void setTextColor(Color c)
	{
		textColor = c;
	}
	
}
