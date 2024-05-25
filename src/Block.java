import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block extends JLabel{
    public static final int BLOCK_LENGTH = 30;
    private int x, y;
    private Color color;
    protected static List<Path> pathWithPoints;

    public Block(int x, int y, Color color){
        setOpaque(true);
        this.x = x;
        this.y = y;
        this.color = color;
        editBlock();
        if(pathWithPoints==null) pathWithPoints = new ArrayList<>();
    }

    public Block(){}

    public void editBlock(){
        setPreferredSize(new Dimension(BLOCK_LENGTH, BLOCK_LENGTH));
        setBounds(x*BLOCK_LENGTH, y*BLOCK_LENGTH, BLOCK_LENGTH, BLOCK_LENGTH);
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

    public static List<Path> getPathWithPoints() {
        return pathWithPoints;
    }
}
