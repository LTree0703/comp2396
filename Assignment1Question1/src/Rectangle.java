public class Rectangle {
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public double getPositionX() {
		return x;
	}
	
	public double getPositionY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getPerimeter() {
		return 2 * (width + height);
	}
	
	public double getArea() {
		return width * height;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean overlapsWith(Rectangle r) {
		double rect1Top = y + height / 2;
		double rect1Right = x + width / 2;
		double rect1Bottom = y - height / 2;
		double rect1Left = x - width / 2;
		
		double rect2Top = r.getPositionY() + r.getHeight() / 2;
		double rect2Right = r.getPositionX() + r.getWidth() / 2;
		double rect2Bottom = r.getPositionY() - r.getHeight() / 2;
		double rect2Left = r.getPositionX() - r.getWidth() / 2;
 		
		if (rect1Right > rect2Left && rect1Left < rect2Right && rect1Top > rect2Bottom && rect1Bottom < rect2Top ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}