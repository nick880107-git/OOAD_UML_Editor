package mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Listener.CanvasHandler;

import UML.UMLobject;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 332761704430200019L;
	private ArrayList<UMLobject> UMLlist = new ArrayList<UMLobject>();

	public Canvas() {
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
	}
	
	public void SetHandler(CanvasHandler ch) {
		this.addMouseListener(ch);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Collections.sort(UMLlist,new UMLobjectSort());
		super.paintComponent(g2);
		for (UMLobject obj : UMLlist) {
			obj.draw(g2);
		}
	}

	public void SelectInit() {
		for (UMLobject obj : UMLlist) {
			obj.SetSelected(false);
		}
	}

	public ArrayList<UMLobject> GetUMLlist() {
		return UMLlist;
	}

	public void AddUMLobject(UMLobject obj) {
		UMLlist.add(obj);
	}

	public void DelUMLobject(UMLobject obj) {
		UMLlist.remove(obj);
	}
	
	private class UMLobjectSort implements Comparator<UMLobject>{

		@Override
		public int compare(UMLobject o1, UMLobject o2) {
			// TODO Auto-generated method stub
			return o1.GetDepth() - o2.GetDepth();
		}
		
	}
}
