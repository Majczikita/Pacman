public class PacmanAnimationThread extends GameHandler implements Runnable{
    private static final String pathWalking1 = "src/img/pacman/pacman1.png";
    private static final String pathWalking2 = "src/img/pacman/pacman2.png";
    private final Pacman pacman;
    private static final int WAIT_TIME = 70;

    public PacmanAnimationThread(Pacman pacman){
        this.pacman = pacman;
        pacman.setIcon1(pacman.loadIcon(pathWalking1));
        pacman.setIcon2(pacman.loadIcon(pathWalking2));

        addEntityThread(this);
        addThread(this);
    }

    @Override
    public void run() {
        while(GameHandler.runEntityThread) {
            pacman.setIcon(pacman.getIcon1());
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            pacman.setIcon(pacman.getIcon2());
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
