import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HighScoresWindow extends JFrame {
    private MainMenuWindow parentWindow;

    HighScoresWindow(MainMenuWindow parentWindow){
        this.parentWindow = parentWindow;
        setTitle("High Scores");
        this.setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);

        addCloseListener();
        launchHighScores();

        this.setVisible(true);
    }

    private void launchHighScores() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scroll = new JScrollPane();
        String[] testItems = {"Maja", "Antek", "Antek2", "Maja2"};

        JList<String> listHighScores = new JList<>(testItems);
        panel.add(listHighScores);
        panel.add(scroll);
        this.add(panel);
    }

    private void addCloseListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parentWindow.windowClosed();
            }
        });
    }
}
