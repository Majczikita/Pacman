import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block extends JLabel{
    public static final int BLOCK_LENGTH = 30;
    public static int POINT_VALUE = 1;
    private int x, y;
    private Color color;
    protected static List<Path> pathWithPoints;
    protected static List<Bonus> bonuses;
    protected static List<Entity> ghosts;
    protected static List<Entity> entities;

    public Block(int x, int y, Color color){
        setOpaque(true);
        this.x = x*BLOCK_LENGTH;
        this.y = y*BLOCK_LENGTH;
        this.color = color;
        editBlock();
        if(pathWithPoints==null) pathWithPoints = new ArrayList<>();
        if(bonuses==null) bonuses = new ArrayList<>();
    }
    public Block(int x, int y){
        setOpaque(false);
        this.x = x;
        this.y = y;
        this.color = null;
        editBlock();
    }

    public Block(){}

    public void editBlock(){
        setPreferredSize(new Dimension(BLOCK_LENGTH, BLOCK_LENGTH));
        setBounds(x, y, BLOCK_LENGTH, BLOCK_LENGTH);
        setBackground(color);
        setVisible(true);
    }
    public ImageIcon loadIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(Block.BLOCK_LENGTH - 5, Block.BLOCK_LENGTH - 5, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return icon;
    }
    public static void clearPaths(){
        pathWithPoints.clear();
    }
    public static void clearBonuses(){
        bonuses.clear();
    }

    public static List<Path> getPathWithPoints() {
        return pathWithPoints;
    }
}
