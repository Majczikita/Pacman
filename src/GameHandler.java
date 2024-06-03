import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameHandler {
    public static Font MAIN_FONT = new Font("Arial", Font.BOLD, 20);
    private static final List<Runnable> entityThreads = new ArrayList<>();
    private static final List<Runnable> allThreads = new ArrayList<>();
    protected static boolean runEntityThread;
    protected static boolean runThread;
    public static boolean gameOn;

    public static void startGame(){
        runThread = true;
        runEntityThread = true;
        for(Runnable thread : allThreads){
            Thread newThread = new Thread(thread);
            newThread.start();
        }
        gameOn = true;
    }

    public static void endGame(JFrame frameToClose){
        gameOn = false;
        runThread = false;
        runEntityThread = false;
        entityThreads.clear();
        allThreads.clear();
        Block.clearBonuses();
        frameToClose.dispose();

        String s = JOptionPane.showInputDialog(
                null,
                "Write your username:",
                "Your score is: " + Pacman.pointsCollected,
                JOptionPane.PLAIN_MESSAGE);
        if (s != null && !s.isEmpty())
            new Score(s, Pacman.pointsCollected);
    }

    public static void endGame(){
        gameOn = false;
        runThread = false;
        runEntityThread = false;
        entityThreads.clear();
        allThreads.clear();
        Block.clearBonuses();

        String s = JOptionPane.showInputDialog(
                null,
                "Write your username:",
                "Your score is: " + Pacman.pointsCollected,
                JOptionPane.PLAIN_MESSAGE);
        if (s != null && !s.isEmpty())
            new Score(s, Pacman.pointsCollected);
    }

    public static void addEntityThread(Runnable runnable){
        entityThreads.add(runnable);
    }
    public static void addThread(Runnable runnable){
        allThreads.add(runnable);
    }

    public static void startEntityThreads(){
        runEntityThread = true;
        System.out.println("Threads: " + entityThreads.size());
        for(Runnable entity : entityThreads){
            Thread newThread = new Thread(entity);
            newThread.start();
        }
    }
    public static void endEntityThreads(){
        runEntityThread = false;
    }
}
