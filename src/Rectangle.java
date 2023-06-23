import java.awt.*;

public class Rectangle extends Figure{
    private Point p1, p2;
    private int height, width;

    //Default constructor
    public Rectangle(Point p1, Point p2){
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    //Constructor with a pre-selected color
    public Rectangle(Color color, Point p1, Point p2){
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    //Getters
    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    //Setters
    public void setP1(int x, int y){
        this.p1 = new Point(this.color, x, y);
    }

    public void setP2(int x, int y){
        this.p2 = new Point(this.color, x, y);
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    @Override
    //toString
    public String toString(){
        return super.toString() + "p1: " + p1 + "\n" + "p2: " + p2 + "\n" + "height: " + height + ", width:" + width + "\n";
    }

    //Calculate the dimensions of the rectangle
    public void calculateDimensions(){
        int deltaX, deltaY;

        //calculating deltaX
        deltaX = p1.getX() - p2.getX();
        if(deltaX < 0){
            deltaX = -1*deltaX; //module
        }

        //calculating deltaY
        deltaY = p1.getY() - p2.getY();
        if(deltaY < 0){
            deltaY = -1*deltaY; //module
        }

        this.height = deltaY;
        this.width = deltaX;
    }

    //Draw the rectangle
    @Override
    public void draw(Graphics g, Color fc){
        g.setColor(color); //contour color
        calculateDimensions();
        g.drawRect(p1.getX(), p1.getY(), //start point
                   width, height);
        g.setColor(fc); //filling color
        g.fillRect(p1.getX(), p1.getY(), //same start point
                   width, height);
    }
}
