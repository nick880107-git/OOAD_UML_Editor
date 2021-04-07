package mainUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import UML.BasicObject;

public class Textapp {
	JFrame frame;
	JTextField textArea = new JTextField(100);
	JButton submit = new JButton("Submit");
	JButton cancel = new JButton("cancel");
	JLabel caution = new JLabel();
	String inputname = "";

	public Textapp(BasicObject obj, Canvas canvas) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(new Dimension(400, 100));
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.add(textArea);
		frame.add(submit);
		frame.add(cancel);
		frame.add(caution);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				inputname = textArea.getText();
				if (inputname.strip().length() != 0) {
					obj.SetName(inputname);
					canvas.repaint();
					frame.dispose();
				} else {
					caution.setText("Invalid name");
					caution.setForeground(Color.RED);
					caution.setVisible(true);
				}

			}

		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}

		});
		frame.setVisible(true);
		caution.setVisible(false);
	}
}
