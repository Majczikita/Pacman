public class GhostStopThread extends GameHandler implements Runnable{
    private final int seconds;
    private boolean stop;
    private final Thread activeThread;

    GhostStopThread(int seconds, Thread activeThread){
        this.seconds = seconds;
        this.activeThread = activeThread;
        stop = false;
    }
    @Override
    public void run() {
        if(activeThread!=null && activeThread.isAlive()){
            try {
                activeThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        GhostWalkingThread.setRun(false);
        for(Ghost ghost : Block.ghosts){
            if(GameHandler.runnableThreadMap.get(ghost.thread)!=null){
                try {
                    GameHandler.runnableThreadMap.get(ghost.thread).join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                GameHandler.runnableThreadMap.remove(ghost.thread);
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
                if(GameHandler.runnableThreadMap.get(ghost.thread)==null){
                    Thread newThread = new Thread(ghost.thread);
                    GameHandler.runnableThreadMap.put(ghost.thread, newThread);
                    newThread.start();
                }
            }
        }
    }
    public void stop(){
        stop = true;
    }
}
