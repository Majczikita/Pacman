import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Pacman extends Entity implements KeyListener, Runnable {
    private ImageIcon icon1;
    private ImageIcon icon2;
    private int savedDirection;
    private boolean isAnimation;
    private boolean isWalking;
    private int lives;

    private final Map parentWindow;
    private JLabel livesLabel;
    public static int pointsCollected;

    public Pacman(String path1, String path2, JLabel livesLabel, Map parentWindow){
        icon1 = loadIcon(path1);
        icon2 = loadIcon(path2);
        this.parentWindow = parentWindow;
        pointsCollected = 0;
        lives = 3;
        direction = RIGHT;
        savedDirection = NULL;
        isAnimation = false;
        isWalking = false;
        walkingThread = new Thread(this);
        this.livesLabel = livesLabel;
        this.livesLabel.setText("Lives: " + lives);
    }
    @Override
    public void run(){
        //ANIMATION THREAD
        if(!isAnimation){
            isAnimation = true;
            while(isThread) {
                setIcon(icon1);
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                setIcon(icon2);
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //WALKING THREAD
        if(isAnimation && !isWalking){
            isWalking = true;
            while(isThread){
                float blockX = (float) getX()/Block.BLOCK_LENGTH, blockY = (float) getY()/Block.BLOCK_LENGTH;
                int newX = getX(), newY = getY();

                //checking if pacman gets point
                if(blockX % 1 == 0 && blockY % 1 == 0) {
                    Iterator<Path> iterator = pathWithPoints.iterator();
                    while (iterator.hasNext()) {
                        Path path = iterator.next();
                        if (path.getX() == this.getX() && path.getY() == this.getY()) {
                            path.pointCollected();
                            iterator.remove();
                            pointsCollected++;
                            break;
                        }
                    }
                }

                //checking collision with ghosts
                if(isCollision(newX, newY)){
                    isThread = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    isAnimation = false;
                    isWalking = false;
                    lives--;
                    if(lives == 0){
                        GameHandler.endGame(parentWindow);
                    }
                    this.livesLabel.setText("Lives: " + lives);
                    startThreads();
                    break;
                }

                //checking if pacman can change direction
                if(direction == LEFT){
                    if(!changeDirection(direction, blockX, blockY)) newX = newX - STEP;
                    changeLocation(newX, newY);

                } else if(direction == RIGHT){
                    if(!changeDirection(direction, blockX, blockY)) newX = newX + STEP;
                    changeLocation(newX, newY);

                } else if(direction == UP){
                    if(!changeDirection(direction, blockX, blockY)) newY = newY - STEP;
                    changeLocation(newX, newY);

                } else if(direction == DOWN){
                    if(!changeDirection(direction, blockX, blockY)) newY = newY + STEP;
                    changeLocation(newX, newY);
                }
                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean isCollision(float x, float y){
        for(Entity ghost : ghosts){
            //checking collision in range because of multi-threading
            if((int)x == ghost.getX() && (int)y == ghost.getY()) return true;
            if((int)x == ghost.getX()-STEP && (int)y == ghost.getY()) return true;
            if((int)x == ghost.getX()+STEP && (int)y == ghost.getY()) return true;
            if((int)x == ghost.getX() && (int)y == ghost.getY()-STEP) return true;
            if((int)x == ghost.getX() && (int)y == ghost.getY()+STEP) return true;
        }
        return false;
    }

    public boolean changeDirection(int currentDirection, float x, float y){
        if(savedDirection!=NULL && savedDirection!=currentDirection){
            if(tryLeft(x, y)) return true;
            if(tryRight(x, y)) return true;
            if(tryUp(x, y)) return true;
            return tryDown(x, y);
        }
        return false;
    }

    public boolean tryUp(float x, float y){
        if(direction!=UP && savedDirection == UP && x%1==0 && Map.map.get((int)y-1).get((int)x) == Map.PATH){
            changeIconDirection(direction, savedDirection);
            savedDirection = NULL;
            direction = UP;
            return true;
        }
        return false;
    }
    public boolean tryDown(float x, float y){
        if(direction!=DOWN && savedDirection == DOWN && x%1==0 && Map.map.get((int)y+1).get((int)x) == Map.PATH){
            changeIconDirection(direction, savedDirection);
            savedDirection = NULL;
            direction = DOWN;
            return true;
        }
        return false;
    }
    public boolean tryLeft(float x, float y){
        if(direction!=LEFT && savedDirection == LEFT && y%1==0 && Map.map.get((int)y).get((int)x-1) == Map.PATH){
            changeIconDirection(direction, savedDirection);
            savedDirection = NULL;
            direction = LEFT;
            return true;
        }
        return false;
    }
    public boolean tryRight(float x, float y){
        if(direction!=RIGHT && savedDirection == RIGHT && y%1==0 && Map.map.get((int)y).get((int)x+1) == Map.PATH){
            changeIconDirection(direction, savedDirection);
            savedDirection = NULL;
            direction = RIGHT;
            return true;
        }
        return false;
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
        }else if(Map.map.size() == 20 || Map.map.size() == 19){
            return 10 * Block.BLOCK_LENGTH;
        } else return 0;
    }

    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                if(savedDirection != LEFT){
                    savedDirection = LEFT;
                }
                break;
            case 'd':
                if(savedDirection != RIGHT){
                    savedDirection = RIGHT;
                }
                break;
            case 'w':
                if(savedDirection != UP){
                    savedDirection = UP;
                }
                break;
            case 's':
                if(savedDirection != DOWN){
                    savedDirection = DOWN;
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37:
                if(savedDirection != LEFT) {
                    savedDirection = LEFT;
                }
                break;
            case 39:
                if(savedDirection != RIGHT){
                    savedDirection = RIGHT;
                }
                break;
            case 38:
                if(savedDirection != UP){
                    savedDirection = UP;
                }
                break;
            case 40:
                if(savedDirection != DOWN){
                    savedDirection = DOWN;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void changeIconDirection(int prev, int current){
        icon1 = rotateIcon(prev - current, icon1);
        icon2 = rotateIcon(prev - current, icon2);
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
            float blockX = (float)(x-STEP)/Block.BLOCK_LENGTH, blockY = (float)y/Block.BLOCK_LENGTH;
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
            float blockX = (float)x/Block.BLOCK_LENGTH, blockY = (float)(y-STEP)/Block.BLOCK_LENGTH;
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
