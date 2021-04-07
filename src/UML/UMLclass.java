package UML;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class UMLclass extends BasicObject {

	double h = 180;
	double w = 180;

	public UMLclass(double x, double y, int depth) {
		super(x, y, depth);
		SetName("New Class");
		Sethw(h,w);
		SetArea();
		SetPoint();
		
	}

	public void centerString(Graphics2D g, String s, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		float x = (float) (this.x + (this.w - metrics.stringWidth(s)) / 2);
		float y = (float) (this.y + ((this.h / 3 - metrics.getHeight()) / 2) + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y);

	}

	@Override
	public void draw(Graphics2D g) {
		Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h / 3);
		Rectangle2D rectangle1 = new Rectangle2D.Double(x, y + h / 3, w, h / 3);
		Rectangle2D rectangle2 = new Rectangle2D.Double(x, y + h / 3 * 2, w, h / 3);
		rectangle.add(rectangle1);
		rectangle.add(rectangle2);
		g.setColor(Color.ORANGE);
		g.fill(rectangle);
		g.setColor(Color.BLACK);
		g.draw(rectangle);
		g.draw(rectangle1);
		g.draw(rectangle2);		
		centerString(g, name, font);
	}
	

	public boolean contains(double x, double y) {
		Rectangle2D rectangle = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
		return rectangle.contains(x, y);
	}
}
