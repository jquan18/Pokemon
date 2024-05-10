public class Moves {
    private int DPR; //Damage per Round
    private String movesName;
    private Type movesType;
    int QMPoint = 100, MMPoint = 10;
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
    public String getMovesTypeAString() {
        return movesType.toString();
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
