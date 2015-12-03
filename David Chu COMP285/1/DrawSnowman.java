import javax.swing.JApplet;
import java.awt.Graphics;
public class DrawSnowman extends JApplet
{
    public void paint (Graphics canvas)
    {
        canvas.drawOval (105, 30, 200, 200);
        canvas.fillOval (155, 80, 10, 20);
        canvas.fillOval (230, 80, 10, 20);
        canvas.drawArc (150, 140, 100, 50, 180, 180);
        
        //bottom two
        
        canvas.drawOval(80, 230, 250, 250);
        canvas.drawOval(55, 480, 300, 300);
    }
}