import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class HighScoresWindow extends JFrame{
    private MainMenuWindow parentWindow;

    HighScoresWindow(MainMenuWindow parentWindow) throws Exception {
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

    private void launchHighScores() throws Exception {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scroll = new JScrollPane();

        Score.readFromFile();

        Vector<String> testVector = new Vector<>(Score.listToString());
        HighScoresList listModel = new HighScoresList(testVector);

        JList jList = new JList(testVector);
        jList.setModel(listModel);

        panel.add(jList);
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
