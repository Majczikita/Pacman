import javax.swing.*;
import java.awt.*;

public class Entity extends JLabel {
    protected int size;
    protected ImageIcon icon;
    protected int direction;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    public Entity(String path, int x, int y){
        size = Block.BLOCK_LENGTH;
        setBounds(x, y, size, size);
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
}
