// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl

/**
 * Creates the Flight object 
 * @author Laura Dinh
 *
 */
public class Flight {
	
	private String name;
	private String target;
	private String source;
	private double duration;
	private int distance;
	private int price;

	public Flight(String name, String source, String target, double duration, int distance, int price) {
		this.name = name;
		this.source = source;
		this.target = target;
		this.duration = duration;
		this.distance = distance;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTarget() {
		return target;
	}

	public String getSource() {
		return source;
	}

	public double getDuration() {
		return duration;
	}

	public int getDistance() {
		return distance;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return String.format("%s: %s to %s (%.2f hr, %d mi, $%d)", name, source, target, duration, distance, price);
	}
}
