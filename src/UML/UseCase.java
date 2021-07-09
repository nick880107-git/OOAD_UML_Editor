package UML;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;



public class UseCase extends BasicObject {

	double h = 120;
	double w = 180;
	
	public UseCase(double x, double y, int depth) {
		Initialize(x,y,h,w,depth);
		type = "BasicObject";
		SetName("New UseCase");
		
	}

	public void centerString(Graphics2D g, String s, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		float x = (float) (x1 + (x2- metrics.stringWidth(s)) / 2);
		float y = (float) (y1 + ((y2 - metrics.getHeight()) / 2) + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y);

	}

	@Override
	public void draw(Graphics2D g) {
		Ellipse2D oval = new Ellipse2D.Double(x1, y1, x2, y2);
		g.setColor(Color.YELLOW);
		g.fill(oval);
		g.setColor(Color.BLACK);
		g.draw(oval);
		centerString(g, name, font);
		
		if(GetSelected()) {
			drawPoint(g);
		}
				
	}

	public boolean contains(double x, double y) {
		Ellipse2D oval = new Ellipse2D.Double(x1, y1,x2, y2);
		return oval.contains(x, y);
	}
}
