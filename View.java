import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class View extends JPanel {
	Model model;

	View(Model m) throws IOException {
		this.model = m;
	}

	public void paintComponent(Graphics g) {
		
		// Draw all sprites
		Iterator<Sprite> it = model.sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			
			s.draw(g);
		}
			
	}
}
