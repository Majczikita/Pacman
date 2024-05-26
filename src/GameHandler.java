import javax.swing.*;

public abstract class GameHandler {
    public static void endGame(JFrame frameToClose){
        ScoreThread.isThread = false;
        Entity.isThread = false;
        TimeThread.isThread = false;
        frameToClose.dispose();

        String s = JOptionPane.showInputDialog(
                null,
                "Write your username:",
                "Your score is: " + Pacman.pointsCollected,
                JOptionPane.PLAIN_MESSAGE);
        if (s != null && !s.isEmpty())
            new Score(s, Pacman.pointsCollected);
    }
}
