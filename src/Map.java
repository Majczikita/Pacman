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
    private List<List<Character>> map;
    private JPanel panel;
    private MapMenuWindow parentWindow;
    private JLayeredPane mainPane;

    public Map(String path, MapMenuWindow parentWindow) throws IOException {
        this.parentWindow = parentWindow;
        map = new ArrayList<>();
        setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setResizable(false);

        addCloseListener();
        loadFromFile(path);
        loadMap();

        Pacman p = new Pacman("src/pacman.png", 320, 350);
        mainPane.add(p, JLayeredPane.POPUP_LAYER);
        addKeyListener(p);

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
    public void printMap(){
        for(List<Character> row : map){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
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
        //add(panel);
    }
    private void addCloseListener(){
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                parentWindow.mapClosed();
            }
        });
    }
}
