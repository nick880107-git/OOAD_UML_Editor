package mainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Factory.ObjectFactory;
import UML.BasicObject;
import UML.Composite;
import UML.UMLobject;

public class MenuBar extends JMenuBar {
	Toolbox toolbox;
	Canvas canvas;
	private static final long serialVersionUID = 1225151121953385022L;

	public MenuBar(Canvas canvas, ObjectFactory of) {
		this.canvas = canvas;
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenuItem group = new JMenuItem("Group");
		JMenuItem ungroup = new JMenuItem("UnGroup");
		JMenuItem editname = new JMenuItem("Change object name");
		group.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UMLobject obj = of.factory("composite");
				obj.SetSelected(true);
				canvas.SelectInit();
				canvas.AddUMLobject(obj);
				canvas.repaint();
				
				
			}
		});

		ungroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Composite target = null;
				int checknum = 0;
				for (UMLobject obj : canvas.GetUMLlist()) {
					if(obj.GetType() == "Composite" && obj.GetSelected()) {
						target = (Composite)obj;
						checknum++;
					}
				}
				if(checknum == 1) {
					DeleteComposite(target);
				}

			}
		});
		editname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UMLobject target = null;
				int checknum = 0;
				for (UMLobject obj : canvas.GetUMLlist()) {
					if (obj.GetSelected()) {
						checknum++;
						if (target == null) {
							target = obj;
						}
					}
				}
				if (checknum == 1 && target.GetType() == "BasicObject") {

					Textapp textapp = new Textapp((BasicObject)target, canvas);
					textapp.frame.setLocationRelativeTo(null);
				}
			}
		});

		edit.add(group);
		edit.add(ungroup);
		edit.add(editname);
		this.add(file);
		this.add(edit);

	}	
	
	private void DeleteComposite(Composite comp) {
		ArrayList<UMLobject> objlist = comp.GetBasiclist();
		for(UMLobject obj:objlist) {
			canvas.AddUMLobject(obj);
		}
		canvas.DelUMLobject(comp);
		canvas.repaint();
	}

}
