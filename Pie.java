import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class Pie extends Sprite {
	
	static Image pie;
	LinkedList<Sprite> sprites;
	
	Pie(int xBirdPos, int yBirdPos, double birdVert_vel, LinkedList s) throws IOException {
		if (this.pie == null) {
			this.pie = ImageIO.read(new File("pie.png"));
		}  
		this.x = xBirdPos;
		this.y = yBirdPos;
		this.vert_vel = birdVert_vel - 2;
		this.up = 0;
		this.sprites = s;
		this.width = 40;
		this.height = 28;
	}
	
	Pie(Pie other, LinkedList<Sprite> otherS) {
		sprites = otherS;
		x = other.x;
		y = other.y;
		vert_vel = other.vert_vel;
		up = other.up;
		width = other.width;
		height = other.height;
	}
	
	boolean update() {
		vert_vel += .25;
		y += vert_vel;
		this.x += 5;
		
		if (collision(sprites)) {
			return false;
		}
		
		return true;
	}
	
	void draw(Graphics g) {
		g.drawImage(this.pie, this.x, this.y, null);
	}
}