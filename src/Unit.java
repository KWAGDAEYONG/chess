import java.util.List;

public abstract interface Unit {
    public List<String> canMoveCoordinate(Board board, Player player);
    public String getName();
    public Player getOwner();
    public void setCoordinate(Board.Coordinate coordinate);

    public Board.Coordinate getCoordinate();
}
