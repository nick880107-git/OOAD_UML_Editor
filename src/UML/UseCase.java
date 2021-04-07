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
		super(x, y, depth);
		SetName("New UseCase");
		Sethw(h,w);
		SetArea();
		SetPoint();
	}

	public void centerString(Graphics2D g, String s, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		float x = (float) (this.x + (this.w - metrics.stringWidth(s)) / 2);
		float y = (float) (this.y + ((this.h - metrics.getHeight()) / 2) + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y);

	}

	@Override
	public void draw(Graphics2D g) {
		Ellipse2D oval = new Ellipse2D.Double(x, y, w, h);
		g.setColor(Color.YELLOW);
		g.fill(oval);
		g.setColor(Color.BLACK);
		g.draw(oval);
		centerString(g, name, font);

	}

	public boolean contains(double x, double y) {
		Ellipse2D oval = new Ellipse2D.Double(this.x, this.y,this.w, this.h);
		return oval.contains(x, y);
	}

}
