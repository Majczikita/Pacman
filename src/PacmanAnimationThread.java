import javax.swing.*;

public class PacmanAnimationThread extends GameHandler implements Runnable{
    private final ImageIcon iconWalk1;
    private final ImageIcon iconWalk2;
    private static final String pathWalking1 = "src/img/pacman/pacman1.png";
    private static final String pathWalking2 = "src/img/pacman/pacman2.png";
    private final Pacman pacman;

    public PacmanAnimationThread(Pacman pacman){
        this.pacman = pacman;
        iconWalk1 = pacman.loadIcon(pathWalking1);
        iconWalk2 = pacman.loadIcon(pathWalking2);
        pacman.setIcon1(iconWalk1);
        addEntityThread(new Thread(this));
    }

    @Override
    public void run() {
        while(GameHandler.runEntityThread) {
            pacman.setIcon1(iconWalk1);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            pacman.setIcon2(iconWalk2);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
