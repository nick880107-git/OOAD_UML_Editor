package UML;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Composite{
	private ArrayList<BasicObject> basiclist;
	private double min_x, min_y;
	private double max_x, max_y;
	private double distance = 10;
	private Rectangle2D area;
	private boolean isSelected;
	private int depth;

	public Composite(ArrayList<BasicObject> objlist, int depth) {
		basiclist = objlist;
		for(BasicObject obj:basiclist) {
			obj.AddCompositenum();
		}
		this.depth = depth;
		SetArea();
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		float[] dashingPattern1 = { 2f, 2f };
		Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
		g.setStroke(stroke);
		g.draw(area);
	}

	public void SetBasicSelect(boolean b) {
		for (BasicObject obj : basiclist) {
			obj.isSelected = b;
		}
	}
	
	public boolean GetSelected() {
		return isSelected;
	}
	
	public void SetSelected(boolean d) {
		isSelected = d;
	}

	public void deleteProcedure() {
		for (BasicObject obj : basiclist) {
			obj.MinusCompositenum();
		}
	}
	
	public void Move(double x,double y) {
		for(BasicObject obj : basiclist) {
			obj.Move(x, y);
		}
		SetArea();
	}
	
	public int GetDepth() {
		return depth;
	}
	
	public ArrayList<BasicObject> GetBasiclist(){
		return basiclist;
	}
	
	private void SetArea() {
		boolean isfirst = true;
		for (BasicObject obj : basiclist) {
			
			

			if (isfirst) {
				min_x = obj.x;
				max_x = obj.x + obj.w;
				min_y = obj.y;
				max_y = obj.y + obj.h;
				isfirst = false;
			} else {
				min_x = Math.min(min_x, obj.x) - distance;
				min_y = Math.min(min_y, obj.y) - distance;
				max_x = Math.max(max_x, obj.x + obj.w) + distance;
				max_y = Math.max(max_y, obj.y + obj.h) + distance;
			}
		}
		area = new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y);
		
	}
	
	

}
