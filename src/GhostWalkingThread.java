import java.util.ArrayList;
import java.util.List;

public class GhostWalkingThread extends GameHandler implements Runnable{
    private final Ghost ghost;

    public GhostWalkingThread(Ghost ghost) {
        this.ghost = ghost;
        addEntityThread(this);
        addThread(this);
    }

    @Override
    public void run() {
        ghost.setBounds(ghost.setStartingX(), ghost.setStartingY(), Block.BLOCK_LENGTH, Block.BLOCK_LENGTH);
        float blockX, blockY;
        int newX, newY;
        List<Integer> availableDirections = new ArrayList<>();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(GameHandler.runEntityThread){
            blockX = (float) ghost.getX()/Block.BLOCK_LENGTH;
            blockY = (float) ghost.getY()/Block.BLOCK_LENGTH;
            newX = ghost.getX();
            newY = ghost.getY();
            availableDirections.clear();
            ghost.switchIcon();

            if(ghost.direction == Entity.RIGHT){
                if(blockX%1==0 && blockY%1==0){
                    if(ghost.canTurn(Entity.UP, (int)blockX, (int)blockY)) availableDirections.add(Entity.UP);
                    if(ghost.canTurn(Entity.DOWN, (int)blockX, (int)blockY)) availableDirections.add(Entity.DOWN);
                    if(ghost.canTurn(Entity.RIGHT, (int)blockX, (int)blockY)) availableDirections.add(Entity.RIGHT);

                    ghost.direction = ghost.makeStep(newX, newY, availableDirections, Entity.LEFT);
                } else ghost.setLocation(newX + Entity.STEP, newY);
            } else if(ghost.direction == Entity.DOWN){
                if(blockX%1==0 && blockY%1==0){
                    if(ghost.canTurn(Entity.LEFT, (int)blockX, (int)blockY)) availableDirections.add(Entity.LEFT);
                    if(ghost.canTurn(Entity.DOWN, (int)blockX, (int)blockY)) availableDirections.add(Entity.DOWN);
                    if(ghost.canTurn(Entity.RIGHT, (int)blockX, (int)blockY)) availableDirections.add(Entity.RIGHT);

                    ghost.direction = ghost.makeStep(newX, newY, availableDirections, Entity.UP);
                } else ghost.setLocation(newX, newY + Entity.STEP);
            } else if(ghost.direction == Entity.LEFT){
                if(blockX%1==0 && blockY%1==0){
                    if(ghost.canTurn(Entity.LEFT, (int)blockX, (int)blockY)) availableDirections.add(Entity.LEFT);
                    if(ghost.canTurn(Entity.DOWN, (int)blockX, (int)blockY)) availableDirections.add(Entity.DOWN);
                    if(ghost.canTurn(Entity.UP, (int)blockX, (int)blockY)) availableDirections.add(Entity.UP);

                    ghost.direction = ghost.makeStep(newX, newY, availableDirections, Entity.RIGHT);
                } else ghost.setLocation(newX - Entity.STEP, newY);
            } else if(ghost.direction == Entity.UP){
                if(blockX%1==0 && blockY%1==0){
                    if(ghost.canTurn(Entity.LEFT, (int)blockX, (int)blockY)) availableDirections.add(Entity.LEFT);
                    if(ghost.canTurn(Entity.UP, (int)blockX, (int)blockY)) availableDirections.add(Entity.UP);
                    if(ghost.canTurn(Entity.RIGHT, (int)blockX, (int)blockY)) availableDirections.add(Entity.RIGHT);

                    ghost.direction = ghost.makeStep(newX, newY, availableDirections, Entity.DOWN);
                } else ghost.setLocation(newX, newY - Entity.STEP);
            }

            try {
                Thread.sleep(Entity.WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
