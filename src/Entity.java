import javax.swing.*;
import java.awt.*;

public class Entity extends JLabel {
    protected int size;

    public Entity(String path, int x, int y){
        size = Block.BLOCK_LENGTH-5;
        setBounds(x, y, size, size);
        setIcon(loadIcon(path));
    }

    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }
}
