import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameHandler {
    private static final List<Thread> entityThreads = new ArrayList<>();
    protected static boolean runEntityThread;

    public static void endGame(JFrame frameToClose){
        ScoreThread.isThread = false;
        runEntityThread = false;
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

    public void addEntityThread(Thread thread){
        entityThreads.add(thread);
    }

    public static void startEntityThreads(){
        runEntityThread = true;
        for(Thread thread : entityThreads){
            thread.start();
        }
    }
    public static void endEntityThreads(){
        runEntityThread = false;
    }
}
