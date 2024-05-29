import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends Block {
    protected int direction;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;

    protected static final int NULL = -1;

    protected static final int STEP = 5;
    protected static final int WAIT_TIME = 40;

    protected static List<Entity> ghosts;
    protected static List<Entity> entities;


    public Entity(){
        setBounds(setStartingX(), setStartingY(), BLOCK_LENGTH, BLOCK_LENGTH);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        if(ghosts==null) ghosts = new ArrayList<>();
        if(entities==null) entities = new ArrayList<>();
        entities.add(this);
    }

    public static void clearEntities(){
        if(!(entities==null) && !entities.isEmpty()){
            entities.clear();
        }
    }
    public void setStartingPosition(){
        this.setBounds(this.setStartingX(), this.setStartingY(), Block.BLOCK_LENGTH, Block.BLOCK_LENGTH);
    }
    public abstract int setStartingX();
    public abstract int setStartingY();
}
