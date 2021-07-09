package UML;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



public class Port extends UMLobject {

	private int radius = 6;

	public Port(double x, double y) {
		x1 = x - radius / 2;
		y1 = y - radius / 2;
		x2 = x;
		y2 = y;
		type = "port";
		
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		x1 = x1 + x;
		y1 = y1 + y;
		x2 = x2 + x;
		y2 = y2 + y;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		Rectangle2D point = new Rectangle2D.Double(x1, y1, radius, radius);
		g.setColor(Color.BLACK);
		g.fill(point);

	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rectangle2D GetArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
