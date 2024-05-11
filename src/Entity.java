import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Entity extends JLabel implements Runnable {
    protected int size;
    protected ImageIcon currentIcon;
    protected ImageIcon testIcon;
    protected int direction;
    protected boolean isThread;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    public Entity(String path1, String path2){
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        testIcon = loadIcon(path1);
        currentIcon = loadIcon(path2);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        isThread = true;
    }

    @Override
    public void run(){
        while(isThread) {
            setIcon(testIcon);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            setIcon(currentIcon);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(size-5, size-5, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }
    public void printDirection(){
        System.out.println(direction);
    }

    public abstract int setStartingX();
    public abstract int setStartingY();
}
