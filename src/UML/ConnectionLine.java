package UML;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public abstract class ConnectionLine{
	protected double x,y;
	protected int depth;
	protected double x2,y2;	
	protected BasicObject a,b;
	
	protected double dy, dx, theta;
	protected double phi;
	protected int barb = 20;
	public ConnectionLine(BasicObject a, BasicObject b) {
		this.a = a;
		this.b = b;
		SetDepth();
		SetPort();		
		
				
	}
	
	public void SetDepth() {
		int max_depth = Math.max(a.GetDepth(), b.GetDepth());
		depth = max_depth;
	}
	
	public void SetPort() {
		double mid_x = (a.x + b.x + b.w) / 2;
		double mid_y = (a.y + b.y + b.h) / 2;
		Point2D midpoint = new Point2D.Double(mid_x,mid_y);

		double minL = 99999 , minR = 99999;
		for(int i = 0 ; i < 4 ; i++) {
			if(midpoint.distance(a.point[i].getCenterX(), a.point[i].getCenterY()) < minL) {
				minL = midpoint.distance(a.point[i].getCenterX(), a.point[i].getCenterY());
				x = a.point[i].getCenterX();
				y = a.point[i].getCenterY();
			}
			if(midpoint.distance(b.point[i].getCenterX(), b.point[i].getCenterY()) < minR) {
				minR = midpoint.distance(b.point[i].getCenterX(), b.point[i].getCenterY());
				x2 = b.point[i].getCenterX();
				y2 = b.point[i].getCenterY();
			}
		}
		dy = y2 - y;
		dx = x2 - x;
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
	
	public abstract void draw(Graphics2D g); 
}
