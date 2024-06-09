import java.util.Random;

public class Bonus extends Block{
    //time for witch the bonus is active
    private static final int seconds = 5;
    private BonusType type;
    private static PointsValueRunnable pointsValueRunnable;
    private static DoubleSpeedRunnable doubleSpeedRunnable;
    private static GhostStopRunnable ghostStopRunnable;
    private static Thread activePointsThread;
    private static Thread activeSpeedThread;
    private static Thread activeGhostStopThread;

    public Bonus(int x, int y){
        super(x, y);
        setRandomType();
        bonuses.add(this);
    }
    public void setRandomType(){
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

    public void collected() throws InterruptedException {
        setIcon(null);

        switch (type){
            case BonusType.ghostsBackToStart -> ghostsBackToStart();
            case BonusType.doublePoint -> doublePoints();
            case BonusType.triplePoints -> triplePoints();
            case BonusType.doubleSpeed -> doubleSpeed();
            case BonusType.ghostsStop -> ghostsStop();
        }
    }

    public void ghostsBackToStart() throws InterruptedException {
        //stop ghosts' threads and wait for them to finish
        GhostWalkingThread.setRun(false);
        for(Ghost ghost : ghosts){
            if(GameHandler.runnableThreadMap.get(ghost.getThread())!=null) {
                GameHandler.runnableThreadMap.get(ghost.getThread()).join();
                GameHandler.runnableThreadMap.remove(ghost.getThread());
            }
        }

        //rerun ghosts' threads from the starting point
        GhostWalkingThread.setRun(true);
        for(Ghost ghost : ghosts){
            ghost.setStartingPosition();
            if(activeGhostStopThread == null || !activeGhostStopThread.isAlive()) {
                Thread newThread = new Thread(ghost.getThread());
                GameHandler.runnableThreadMap.put(ghost.getThread(), newThread);
                newThread.start();
            }
        }
    }
    public void doublePoints() {
        //if another thread changing value of points is active - stop it
        if(pointsValueRunnable != null){
            pointsValueRunnable.stop();
        }
        pointsValueRunnable = new PointsValueRunnable(seconds, activePointsThread, 2);
        activePointsThread = new Thread(pointsValueRunnable);
        activePointsThread.start();
    }

    public void triplePoints(){
        //if another thread changing value of points is active - stop it
        if(pointsValueRunnable != null){
            pointsValueRunnable.stop();
        }
        pointsValueRunnable = new PointsValueRunnable(seconds, activePointsThread, 3);
        activePointsThread = new Thread(pointsValueRunnable);
        activePointsThread.start();
    }
    public void ghostsStop() {
        //if another thread stopping ghosts is active - stop it
        if(ghostStopRunnable !=null){
            ghostStopRunnable.stop();
        }
        ghostStopRunnable = new GhostStopRunnable(seconds, activeGhostStopThread);
        activeGhostStopThread = new Thread(ghostStopRunnable);
        activeGhostStopThread.start();
    }
    public void doubleSpeed(){
        //if another thread speeding pacman is active - stop it
        if(doubleSpeedRunnable != null){
            doubleSpeedRunnable.stop();
        }
        doubleSpeedRunnable = new DoubleSpeedRunnable(seconds, activeSpeedThread);
        activeSpeedThread = new Thread(doubleSpeedRunnable);
        activeSpeedThread.start();
    }
}

