import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapMenuWindow extends JFrame {
    private final MainMenuWindow parentWindow;
    private Map map;

    MapMenuWindow(MainMenuWindow parentWindow) {
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
        //creating panel with BoxLayout - to center buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //creating buttons
        JButton btnMap1 = createButton("Small");
        mapOnClick(btnMap1, "src/map/smallMap.txt", this);
        JButton btnMap2 = createButton("Medium 1");
        mapOnClick(btnMap2, "src/map/mediumMap1.txt", this);
        JButton btnMap3 = createButton("Medium 2");
        mapOnClick(btnMap3, "src/map/mediumMap2.txt", this);
        JButton btnMap4 = createButton("Medium 3");
        mapOnClick(btnMap4, "src/map/mediumMap3.txt", this);
        JButton btnMap5 = createButton("Huge!!!");
        mapOnClick(btnMap5, "src/map/hugeMap.txt", this);

        //adding buttons to the center of the screen
        panel.add(Box.createVerticalGlue());
        panel.add(btnMap1);
        panel.add(Box.createRigidArea(new Dimension(0, GameHandler.MAIN_FONT.getSize())));
        panel.add(btnMap2);
        panel.add(Box.createRigidArea(new Dimension(0, GameHandler.MAIN_FONT.getSize())));
        panel.add(btnMap3);
        panel.add(Box.createRigidArea(new Dimension(0, GameHandler.MAIN_FONT.getSize())));
        panel.add(btnMap4);
        panel.add(Box.createRigidArea(new Dimension(0, GameHandler.MAIN_FONT.getSize())));
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

        btn.setFont(GameHandler.MAIN_FONT);

        return btn;
    }

    public void mapOnClick(JButton btn, String path, MapMenuWindow w){
        btn.addActionListener(e -> {
            try {
                map = new Map(path, w);
                parentWindow.addFrame(map);
                closeWindow();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void closeWindow(){
        this.dispose();
    }
    public void mapClosed(){
        parentWindow.getBtnNewGame().setEnabled(true);
    }
}
