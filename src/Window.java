import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;

public class Window extends JFrame {

    //Interactive buttons (menu)
    private JButton[] buttons = {   new JButton(Constants.figureNames[0]), 
                                    new JButton(Constants.figureNames[1]), 
                                    new JButton(Constants.figureNames[2]), 
                                    new JButton(Constants.figureNames[3]), 
                                    new JButton(Constants.figureNames[4]), 
                                    new JButton(Constants.figureNames[5]), 
                                    new JButton(Constants.figureNames[6]), 
                                    new JButton(Constants.figureNames[7]), 
                                    new JButton(Constants.figureNames[8]), 
                                    new JButton(Constants.figureNames[9])};

    //Buttons actions
    private ActionListener[] functions = { new DrawingPoint(), 
                                           new DrawingLine (),
                                           new DrawingCircle(),
                                           new DrawingEllipse(),
                                           new DrawingSquare(),
                                           new DrawingRectangle(),
                                           new colorOut(),
                                           new colorIn(),
                                           null,
                                           null
    };

    private MeuJPanel pnlDrawing = new MeuJPanel();

    private JLabel statusBar1 = new JLabel("Message:"),
                   statusBar2 = new JLabel("Coordinates:");



    private Color colorOut = Color.BLACK;

    private Color colorIn  = Color.WHITE;

    private Point p1;

    private Vector<Figure> figures = new Vector<Figure>();

    DrawEnum action;

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
        
        this.addWindowListener (new FechamentoJanela());

        this.setSize (1200,600);
        this.setVisible (true);
    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            try {
                if (Constants.figureNames[i].equals("Outside") || Constants.figureNames[i].equals("Inside")) {
                    Image image = ImageIO.read(getClass().getResource("resources/Colors.jpg"));
                    buttons[i].setIcon(new ImageIcon(image));
                } else {
                    Image image = ImageIO.read(getClass().getResource("resources/" + Constants.figureNames[i] + ".jpg"));
                    buttons[i].setIcon(new ImageIcon(image));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                                              "Open image " + Constants.figureNames[i] + " error" , 
                                              "File not found", JOptionPane.WARNING_MESSAGE);
            }

            buttons[i].addActionListener(functions[i]);
        }
    }

    private void addButtonsToPanel(JPanel panel) {
        for (JButton button : buttons) 
            panel.add(button);
    }

    protected class MeuJPanel extends JPanel
            implements MouseListener,
            MouseMotionListener {

        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void mousePressed(MouseEvent e) {
            switch(action){
                case WAIT_POINT:
                    figures.add(new Point(e.getX(), e.getY(), colorOut));
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
                    action = DrawEnum.WAIT_POINT;
                    break;
                case WAIT_BEGIN_LINE:
                    p1 = new Point(e.getX(), e.getY(), colorOut);
                    action = DrawEnum.WAIT_END_LINE;
                    statusBar1.setText("Message: set the line final point");
                    break;
                case WAIT_END_LINE:
                    action = DrawEnum.WAIT_BEGIN_LINE;
                    figures.add(new Line(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
                    statusBar1.setText("Message:");
                    break;
                case WAIT_BEGIN_SQUARE:
                    p1 = new Point(e.getX(), e.getY(), colorOut);
                    action = DrawEnum.WAIT_END_SQUARE;
                    statusBar1.setText("Message: set the square side");
                    break;    
                case WAIT_END_SQUARE:
                    action = DrawEnum.WAIT_BEGIN_SQUARE;
                    figures.add(new Square(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
                    statusBar1.setText("Message:");
                    break;
                case WAIT_BEGIN_RECTANGLE:
                    p1 = new Point(e.getX(), e.getY(), colorOut);
                    action = DrawEnum.WAIT_END_RECTANGLE;
                    statusBar1.setText("Message: set the rectangle side");
                    break;
                case WAIT_END_RECTANGLE:
                    action = DrawEnum.WAIT_BEGIN_RECTANGLE;
                    figures.add(new Rectangle(new Point(p1.getX(), p1.getY()), // first Point
                                new Point(e.getX(), e.getY()), // width
                                colorOut));
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
                    statusBar1.setText("Message:");
                    break;
                case WAIT_BEGIN_CIRCLE:
                    p1 = new Point (e.getX(), e.getY(), colorOut);
                    statusBar1.setText("Mensagem: set the circle border point");
                    action = DrawEnum.WAIT_END_CIRCLE; 
                    break;
                case WAIT_END_CIRCLE:
                    figures.add(new Circle(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut));
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(), colorIn);
                    statusBar1.setText("Message:");
                    action = DrawEnum.WAIT_BEGIN_CIRCLE;
                    break;
                case WAIT_BEGIN_ELLIPSE:
                    p1 = new Point (e.getX(), e.getY(), colorOut);
                    statusBar1.setText("Message: set the ellipse border"); 
                    action = DrawEnum.WAIT_END_ELLIPSE;
                    break;
                case WAIT_END_ELLIPSE:
                    figures.add(new Ellipse(new Point(p1.getX(), p1.getY()), new Point(e.getX(), e.getY()), colorOut)); 
                    figures.get(figures.size() - 1).draw(pnlDrawing.getGraphics(),colorIn);
                    statusBar1.setText("Message:");
                    action = DrawEnum.WAIT_BEGIN_ELLIPSE;
                    break;
                }
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordinates: " + e.getX() + "," + e.getY());
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

    
    protected class colorOut implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                colorOut = JColorChooser.showDialog(Window.this,
                        "Choose a color", colorOut);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    protected class colorIn implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                colorIn = JColorChooser.showDialog(Window.this,
                        "Choose a color", colorIn);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }

        }
    }

    protected class NovoSalvamento implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class AbrirDrawing implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    protected class FechamentoJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}