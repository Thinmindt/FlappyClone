import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class Branch extends Sprite {
	static Image branch_up;
	static Image branch_down;

	Branch() throws IOException {
		this.x = 700;
		this.width = 82;
		this.height = 400;
		if (branch_up == null) {
			this.branch_up = ImageIO.read(new File("branchUp.png"));
		}
		if (branch_down == null) {
			this.branch_down = ImageIO.read(new File("branchDown.png"));
		}
		
		this.up = 1;
		this.y = -300;
		this.vert_vel = 0;
	}
	
	Branch(Branch other) {
		x = other.x;
		y = other.y;
		width = other.width;
		height = other.height;
		up = other.up;
		this.vert_vel = other.vert_vel;
	}
	
	Branch(Random rand) throws IOException {
		this.x = 700;
		this.width = 82;
		this.height = 400;
		this.branch_up = ImageIO.read(new File("branchUp.png"));
		this.branch_down = ImageIO.read(new File("branchDown.png"));
		
		
		this.up = rand.nextInt(2) + 1;
		
		if (up == 2) {
			this.y = rand.nextInt(200) - 400;
		}
		else if (up == 1) {
			this.y = rand.nextInt(300) + 200;
		}
	}

	boolean update() {
		this.x -= 10;
		if (this.x == -50) {
			return false;
		}
		
		this.y += vert_vel;
		return true;
	}
	
	void draw(Graphics g) {
		if (up == 1) {
				g.drawImage(this.branch_up, this.x, this.y, null);
			}
		else if (up == 2) { 
				g.drawImage(this.branch_down, this.x, this.y, null);
			}
	}
}
