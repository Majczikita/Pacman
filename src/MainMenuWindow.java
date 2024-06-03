import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenuWindow extends JFrame {
    private List<JFrame> openFrames;
    private JButton btnNewGame;
    private JButton btnHighScores;
    private JButton btnExit;
    private HighScoresWindow highScores;
    private MapMenuWindow mapMenuWindow;

    MainMenuWindow(){
        setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);
        openFrames = new ArrayList<>();

        launchMainMenu();

        this.setVisible(true);
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }

    public void launchMainMenu(){
        //creating panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //creating buttons
        btnNewGame = createButton("New Game");
        mapMenuOnClick(btnNewGame);
        btnHighScores = createButton("High Scores");
        highScoresOnClick(btnHighScores);
        btnExit = createButton("Exit");
        exitOnClick(btnExit);

        //adding buttons to the center of the screen
        panel.add(Box.createVerticalGlue());
        panel.add(btnNewGame);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnHighScores);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnExit);
        panel.add(Box.createVerticalGlue());

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public JButton createButton(String text){
        JButton btn = new JButton(text);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(200, 30));
        btn.setFocusable(false);

        btn.setFont(GameHandler.MAIN_FONT);

        return btn;
    }

    public void exitOnClick(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllFrames();
            }
        });
    }
    public void highScoresOnClick(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openHighScoresWindow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void openHighScoresWindow() throws Exception {
        if (highScores == null) {
            highScores = new HighScoresWindow(this);
            highScores.setVisible(true);
            btnHighScores.setEnabled(false);
            openFrames.add(highScores);
        }
    }

    public void mapMenuOnClick(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openMapMenuWindow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void openMapMenuWindow() {
        if (mapMenuWindow == null) {
            mapMenuWindow = new MapMenuWindow(this);
            mapMenuWindow.setVisible(true);
            btnNewGame.setEnabled(false);
            openFrames.add(mapMenuWindow);
        }
    }

    public void highScoresClosed() {
        btnHighScores.setEnabled(true);
        openFrames.remove(highScores);
        highScores = null;
    }
    public void mapMenuClosed() {
        btnNewGame.setEnabled(!openFrames.contains(mapMenuWindow.getMap()));
        openFrames.remove(mapMenuWindow);
        mapMenuWindow = null;
    }
    public void closeAllFrames() {
        for (JFrame frame : openFrames) {
            frame.dispose();
        }
        openFrames.clear();
        dispose();
    }
    public void addFrame(JFrame frame){
        openFrames.add(frame);
    }
}
