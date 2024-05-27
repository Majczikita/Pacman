
import java.util.Random;
public class BonusThread implements Runnable{
    private final static int WAIT_TIME = 5000;
    private final Ghost ghost;

    public BonusThread(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public void run() {
        while (Entity.isThread){
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Random random = new Random();
            int number = random.nextInt(3); // 0 - 3
            if(number == 1){
                Map.addBonus(new Bonus(ghost.getX(), ghost.getY()));
            }
        }
    }
}
