public class GhostStopRunnable extends GameHandler implements Runnable{
    private final int seconds;
    private boolean stop;
    private final Thread activeThread;

    GhostStopRunnable(int seconds, Thread activeThread){
        this.seconds = seconds;
        this.activeThread = activeThread;
        stop = false;
    }
    @Override
    public void run() {
        waitForThread(activeThread);

        GhostWalkingThread.setRun(false);
        for(Ghost ghost : Block.ghosts){
            if(GameHandler.runnableThreadMap.get(ghost.getThread())!=null){
                try {
                    GameHandler.runnableThreadMap.get(ghost.getThread()).join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                GameHandler.runnableThreadMap.remove(ghost.getThread());
            }
        }

        for(int i = 0; i <= seconds; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!runEntityThread || !runThread || stop){
                GhostWalkingThread.setRun(true);
                return;
            }
        }
        GhostWalkingThread.setRun(true);
        if(runThread && !stop && runEntityThread){
            for(Ghost ghost : Block.ghosts){
                if(GameHandler.runnableThreadMap.get(ghost.getThread())==null){
                    Thread newThread = new Thread(ghost.getThread());
                    GameHandler.runnableThreadMap.put(ghost.getThread(), newThread);
                    newThread.start();
                }
            }
        }
    }
    public void stop(){
        stop = true;
    }
}
