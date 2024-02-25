package corpcooga.canvas;

import corpcooga.components.*;
import corpcooga.pages.*;

import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Toolkit;
import processing.core.PApplet;

public class DrawingSurface extends PApplet
{
//	Fields
	
	public static final int DRAWING_WIDTH = 1280, 
							DRAWING_HEIGHT = 800;
	
//	static {
//        // obtain screen dimensions dynamically
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        DRAWING_WIDTH = (int)screenSize.getWidth();
//        DRAWING_HEIGHT = (int)screenSize.getHeight();
//    }
	
	private PageManager pageManager;
	private Button goButton, backButton, nextButton;
	
	private double uMouseX, uMouseY;
	
	
//	Constructors
	
	public DrawingSurface()
	{
		GraphicsInfoReader infoManager = new GraphicsInfoReader();
		
		pageManager = new PageManager(infoManager.readPages(), infoManager.readTitlePages());
		
//		TODO add a button manager
//		TODO add more interactive buttons
		backButton = new Button("Back", 35, DRAWING_HEIGHT - 80, 100, 50, new Color(220, 20, 60), Color.black, Color.black);
		nextButton = new Button("Next", DRAWING_WIDTH - 135, DRAWING_HEIGHT - 80, 100, 50, new Color(30, 144, 255), Color.black, Color.black);
		goButton = new Button("Go!", DRAWING_WIDTH / 2 - 100, DRAWING_HEIGHT - 150, 200, 100, new Color(20, 240, 80), Color.black, Color.black);
	}
	
	
//	Methods
	
	public void settings()
	{
		size(DRAWING_WIDTH, DRAWING_HEIGHT);
//		TODO find a way to use these methods
//		fullScreen();
//		smooth(8);
	}
	
	public void setup()
	{
		textFont(createFont("resources/fonts/avenir.ttf", 69));
	}
	
	public void draw()
	{
		scale((float)width / DRAWING_WIDTH, (float)height / DRAWING_HEIGHT);
		
//		Color textColor = new GraphicsInfoReader().readSectionColors()[pageManager.getSection()];
//		fill(textColor.getRed() * 5.5f, textColor.getGreen() * 5.5f, textColor.getBlue() * 5.5f);
		pageManager.draw(this);
		
		if (pageManager.onTitlePage())
			goButton.draw(this);
		else {
			if (pageManager.getPage() >= 1)
				nextButton.draw(this);
			if (pageManager.getPage() >= 2)
				backButton.draw(this);
		}
	}
	
	public void mousePressed()
	{
		uMouseX = mouseX * DRAWING_WIDTH / width;
		uMouseY = mouseY * DRAWING_HEIGHT / height;
		
		if (pageManager.onTitlePage()) {
			if (goButton.pointOver(uMouseX, uMouseY))
				pageManager.changePage(1);
		} else {
			if (pageManager.getPage() >= 1 && nextButton.pointOver(uMouseX, uMouseY))
				pageManager.changePage(1);
			if (pageManager.getPage() >= 2 && backButton.pointOver(uMouseX, uMouseY)) {
				for (int x : pageManager.getTitlePages())
					if (pageManager.getPage() == x + 1) {
						pageManager.changePage(-1);
						break;
					}
				pageManager.changePage(-1);
			}
		}
	}

}
