import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //creating panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //creating buttons
        JButton btnNewGame = createButton("New Game");
        JButton btnHighScores = createButton("High Scores");
        JButton btnExit = createButton("Exit");
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

        Font font = new Font("Arial", Font.BOLD, 20); // Change font family, style, and size as needed
        btn.setFont(font);

        return btn;
    }

    public void exitOnClick(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close the window
            }
        });
    }
}
