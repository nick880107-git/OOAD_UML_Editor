package UML;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public abstract class ConnectionLine extends UMLobject{

	protected BasicObject a,b;
	protected Port port_a,port_b;
	protected double dy, dx, theta;
	protected double phi;
	protected int barb = 20;

	
	public void SetPort(double pre_x, double pre_y, double x, double y) {
		Point2D pos_b = new Point2D.Double(pre_x,pre_y);
		Point2D pos_a = new Point2D.Double(x,y);
		double minL = 99999;
		double minR = 99999;
		for(int i = 0 ; i < 4 ; i++) {
			
			if(pos_a.distance(a.port[i].x2, a.port[i].y2) < minL) {
				minL = pos_a.distance(a.port[i].x2, a.port[i].y2);
				x1 = a.port[i].x2;
				y1 = a.port[i].y2;
				port_a = a.port[i];
			}
			
			if(pos_b.distance(b.port[i].x2, b.port[i].y2) < minR) {
				minR = pos_b.distance(b.port[i].x2, b.port[i].y2);
				x2 = b.port[i].x2;
				y2 = b.port[i].y2;
				port_b = b.port[i];
			}
			
		}
		
		
		dy = y2 - y1;
		dx = x2 - x1;
		theta = Math.atan2(dy, dx);
	}
	
	public boolean contains(BasicObject obj) {
		if(obj == a || obj == b) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void move(double x, double y) {

	}
	
	public void move() {

		x1 = port_a.x2;
		y1 = port_a.y2;
		x2 = port_b.x2;
		y2 = port_b.y2;
		dy = y2 - y1;
		dx = x2 - x1;
		theta = Math.atan2(dy, dx);
	}
	
	@Override
	public boolean contains(double x, double y) {
		return false;
	}
	
	@Override
	public Rectangle2D GetArea() {
		return null;
	}
	

	public abstract void draw(Graphics2D g); 
}
