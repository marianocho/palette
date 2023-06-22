import java.awt.*;

public class Point extends Figure{
    private int x, y; //coordinates of the point, we use this to localize the point and create lines/figures
    private int diameter = 2; //the point is a minuscle circle, we ONLY use this to draw the point

    //Default constructor
    public Point(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }

    //Constructor with a pre-selected color
    public Point(Color color, int x, int y){
        super(color);
        this.x = x;
        this.y =y;
    }

    //Getters
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDiameter(){
        return diameter;
    }

    //Setters
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDiameter(int diameter){
        this.diameter = diameter;
    }

    @Override
    //toString
    public String toString(){
        return super.toString() + "x: " + x + ", y: " + y + "\n";
    }
}
