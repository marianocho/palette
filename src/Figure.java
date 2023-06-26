import java.awt.*;

public abstract class Figure {
    protected Color colorIn, colorOut;

    //Default constructor
    public Figure(){
        this.colorOut = Color.BLACK; //default color
    }

    //Constructor with a pre-selected color
    public Figure(Color color){
        this.colorOut = color;
    }

    public Figure(Color colorOut, Color colorIn){
        this.colorOut = colorOut;
        this.colorIn = colorIn;
    }

    //Getter
    public Color getColor(){
        return colorOut;
    }

    public Color getColorIn() {
        return colorIn;
    }

    //Setter
    public void setColor(Color color){
        this.colorOut = color;
    }

    public void setColorIn(Color colorIn) {
        this.colorIn = colorIn;
    }

    //Draw the figure
    public abstract void draw(Graphics g, Color c);
    public abstract void draw(Graphics g);
    public abstract void calculateDimensions();
}