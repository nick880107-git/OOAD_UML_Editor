package mainUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import UML.AssociationLine;
import UML.BasicObject;
import UML.Composite;
import UML.CompositeComparator;
import UML.CompositionLine;
import UML.ConnectionLine;
import UML.GeneralizationLine;
import UML.UMLclass;
import UML.UseCase;

public class Canvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 332761704430200019L;
	private Toolbox toolbox;
	private int depth;
	private double x, y;
	private double pre_x, pre_y;
	private ArrayList<BasicObject> Basiclist = new ArrayList<BasicObject>();
	private ArrayList<ConnectionLine> Linelist = new ArrayList<ConnectionLine>();
	private ArrayList<Composite> Complist = new ArrayList<Composite>();

	public Canvas(Toolbox box) {
		toolbox = box;
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.addMouseListener(new MouseEventHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		for (BasicObject obj : Basiclist) {
			obj.draw(g2);
			if (obj.GetSelected()) {
				obj.drawPoint(g2);
			}
		}
		for (ConnectionLine line : Linelist) {
			line.draw(g2);
		}
		for (Composite comp : Complist) {
			if (comp.GetSelected()) {
				comp.draw(g2);
			}
		}
	}
	
	public void SelectInit() {
		for (BasicObject obj : Basiclist) {
			obj.SetSelected(false);
		}
		for (Composite obj : Complist) {
			obj.SetSelected(false);
		}
	}
	
	public ArrayList<BasicObject> GetBasiclist() {
		return Basiclist;
	}
	
	public ArrayList<ConnectionLine> GetLinelist() {
		return Linelist;
	}
	
	public ArrayList<Composite> GetComplist() {
		return Complist;
	}
	
	public void AddBasicObject(BasicObject obj) {
		Basiclist.add(obj);
	}
	
	public void AddCompsite(Composite obj) {
		Complist.add(obj);
	}
	
	public void AddConnectionLine(ConnectionLine obj) {
		Linelist.add(obj);
	}
	
	public void DeleteComposite(Composite obj) {
		Complist.remove(obj);
	}

	private class MouseEventHandler implements MouseListener {
		
		BasicObject target = null;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String state = toolbox.GetState();
			if (state.equals("use case") || state.equals("class")) {
				BasicObject obj = null;
				switch (state) {
				case "use case":
					obj = new UseCase(x, y, depth);
					break;
				case "class":
					obj = new UMLclass(x, y, depth);
					break;
				}
				if (obj != null) {
					Basiclist.add(obj);
					depth++;
				}

			} else if (state.equals("select")) {
				boolean isfirst = true;

				SelectInit();
				for (BasicObject obj : Basiclist) {
					if (obj.contains(x, y)) {
						if (isfirst) {
							target = obj;
							isfirst = false;
						} else if (obj.GetDepth() > target.GetDepth()) {
							target = obj;
						}
					}
				}
				if (target != null) {
					target.SetSelected(true);
					if (target.GetCompositenum() > 0) {
						boolean isfirst_c = true;
						Composite target_c = null;
						for (Composite composite : Complist) {
							if (composite.GetBasiclist().contains(target)) {
								if (isfirst_c) {
									target_c = composite;
									isfirst_c = false;
								} else if (composite.GetDepth() > target_c.GetDepth()) {
									target_c = composite;
								}
							}
						}
						if (target_c != null) {
							target_c.SetSelected(true);
						}
					}
				}
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			pre_x = e.getX();
			pre_y = e.getY();
			boolean findtarget = false;
			for (BasicObject obj : Basiclist) {
				if (obj.contains(pre_x, pre_y)) {
					target = obj;
					findtarget = true;

				}
			}
			if (!findtarget) {
				target = null;

			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			SelectInit();
			String state = toolbox.GetState();
			if (state.equals("select")) {
				// °é¿ï¼Ò¦¡
				if (target == null) {
					double x1, y1;
					x1 = Math.min(x, pre_x);
					y1 = Math.min(y, pre_y);
					Rectangle2D area = new Rectangle2D.Double(x1, y1, Math.abs(pre_x - x), Math.abs(pre_y - y));

					for (BasicObject obj : Basiclist) {

						if (area.contains(obj.GetArea())) {
							obj.SetSelected(true);
						}
					}
					ArrayList<Composite> comp_to_select = new ArrayList<Composite>();
					Collections.sort(Complist, new CompositeComparator());
					for (Composite comp : Complist) {

						boolean hasSelect = true;
						for (BasicObject obj : comp.GetBasiclist()) {
							if (!obj.GetSelected()) {
								hasSelect = false;
								break;
							}
						}
						if (hasSelect) {
							comp_to_select.add(comp);
						} else {
							comp.SetBasicSelect(false);
						}
					}

					Collections.sort(comp_to_select, new CompositeComparator());
					for (Composite comp : comp_to_select) {
						comp.SetBasicSelect(false);
					}

					for (Composite comp : comp_to_select) {
						boolean hasSelect = false;
						for (BasicObject obj : comp.GetBasiclist()) {
							if (obj.GetSelected()) {
								hasSelect = true;
								break;
							}
						}
						if (!hasSelect) {
							comp.SetSelected(true);
							comp.SetBasicSelect(true);
						}
					}
				}

				// ©ì¦²¼Ò¦¡
				else {
					double distance_x, distance_y;
					distance_x = x - pre_x;
					distance_y = y - pre_y;
					if (target.GetCompositenum() > 0) {
						Collections.sort(Complist, new CompositeComparator());
						Composite target_c = null;
						for (Composite comp : Complist) {
							if (comp.GetBasiclist().contains(target)) {
								target_c = comp;
								break;
							}
						}
						if (target_c != null) {
							target_c.Move(distance_x, distance_y);
							for (ConnectionLine line : Linelist) {
								for (BasicObject obj : target_c.GetBasiclist()) {
									if (line.contains(obj)) {
										line.SetPort();
									}
								}
							}
						}
					} else {
						target.Move(distance_x, distance_y);
						for (ConnectionLine line : Linelist) {
							if (line.contains(target)) {
								line.SetPort();
							}
						}
					}

				}

			} else {
				BasicObject a = null, b = null;
				for (BasicObject obj : Basiclist) {
					if (a == null && obj.contains(pre_x, pre_y)) {
						a = obj;
					}
					if (b == null && obj.contains(x, y)) {
						b = obj;
					}
				}
				if (a != null && b != null) {
					ConnectionLine line = null;
					switch (state) {
					case "association line":
						line = new AssociationLine(a, b);
						break;
					case "generalization line":
						line = new GeneralizationLine(a, b);
						break;
					case "composition line":
						line = new CompositionLine(a, b);
						break;

					}
					if (line != null) {
						Linelist.add(line);
					}
				}

			}

			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class MouseMotionHandler implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
		}

	}
	
	public int GetDepth() {
		return depth++;
	}
	

	

}
