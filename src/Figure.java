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

    //Constructor with two pre-selected color
    public Figure(Color colorOut, Color colorIn){
        this.colorOut = colorOut;
        this.colorIn = colorIn;
    }

    //Getter
    public Color getColorOut(){
        return colorOut;
    }

    public Color getColorIn() {
        return colorIn;
    }

    //Setter
    public void setColorOut(Color color){
        this.colorOut = color;
    }

    public void setColorIn(Color colorIn) {
        this.colorIn = colorIn;
    }

    @Override
    public String toString(){
        return "ColorIn: " + colorIn + ", ColorOut: " + colorOut + "\n";
    }

    //Draw the figure
    public abstract void draw(Graphics g, Color c);
    //Draw when open a saved drawing
    public abstract void draw(Graphics g);
    public abstract void calculateDimensions();
}