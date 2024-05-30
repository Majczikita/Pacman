
import java.util.Random;
public class BonusThread extends GameHandler implements Runnable{
    private final static int WAIT_TIME = 5000;
    private final Ghost ghost;

    public BonusThread(Ghost ghost) {
        this.ghost = ghost;
        addEntityThread(this);
        addThread(this);
    }

    @Override
    public void run() {
        FIRST: while (runEntityThread){
            for(int i = 0; i<WAIT_TIME/1000; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(!runEntityThread){
                    break FIRST;
                }
            }
            Random random = new Random();
            int number = random.nextInt(3); // 0 - 3
            if(number == 1){
                Map.addBonus(new Bonus(ghost.getX(), ghost.getY()));
            }
        }
    }
}
