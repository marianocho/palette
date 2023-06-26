import java.awt.*;

public class Ellipse extends Figure {
    Point p1, p2, p3;
    int height = 0, width = 0;
    
    //DEfault constructor
    public Ellipse(Point p1, Point p2)
    {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    //constructor with a pre-selected color
    public Ellipse(Point p1, Point p2, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Ellipse(Point p1, int height, int width, Color colorOut, Color colorIn) {
        super(colorOut, colorIn);
        this.p1 = p1;
        this.width = width;
        this.height = height;
    }
    
    //Getters
    public Point getP1() {
        return this.p1;
    }

    public Point getP2() {
        return this.p2;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    //Setters
    public void setP1(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setP2(int x, int y)
    {
        this.p2.setX(x);
        this.p2.setY(y);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
   
    public void calculateDimensions() {
        int deltaX, deltaY;

        //calculating deltaX
        deltaX = p1.getX() - p2.getX();

        //calculating deltaY
        deltaY = p1.getY() - p2.getY();

        if (deltaX < 0 && deltaY > 0) {
            this.p1.setY(this.p2.getY());
            this.height = deltaY;
            this.width = -deltaX;
        } else if (deltaX > 0 && deltaY > 0) {
            this.setP1(this.p2.getX(), this.p2.getY());
            this.height = deltaY;
            this.width = deltaX;
        } else if (deltaX > 0 && deltaY < 0) {
            this.p1.setX(this.p2.getX());
            this.height = -deltaY;
            this.width = deltaX;
        } else if (deltaX < 0 && deltaY < 0) {
            this.height = -deltaY;
            this.width = -deltaX;
        }
    }

    public void draw(Graphics g,Color c) {
        calculateDimensions(); 
        g.setColor(this.getColor());
        g.drawOval(this.p1.getX(), this.p1.getY(), width, height); 
        g.setColor(c);//set color to fill in the ellipse
        colorIn = c;
        g.fillOval(this.p1.getX()+1, this.p1.getY()+1, width-1, height-1);
    }
    //Draw when open a saved drawing
    @Override
    public void draw(Graphics g) {
        g.setColor(this.colorOut);
        g.drawOval(this.p1.getX(), this.p1.getY(), width, height); 
        g.setColor(this.colorIn);//set color to fill in the ellipse
        g.fillOval(this.p1.getX()+1, this.p1.getY()+1, width-1, height-1);
    }
}
