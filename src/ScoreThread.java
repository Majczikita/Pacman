import javax.swing.*;

public class ScoreThread implements Runnable {
    private final JLabel label;
    private final JFrame mapFrame;
    public static boolean isThread;
    private final Score score;

    public ScoreThread(JLabel label, JFrame mapFrame, Score score){
        this.label = label;
        this.mapFrame = mapFrame;
        isThread = true;
        this.score = score;
    }
    @Override
    public void run() {
        while (isThread) {
            label.setText("Score: " + score.getPoints());
            if(Block.getPathWithPoints().isEmpty()){
                isThread = false;
                Entity.isThread = false;
                TimeThread.isThread = false;
                mapFrame.dispose();
                //DODAJ WALIDACJE
                String s = JOptionPane.showInputDialog(
                        null,
                        "Write your username:",
                        "Your score is: " + score.getPoints(),
                        JOptionPane.PLAIN_MESSAGE);
                score.setName(s);
                Score.saveScore(score);

            }
            try {
                Thread.sleep(Entity.WAIT_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
