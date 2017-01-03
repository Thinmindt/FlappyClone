import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.IOException;

public class Game extends JFrame implements ActionListener {
	Model model;
	Controller controller;

	public Game() throws Exception {
		this.model = new Model();
		this.controller = new Controller(this.model);
		View view = new View(this.model);
		view.addMouseListener(controller);
		this.setTitle("Bird Dodge 3000");
		this.setSize(500, 500);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		new Timer(50, this).start(); // Indirectly calls actionPerformed at regular intervals
	}

	public void actionPerformed(ActionEvent evt) {
		controller.update();
		
		try {
			this.model.update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		repaint(); // Indirectly calls View.paintComponent
	}

	public static void main(String[] args) throws Exception {
		new Game();
	}
}
