import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MapMenuWindow extends JFrame {
    private MainMenuWindow parentWindow;
    private Map map;
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

    public Map getMap() {
        return map;
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
        mapOnClick(btnMap1, "src/smallMap.txt", this);
        btnMap2 = createButton("Medium 1");
        mapOnClick(btnMap2, "src/mediumMap1.txt", this);
        btnMap3 = createButton("Medium 2");
        mapOnClick(btnMap3, "src/mediumMap2.txt", this);
        btnMap4 = createButton("Medium 3");
        mapOnClick(btnMap4, "src/mediumMap3.txt", this);
        btnMap5 = createButton("Huge!!!");
        mapOnClick(btnMap5, "src/hugeMap.txt", this);

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

        Font font = new Font("Arial", Font.BOLD, 20);
        btn.setFont(font);

        return btn;
    }

    public void mapOnClick(JButton btn, String path, MapMenuWindow w){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    map = new Map(path, w);
                    parentWindow.addFrame(map);
                    closeWindow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void closeWindow(){
        //parentWindow.getBtnNewGame().setEnabled(false);
        this.dispose();
    }
    public void mapClosed(){
        parentWindow.getBtnNewGame().setEnabled(true);
    }
}
