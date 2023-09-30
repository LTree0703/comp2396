/**
 *  (Description)
 *  The Rectangle class stores the information of a
 *  rectangle, namely its position of the centre point, width, and height. 
 *  
 *  In addition, this class also allows the calculation of
 *  the rectangle's perimeter and area.
 *  
 */
public class Rectangle {
	private Position p;
	private double width;
	private double height;
	
	/**
	 * This constructor is used to initialize 
	 * all instance variables.
	 * @param p This is the reference variable to the position of the rectangle.
	 * @param width This is the width of the rectangle.
	 * @param height This is the height of the rectangle.
	 */
	public Rectangle(Position p, double width, double height) {
		this.p = p;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * This setter method sets the position of the centre point of 
	 * the rectangle object. 
	 * @param p
	 */
	public void setPosition(Position p) {
		this.p = p;
	}
	
	/**
	 * This getter method returns the position of the rectangle.
	 * @return p
	 */
	public Position getPosition() {
		return p;
	}
	
	/**
	 * This setter method sets the size of the rectangle.
	 * @param width
	 * @param height
	 */
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * This getter method returns the width of the rectangle.
	 * @return width
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * This getter method returns the height of the rectangle.
	 * @return height
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * This method calculates the perimeter of the rectangle and
	 * then return it.
	 * @return perimeter
	 */
	public double getPerimeter() {
		return 2 * (height + width);
	}
	
	/**
	 * This method calculates the area of the rectangle and
	 * then return it
	 * @return area
	 */
	public double getArea() {
		return height * width;
	}
	
	/**
	 * This method determines whether this rectangle has overlapped with
	 * another given rectangle.
	 * @param r This is the rectangle we want to compare with.
	 * @return true if has overlapped, false otherwise.
	 */
	public boolean overlapsWith(Rectangle r) {
		double rect1Top = p.getY() + height / 2;
		double rect1Right = p.getX() + width / 2;
		double rect1Bottom = p.getY() - height / 2;
		double rect1Left = p.getX() - width / 2;
		
		double rect2Top = r.getPosition().getY() + r.getHeight() / 2;
		double rect2Right = r.getPosition().getX() + r.getWidth() / 2;
		double rect2Bottom = r.getPosition().getY() - r.getHeight() / 2;
		double rect2Left = r.getPosition().getX() - r.getWidth() / 2;
 		
		if (rect1Right > rect2Left && rect1Left < rect2Right && rect1Top > rect2Bottom && rect1Bottom < rect2Top ) {
			return true;
		}
		else {
			return false;
		
		}
	}
}