
/**
 * A class for image resizing and the check 'em code.
 * 
 * @author David Chu & Brian Libby 
 * @version 1/26/2012
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
public class Utilities
{
    public static BufferedImage resizeFullImage(BufferedImage original) { //resizes the larger images to make them fit the window
        if (original.getWidth() < 900 && original.getHeight() < 600) return original;
        int newX;
        int newY;
        if (original.getWidth() - 900 < original.getHeight() - 600) {
            newX = 900;
            newY = original.getHeight() * 900 / original.getWidth();
            if (newY > 600) {
                newX = original.getWidth() * 600 / original.getHeight();
                newY = 600;
            }
        } else {
            newX = original.getWidth() * 600 / original.getHeight();
            newY = 600;
            if (newX > 900) {
                newX = 900;
                newY = original.getHeight() * 900 / original.getWidth();
            }
        }
        return resizeImage(original, newX, newY, BufferedImage.TYPE_INT_RGB);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight, int type){
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage,0,0,newWidth,newHeight,null);
        g.dispose();
        System.gc();
        return resizedImage;
    }

    public static void checkEm(BufferedImage[] images, BufferedImage bufferImage, Graphics g, Viewer frame) {
        int firstNum = (int)(Math.random()*images.length);
        int secondNum = (int)(Math.random()*images.length);
        frame.drawPopOver();
        frame.drawPopOver();
        frame.drawPopOver();
        g.drawImage(images[firstNum], 420-images[firstNum].getWidth(), 347 -images[firstNum].getHeight()/2, frame);
        g.drawImage(images[secondNum], 480, 347-images[secondNum].getHeight()/2, frame);
        frame.gotDubs.setText("");
        if (firstNum==secondNum) {
            frame.gotDubs.setText("You got dubs!");
        }
        else {
            frame.gotDubs.setText("No dubs!");
        }
        frame.getGraphics().drawImage(bufferImage, 0, 0, frame);
    }
}
