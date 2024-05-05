import javax.swing.*;
import java.util.Vector;

public class HighScoresList extends AbstractListModel {
    Vector<String> namesScores;

    public HighScoresList(Vector<String> namesScores){
        this.namesScores = namesScores;
    }
    @Override
    public int getSize() {
        return namesScores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return namesScores.get(index);
    }

    public void add(String text, int index){
        namesScores.add(index, text);
        fireIntervalAdded(this, index, index);
    }
    public void add(String text){
        namesScores.add(getSize(), text);
    }

}
