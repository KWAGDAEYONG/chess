public class PlayGame {
    public void readyForGame(){
        Player p1 = new Player();
        Player p2 = new Player();

        UnitsCollection collection = new UnitsCollection();
        collection.setUnits(p1);
        collection.setUnits(p2);

        Board board = new Board();
        board.readyBoard();

    }

}
