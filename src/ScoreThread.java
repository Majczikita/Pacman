import javax.swing.*;

public class ScoreThread implements Runnable {
    private final JLabel label;
    private final JFrame mapFrame;
    public static boolean isThread;

    public ScoreThread(JLabel label, JFrame mapFrame){
        this.label = label;
        this.mapFrame = mapFrame;
        isThread = true;
    }
    @Override
    public void run() {
        while (isThread) {
            label.setText("Score: " + Pacman.pointsCollected);
            if(Block.getPathWithPoints().isEmpty()){
                GameHandler.endGame(mapFrame);
            }
            try {
                Thread.sleep(Entity.WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
