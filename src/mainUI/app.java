package mainUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;



public class app {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("UML Editor");
		frame.setMinimumSize(new Dimension(1200,1000));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Toolbox toolbox = new Toolbox();
		frame.getContentPane().add(toolbox, BorderLayout.WEST);
		Canvas canvas = new Canvas(toolbox);
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		MenuBar menu = new MenuBar(toolbox,canvas);
		frame.getContentPane().add(menu, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}


