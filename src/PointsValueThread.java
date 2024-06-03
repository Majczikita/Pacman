import java.util.List;

public class PointsValueThread extends GameHandler implements Runnable{
    private int seconds;
    private boolean stop;
    private Thread activeThread;
    private int value;

    PointsValueThread(int seconds, Thread activeThread, int value){
        this.seconds = seconds;
        this.value = value;
        this.activeThread = activeThread;
        stop = false;
    }
    @Override
    public void run() {
        if(activeThread!=null) {
            try {
                activeThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Block.POINT_VALUE = value;
        for(int i = 0; i <= seconds; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(runThread + " " + stop + " " + i);
            if(!runThread || stop){
                //activeBonus = null;
                Block.POINT_VALUE = 1;
                return;
            }
        }
        //activeBonus = null;
        Block.POINT_VALUE = 1;
    }
    public void stop(){
        stop = true;
    }
}
