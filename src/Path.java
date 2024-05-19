import java.awt.*;

public class Path extends Block{

    public Path(int x, int y){
        super(x, y, Color.BLACK);
        setIcon(loadIcon("src/img/point.png"));
        pathWithPoints.add(this);
    }

    public void pointCollected(){
        setIcon(null);
    }

}
