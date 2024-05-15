import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Entity implements Runnable{
    private String pathR, pathU, pathL, pathD;
    private String mainPath = "src/img";

    public Ghost(ColorEnum colorEnum){
        super(colorEnum);
        this.color = colorEnum;
        startingConfig();
        setIcon(loadIcon(pathR));
    }

    @Override
    public void run() {
        float blockX, blockY;
        int newX, newY;
        List<Integer> availableDirections = new ArrayList<>();

        while(isThread){
            blockX = (float) getX()/Block.BLOCK_LENGTH;
            blockY = (float) getY()/Block.BLOCK_LENGTH;
            newX = getX();
            newY = getY();
            availableDirections.clear();
            switchIcon();

            if(direction == RIGHT){
                if(blockX%1==0 && blockY%1==0){
                    if(canTurn(UP, (int)blockX, (int)blockY)) availableDirections.add(UP);
                    if(canTurn(DOWN, (int)blockX, (int)blockY)) availableDirections.add(DOWN);
                    if(canTurn(RIGHT, (int)blockX, (int)blockY)) availableDirections.add(RIGHT);

                    direction = makeStep(newX, newY, availableDirections, LEFT);
                } else setLocation(newX + STEP, newY);
            } else if(direction == DOWN){
                if(blockX%1==0 && blockY%1==0){
                    if(canTurn(LEFT, (int)blockX, (int)blockY)) availableDirections.add(LEFT);
                    if(canTurn(DOWN, (int)blockX, (int)blockY)) availableDirections.add(DOWN);
                    if(canTurn(RIGHT, (int)blockX, (int)blockY)) availableDirections.add(RIGHT);

                    direction = makeStep(newX, newY, availableDirections, UP);
                } else setLocation(newX, newY + STEP);
            } else if(direction == LEFT){
                if(blockX%1==0 && blockY%1==0){
                    if(canTurn(LEFT, (int)blockX, (int)blockY)) availableDirections.add(LEFT);
                    if(canTurn(DOWN, (int)blockX, (int)blockY)) availableDirections.add(DOWN);
                    if(canTurn(UP, (int)blockX, (int)blockY)) availableDirections.add(UP);

                    direction = makeStep(newX, newY, availableDirections, RIGHT);
                } else setLocation(newX - STEP, newY);
            } else if(direction == UP){
                if(blockX%1==0 && blockY%1==0){
                    if(canTurn(LEFT, (int)blockX, (int)blockY)) availableDirections.add(LEFT);
                    if(canTurn(UP, (int)blockX, (int)blockY)) availableDirections.add(UP);
                    if(canTurn(RIGHT, (int)blockX, (int)blockY)) availableDirections.add(RIGHT);

                    direction = makeStep(newX, newY, availableDirections, DOWN);
                } else setLocation(newX, newY - STEP);
            }

            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean canTurn(int direction, int x, int y){
        return switch (direction) {
            case UP -> (Map.map.get(y - 1).get(x) == Map.PATH);
            case RIGHT -> (Map.map.get(y).get(x + 1) == Map.PATH);
            case DOWN -> (Map.map.get(y + 1).get(x) == Map.PATH);
            case LEFT -> (Map.map.get(y).get(x - 1) == Map.PATH);
            default -> false;
        };
    }

    public void switchIcon(){
        switch(direction){
            case UP -> setIcon(loadIcon(pathU));
            case LEFT -> setIcon(loadIcon(pathL));
            case RIGHT -> setIcon(loadIcon(pathR));
            case DOWN -> setIcon(loadIcon(pathD));
        }
    }

    public int pickRandomDirection(List<Integer> directions){
        Random random = new Random();
        return random.nextInt(directions.size());
    }

    public int makeStep(int x, int y, List<Integer> list, int opposite){
        int newDirection;
        if(list.size()==1) newDirection = list.getFirst();
        else if(list.isEmpty()) newDirection = opposite;
        else newDirection = list.get(pickRandomDirection(list));

        switch (newDirection) {
            case UP -> setLocation(x, y - STEP);
            case RIGHT -> setLocation(x + STEP, y);
            case DOWN -> setLocation(x, y + STEP);
            case LEFT -> setLocation(x - STEP, y);
        }
        return newDirection;
    }

    public void startingConfig(){
        switch(color){
            case RED:
                mainPath+="/redGhost";
                break;
            case PINK:
                mainPath+="/pinkGhost";
                break;
            case ORANGE:
                mainPath+="/orangeGhost";
                break;
            case BLUE:
                mainPath+="/blueGhost";
                break;
        }
        pathR = mainPath + "/right.png";
        pathL = mainPath + "/left.png";
        pathU = mainPath + "/up.png";
        pathD = mainPath + "/down.png";
    }

    @Override
    public int setStartingX() {
        if(color == ColorEnum.PINK || color == ColorEnum.RED) return (Map.map.get(0).size()/2)*Block.BLOCK_LENGTH;
        else if (color == ColorEnum.BLUE)return (Map.map.get(0).size()/2-1)*Block.BLOCK_LENGTH;
        else return (Map.map.get(0).size()/2+1)*Block.BLOCK_LENGTH;
    }

    @Override
    public int setStartingY() {
        if(color == ColorEnum.BLUE || color == ColorEnum.ORANGE || color == ColorEnum.PINK){
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 7 * Block.BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 9 * Block.BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 8 * Block.BLOCK_LENGTH;
            } else return 0;
        } else{
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 5 * Block.BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 7 * Block.BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 6 * Block.BLOCK_LENGTH;
            } else return 0;
        }

    }

}
