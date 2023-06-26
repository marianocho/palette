import java.util.Vector;

public class Save {
    private String creator, drawingName;
    private Vector<Figure> figures;

    //Constructor
    public Save(String creator, String drawingName, Vector<Figure> figures) {
        this.creator  = creator;
        this.drawingName = drawingName;
        this.figures = figures;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDrawingName() {
        return this.drawingName;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    public void setFigures(Vector<Figure> figures) {
        this.figures = figures;
    }

    public Vector<Figure> getFigures() {
        return this.figures;
    }

    //Writes a line that contains all the informations of a drawing
    public String newLine() {
        String ret = "";

        ret += this.creator + ",";
        ret += this.drawingName + ",";

        for (Figure figure : figures) {
            if (figure instanceof Point) {
                Point point = (Point) figure;

                ret += "p," + point.getX() + "," + point.getY() + "," + point.getColor().getRGB() + ",";
            }

            if (figure instanceof Line) {
                Line line = (Line) figure;

                ret += "l," + line.getP1().getX() + "," + line.getP1().getY() + "," + 
                              line.getP2().getX() + "," + line.getP2().getY() + "," + 
                              line.getColor().getRGB() + ",";
            }

            if (figure instanceof Square) {
                Square square = (Square) figure;

                ret += "s," + square.getP1().getX() + "," + square.getP1().getY() + "," + 
                              square.getDimension() + "," + square.getColor().getRGB() + "," +
                              square.getColorIn().getRGB() + ",";
            }

            if (figure instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) figure;

                ret += "r," + rectangle.getP1().getX() + "," + rectangle.getP1().getY() + "," + 
                              rectangle.getHeight() + "," + rectangle.getWidth() + "," + 
                              rectangle.getColor().getRGB() + "," + rectangle.getColorIn().getRGB() + ",";
            }

            if (figure instanceof Circle) {
                Circle circle = (Circle) figure;

                ret += "c," + circle.getP1().getX() + "," + circle.getP1().getY() + "," + 
                              circle.getDiameter() + "," + circle.getColor().getRGB() + "," +
                              circle.getColorIn().getRGB() + ",";
            }

            if (figure instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) figure;

                ret += "e," + ellipse.getP1().getX() + "," + ellipse.getP1().getY() + "," 
                            + ellipse.getHeight() + "," + ellipse.getWidth() + "," +
                              ellipse.getColor().getRGB() + "," + ellipse.getColorIn().getRGB() + ",";
            }
        }

        return ret;
    }

    //Checking if the object is a drawing, and if so, check if is the same drawing
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Save.class)
            return false;

        Save s = (Save) obj;

        if (this.creator.equals(s.getCreator()) && this.drawingName.equals(s.getDrawingName()))
            return true;

        return false;
    }
}