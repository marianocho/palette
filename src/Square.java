import java.awt.*;

public class Square extends Figure {
    private Point p1, p2;
    private int dimension;

    // Default constructor
    public Square(Point p1, Point p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    // Constructor with a pre-selected color
    public Square(Point p1, Point p2, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    // Getters
    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public int getDimension() {
        return dimension;
    }

    // Setters
    public void setP1(int x, int y) {
        this.p1 = new Point(x, y, this.color);
    }

    public void setP2(int x, int y) {
        this.p2 = new Point(x, y, this.color);
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    // toString
    public String toString() {
        return "p1: " + p1 + "\n" + "p2: " + p2 + "\n" + "dimension: " + dimension + "\n";
    }

    // Calculate the dimension of the square
    public void calculateDimensions() {
        int deltaX, deltaY;

        // calculating deltaX
        deltaX = p1.getX() - p2.getX();

        // calculating deltaY
        deltaY = p1.getY() - p2.getY();

        if (deltaX < 0 && deltaY > 0) {
            this.p1.setY(this.p2.getY());
            deltaX = -deltaX;
        } else if (deltaX > 0 && deltaY > 0) {
            this.setP1(this.p2.getX(), this.p2.getY());
        } else if (deltaX > 0 && deltaY < 0) {
            this.p1.setX(this.p2.getX());
            deltaY = -deltaY;
        } else if (deltaX < 0 && deltaY < 0) {
            deltaX = -deltaX;
            deltaY = -deltaY;
        }

        if (deltaX == deltaY) { // if deltas are the same, it doesn't matter
            this.dimension = deltaX;
        } else { // if the deltas are different, calculate the mean value of them and use it as
                 // the size
            double averageDouble = (deltaX + deltaY) / 2;
            int averageInt = (int) averageDouble;
            this.dimension = averageInt;
        }
    }

    //Draw the square
    @Override
    public void draw(Graphics g, Color fc){
        g.setColor(color); //contour color
        calculateDimensions();
        g.drawRect(p1.getX(), p1.getY(), //start point 
                   dimension, dimension);
        g.setColor(fc); //filling color
        g.fillRect(p1.getX()+1, p1.getY()+1, //same start point
                   dimension-1, dimension-1);

    }
}
