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
    public Rectangle(Point p1, Point p2, Color color){
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Rectangle(Point p1, int height, int width, Color colorOut, Color colorIn){
        super(colorOut, colorIn);
        this.p1 = p1;
        this.height = height;
        this.width = width;
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
        this.p1 = new Point(x, y, this.colorOut);
    }

    public void setP2(int x, int y){
        this.p2 = new Point(x, y, this.colorOut);
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    //Calculate the dimensions of the rectangle
    public void calculateDimensions(){
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

    //Draw the rectangle
    @Override
    public void draw(Graphics g, Color fc){
        g.setColor(colorOut); //contour color
        calculateDimensions();
        g.drawRect(p1.getX(), p1.getY(), //start point
                   width, height);
        g.setColor(fc); //filling color
        colorIn = fc;
        g.fillRect(p1.getX()+1, p1.getY()+1, //same start point
                   width-1, height-1);
    }

    //Draw when open a saved drawing
    @Override
    public void draw(Graphics g){
        g.setColor(colorOut); //contour color
        g.drawRect(p1.getX(), p1.getY(), //start point
                   width, height);
        g.setColor(this.colorIn); //filling color
        g.fillRect(p1.getX()+1, p1.getY()+1, //same start point
                   width-1, height-1);
    }
}
