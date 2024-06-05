import java.util.Random;

public class Bonus extends Block{
    private static final int seconds = 5;
    private BonusType type;
    private static PointsValueThread pointsValueThread;
    private static DoubleSpeedThread doubleSpeedThread;
    private static Thread activePointsThread;
    private static Thread activeSpeedThread;

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
        type = BonusType.doubleSpeed;
//        switch (r){
//            case 0 -> type = BonusType.doublePoint;
//            case 1 -> type = BonusType.doubleSpeed;
//            case 2 -> type = BonusType.triplePoints;
//            case 3 -> type = BonusType.ghostsStop;
//            case 4 -> type = BonusType.ghostsBackToStart;
//        }
    }

    public void collected() throws InterruptedException {
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
        else if(type == BonusType.doubleSpeed){
            doubleSpeed();
        }
    }

    public void ghostsBackToStart() throws InterruptedException {
        GhostWalkingThread.setRun(false);
        for(Ghost ghost : ghosts){
            GameHandler.runnableThreadMap.get(ghost.thread).join();
            GameHandler.runnableThreadMap.remove(ghost.thread);
        }
        GhostWalkingThread.setRun(true);
        for(Ghost ghost : ghosts){
            ghost.setStartingPosition();
            Thread newThread = new Thread(ghost.thread);
            GameHandler.runnableThreadMap.put(ghost.thread, newThread);
            newThread.start();
        }
    }
    public void doublePoints() {
        if(pointsValueThread!=null){
            pointsValueThread.stop();
        }
        pointsValueThread = new PointsValueThread(seconds, activePointsThread, 2);
        activePointsThread = new Thread(pointsValueThread);
        activePointsThread.start();
    }

    public void triplePoints(){
        if(pointsValueThread!=null){
            pointsValueThread.stop();
        }
        pointsValueThread = new PointsValueThread(seconds, activePointsThread, 3);
        activePointsThread = new Thread(pointsValueThread);
        activePointsThread.start();
    }
    public void ghostsStop() throws InterruptedException {
        GhostWalkingThread.setRun(false);
        for(Ghost ghost : ghosts){
            GameHandler.runnableThreadMap.get(ghost.thread).join();
            GameHandler.runnableThreadMap.remove(ghost.thread);
        }
    }
    public void doubleSpeed(){
        if(doubleSpeedThread!=null){
            doubleSpeedThread.stop();
        }
        doubleSpeedThread = new DoubleSpeedThread(seconds, activeSpeedThread);
        activeSpeedThread = new Thread(doubleSpeedThread);
        activeSpeedThread.start();
    }
}
