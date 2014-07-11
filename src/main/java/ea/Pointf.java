package ea;

public class Pointf {
	public double x = 0;
	public double y = 0;

	public Pointf(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Pointf position) {
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
}
