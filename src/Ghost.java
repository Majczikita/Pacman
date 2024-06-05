import java.util.List;
import java.util.Random;

public class Ghost extends Entity {
    private String pathR, pathU, pathL, pathD;
    private String mainPath = "src/img";
    private final ColorEnum color;
    public GhostWalkingThread thread;

    public Ghost(ColorEnum colorEnum){
        this.color = colorEnum;
        startingConfig();
        setStartingPosition();
        setIcon(loadIcon(pathR));
        ghosts.add(this);
        new BonusThread(this);
        thread = new GhostWalkingThread(this);
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
        if(color == ColorEnum.PINK || color == ColorEnum.RED) return (Map.map.get(0).size()/2)*BLOCK_LENGTH;
        else if (color == ColorEnum.BLUE)return (Map.map.get(0).size()/2-1)*BLOCK_LENGTH;
        else return (Map.map.get(0).size()/2+1)*BLOCK_LENGTH;
    }

    @Override
    public int setStartingY() {
        if(color == ColorEnum.BLUE || color == ColorEnum.ORANGE || color == ColorEnum.PINK){
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 7 * BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 9 * BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 8 * BLOCK_LENGTH;
            } else return 0;
        } else{
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 5 * BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 7 * BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 6 * BLOCK_LENGTH;
            } else return 0;
        }

    }

}
