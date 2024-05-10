import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JLabel {
    protected int size;
    protected ImageIcon icon;
    protected int direction;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    public Entity(String path){
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        icon = loadIcon(path);
        setIcon(icon);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
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
