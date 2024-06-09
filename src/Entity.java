import javax.swing.*;
import java.util.ArrayList;

public abstract class Entity extends Block {
    protected int direction;

    protected static final int UP = 180;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 0;
    protected static final int LEFT = 270;
    protected static final int NULL = -1;

    protected static int STEP = 5;
    protected static int WAIT_TIME = 40;
    protected static int PACMAN_WAIT_TIME = WAIT_TIME;


    public Entity(){
        setBounds(getStartingX(), getStartingY(), BLOCK_LENGTH, BLOCK_LENGTH);
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
        this.setBounds(this.getStartingX(), this.getStartingY(), Block.BLOCK_LENGTH, Block.BLOCK_LENGTH);
    }
    public abstract int getStartingX();
    public abstract int getStartingY();
}
