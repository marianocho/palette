import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class IOSave {
    private static Vector<Save> saves = new Vector<>();

    public static boolean writeArchive() {
        PrintWriter print;
		
		try {
            print = new PrintWriter(new FileWriter(Constants.NAME_ARQ_DRAWING)); //going to write on the archive saved_drawings.txt
            //rewriting the archive
            for (Save save : saves)
                print.println(save.newLine()); //writing a line with the drawing informations
			
			print.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }

    public static void readArchive() {
        BufferedReader bf;

        try {
            bf = new BufferedReader(new FileReader(Constants.NAME_ARQ_DRAWING)); //going to read saved_drawings.txt
            String line;

            for(;;) {
                line = bf.readLine(); //reading line and store in variable line

                if (line != null) {
                    Vector<Figure> figures = new Vector<>(); //vector that contains the figures of the drawing
                    String[] parts = line.split(","); //split the informations of the drawing
                     
                    for (int i = 2; i < parts.length; i++) { //the index 0 and 1 are creator and name
                        if(parts[i].equals("p")) { //if it is a point
                            figures.add(new Point(Integer.parseInt(parts[i+1]), 
                                                  Integer.parseInt(parts[i+2]), 
                                                  new Color(Integer.parseInt(parts[i+3]))));
                        }
                        //if it is a line
                        if(parts[i].equals("l")) {
                            figures.add(new Line( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                  new Point(Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4])),
                                                  new Color(Integer.parseInt(parts[i+5]))));
                        }
                        //if it is a square
                        if(parts[i].equals("s")) {
                            figures.add(new Square( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                    Integer.parseInt(parts[i+3]),
                                                    new Color(Integer.parseInt(parts[i+4])),
                                                    new Color(Integer.parseInt(parts[i+5]))));
                        }
                        //if it is a rectangle
                        if(parts[i].equals("r")) {
                            figures.add(new Rectangle( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4]),
                                                       new Color(Integer.parseInt(parts[i+5])),
                                                       new Color(Integer.parseInt(parts[i+6]))));
                        }
                        //if it is a circle
                        if(parts[i].equals("c")) {
                            figures.add(new Circle( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]),
                                                       new Color(Integer.parseInt(parts[i+4])),
                                                       new Color(Integer.parseInt(parts[i+5]))));
                        }
                        //if it is a ellipse
                        if(parts[i].equals("e")) {
                            figures.add(new Ellipse( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4]),
                                                       new Color(Integer.parseInt(parts[i+5])),
                                                       new Color(Integer.parseInt(parts[i+6]))));
                        }
                    }
                    //                 creator   name      vector of figures
                    saves.add(new Save(parts[0], parts[1], figures)); //add the read drawing to the vector
                } else
                    break;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean saveNewDrawing(Save save) {
        //remove the old drawing and add the new (if they have the same creator and name)
        if (saves.contains(save))
            saves.remove(save);

        return saves.add(save);
    }

    public static Save getSave(Save save) {
        //Check if the drawing exists
        int indice = saves.indexOf(save);
        if (indice > -1)
            return saves.get(indice);
        return null;
    }
}
