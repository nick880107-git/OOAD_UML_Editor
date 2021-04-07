package mainUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import UML.BasicObject;
import UML.Composite;
import UML.CompositeComparator;

public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1225151121953385022L;

	public MenuBar(Toolbox toolbox, Canvas canvas) {
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenuItem group = new JMenuItem("Group");
		JMenuItem ungroup = new JMenuItem("UnGroup");
		JMenuItem editname = new JMenuItem("Change object name");
		group.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<BasicObject> objlist = new ArrayList<>();
				for (BasicObject obj : canvas.GetBasiclist()) {
					if (obj.GetSelected()) {
						objlist.add(obj);
					}
				}
				if (objlist.size() > 1) {
					Composite comp = new Composite(objlist,canvas.GetDepth());
					comp.SetSelected(true);
					canvas.SelectInit();
					canvas.AddCompsite(comp);
					canvas.repaint();
					System.out.println("group, composite num = "+canvas.GetComplist().size());
				}

			}
		});

		ungroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Composite target = null;
				int checknum = 0;
				Collections.sort(canvas.GetComplist(), new CompositeComparator());
				for (Composite comp : canvas.GetComplist()) {
					if (comp.GetSelected()) {
						checknum++;
					}
					if (target == null) {
						target = comp;
					}
				}
				if (checknum == 1 && target != null) {
					target.deleteProcedure();
					canvas.DeleteComposite(target);
					canvas.repaint();
					System.out.println("ungroup, composite num = "+canvas.GetComplist().size());

				}
				

			}
		});
		editname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicObject target = null;
				int checknum = 0;
				for (BasicObject obj : canvas.GetBasiclist()) {
					if (obj.GetSelected()) {
						checknum++;
						if (target == null) {
							target = obj;
						}
					}
				}
				if (checknum == 1 && target != null) {

					Textapp textapp = new Textapp(target, canvas);
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

}
