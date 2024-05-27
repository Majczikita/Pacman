import java.awt.*;
import java.util.Random;

public class Bonus extends Block{
    private static final int secodns = 15;
    private BonusType type;

    public Bonus(int x, int y){
        super(x, y);
        getRandomType();
    }
    public void getRandomType(){
        Random random = new Random();
        int r = random.nextInt(BonusType.values().length);
        String path = "src/img/bonuses/bonus" + (r + 1) + ".png";
        setIcon(loadIcon(path));
        switch (r){
            case 0 -> type = BonusType.doublePoint;
            case 1 -> type = BonusType.doubleSpeed;
            case 2 -> type = BonusType.triplePoints;
            case 3 -> type = BonusType.ghostsStop;
            case 4 -> type = BonusType.ghostsBackToStart;
        }
    }
}
