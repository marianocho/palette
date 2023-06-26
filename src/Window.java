import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;

public class Window extends JFrame {

    //Interactive buttons (menu)
    private JButton[] buttons = new JButton[Constants.NUMBER_OF_BUTTONS];

    //Buttons actions
    private ActionListener[] functions = { new DrawnigPencil(),
                                           new DrawingPoint(), 
                                           new DrawingLine (),
                                           new DrawingCircle(),
                                           new DrawingEllipse(),
                                           new DrawingSquare(),
                                           new DrawingRectangle(),
                                           new ColorOut(),
                                           new ColorIn(),
                                           new ClearAll(),
                                           new OpenDrawing(),
                                           new NewSave()
    };

    private MeuJPanel pnlDrawing = new MeuJPanel();

    private JLabel statusBar1 = new JLabel("Message:"),
                   statusBar2 = new JLabel("Coordinates:");



    private Color colorOut = Color.BLACK;

    private Color colorIn  = Color.WHITE;

    private Point p1;

    private Vector<Figure> figures = new Vector<Figure>();

    private DrawEnum action;

    public Window () { //constructor of Window 
        super("Pallete");
        JPanel     pnlButtons = new JPanel();
        FlowLayout flwButtons = new FlowLayout(); 

        this.getContentPane().setBackground(Color.WHITE);

        pnlButtons.setLayout (flwButtons);
        pnlButtons.setBackground(Color.LIGHT_GRAY);
        initializeButtons();
        addButtonsToPanel(pnlButtons);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);
        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlButtons,  BorderLayout.NORTH);
        cntForm.add (pnlDrawing, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);

        this.addWindowListener(new CloseWindow());

        IOSave.readArchive();

        this.setSize (1200,600);
        this.setVisible (true);
    }

    public void openDraw(String creator, String drawingName) {
        Save save = IOSave.getSave(new Save(creator, drawingName, null));
        
        if (save != null) {
            repaint();
            figures.removeAllElements();
            figures = save.getFigures();
            
            for (Figure figure : save.getFigures())
                figure.draw(getGraphics());

            JOptionPane.showMessageDialog(null, "Your drawning has been opened!", 
                                            "Opened Drawing", JOptionPane.INFORMATION_MESSAGE);
        }
        else 
            JOptionPane.showMessageDialog(null, "An unexpected error occurred", 
                                            "Error", JOptionPane.ERROR_MESSAGE);

    }

    public void saveNewDraw(String creator, String drawingName) {
        if (figures.size() == 0) {
            JOptionPane.showMessageDialog(null, "Add any drawing before to save", 
                                          "Drawing not found", JOptionPane.ERROR_MESSAGE);

            return;
        }

        if (IOSave.saveNewDrawing(new Save(creator, drawingName, figures))) 
            JOptionPane.showMessageDialog(null, "Your drawning has been saved!", 
                                         "Saved Drawing", JOptionPane.INFORMATION_MESSAGE);
        else 
            JOptionPane.showMessageDialog(null, "An unexpected error occurred", 
                                         "Error", JOptionPane.ERROR_MESSAGE);

    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            ImageIcon image = null;
            try {
                if (Constants.figureNames[i].equals("Outside") || Constants.figureNames[i].equals("Inside")) 
                    image = new ImageIcon(ImageIO.read(getClass().getResource("resources/Colors.jpg")));
                else 
                    image = new ImageIcon(ImageIO.read(getClass().getResource("resources/" + Constants.figureNames[i] + ".jpg")));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                                              "Open image " + Constants.figureNames[i] + " error" , 
                                              "File not found", JOptionPane.WARNING_MESSAGE);
            }
            buttons[i] = new JButton(Constants.figureNames[i], image);
            buttons[i].addActionListener(functions[i]);
        }
    }

    private void addButtonsToPanel(JPanel panel) {
        for (JButton button : buttons) 
            panel.add(button);
    }

    private class MeuJPanel extends JPanel
                            implements MouseListener,
                                       MouseMotionListener 
    {
        Vector<Point> points = new Vector<>();

        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void mousePressed(MouseEvent e) {
            switch(action){
                case WAIT_POINT:
                    newPoint(e);
                    break;
                case WAIT_BEGIN_LINE:
                    beginNewLine(e);
                    break;
                case WAIT_END_LINE:
                    endNewLine(e);
                    break;
                case WAIT_BEGIN_SQUARE:
                    beginNewSquare(e);
                    break;    
                case WAIT_END_SQUARE:
                    endNewSquare(e);
                    break;
                case WAIT_BEGIN_RECTANGLE:
                    beginNewRectangle(e);
                    break;
                case WAIT_END_RECTANGLE:
                    endNewRectangle(e);
                    break;
                case WAIT_BEGIN_CIRCLE:
                    beginNewCircle(e);
                    break;
                case WAIT_END_CIRCLE:
                    endNewCircle(e);
                    break;
                case WAIT_BEGIN_ELLIPSE:
                    beginNewEllipse(e);
                    break;
                case WAIT_END_ELLIPSE:
                    endNewEllipse(e);
                    break;
                case WAIT_PENCIL:
                    beginNewPencil(); 
                    break;
                }
        }

        public void mouseReleased(MouseEvent e) {
            if (action == DrawEnum.WAIT_PENCIL) {
                for (int j = 1; j < points.size(); j++) {
                    figures.add(new Line(points.get(j-1), points.get(j), colorOut));
                    figures.get(figures.size() - 1).draw(getGraphics(), colorIn);
                }
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            if (action == DrawEnum.WAIT_PENCIL) {
                points.add(new Point(e.getX(), e.getY()));
            }
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordinates: " + e.getX() + "," + e.getY());
        }

        private void newPoint(MouseEvent e) {
            figures.add(new Point(e.getX(), e.getY(), colorOut));
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
            action = DrawEnum.WAIT_POINT;
        }

        private void beginNewLine(MouseEvent e) {
            p1 = new Point(e.getX(), e.getY(), colorOut);
            action = DrawEnum.WAIT_END_LINE;
            statusBar1.setText("Message: set the line final point");
        }

        private void endNewLine(MouseEvent e) {
            action = DrawEnum.WAIT_BEGIN_LINE;
            figures.add(new Line(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
            statusBar1.setText("Message: set the line initial point");
        }

        private void beginNewSquare(MouseEvent e) {
            p1 = new Point(e.getX(), e.getY(), colorOut);
            action = DrawEnum.WAIT_END_SQUARE;
            statusBar1.setText("Message: set the square side");
        }

        private void endNewSquare(MouseEvent e) {
            action = DrawEnum.WAIT_BEGIN_SQUARE;
            figures.add(new Square(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
            statusBar1.setText("Message: set the square intial point");
        }

        private void beginNewRectangle(MouseEvent e) {
            p1 = new Point(e.getX(), e.getY(), colorOut);
            action = DrawEnum.WAIT_END_RECTANGLE;
            statusBar1.setText("Message: set the rectangle side");
        }

        private void endNewRectangle(MouseEvent e) {
            action = DrawEnum.WAIT_BEGIN_RECTANGLE;
            figures.add(new Rectangle(new Point(p1.getX(), p1.getY()), // first Point
                        new Point(e.getX(), e.getY()), // width
                        colorOut));
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
            statusBar1.setText("Message: set the rectangle initial point");
        }

        private void beginNewCircle(MouseEvent e) {
            p1 = new Point (e.getX(), e.getY(), colorOut);
            statusBar1.setText("Mensagem: set the circle border point");
            action = DrawEnum.WAIT_END_CIRCLE; 
        }

        private void endNewCircle(MouseEvent e) {
            action = DrawEnum.WAIT_BEGIN_CIRCLE;
            figures.add(new Circle(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
            statusBar1.setText("Message: set the circle initial point");
        }

        private void beginNewEllipse(MouseEvent e) {
            p1 = new Point (e.getX(), e.getY(), colorOut);
            statusBar1.setText("Message: set the ellipse border"); 
            action = DrawEnum.WAIT_END_ELLIPSE;
        }

        private void endNewEllipse(MouseEvent e) {
            action = DrawEnum.WAIT_BEGIN_ELLIPSE;
            figures.add(new Ellipse(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut)); 
            figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(),colorIn);
            statusBar1.setText("Message: set the ellipse initial point");
        }

        private void beginNewPencil() {
            points.removeAllElements();
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    protected class DrawnigPencil implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_PENCIL;

            statusBar1.setText("Message: use your pencil");
        } 
    }

    protected class DrawingPoint implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_POINT; //WaitPoint

            statusBar1.setText("Message: set the local for the point");
        }
    }

    protected class DrawingLine implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_BEGIN_LINE; //BeginLine

            statusBar1.setText("Message: set the line initial point");
        }
    }

    protected class DrawingSquare implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           action = DrawEnum.WAIT_BEGIN_SQUARE;

            statusBar1.setText("Message: set the square intial point");
        }
    }

    protected class DrawingRectangle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_BEGIN_RECTANGLE;

            statusBar1.setText("Message: set the rectangle initial point");
        }
    }

    protected class DrawingCircle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_BEGIN_CIRCLE;
            statusBar1.setText("Message: set the circle initial point");
        }
    }

    protected class DrawingEllipse implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            action = DrawEnum.WAIT_BEGIN_ELLIPSE;
            statusBar1.setText("Message: set the ellipse initial point");
        }
    }
    
    protected class ColorOut implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                colorOut = JColorChooser.showDialog(Window.this,
                        "Choose a color", colorOut);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    protected class ColorIn implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                colorIn = JColorChooser.showDialog(Window.this,
                        "Choose a color", colorIn);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }

        }
    }

    protected class ClearAll implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
            figures.removeAllElements();
        }
    }

    protected class NewSave implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new SaveWindow(Window.this);
        }
    }

    protected class OpenDrawing implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new OpenWindow(Window.this);
        }
    }

    protected class CloseWindow extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            IOSave.writeArchive();
            System.exit(0);
        }
    }
}