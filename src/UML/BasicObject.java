package UML;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class BasicObject extends UMLobject {

	protected Port[] port = new Port[4];
	protected Rectangle2D area;
	protected Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	protected String name;

	public void SetPort() {
		port[0] = new Port(x1 + x2 / 2, y1);
		port[1] = new Port(x1 + x2, y1 + y2 / 2);
		port[2] = new Port(x1 + x2 / 2, y1 + y2);
		port[3] = new Port(x1, y1 + y2 / 2);
	}
	

	@Override
	public void move(double x, double y) {
		x1 = x1 + x;
		y1 = y1 + y;
		SetArea();
		for(Port p:port) {
			p.move(x, y);
		}
	}

	public void SetName(String s) {
		name = s;
	}

	protected void Initialize(double x, double y, double h, double w, int depth) {
		x1 = x;
		y1 = y;
		y2 = h;
		x2 = w;
		this.depth = depth;
		SetArea();

		SetPort();
	}

	public Rectangle2D GetArea() {
		return area;
	}

	protected void SetArea() {
		Rectangle2D rectangle = new Rectangle2D.Double(x1, y1, x2, y2);
		area = rectangle;
	}

	public abstract boolean contains(double x, double y);

	public abstract void draw(Graphics2D g);

	public void drawPoint(Graphics2D g) {
		for (Port p : port) {
			p.draw(g);
		}
	}

}
