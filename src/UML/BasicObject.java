package UML;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class BasicObject{
	protected Rectangle2D[] point = new Rectangle2D[4];
	protected Rectangle2D area;	
	protected Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	protected double x,y;
	protected double h,w;
	protected int depth;
	protected String name;
	protected boolean isSelected = false;
	protected int inComposite = 0;  //表這個物件在多少composite裡面
	
	public BasicObject(double x, double y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;		
	}
	
	public void SetPoint() {	
		point[0] = new Rectangle2D.Double(x+w/2-3, y-3,6,6);
		point[1] = new Rectangle2D.Double(x+w-3,y+h/2-3,6,6);
		point[2] = new Rectangle2D.Double(x+w/2-3,y+h-3,6,6);
		point[3] = new Rectangle2D.Double(x-3,y+h/2-3,6,6);
		
	}
	
	public void Move(double x,double y) {
		this.x = this.x + x;
		this.y = this.y + y;
		SetArea();
		SetPoint();
	}
	
	public void drawPoint(Graphics2D g) {
		for(int i = 0;i<4;i++) {
			g.setColor(Color.BLACK);
			g.fill(point[i]);
		}
	}
	
	public void SetName(String s) {
		name = s;
	}

	protected void Sethw(double h,double w) {
		this.h = h;
		this.w = w;
	}
	
	public Rectangle2D GetArea() {
		return area;
	}
	
	public boolean GetSelected() {
		return isSelected;
	}
	
	public void SetSelected(boolean r) {
		isSelected = r;
	}
	
	public int GetCompositenum() {
		return inComposite;
	}
	
	public void AddCompositenum() {
		inComposite++;
	}
	
	public void MinusCompositenum() {
		inComposite--;
	}
	
	public int GetDepth() {
		return depth;
	}
	
	public void SetDepth(int d) {
		depth = d;
	}
	
	protected void SetArea() {
		Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
		area = rectangle;
	};
	
	public abstract boolean contains(double x, double y);
	public abstract void draw(Graphics2D g);
}
