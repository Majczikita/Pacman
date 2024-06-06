import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public abstract class GameHandler {
    public static Font MAIN_FONT = new Font("Arial", Font.BOLD, 20);
    public static final List<Runnable> entityThreads = new ArrayList<>();
    private static final List<Runnable> allThreads = new ArrayList<>();
    protected static boolean runEntityThread;
    protected static boolean runThread;
    public static boolean gameOn;
    public static Map<Runnable, Thread> runnableThreadMap = new HashMap<>();

    public static void startGame(){
        runThread = true;
        runEntityThread = true;
        for(Runnable thread : allThreads){
            Thread newThread = new Thread(thread);
            newThread.start();
            if(thread.getClass()==GhostWalkingThread.class) runnableThreadMap.put(thread, newThread);
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

    public static void startEntityThreads() throws InterruptedException {
        runnableThreadMap.clear();
        runEntityThread = true;

        for(Runnable entity : entityThreads){
            Thread newThread = new Thread(entity);
            if(entity.getClass()==GhostWalkingThread.class) {
                ((GhostWalkingThread) entity).setGhostOnStartingLocation();
                runnableThreadMap.put(entity, newThread);
            }
            newThread.start();
        }
    }
    public static void endEntityThreads(){
        runEntityThread = false;
    }
}
