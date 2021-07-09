package UML;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



public class Composite extends UMLobject {
	private ArrayList<UMLobject> complist;
	private double min_x, min_y, max_x, max_y;
	private double distance = 10;
	private Rectangle2D area;

	public Composite(ArrayList<UMLobject> objlist) {
		complist = objlist;
		type = "Composite";
		for(UMLobject obj:complist) {
			depth = Math.max(depth,obj.GetDepth());
		}
		SetArea();
	}

	@Override
	public void draw(Graphics2D g) {
		for (UMLobject obj : complist) {
			obj.draw(g);
		}
	}

	@Override
	public void SetSelected(boolean d) {
		isSelected = d;
		for (UMLobject obj : complist) {
			obj.SetSelected(d);
		}

	}

	public ArrayList<UMLobject> GetBasiclist() {
		return complist;
	}

	private void SetArea() {
		boolean isfirst = true;
		for (UMLobject obj : complist) {
			if (isfirst) {
				min_x = obj.x1;
				max_x = min_x + obj.x2;
				min_y = obj.y1;
				max_y = min_y + obj.y2;
				isfirst = false;
			} else {
				min_x = Math.min(min_x, obj.x1) - distance;
				min_y = Math.min(min_y, obj.y1) - distance;
				max_x = Math.max(max_x, obj.x1 + obj.x2) + distance;
				max_y = Math.max(max_y, obj.y1 + obj.y2) + distance;
			}
		}
		area = new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y);
		x1 = min_x;
		y1 = min_y;
		x2 = max_x - min_x;
		y2 = max_y - min_y;
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		for (UMLobject obj : complist) {
			obj.move(x, y);
		}
		SetArea();
	}

	@Override
	public boolean contains(double x, double y) {
		for (UMLobject obj : complist) {
			if (obj.contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Rectangle2D GetArea() {
		// TODO Auto-generated method stub
		return area;
	}

}
