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
            print = new PrintWriter(new FileWriter(Constants.NAME_ARQ_DRAWING));
            
            for (Save save : saves)
                print.println(save.newLine());
			
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
            bf = new BufferedReader(new FileReader(Constants.NAME_ARQ_DRAWING));
            String line;

            for(;;) {
                line = bf.readLine();

                if (line != null) {
                    Vector<Figure> figures = new Vector<>();
                    String[] parts = line.split(",");
                     
                    for (int i = 2; i < parts.length; i++) {
                        if(parts[i].equals("p")) {
                            figures.add(new Point(Integer.parseInt(parts[i+1]), 
                                                  Integer.parseInt(parts[i+2]), 
                                                  new Color(Integer.parseInt(parts[i+3]))));
                        }

                        if(parts[i].equals("l")) {
                            figures.add(new Line( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                  new Point(Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4])),
                                                  new Color(Integer.parseInt(parts[i+5]))));
                        }

                        if(parts[i].equals("s")) {
                            figures.add(new Square( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                    Integer.parseInt(parts[i+3]),
                                                    new Color(Integer.parseInt(parts[i+4])),
                                                    new Color(Integer.parseInt(parts[i+5]))));
                        }

                        if(parts[i].equals("r")) {
                            figures.add(new Rectangle( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4]),
                                                       new Color(Integer.parseInt(parts[i+5])),
                                                       new Color(Integer.parseInt(parts[i+6]))));
                        }

                        if(parts[i].equals("c")) {
                            figures.add(new Circle( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]),
                                                       new Color(Integer.parseInt(parts[i+4])),
                                                       new Color(Integer.parseInt(parts[i+5]))));
                        }

                        if(parts[i].equals("e")) {
                            figures.add(new Ellipse( new Point(Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2])),
                                                       Integer.parseInt(parts[i+3]), Integer.parseInt(parts[i+4]),
                                                       new Color(Integer.parseInt(parts[i+5])),
                                                       new Color(Integer.parseInt(parts[i+6]))));
                        }
                    }

                    saves.add(new Save(parts[0], parts[1], figures));
                } else
                    break;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean saveNewDrawing(Save save) {
        if (saves.contains(save))
            saves.remove(save);

        return saves.add(save);
    }

    public static Save getSave(Save save) {
        int indice = saves.indexOf(save);
        if (indice > -1)
            return saves.get(indice);
        return null;
    }
}
