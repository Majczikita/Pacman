import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Score extends HighScoresWindow implements Serializable {
    @Serial
    private static final long serialVersionUID = -7980315813535367733L;

    private static final List<Score> scoreSorted = new ArrayList<>();
    private final int points;
    private final String name;
    private static boolean dataLoaded;

    public Score(String name, int points){
        this.name = name;
        this.points = points;
        saveScore(this);
    }

    public static void addAndSort(Score score){
        if(scoreSorted.isEmpty()) {
            scoreSorted.add(score);
            listModel.add(score.toString());
            return;
        }
        int counter = 0;
        for(Score s : scoreSorted){
            if(s.getPoints()<score.points){
                scoreSorted.add(counter, score);
                listModel.add(score.toString(), counter);
                return;
            }
            counter++;
        }
        scoreSorted.add(score);
        listModel.add(score.toString());
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return name + ": " + points;
    }

    public static void saveScore(Score score) {
        if(!dataLoaded)
            Score.loadData();
        addAndSort(score);
        saveAllUserData();
    }

    public static List<Score> loadAllUserData() {
        List<Score> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/score/scores.txt"))) {
            while (true) {
                Score score = (Score) ois.readObject();
                list.add(score);
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataLoaded = true;
        return list;
    }

    public static void loadData(){
        for(Score s : loadAllUserData()){
            addAndSort(s);
        }
    }

    private static void saveAllUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/score/scores.txt"))) {
            for (Score score : Score.scoreSorted) {
                oos.writeObject(score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean getDataLoaded(){
        return dataLoaded;
    }
}
