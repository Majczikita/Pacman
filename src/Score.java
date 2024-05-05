import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Score implements Serializable {
    private static final long serialVersionUID = -7980315813535367733L;
    private static List<Score> scoreSorted = new ArrayList<>();
    private int points;
    private String name;

    public Score(String name, int points) throws Exception {
        this.name = name;
        this.points = points;
        addAndSort();
        saveToFile();
    }

    public void addAndSort(){
        if(scoreSorted.isEmpty()) {
            scoreSorted.add(this);
            return;
        }
        int counter = 0;
        for(Score s : scoreSorted){
            if(s.getPoints()<this.points){
                scoreSorted.add(counter, this);
                return;
            }
            counter++;
        }
        scoreSorted.add(this);
    }

    public static void addAndSort(Score score){
        if(scoreSorted.isEmpty()) {
            scoreSorted.add(score);
            return;
        }
        int counter = 0;
        for(Score s : scoreSorted){
            if(s.getPoints()<score.points){
                scoreSorted.add(counter, score);
                return;
            }
            counter++;
        }
        scoreSorted.add(score);
    }

    public int getPoints() {
        return points;
    }

    public static List<Score> getScoreSorted() {
        return scoreSorted;
    }

    @Override
    public String toString() {
        return name + ": " + points;
    }
    public static List<String> listToString(){
        List<String> result = new ArrayList<>();
        for(Score s : scoreSorted){
            result.add(s.toString());
        }
        return result;
    }
    public void saveToFile() throws Exception{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/scores.txt", true))) {
            oos.writeObject(this);
        }
    }
    public static void readFromFile() throws IOException{
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream("src/scores.txt"));
            while (true) {
                try {
                    Score score = (Score) ois.readObject();
                    addAndSort(score);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if(ois != null)
                ois.close();
        }
    }
}
