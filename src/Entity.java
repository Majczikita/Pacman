import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public abstract class Entity extends JLabel implements  Runnable{
    protected static int size;
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
    protected static List<Entity> entities;
    protected Thread thread;
    protected Thread walkingThread;

    public Entity(){
        size = Block.BLOCK_LENGTH;
        setBounds(setStartingX(), setStartingY(), size, size);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        isThread = true;
        thread = new Thread(this);
        if(ghosts==null) ghosts = new ArrayList<>();
        if(entities==null) entities = new ArrayList<>();
        entities.add(this);
    }

    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(size-5, size-5, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }

    public static void startThreads(){
        isThread = true;
        for(Entity entity : entities){
            entity.setBounds(entity.setStartingX(), entity.setStartingY(), size, size);
            entity.thread = new Thread(entity);
            entity.thread.start();

            if(entity instanceof KeyListener){
                entity.walkingThread = new Thread(entity);
                entity.walkingThread.start();
            }
        }
    }

    public abstract int setStartingX();
    public abstract int setStartingY();
}
