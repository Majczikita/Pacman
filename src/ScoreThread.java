import javax.swing.*;

public class ScoreThread implements Runnable {
    private final JLabel label;
    public static boolean isThread;
    private final Score score;

    public ScoreThread(JLabel label, Score score){
        this.label = label;
        isThread = true;
        this.score = score;
    }
    @Override
    public void run() {
        while (isThread) {
            label.setText("Score: " + score.getPoints());
            try {
                Thread.sleep(Entity.WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
