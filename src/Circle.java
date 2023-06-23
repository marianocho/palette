import java.awt.*;
import java.util.StringTokenizer;

public class Circle extends Figura {
    protected Point p1,p2;
    protected int diameter;
    
    public Circle(int x1, int y1, int x2, int y2, Color cor) {
        super(cor);
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    public void setP1(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setP2(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public Point getP1()
    {
        return this.p1;
    }

    public Point getP2()
    {
        return this.p2;
    }

    

    private void calculateSize()
    {
        int deltaX, deltaY;

        deltaX = this.p1.getX() - this.p2.getX();

        if(deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY();

        if(deltaY < 0)
            deltaY = -deltaY;

        if(deltaX >= deltaY) //vejo se a distancia entre os dois pontos é maior no eixo X ou no eixo Y
        {
            this.diametro = deltaX;                              //sendo no eixo x:
                                                                
        }
        else 
        {
            this.diametro = deltaY; 
        }
    }

    public String toString()
    {
        return "c:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getColor().getRed() +
               ":" +
               this.getColor().getGreen() +
               ":" +
               this.getColor().getBlue();
    }

    //outros metodos
    @Override
    public Object clone()
    {
        Circle clone = null;
        try
        {
            clone = new Circle(this);
        }
        catch(Exception e)
        {}      
        return clone;
    }

    public Circle(Circle modelo) throws Exception{
        if(modelo==null){
            throw new Exception("nao pode ser nulo");
        }
        this.p1 = modelo.p1;
        this.p2 = modelo.p2;
        this.diameter = modelo.diameter;
        this.diameter = modelo.diameter;

    }
    

    @Override
    public int hashCode()
    {
        int ret = 111;
        ret = ret *5 + this.p1.hashCode();
        ret = ret * 5 + this.p2.hashCode();
        ret = ret * 5 + new Integer(this.diameter).hashCode();
        ret = ret * 5 + new Integer(this.diameter).hashCode();
        if(ret<0)
            ret = -ret;

        return ret;
    }






}
