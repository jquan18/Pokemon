public class Moves {
    private int DPR; //Damage per Round
    private String movesName;
    private Type movesType;
    public Moves(String movesName,String movesType, int DPR) {
        this.movesName = movesName;
        this.movesType = new Type(new String[] {movesType});
        this.DPR = DPR;
    }
    public int getDPR() {
        return DPR;
    }
    public String getMovesName() {
        return movesName;
    }
    public Type getMovesType() {
        return movesType;
    }
    public String[] getMovesTypeString() {
        return movesType.getMovesTypeString();
    }
    public void setDPR(int DPR) {
        this.DPR = DPR;
    }
    public void setMovesName(String movesName) {
        this.movesName = movesName;
    }
    public void setMovesType(String movesType) {
        Type tmp = new Type(new String[] {movesType});
        this.movesType = tmp;
    }
}
class QuickMove extends Moves {
    int QMPoint = 100;

    public QuickMove(String movesName, String movesType, int DPR) {
        super(movesName, movesType, DPR);
    }
    public int getQMPoint() {
        return QMPoint;
    }
}
class MainMove extends  Moves {
    int MMPoint = 8;

    public MainMove(String movesName, String movesType, int DPR) {
        super(movesName, movesType, DPR);
    }
}
