import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Iterator;

class Controller implements MouseListener
{
	Model model;

	Controller(Model m) {
		this.model = m;
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			model.bird.flap();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			model.bird.throw_pie();
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	
	public void update() {
		int a = model.evaluateAction(0, 0);
		int b = model.evaluateAction(1, 0);
		int c = model.evaluateAction(2, 0);
		int d = model.evaluateAction(3, 0);
		int choice = model.greatest(a, b, c, d);
		if (a == choice) {
		}
		else if (b == choice) {
			this.model.bird.flap();
		}
		else if (c == choice) {
			this.model.bird.throw_pie();
		}
		else if (d == choice) {
			this.model.bird.flap();
			this.model.bird.throw_pie();
		}
		
	}
}
