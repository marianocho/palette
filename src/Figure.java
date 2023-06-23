import java.awt.*;

public abstract class Figure{
    protected Color color;

    //Default constructor
    public Figure(){
        this.color = Color.BLACK; //default color
    }

    //Constructor with a pre-selected color
    public Figure(Color color){
        this.color = color;
    }

    //Getter
    public Color getColor(){
        return color;
    }

    //Setter
    public void setColor(Color color){
        this.color = color;
    }

    //toString
    public String toString(){
        return "Color: " + color + "\n";
    }

    //Draw the figure
    public void draw(Graphics g, Color c){};
}