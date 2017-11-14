import java.util.Map;

public class Player {
   private Map<String, Unit> unitSet;
   private boolean isTurn;
   private boolean isLose;
   private boolean isWin;


    public void setUnitSet(Map<String, Unit> unitSet) {
        this.unitSet = unitSet;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public void setLose(boolean lose) {
        isLose = lose;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public Map<String, Unit> getUnitSet() {
        return unitSet;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public boolean isLose() {
        return isLose;
    }

    public boolean isWin() {
        return isWin;
    }

}
