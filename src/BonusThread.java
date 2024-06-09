
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
        while (runEntityThread){
            //wait for WAIT_TIME and check if runEntityThread have changed
            for(int i = 0; i<WAIT_TIME/1000; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(!runEntityThread){
                    return;
                }
            }
            //making sure ghosts don't produce bonuses on spawn
            if(!(ghost.getX() == ghost.getStartingX() && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()-10 && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()+10 && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()-20 && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()+20 && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()-30 && ghost.getY() == ghost.getStartingY()) &&
                !(ghost.getX() == ghost.getStartingX()+30 && ghost.getY() == ghost.getStartingY())){
                Random random = new Random();
                int number = random.nextInt(3); // 0 - 3
                if(number == 1){
                    Map.addBonus(new Bonus(ghost.getX(), ghost.getY()));
                }
            }
        }
    }
}
