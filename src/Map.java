import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map extends JFrame {
    public static final char PATH = '0';
    public static final char BORDER = '1';
    private int width;
    private int heigh;
    public static List<List<Character>> map;
    private JPanel panel;
    private MapMenuWindow parentWindow;
    private JLayeredPane mainPane;

    private Pacman pacman;

    private final Ghost ghostRed;
    private final Ghost ghostPink;
    private final Ghost ghostOrange;
    private final Ghost ghostBlue;

    public Map(String path, MapMenuWindow parentWindow) throws IOException {
        this.parentWindow = parentWindow;
        map = new ArrayList<>();
        setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.black);

        addCloseListener();
        loadFromFile(path);
        loadMap();

        //add four ghosts
        ghostRed = new Ghost(ColorEnum.RED);
        ghostPink = new Ghost(ColorEnum.PINK);
        ghostBlue = new Ghost(ColorEnum.BLUE);
        ghostOrange = new Ghost(ColorEnum.ORANGE);

        //add pacman
        pacman = new Pacman("src/img/pacman/pacman1.png", "src/img/pacman/pacman2.png");
        addKeyListener(pacman);

        Entity.startThreads();

        //add entities to mainPane
        for(Entity entity : Entity.entities){
            mainPane.add(entity, JLayeredPane.POPUP_LAYER);
        }

        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void loadFromFile(String path) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String s;

            while ((s = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for(int i = 0; i < s.length(); i++){
                    if(s.charAt(i)=='\n') continue;
                    row.add(s.charAt(i));
                }
                map.add(row);
            }

        } finally {
            if(br != null)
                br.close();
        }
    }

    public void loadMap(){
        heigh = map.size();
        width = map.get(0).size();

        this.setSize(width*Block.BLOCK_LENGTH+10, heigh*Block.BLOCK_LENGTH+Block.BLOCK_LENGTH);
        mainPane = new JLayeredPane();
        mainPane.setPreferredSize(new Dimension(width*Block.BLOCK_LENGTH, heigh*Block.BLOCK_LENGTH));
        getContentPane().add(mainPane);

        panel = new JPanel();
        panel.setLayout(new GridLayout(heigh, width));
        panel.setBounds(0, 0, width*Block.BLOCK_LENGTH, heigh*Block.BLOCK_LENGTH);

        int x = 0;
        for(List<Character> row : map){
            int y = 0;
            for(char c : row){
                if(c == PATH){
                    panel.add(new Path(x, y));
                }
                else if(c==BORDER){
                    panel.add(new Border(x, y));
                }
                y++;
            }
            x++;
        }

        mainPane.add(panel, JLayeredPane.DEFAULT_LAYER);
    }

    private void addCloseListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parentWindow.mapClosed();
                Entity.isThread = false;

                ghostOrange.setBounds(0,0,0,0);
                ghostBlue.setBounds(0,0,0,0);
                ghostPink.setBounds(0,0,0,0);
                ghostRed.setBounds(0,0,0,0);
            }
        });
    }
}
