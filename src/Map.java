import javax.swing.*;
import java.awt.*;
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

    public Map(String path) throws IOException {
        map = new ArrayList<>();
        setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setResizable(false);

        loadFromFile(path);
        loadMap();

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
        width = map.size();
        heigh = map.getFirst().size();

        this.setSize(width*Block.BLOCK_LENGTH, heigh*Block.BLOCK_LENGTH);
        panel = new JPanel();
        panel.setLayout(new GridLayout(width, heigh));

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
        add(panel);
    }
}
