public class Moves {
    private int DPR; //Damage per Round
    private String movesName;
    public Moves(String movesName, int DPR) {
        this.movesName = movesName;
        this.DPR = DPR;
    }
    public int getDPR() {
        return DPR;
    }
    public String getMovesName() {
        return movesName;
    }
    public void setDPR(int DPR) {
        this.DPR = DPR;
    }
    public void setMovesName(String movesName) {
        this.movesName = movesName;
    }

}
