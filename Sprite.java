
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Iterator;

abstract class Sprite {


	int up;

	int x;
	int y;
	double vert_vel;

	int width;
	int height;
	
	Sprite() {
	
	}
	
	abstract boolean update();

	abstract void draw(Graphics g);
	
	boolean collision(LinkedList<Sprite> sprites) {
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite s = it.next();
			if (s.up == 1) {
				if (this.x + this.width > s.x && this.x < s.x + 
					s.width && this.y + this.height > s.y) {
					if (isPie()) {
						s.vert_vel = 3;
					}
					return true;
				}
			}
		
			else if (s.up == 2) {
				if (this.x + this.width > s.x && this.x < s.x + 
				    s.width && this.y < s.y + s.height) {
					if (isPie()) {
						s.vert_vel = -3;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	boolean isPie() {
		if (this instanceof Pie) {
			return true;
		}
		return false;
	}
	
	boolean isBird() {
		if (this instanceof Bird) {
			return true;
		}
		return false;	
	}
}