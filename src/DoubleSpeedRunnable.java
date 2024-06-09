public class DoubleSpeedRunnable extends GameHandler implements Runnable{
    //seconds of thread to be active
    private final int seconds;
    private boolean stop;
    private final Thread activeThread;

    DoubleSpeedRunnable(int seconds, Thread activeThread){
        this.seconds = seconds;
        this.activeThread = activeThread;
        stop = false;
    }
    @Override
    public void run() {
        waitForThread(activeThread);

        Entity.PACMAN_WAIT_TIME/=2;
        for(int i = 0; i <= seconds; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!runThread || stop){
                Entity.PACMAN_WAIT_TIME*=2;
                return;
            }
        }
        Entity.PACMAN_WAIT_TIME*=2;
    }
    public void stop(){
        stop = true;
    }
}
