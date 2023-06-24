import java.awt.*;

public class Ellipse extends Figure {
    Point p1, p2, p3;
    int height = 0, width = 0;
    Color color;

    //DEfault constructor
    public Ellipse(Point p1, Point p2, Point p3, int height, int width)
    {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.height = height;
        this.width = width;

    }

    //constructor with a pre-selected color
    public Ellipse(Point p1, Point p2, Point p3, int height, int width, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.height = height;
        this.width = width;
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

    //toString
    @Override
    public String toString() {
        return  super.toString() + 
                "p1: " + p1 + "\n" + 
                "p2: " + p2 + "\n" +
                "height: " + height + "\n" +
                "width: " + width + "\n" +
                "color: " + color;   
        }
   
    private void calculateDimensions() {
        int deltaX, deltaY;
        
        //calculate deltaX
        deltaX = this.p1.getX() - this.p2.getX(); 
        if(deltaX < 0)
            deltaX = -deltaX; //module
        
        //calculate deltaY
        deltaY = this.p1.getY() - this.p2.getY(); 
        if(deltaY < 0)
            deltaY = -deltaY; //module

        if(deltaX >= deltaY) //check which delta is bigger
            if(this.width == 0)
                this.width = deltaX;
            else
                this.height = deltaX;
        else
            if(this.height == 0)
                this.height = deltaY;
            else
                this.width = deltaY;
    }

    public void Draw(Graphics g,Color c) {
        g.setColor(this.color);
        calculateDimensions();  
        g.drawOval(this.p1.getX(), this.p1.getY(), width, height); 
        g.setColor(c);//set color to fill in the ellipse
        g.fillOval(this.p1.getX(), this.p1.getY(), width, height);
    }
        
}
