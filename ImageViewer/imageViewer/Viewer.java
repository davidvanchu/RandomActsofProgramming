/**
 * This application will display a folder of images in thumbnails. The user can then click on a photo and enlarge it to view it better. Also, theres an easter egg
 * where you can click the Check 'em button and it will randomly display two images, and if they are the same, you win!
 *
 *
 * @author David Chu & Brian Libby
 * @updated 1/26/12 (v1.2)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.*;
import java.applet.*;
import javax.imageio.*;
public class Viewer extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    String randomFile, directory = "";
    File dir;
    JButton dubsCheckButton = new JButton("Check 'em");
    JLabel gotDubs = new JLabel(""); //label for either yes, you got dubs, or no, you got no dubs.
    String[] allTheFiles;
    BufferedImage originalImages[], resizedImages[];
    BufferedImage bufferImage = new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);
    Image img;
    Graphics bufferGraphics = bufferImage.getGraphics();
    int mouseBegin, mouseEnd, newPosition = 0;
    boolean isBig;
    public static void main( String[] args ) {
        Viewer frame = new Viewer();
        frame.setSize(500,500);
        frame.reader();
        frame.init();
        frame.makeArrays();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void init() {
        setBounds(100,100,900,600);

        Container screen = getContentPane();
        screen.setBackground(Color.lightGray);
        screen.setLayout (new FlowLayout() );
        screen.add(dubsCheckButton); //Just a little easter egg. If you click on the button, it chooses two images at random and shows them to you side by side.
        screen.add(gotDubs);

        dubsCheckButton.addActionListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void reader() {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        directory = fc.getSelectedFile().getName();
        dir = fc.getSelectedFile();
        // System.out.println(directory);
        allTheFiles = dir.list();
        originalImages = new BufferedImage[allTheFiles.length];
        for (int i=0; i<allTheFiles.length; i++) {
            // Get filename of file or directory
            String filename = allTheFiles[i];
            // System.out.println(filename);
        }
    }

    public void paint (Graphics g)
    {
        super.paint(g);
        bufferGraphics.setColor(Color.lightGray);
        bufferGraphics.fillRect(0,0,2000,2000);
        draw(bufferGraphics,0);
    }

    public void draw(Graphics g, int xcoord) {
        allTheFiles = dir.list();
        for (int i=0; i<allTheFiles.length; i++) {
            bufferGraphics.drawImage(resizedImages[i],((200*i)+20*(i+1))+(xcoord+newPosition),200,this);
        }   
        getGraphics().drawImage(bufferImage,0,0,this);
    }

    public void drawPopOver(){ //draws a transparent backdrop over the images to make the enlarged image look nice :D.
        bufferGraphics.setColor(new Color(0,0,0,128));
        bufferGraphics.fillRect(0,0,900,600);
    }

    public void drawFullImage(int index) { //draws the resized images, index is the image to enlarge
        BufferedImage image = Utilities.resizeFullImage(originalImages[index]);
        int xPos = 450 - (image.getWidth() / 2);
        int yPos = 300 - (image.getHeight() / 2);
        bufferGraphics.drawImage(image, xPos, yPos, this);
    }

    public void actionPerformed(ActionEvent thisEvent)
    {
        Object source = thisEvent.getSource();
        if (source == dubsCheckButton)
        {
            Utilities.checkEm(resizedImages, bufferImage, bufferGraphics, this);
        }
    }

    public void makeArrays() {
        for (int i=0; i<allTheFiles.length; i++) {
            try
            {BufferedImage originalImage=(BufferedImage)javax.imageio.ImageIO.read(new File(dir+"\\"+allTheFiles[i]));
                originalImages[i]=originalImage;
            }
            catch (IOException e) {}
        }
        resizedImages = new BufferedImage[originalImages.length];
        for (int i=0; i<originalImages.length; i++) {
            BufferedImage originalImage=originalImages[i];

            int height_threshold = 200;
            int width_threshold = 200;

            int height=originalImage.getHeight();
            int width=originalImage.getWidth();

            int newWidth,newHeight;
            if (width>height){
                newWidth = width_threshold;
                newHeight= originalImage.getHeight() * height_threshold / originalImage.getWidth();
            }
            else{
                newHeight = height_threshold;
                newWidth = originalImage.getWidth() * width_threshold / originalImage.getHeight();
            }

            BufferedImage resizedImage = Utilities.resizeImage(originalImage,newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
            resizedImages[i]=resizedImage;
        }
    }

    public void mousePressed (MouseEvent e) {
        mouseBegin = e.getX();
        gotDubs.setText("");
        //System.out.println(mouseBegin+" = "+e.getX());
    }

    public void mouseReleased (MouseEvent e) {
        mouseEnd = e.getX();
        newPosition = newPosition + (mouseEnd - mouseBegin); //this keeps track of where the current "scroll position" is.
        //System.out.println(newPosition);
    }

    public void mouseEntered (MouseEvent e) {

    }

    public void mouseExited (MouseEvent e) {

    }

    public void mouseClicked (MouseEvent e) { //detects which picture you clicked on so that it enlarges the correct one
        int x = e.getX() + Math.abs(newPosition);
        int y = e.getY();
        //System.out.println(y);
        int pictureID = x/220;
        gotDubs.setText("");
        // System.out.println(pictureID);
        if (isBig==false) {
            if (y>200 && y<400) {
                drawPopOver();
                drawFullImage(pictureID);
                getGraphics().drawImage(bufferImage, 0, 0, this);
                isBig=true;
            }
        }

        else if (isBig==true) {
            bufferGraphics.setColor(Color.lightGray);
            bufferGraphics.fillRect(0,0,900,600);
            draw(bufferGraphics,e.getX()-mouseBegin);
            getGraphics().drawImage(bufferImage, 0, 0, this);
            isBig=false;

        }

    }
    public void mouseDragged (MouseEvent e) {
        int x = e.getX();
        gotDubs.setText("");
        for (int i=0; i<resizedImages.length; i++) {
            bufferGraphics.setColor(Color.lightGray);
            bufferGraphics.fillRect(0,0,2000,2000);
            int xcoord = x-mouseBegin;
            if(xcoord+newPosition>0||xcoord+newPosition<=-220*(resizedImages.length-4)){
                mouseBegin=e.getX();
            }
            else {
                newPosition=xcoord+newPosition;
                mouseBegin=e.getX();
                draw(bufferGraphics,xcoord);
                //System.out.println(xcoord + "yay" + i + mouseBegin);
            }
        }
    }

    public void mouseMoved (MouseEvent e) {

    }
}