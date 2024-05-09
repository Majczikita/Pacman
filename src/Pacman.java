import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pacman extends Entity implements KeyListener {
    public Pacman(String path, int x, int y){
        super(path, x, y);
    }

    public int getEntitySize() {
        return size;
    }

    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a': setLocation(getX()-10, getY());
                break;
            case 'd': setLocation(getX()+10, getY());
                break;
            case 'w': setLocation(getX(), getY()-10);
                break;
            case 's': setLocation(getX(), getY()+10);
                break;
        }
        System.out.print("X: "+getX()+" Y: "+getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37: setLocation(getX()-10, getY());
                break;
            case 39: setLocation(getX()+10, getY());
                break;
            case 38: setLocation(getX(), getY()-10);
                break;
            case 40: setLocation(getX(), getY()+10);
                break;
        }
        System.out.print("X: "+getX()+" Y: "+getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
