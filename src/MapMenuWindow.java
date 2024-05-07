import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapMenuWindow extends JFrame {
    private MainMenuWindow parentWindow;
    private JButton btnMap1, btnMap2, btnMap3, btnMap4, btnMap5;

    MapMenuWindow(MainMenuWindow parentWindow) throws Exception {
        this.parentWindow = parentWindow;
        setTitle("Choose a map");
        this.setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);

        addCloseListener();
        launchMapMenu();

        this.setVisible(true);
    }
    private void addCloseListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parentWindow.mapMenuClosed();
            }
        });
    }
    private void launchMapMenu(){
        //creating panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //creating buttons
        btnMap1 = createButton("Small");
        btnMap2 = createButton("Medium 1");
        btnMap3 = createButton("Medium 2");
        btnMap4 = createButton("Medium 3");
        btnMap5 = createButton("Huge!!!");

        //adding buttons to the center of the screen
        panel.add(Box.createVerticalGlue());
        panel.add(btnMap1);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnMap2);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnMap3);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnMap4);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnMap5);
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

        Font font = new Font("Arial", Font.BOLD, 20); // Change font family, style, and size as needed
        btn.setFont(font);

        return btn;
    }
}
