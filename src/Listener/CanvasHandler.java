package Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Factory.ObjectFactory;
import UML.UMLobject;
import mainUI.Canvas;
import mainUI.Toolbox;

public class CanvasHandler implements MouseListener {

	Toolbox toolbox;
	Canvas canvas;
	ObjectFactory of = new ObjectFactory(this);
	private double x1, y1, x2, y2;
	private int depth;
	private UMLobject temp1;
	private UMLobject temp2;

	public CanvasHandler(Canvas canvas, Toolbox toolbox) {
		this.canvas = canvas;
		this.toolbox = toolbox;
	}

	public double getX1() {
		return x1;
	}

	public double getX2() {
		return x2;
	}

	public double getY1() {
		return y1;
	}

	public double getY2() {
		return y2;
	}

	public int getDepth() {
		return depth;
	}

	public UMLobject getObj1() {
		return temp1;
	}

	public UMLobject getObj2() {
		return temp2;
	}

	public ArrayList<UMLobject> getSelectionList() {
		ArrayList<UMLobject> objlist = new ArrayList<UMLobject>();
		for (UMLobject obj : canvas.GetUMLlist()) {
			if (obj.GetSelected()) {
				objlist.add(obj);
			}
		}
		return objlist;
	}
	
	public void removeDuplicate(ArrayList<UMLobject> objlist) {
		for(UMLobject obj:objlist) {
			canvas.DelUMLobject(obj);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		UMLobject obj = null;
		switch (toolbox.GetMode()) {
		case "select":
			obj = getSelectObject(x1, y1);
			if (obj != null) {
				obj.SetSelected(true);
			}
			break;
		case "createObject":
			obj = of.factory(toolbox.GetState());
			canvas.AddUMLobject(obj);
			depth++;
			break;
		}
		canvas.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		UMLobject obj = getSelectObject(x1, y1);
		if (obj != null) {
			temp1 = obj;
		} else {
			temp1 = null;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		canvas.SelectInit();
		switch (toolbox.GetMode()) {
		case "select":
			if (temp1 == null) {
				Rectangle2D area = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1),
						Math.abs(y2 - y1));
				for (UMLobject obj : canvas.GetUMLlist()) {
					if (obj.GetArea() != null && area.contains(obj.GetArea())) {
						obj.SetSelected(true);
					}
				}
			} else {
				temp1.move(x2 - x1, y2 - y1);
			}
			break;
		case "createObject":
			UMLobject obj = getSelectObject(x2, y2);

			if (obj != null && obj.GetType() == "BasicObject") {
				temp2 = obj;
			}

			if (temp2 != null && temp1 != null && temp2 != temp1) {
				UMLobject obj1 = of.factory(toolbox.GetState());
				canvas.AddUMLobject(obj1);
				depth++;
				break;
			}
		}
		temp1 = null;
		temp2 = null;
		canvas.repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private UMLobject getSelectObject(double x, double y) {
		UMLobject obj = null;
		canvas.SelectInit();
		for (UMLobject target : canvas.GetUMLlist()) {
			if (target.contains(x, y)) {
				obj = target;
			}
		}
		return obj;
	}

}
