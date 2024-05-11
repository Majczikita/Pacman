import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Pacman extends Entity implements KeyListener {
    public Pacman(String path1, String path2){
        super(path1, path2);
        direction = RIGHT;
    }

    @Override
    public int setStartingX(){
        return (Map.map.get(0).size()/2)*Block.BLOCK_LENGTH;
    }

    @Override
    public int setStartingY() {
        if(Map.map.size() == 17){
            return 9 * Block.BLOCK_LENGTH;
        } else if(Map.map.size() == 21){
            return 11 * Block.BLOCK_LENGTH;
        }else if(Map.map.size() == 20 || Map.map.size() == 18){
            return 10 * Block.BLOCK_LENGTH;
        } else return 0;
    }

    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                if(direction != LEFT){
                    changeIconDirection(direction, LEFT);
                    direction = LEFT;
                }
                changeLocation(getX()-10, getY());
                break;
            case 'd':
                if(direction != RIGHT){
                    changeIconDirection(direction, RIGHT);
                    direction = RIGHT;
                }
                changeLocation(getX()+10, getY());
                break;
            case 'w':
                if(direction != UP){
                    changeIconDirection(direction, UP);
                    direction = UP;
                }
                changeLocation(getX(), getY()-10);
                break;
            case 's':
                if(direction != DOWN){
                    changeIconDirection(direction, DOWN);
                    direction = DOWN;
                }
                changeLocation(getX(), getY()+10);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37:
                if(direction != LEFT) {
                    changeIconDirection(direction, LEFT);
                    direction = LEFT;
                }
                changeLocation(getX()-10, getY());
                break;
            case 39:
                if(direction != RIGHT){
                    changeIconDirection(direction, RIGHT);
                    direction = RIGHT;
                }
                changeLocation(getX()+10, getY());
                break;
            case 38:
                if(direction != UP){
                    changeIconDirection(direction, UP);
                    direction = UP;
                }
                changeLocation(getX(), getY()-10);
                break;
            case 40:
                if(direction != DOWN){
                    changeIconDirection(direction, DOWN);
                    direction = DOWN;
                }
                changeLocation(getX(), getY()+10);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void changeIconDirection(int prev, int current){
        testIcon = rotateIcon(prev - current, testIcon);
        currentIcon = rotateIcon(prev - current, currentIcon);
    }
    public ImageIcon rotateIcon(double degrees, ImageIcon icon) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // Rotate the image
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(degrees), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
        g.drawImage(icon.getImage(), transform, null);
        g.dispose();

        // Create a new ImageIcon from the rotated image
        return new ImageIcon(image);
    }
    public void changeLocation(int x, int y){
        if(direction == RIGHT){
            float blockX = (float)(x-10)/Block.BLOCK_LENGTH, blockY = (float)y/Block.BLOCK_LENGTH;
            if(blockY%1==0){
                if(Map.map.get((int)blockY).get((int)blockX+1)==Map.PATH){
                    setLocation(x, y);
                }
            } else{
                if((Map.map.get((int)blockY).get((int)blockX+1)==Map.PATH) &&
                        (Map.map.get((int)blockY+1).get((int)blockX+1)==Map.PATH)){
                    setLocation(x, y);
                }
            }

        }
        else if(direction == DOWN){
            float blockX = (float)x/Block.BLOCK_LENGTH, blockY = (float)(y-10)/Block.BLOCK_LENGTH;
            if(blockX%1==0){
                if(Map.map.get((int)blockY+1).get((int)blockX)==Map.PATH){
                    setLocation(x, y);
                }
            } else{
                if((Map.map.get((int)blockY+1).get((int)blockX)==Map.PATH) &&
                        (Map.map.get((int)blockY+1).get((int)blockX+1)==Map.PATH)){
                    setLocation(x, y);
                }
            }
        }
        else if(direction == LEFT){
            float blockX = (float)x/Block.BLOCK_LENGTH, blockY = (float)y/Block.BLOCK_LENGTH;
            if(blockY%1==0){
                if(Map.map.get((int)blockY).get((int)blockX)==Map.PATH){
                    setLocation(x, y);
                }
            } else{
                if((Map.map.get((int)blockY).get((int)blockX)==Map.PATH) &&
                        (Map.map.get((int)blockY+1).get((int)blockX)==Map.PATH)){
                    setLocation(x, y);
                }
            }
        }
        else{
            float blockX = (float)x/Block.BLOCK_LENGTH, blockY = (float)y/Block.BLOCK_LENGTH;
            if(blockX%1==0){
                if(Map.map.get((int)blockY).get((int)blockX)==Map.PATH){
                    setLocation(x, y);
                }
            } else{
                if((Map.map.get((int)blockY).get((int)blockX)==Map.PATH) &&
                        (Map.map.get((int)blockY).get((int)blockX+1)==Map.PATH)){
                    setLocation(x, y);
                }
            }
        }

    }
}
