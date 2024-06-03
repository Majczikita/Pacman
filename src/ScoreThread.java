import javax.swing.*;

public class ScoreThread extends GameHandler implements Runnable {
    private final JLabel label;
    private final JFrame mapFrame;

    public ScoreThread(JLabel label, JFrame mapFrame){
        this.label = label;
        this.mapFrame = mapFrame;

        addThread(this);
    }
    @Override
    public void run() {
        while (GameHandler.runThread) {
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
