import java.util.Iterator;

public class PacmanWalkingThread extends GameHandler implements Runnable{
    private final Pacman pacman;

    public PacmanWalkingThread(Pacman pacman) {
        this.pacman = pacman;

        addEntityThread(this);
        addThread(this);
    }

    @Override
    public void run() {
        Iterator<Bonus> iteratorBonuses;
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
            iteratorBonuses = Block.bonuses.iterator();
            while (iteratorBonuses.hasNext()) {
                Bonus bonus = iteratorBonuses.next();
                //checking in range
                if ((bonus.getX() == this.pacman.getX() && bonus.getY() == this.pacman.getY()) ||
                    (bonus.getX() - 5 == this.pacman.getX() && bonus.getY() == this.pacman.getY()) ||
                    (bonus.getX() + 5 == this.pacman.getX() && bonus.getY() == this.pacman.getY()) ||
                    (bonus.getX() == this.pacman.getX() && bonus.getY() + 5 == this.pacman.getY()) ||
                    (bonus.getX() == this.pacman.getX() && bonus.getY() - 5 == this.pacman.getY())) {
                    try {
                        bonus.collected();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    iteratorBonuses.remove();
                    break;
                }
            }

            //checking collision with ghosts
            if(pacman.isCollision(newX, newY)){
                endEntityThreads();
                pacman.die();

                //die animation
                String path = pacman.getPathDieAnimation();
                for(int i = 0; i <= 5; i++){
                    pacman.setIcon(pacman.loadIcon(path + i + ".png"));

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(pacman.getLives() == 0){
                    GameHandler.endGame(pacman.getParentWindow());
                }
                pacman.getLivesLabel().setText("Lives: " + pacman.getLives());

                try {
                    startEntityThreads();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
                Thread.sleep(Entity.PACMAN_WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
