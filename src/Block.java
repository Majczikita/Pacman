import javax.swing.*;
import java.awt.*;

public class Block extends JLabel{
    public static final int BLOCK_LENGTH = 30;
    private int x, y;
    private Color color;

    public Block(int x, int y, Color color){
        setOpaque(true);
        this.x = x;
        this.y = y;
        this.color = color;
        editBlock();
    }

    public void editBlock(){
        setPreferredSize(new Dimension(BLOCK_LENGTH, BLOCK_LENGTH));
        setBounds(x*BLOCK_LENGTH, y*BLOCK_LENGTH, BLOCK_LENGTH, BLOCK_LENGTH);
        setBackground(color);
        setVisible(true);
    }
}
