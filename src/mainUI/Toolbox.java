package mainUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Toolbox extends JPanel {

	private static final long serialVersionUID = 7454868855353471216L;

	private String[] toollist = { "select", "association line", "generalization line", "composition line", "class",
			"use case" };

	private GridBagLayout toolbox;
	private GridBagConstraints c;
	private ButtonGroup group = new ButtonGroup();
	private String state = "";
	private String mode = "";
	public Toolbox() {

		toolbox = new GridBagLayout();
		this.setBackground(Color.WHITE);
		this.setLayout(toolbox);
		CreateToolButton();

	}
	
	public String GetState() {
		return state;
	}

	private void CreateToolButton() {
		Font font = new Font("SansSerif", Font.BOLD, 20);
		for (int i = 0; i < toollist.length; i++) {
			JToggleButton btn = new JToggleButton();
			JTextField name = new JTextField(toollist[i]);
			try {
				String img_name = "/" + toollist[i] + ".png";
				Image img = new ImageIcon(this.getClass().getResource(img_name)).getImage();
				btn.setIcon(new ImageIcon(img));
				btn.setMargin(new Insets(0, 0, 0, 0));
				btn.setBackground(Color.WHITE);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						state = name.getText();
						SetMode();
					}
				});
				group.add(btn);
				c = new GridBagConstraints();
				c.gridx = 1;
				c.gridy = i;
				c.insets = new Insets(10, 10, 10, 10);
				this.add(btn, c);

			} catch (Exception e) {
				System.out.println(e);
			}
			name.setFont(font);
			name.setBorder(null);
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i;
			c.insets = new Insets(10, 10, 10, 10);
			this.add(name, c);

		}
	}
	
	private void SetMode() {
		switch(state) {
			case "select":
				mode = "select";
				break;
			case "association line":
			
			case "generalization line":
			case "composition line":
			case "class":
			case "use case":
				mode = "createObject";
				break;
			default:
				mode = "";
				
		}
	}
	
	public String GetMode() {
		return mode;
	}

}
