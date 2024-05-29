import java.util.Iterator;

public class PacmanWalkingThread extends GameHandler implements Runnable{
    private final Pacman pacman;

    public PacmanWalkingThread(Pacman pacman) {
        addEntityThread(new Thread(this));
        this.pacman = pacman;
    }

    @Override
    public void run() {
        pacman.setStartingPosition();
        while(GameHandler.runEntityThread){
            float blockX = (float) pacman.getX()/Block.BLOCK_LENGTH, blockY = (float) pacman.getY()/Block.BLOCK_LENGTH;
            int newX = pacman.getX(), newY = pacman.getY();

            //checking if pacman gets point
            if(blockX % 1 == 0 && blockY % 1 == 0) {
                Iterator<Path> iterator = Block.pathWithPoints.iterator();
                while (iterator.hasNext()) {
                    Path path = iterator.next();
                    if (path.getX() == this.pacman.getX() && path.getY() == this.pacman.getY()) {
                        path.pointCollected();
                        iterator.remove();
                        pacman.addPoint();
                        break;
                    }
                }
            }
            //checking if pacman gets a bonus

            //checking collision with ghosts
            if(pacman.isCollision(newX, newY)){
                endEntityThreads();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                pacman.die();
                if(pacman.getLives() == 0){
                    GameHandler.endGame(pacman.getParentWindow());
                }
                pacman.getLivesLabel().setText("pacman: " + pacman.getLives());

                startEntityThreads();
                break;
            }

            //checking if pacman can change direction
            if(pacman.direction == Entity.LEFT){
                if(!pacman.changeDirection(pacman.direction, blockX, blockY)) newX = newX - Entity.STEP;
                pacman.changeLocation(newX, newY);

            } else if(pacman.direction == Entity.RIGHT){
                if(!pacman.changeDirection(pacman.direction, blockX, blockY)) newX = newX + Entity.STEP;
                pacman.changeLocation(newX, newY);

            } else if(pacman.direction == Entity.UP){
                if(!pacman.changeDirection(pacman.direction, blockX, blockY)) newY = newY - Entity.STEP;
                pacman.changeLocation(newX, newY);

            } else if(pacman.direction == Entity.DOWN){
                if(!pacman.changeDirection(pacman.direction, blockX, blockY)) newY = newY + Entity.STEP;
                pacman.changeLocation(newX, newY);
            }
            try {
                Thread.sleep(Entity.WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
