import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.Vector;

public class HighScoresWindow extends JFrame{
    @Serial
    private static final long serialVersionUID = -3207388830109024763L;
    private MainMenuWindow parentWindow;
    protected static Vector<String> scoreVector = new Vector<>();
    protected static HighScoresList listModel = new HighScoresList(scoreVector);

    HighScoresWindow(MainMenuWindow parentWindow) {
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
    HighScoresWindow(){}

    private void launchHighScores(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //load scores from the file
        if(!Score.getDataLoaded())
            Score.loadData();

        //create list with scores
        JList<String> jList = new JList<>();
        jList.setFont(GameHandler.MAIN_FONT);
        jList.setForeground(Color.WHITE);
        jList.setBackground(Color.black);
        jList.setFixedCellHeight(GameHandler.MAIN_FONT.getSize()*2);
        jList.setModel(listModel);

        JScrollPane scroll = new JScrollPane(jList);
        scroll.setBorder(null);

        panel.add(scroll);
        this.add(panel);
    }

    private void addCloseListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parentWindow.highScoresClosed();
            }
        });
    }
}
