import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Entity extends JLabel {
    protected int size;
    protected int direction;
    protected static boolean isThread;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    protected static final int NULL = -1;

    protected static final int STEP = 5;
    protected static final int WAIT_TIME = 40;

    protected static List<Entity> ghosts;

    public Entity(){
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        isThread = true;
        if(ghosts==null) ghosts = new ArrayList<>();
    }

    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(size-5, size-5, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }

    public void deleteEntity(){

    }

    public abstract int setStartingX();
    public abstract int setStartingY();
}
