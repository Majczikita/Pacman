import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Entity extends JLabel {
    protected int size;
    protected ImageIcon icon1;
    protected ImageIcon icon2;
    protected int direction;
    protected static boolean isThread;
    protected boolean isAnimation;
    protected boolean isWalking;

    //ghost color
    protected ColorEnum color;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    protected static final int NULL = -1;

    protected static final int STEP = 5;
    protected static final int WAIT_TIME = 40;

    //pacman constructor
    public Entity(String path1, String path2){
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        icon1 = loadIcon(path1);
        icon2 = loadIcon(path2);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        isThread = true;
        isAnimation = false;
        isWalking = false;
    }

    //ghost constructor
    public Entity(ColorEnum color){
        this.color = color;
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        direction = RIGHT;
        isThread = true;
    }

    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(size-5, size-5, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }

    public abstract int setStartingX();
    public abstract int setStartingY();
}
