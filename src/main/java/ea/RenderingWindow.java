package ea;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.joda.time.Instant;
import org.joda.time.Period;

import eatest.Main;
import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PDragEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;

public class RenderingWindow extends JFrame {
	private Route route = new Route(true, 6);
	private int generation;
	private final PText desc = new PText("EA");
	private final PCanvas canvas = new PCanvas();
	private final PPath pathRoute = new PPath();

	private final PNode paneCities = new PNode();

	private PImage backgroundImage = new PImage();

	public RenderingWindow() {
		this.setupWindow();

		this.pathRoute.setStrokePaint(Color.BLUE);
		this.pathRoute.setStroke(new BasicStroke(2));
		this.pathRoute.setPickable(false);

		this.canvas.addInputEventListener(new PBasicInputEventHandler() {
			@Override
			public void mouseClicked(PInputEvent event) {
				System.out.println(event.getPosition());
			}
		});

		Keybinder keybinder = new Keybinder(this);
		this.canvas.addKeyListener(keybinder);
		this.addKeyListener(keybinder);
	}

	public void addBackgroundImage(String filename) {
		this.backgroundImage = new PImage("src/main/resources/UnitedKingdom.jpg");
		this.backgroundImage.setPickable(false);
		this.canvas.getCamera().setViewScale(.5);
		this.canvas.getCamera().setViewOffset(0, -100);
		this.canvas.getLayer().addChild(this.backgroundImage);
	}

	public void addCities() {
		this.canvas.getCamera().addChild(this.desc);
		this.canvas.addInputEventListener(new PDragEventHandler());

		for (City c : Main.map.getCities()) {
			PNode n = new PNode(c.name);
			n.setPaint(Color.RED);
			n.setBounds(c.position.x - 3, c.position.y - 3, 6, 6);

			PText t = new PText(c.name);
			t.setPickable(false);
			t.setX(c.position.x + 10);
			t.setY(c.position.y - 5);

			PNode bg = new PNode();
			bg.setBounds(t.getBounds());
			bg.setPaint(Color.YELLOW);
			bg.addChild(t);

			n.addChild(bg);

			this.paneCities.addChild(n);
		}

		this.getLayer().addChild(this.paneCities);
	}

	@ShortcutKey(KeyEvent.VK_ESCAPE)
	public void exit() {
		System.exit(0);
	}

	private PLayer getLayer() {
		return this.canvas.getLayer();
	}

	public void init() {
		this.canvas.getLayer().addChild(this.pathRoute);
		this.addCities();
	}

	public void render() {
		City last = null;

		this.pathRoute.reset();
		this.pathRoute.removeAllChildren();
		this.pathRoute.moveTo(this.route.getStart().position.x, this.route.getStart().position.y);

		for (City c : this.route.getAll()) {
			if (last == null) {
				last = c;
				continue;
			}

			this.pathRoute.lineTo(c.position.x, c.position.y);

			last = c;
		}
	}

	public void setupWindow() {
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		this.setBounds(0, 0, 640, 640);
		this.setLocationRelativeTo(null);
		this.setTitle("EA");
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(this.canvas, BorderLayout.CENTER);
	}

	@ShortcutKey(KeyEvent.VK_B)
	public void toggleBackground() {
		this.backgroundImage.setVisible((!this.backgroundImage.getVisible()));
	}

	@ShortcutKey(KeyEvent.VK_C)
	public void toggleCities() {
		this.paneCities.setVisible(!this.paneCities.getVisible());
	}

	@ShortcutKey(KeyEvent.VK_P)
	public void toggleRoute() {
		this.pathRoute.setVisible(!this.pathRoute.getVisible());
	}

	public void update(int generation, Route best) {
		this.generation = generation;
		this.route = best;

		this.desc.setText("Generation: " + this.generation + ".     Most optimal route: " + this.route.getDistance() + ".     Start time: " + new Period(Main.startTime, Instant.now()));

		this.render();
		this.canvas.repaint();
	}

}
