/**
 * (Description)
 * The Position class stores the x and y position of the 
 * centre point of the rectangle.
 */
public class Position {
	private double x;
	private double y;
	
	/**
	 * This constructor instantiates the Position object with 
	 * all instance variables assigned a value. 
	 * @param x This is the x position of the centre point of the rectangle.
	 * @param y This is the y position of the centre point of the rectangle.
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This getter method returns the x position of the centre point of the rectangle.
	 * @return x position
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * This getter method returns the y position of the centre point of the rectangle. 
	 * @return y position
	 */
	public double getY() {
		return y;
	}
}