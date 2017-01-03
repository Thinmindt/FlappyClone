import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;

class Model
{
	LinkedList<Sprite> sprites;
	Random rand;
	Bird bird;
	
	int gen_counter;
	boolean generate;
	
	static final int do_nothing = 0;
	static final int flap = 1;
	static final int throw_pie = 2;
	static final int flap_and_throw = 3;

	Model() throws IOException {
		this.rand = new Random(0);
		this.sprites = new LinkedList<Sprite>();
		bird = new Bird(sprites);
		this.generate = true;
		this.gen_counter = 48;
		this.sprites.add(bird);
	}
	
 	Model(Model other) {
 	
 		rand = new Random(other.rand);
 		sprites = new LinkedList<Sprite>();
 		generate = other.generate;
 		gen_counter = other.gen_counter;
 		
 		Iterator<Sprite> it = other.sprites.iterator();
 		while (it.hasNext()) {
 			Sprite s = it.next();
 			if (s.isBird()) {
				Bird b = (Bird)s;
				sprites.add(new Bird(b, sprites));
			}
 			else if (s.isPie()) {
				Pie p = (Pie)s;
				sprites.add(new Pie(p, sprites));
			}
			else {
				Branch b = (Branch)s;
				sprites.add(new Branch(b));
			}
 		}
 		
 		bird = (Bird)sprites.getFirst();
 	}

	public void update() throws IOException {
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if (!s.update()) {
				it.remove();
			}
		}
		
		//branch generation counter
		if (this.generate == false) {
			this.gen_counter -= 1;
			if (this.gen_counter <= 0) {
				this.generate = true;
			}
		}
		
		if (this.generate == true) {
			Branch b = new Branch(rand);
			this.sprites.add(b);
			this.gen_counter = 48;
			this.generate = false;
		}
	} 
	
	int evaluateAction(int action, int depth) {
		int d = 12;
		int k = 3;
		
		if (depth == d) {
			if (bird.splat == true) {
				return 0;
			}
			else {
				return 500 - Math.abs(bird.y - 250);
			}
		}
		Model mod = new Model(this);
		
		if (action == do_nothing) {
		}
		else if (action == flap) {
			mod.bird.flap();
		}
		else if (action == throw_pie) {
			mod.bird.throw_pie();
		}
		else if (action == flap_and_throw) {
			mod.bird.throw_pie();
			mod.bird.flap();
		}
		
		try {
			mod.update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (depth % k != 0) {
			return mod.evaluateAction(do_nothing, depth + 1);
		}
		else {
			return greatest(mod.evaluateAction(do_nothing, depth + 1), mod.evaluateAction(flap, depth + 1), 
			    mod.evaluateAction(throw_pie, depth + 1), mod.evaluateAction(flap_and_throw, depth + 1));
		}
	}
	
	int greatest(int a, int b, int c, int d) {
		int biggest = a;
		if (b > biggest) {
			biggest = b;
		}
		if (c > biggest) {
			biggest = c;
		}
		if (d > biggest) {
			biggest = d;
		}
		return biggest;
	}
}
