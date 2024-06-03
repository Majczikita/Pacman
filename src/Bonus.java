import java.awt.*;
import java.util.Random;

public class Bonus extends Block{
    private static final int secodns = 5;
    private BonusType type;

    public Bonus(int x, int y){
        super(x, y);
        getRandomType();
        bonuses.add(this);
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

    public void collected(){
        setIcon(null);
        if(type == BonusType.ghostsBackToStart){
            ghostsBackToStart();
        }
    }

    public BonusType getType() {
        return type;
    }
    public void ghostsBackToStart(){
        for(Entity ghost : ghosts){
            ghost.setBounds(ghost.setStartingX(), ghost.setStartingY(), BLOCK_LENGTH, BLOCK_LENGTH);
        }
    }
}
