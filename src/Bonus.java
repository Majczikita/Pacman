import java.util.Random;

public class Bonus extends Block{
    private static final int seconds = 5;
    private BonusType type;
    private static PointsValueThread pointsValueThread;
    private static Thread activeThread;

    public Bonus(int x, int y){
        super(x, y);
        getRandomType();
        bonuses.add(this);
    }
    public void getRandomType(){
        Random random = new Random();
        int r = random.nextInt(BonusType.values().length);
        String path = "src/img/bonuses/bonus" + (r + 1) + ".png";
        setIcon(loadIcon(path));
        switch (r){
            case 0 -> type = BonusType.doublePoint;
            case 1 -> type = BonusType.doubleSpeed;
            case 2 -> type = BonusType.triplePoints;
            case 3 -> type = BonusType.ghostsStop;
            case 4 -> type = BonusType.ghostsBackToStart;
        }
    }

    public void collected() {
        setIcon(null);
        if(type == BonusType.ghostsBackToStart){
            ghostsBackToStart();
        }
        else if(type == BonusType.doublePoint){
            doublePoints();
        }
        else if(type == BonusType.triplePoints){
            triplePoints();
        }
    }

    public void ghostsBackToStart(){
        for(Entity ghost : ghosts){
            ghost.setBounds(ghost.setStartingX(), ghost.setStartingY(), BLOCK_LENGTH, BLOCK_LENGTH);
        }
    }
    public void doublePoints() {
        if(pointsValueThread!=null){
            pointsValueThread.stop();
        }
        pointsValueThread = new PointsValueThread(seconds, activeThread, 2);
        activeThread = new Thread(pointsValueThread);
        activeThread.start();
    }

    public void triplePoints(){
        if(pointsValueThread!=null){
            pointsValueThread.stop();
        }
        pointsValueThread = new PointsValueThread(seconds, activeThread, 3);
        activeThread = new Thread(pointsValueThread);
        activeThread.start();
    }
}
