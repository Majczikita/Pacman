import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Pacman extends Entity implements KeyListener {
    public Pacman(String path, int x, int y){
        super(path, x, y);
        direction = RIGHT;
    }

    public int getEntitySize() {
        return size;
    }

    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                setLocation(getX()-10, getY());
                if(direction != LEFT){
                    changeIconDirection(direction, LEFT);
                    direction = LEFT;
                }
                break;
            case 'd':
                setLocation(getX()+10, getY());
                if(direction != RIGHT){
                    changeIconDirection(direction, RIGHT);
                    direction = RIGHT;
                }
                break;
            case 'w':
                setLocation(getX(), getY()-10);
                if(direction != UP){
                    changeIconDirection(direction, UP);
                    direction = UP;
                }
                break;
            case 's':
                setLocation(getX(), getY()+10);
                if(direction != DOWN){
                    changeIconDirection(direction, DOWN);
                    direction = DOWN;
                }
                break;
        }
        printDirection();
        System.out.println("X: "+getX()+" Y: "+getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37:
                setLocation(getX()-10, getY());
                if(direction != LEFT) {
                    changeIconDirection(direction, LEFT);
                    direction = LEFT;
                }
                break;
            case 39:
                setLocation(getX()+10, getY());
                if(direction != RIGHT){
                    changeIconDirection(direction, RIGHT);
                    direction = RIGHT;
                }
                break;
            case 38:
                setLocation(getX(), getY()-10);
                if(direction != UP){
                    changeIconDirection(direction, UP);
                    direction = UP;
                }
                break;
            case 40:
                setLocation(getX(), getY()+10);
                if(direction != DOWN){
                    changeIconDirection(direction, DOWN);
                    direction = DOWN;
                }
                break;
        }
        printDirection();
        System.out.println("X: "+getX()+" Y: "+getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void changeIconDirection(int prev, int current){
        icon = rotateIcon(prev - current);
        setIcon(icon);
        System.out.println("rotate: " + (prev-current));
    }
    public ImageIcon rotateIcon(double degrees) {
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
}
