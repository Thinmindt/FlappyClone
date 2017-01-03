import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class Bird extends Sprite {
	LinkedList<Sprite> sprites;
	
	int wing_counter;
	boolean count;
	boolean splat;
	
	static Image bird_flapping;
	static Image bird_falling;
	static Image feathers;
	
	Bird(LinkedList<Sprite> s) throws IOException { 
		this.up = 0;
		this.vert_vel = 0.0;
		this.x = -40;
		this.y = 0;
		this.wing_counter = 5;
		this.count = false;
		this.sprites = s;
		
		if (this.bird_flapping == null) {
			this.bird_flapping = ImageIO.read(new File("birdFlapping.png"));
		}  
		if (this.bird_falling == null) {
			this.bird_falling = ImageIO.read(new File("birdFalling.png"));
		}
		if (this.feathers == null) {
			this.feathers = ImageIO.read(new File("feathers.png"));
		}
		
		this.width = 64;
		this.height = 57;
		
		this.splat = false;
	}
	
	Bird(Bird other, LinkedList<Sprite> otherS) {
		up = other.up;
		vert_vel = other.vert_vel;
		x = other.x;
		y = other.y;
		wing_counter = other.wing_counter;
		count = other.count;
		sprites = otherS;
		width = other.width;
		height = other.height;
		splat = other.splat;
	}

	public boolean update() {
		//move from left to standard position
		if (this.x < 150) {
			this.x += 8;
		}

		//vertical movement
		this.vert_vel += .15;
		this.y += vert_vel;

		//wing movement counter
		if (this.count == true) {
			this.wing_counter -= 1;
			if (this.wing_counter <= 0) {
				this.count = false;
			}
		}
		
		if (collision(sprites)) {
			this.splat = true;
		}	
			
		if (this.splat == true) {
			this.x += 2;
			if (this.y > (500 - this.height)) {
				double bounce = vert_vel + (vert_vel / 2);
				vert_vel -= bounce;
				y = 500 - this.height;
			 }
		}
		
		if (this.x >= 500) {
			System.exit(x);
		}
		return true;
	}
	
	void flap(){
		if (!this.splat) {
			this.vert_vel = -5;
			this.count = true;
			this.wing_counter = 5;
		}
	}
	
	void throw_pie() {
		if (!this.splat) {
			try {
				sprites.add(new Pie(this.x, this.y, this.vert_vel, this.sprites));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void draw(Graphics g) {
		if (this.splat == true) {
			g.drawImage(this.feathers, this.x, this.y, null);
		}
		else {
			if (this.count == false) {
				g.drawImage(this.bird_falling, this.x, this.y, null);
			}
			else if (this.count == true) {
				g.drawImage(this.bird_flapping, this.x, this.y, null);
			}
		}
	}
}
