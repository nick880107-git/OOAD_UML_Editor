package UML;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public abstract class ConnectionLine{
	protected double x,y;
	protected int depth;
	protected double x2,y2;	
	protected BasicObject a,b;
	protected int portnum_a,portnum_b;
	protected double dy, dx, theta;
	protected double phi;
	protected int barb = 20;
	public ConnectionLine(BasicObject a, BasicObject b, double pre_x, double pre_y, double x, double y) {
		this.a = a;
		this.b = b;
		SetDepth();
		SetPort(pre_x, pre_y, x, y);	
				
	}
	
	public void SetDepth() {
		int max_depth = Math.max(a.GetDepth(), b.GetDepth());
		depth = max_depth;
	}
	
	public void SetPort(double pre_x, double pre_y, double x, double y) {
		Point2D pos_a = new Point2D.Double(pre_x,pre_y);
		Point2D pos_b = new Point2D.Double(x,y);
		double minL = 99999;
		double minR = 99999;
		for(int i = 0 ; i < 4 ; i++) {
			
			if(pos_a.distance(a.point[i].getCenterX(), a.point[i].getCenterY()) < minL) {
				minL = pos_a.distance(a.point[i].getCenterX(), a.point[i].getCenterY());
				this.x = a.point[i].getCenterX();
				this.y = a.point[i].getCenterY();
				portnum_a = i;
			}
			
			if(pos_b.distance(b.point[i].getCenterX(), b.point[i].getCenterY()) < minR) {
				minR = pos_b.distance(b.point[i].getCenterX(), b.point[i].getCenterY());
				x2 = b.point[i].getCenterX();
				y2 = b.point[i].getCenterY();
				portnum_b = i;
			}
			
		}
		
		
		dy = y2 - this.y;
		dx = x2 - this.x;
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
	
	public void Move() {
		Point2D pos_a = a.GetPoint(portnum_a);
		Point2D pos_b = b.GetPoint(portnum_b);
		x = pos_a.getX();
		y = pos_a.getY();
		x2 = pos_b.getX();
		y2 = pos_b.getY();
		dy = y2 - y;
		dx = x2 - x;
		theta = Math.atan2(dy, dx);
	}
	
	public abstract void draw(Graphics2D g); 
}
