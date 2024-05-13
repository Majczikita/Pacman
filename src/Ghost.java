public class Ghost extends Entity implements Runnable{
    private int startX, startY;
    private String pathR, pathU, pathL, pathD;
    private String mainPath = "src/img";

    public Ghost(ColorEnum colorEnum){
        super(colorEnum);
        this.color = colorEnum;
        startingConfig();
        setIcon(loadIcon(pathR));
    }

    @Override
    public void run() {

    }

    public void startingConfig(){
        switch(color){
            case RED:
                mainPath+="/redGhost";
                break;
            case PINK:
                mainPath+="/pinkGhost";
                break;
            case ORANGE:
                mainPath+="/orangeGhost";
                break;
            case BLUE:
                mainPath+="/blueGhost";
                break;
        }
        pathR = mainPath + "/right.png";
        pathL = mainPath + "/left.png";
        pathU = mainPath + "/up.png";
        pathD = mainPath + "/down.png";
    }

    @Override
    public int setStartingX() {
        if(color == ColorEnum.PINK || color == ColorEnum.RED) return (Map.map.get(0).size()/2)*Block.BLOCK_LENGTH;
        else if (color == ColorEnum.BLUE)return (Map.map.get(0).size()/2-1)*Block.BLOCK_LENGTH;
        else return (Map.map.get(0).size()/2+1)*Block.BLOCK_LENGTH;
    }

    @Override
    public int setStartingY() {
        if(color == ColorEnum.BLUE || color == ColorEnum.ORANGE || color == ColorEnum.PINK){
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 7 * Block.BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 9 * Block.BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 8 * Block.BLOCK_LENGTH;
            } else return 0;
        } else{
            if(Map.map.size() == 17 || Map.map.size() == 19){
                return 5 * Block.BLOCK_LENGTH;
            } else if(Map.map.size() == 21){
                return 7 * Block.BLOCK_LENGTH;
            }else if(Map.map.size() == 20){
                return 6 * Block.BLOCK_LENGTH;
            } else return 0;
        }

    }

}
