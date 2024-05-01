import javax.swing.*;
import java.awt.*;

public class MainMenuWindow extends JFrame {
    MainMenuWindow(){
        setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);

        launchMainMenu();

        this.setVisible(true);
    }

    public void launchMainMenu(){
        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));
        panel.setOpaque(false);

        JButton btnNewGame = new JButton();
        editBtn(btnNewGame, "New Game");
        panel.add(btnNewGame);

        JButton btnHighScores = new JButton();
        editBtn(btnHighScores, "High Scores");
        panel.add(btnHighScores);

        JButton btnExit = new JButton();
        editBtn(btnExit, "Exit");
        panel.add(btnExit);

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void editBtn(JButton btn, String text){
        btn.setText(text);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        //btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 50));
    }
}
