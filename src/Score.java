import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Score implements Serializable {
    private static final long serialVersionUID = -7980315813535367733L;
    private static List<Score> scoreSorted = new ArrayList<>();
    private static List<Score> storedData = new ArrayList<>();
    private int points;
    private String name;

    public Score(String name, int points){
        this.name = name;
        this.points = points;
        saveScore(this);
    }

    public Score(){
        points = 0;
        name = "";
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

    public void addPoint(){
        points++;
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

    public static void saveScore(Score score) {
        storedData = loadAllUserData();
        storedData.add(score);
        for(Score s : storedData){
            addAndSort(s);
        }
        saveAllUserData(storedData);
    }

    public static List<Score> loadAllUserData() {
        List<Score> dataList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/score/scores.txt"))) {
            while (true) {
                Score score = (Score) ois.readObject();
                dataList.add(score);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void loadData(){
        for(Score s : loadAllUserData()){
            addAndSort(s);
        }
    }

    private static void saveAllUserData(List<Score> dataList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/scores.txt"))) {
            for (Score score : dataList) {
                oos.writeObject(score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scoreReset(){
        for(Score s : scoreSorted){
            scoreSorted.remove(s);
        }
    }
}
