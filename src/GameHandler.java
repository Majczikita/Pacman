import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameHandler {
    private static final List<Runnable> entityThreads = new ArrayList<>();
    private static final List<Runnable> allThreads = new ArrayList<>();
    protected static boolean runEntityThread;
    protected static boolean runThread;

    public GameHandler(){
    }

    public static void startGame(){
        runThread = true;
        runEntityThread = true;
        for(Runnable thread : allThreads){
            Thread newThread = new Thread(thread);
            newThread.start();
        }
    }

    public static void endGame(JFrame frameToClose){
        runThread = false;
        runEntityThread = false;
        entityThreads.clear();
        allThreads.clear();
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
        runThread = false;
        runEntityThread = false;
        entityThreads.clear();
        allThreads.clear();

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
    public static void endThreads(){
        runThread = false;
    }
    public static void clearEntityThreads(){
        entityThreads.clear();
    }
    public static void clearThreads(){
        allThreads.clear();
    }
}
