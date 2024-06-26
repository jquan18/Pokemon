/*
Move is the Pokemon attack way, separate moves to a new type of Object and make it not only a String type attributes for Pokemon is help for a clear structure
The counter relationship is not simply depend on type of Pokemon, even Grass type pokemon also can have Ground type moves
Store the type of moves for each Pokemon
 */
public class Moves {
    private int DPR; //Damage per Round
    private String movesName;
    private Type movesType;

    //Create Move from loading pokemon
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
    int QMPoint = 35; //Use for limit the use of this moves

    public QuickMove(String movesName, String movesType, int DPR) {
        super(movesName, movesType, DPR);
    }

}
class MainMove extends  Moves {
    int MMPoint = 15; //Use for limit the use of this moves

    public MainMove(String movesName, String movesType, int DPR) {
        super(movesName, movesType, DPR);
    }
}
