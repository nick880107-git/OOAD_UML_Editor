package UML;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



public abstract class UMLobject {
	protected double x1,y1,x2,y2;
	protected boolean isSelected = false;
	protected int depth;
	protected String type;
	public abstract void move(double x,double y);
	public abstract void draw(Graphics2D g);
	public abstract boolean contains(double x, double y);
	public abstract Rectangle2D GetArea();
	public boolean GetSelected() {
		return isSelected;
	}
	public int GetDepth() {
		return depth;
	}
	public void SetSelected(boolean d) {
		isSelected = d;
	}
	public String GetType() {
		return type;
	}
}
