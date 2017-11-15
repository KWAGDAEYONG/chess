import java.util.*;

public class Player {
    Scanner scanner = new Scanner(System.in);
   private Map<String, Unit> unitSet;
   private boolean isTurn;
   private boolean isLose;
   private boolean isWin;
   private Board board;
   private String name;
   private Player opponent;
   private List<Unit> dieUnits = new ArrayList<Unit>();

   public Player(String name){
       this.name = name;
   }

    public String getName() {
        return name;
    }

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

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board makeBoard() {
        Board board = new Board();
        board.readyBoard();
        setBoard(board);
        return board;
    }

    public void init(){
       this.board = makeBoard();
       this.unitSet = makeUnitSet(this);
       this.opponent.unitSet = makeUnitSet(opponent);

        board.putUnitOnBoard(1,1,this.unitSet.get("Look1"));
        board.putUnitOnBoard(2,1,this.unitSet.get("Knight1"));
        board.putUnitOnBoard(3,1,this.unitSet.get("Bishop1"));
        board.putUnitOnBoard(4,1,this.unitSet.get("King"));
        board.putUnitOnBoard(5,1,this.unitSet.get("Queen"));
        board.putUnitOnBoard(6,1,this.unitSet.get("Bishop2"));
        board.putUnitOnBoard(7,1,this.unitSet.get("Knight2"));
        board.putUnitOnBoard(8,1,this.unitSet.get("Look2"));
        board.putUnitOnBoard(1,2,this.unitSet.get("Pawn1"));
        board.putUnitOnBoard(2,2,this.unitSet.get("Pawn2"));
        board.putUnitOnBoard(3,2,this.unitSet.get("Pawn3"));
        board.putUnitOnBoard(4,2,this.unitSet.get("Pawn4"));
        board.putUnitOnBoard(5,2,this.unitSet.get("Pawn5"));
        board.putUnitOnBoard(6,2,this.unitSet.get("Pawn6"));
        board.putUnitOnBoard(7,2,this.unitSet.get("Pawn7"));
        board.putUnitOnBoard(8,2,this.unitSet.get("Pawn8"));

        board.putUnitOnBoard(1,8,this.opponent.unitSet.get("Look1"));
        board.putUnitOnBoard(2,8,this.opponent.unitSet.get("Knight1"));
        board.putUnitOnBoard(3,8,this.opponent.unitSet.get("Bishop1"));
        board.putUnitOnBoard(4,8,this.opponent.unitSet.get("Queen"));
        board.putUnitOnBoard(5,8,this.opponent.unitSet.get("King"));
        board.putUnitOnBoard(6,8,this.opponent.unitSet.get("Bishop2"));
        board.putUnitOnBoard(7,8,this.opponent.unitSet.get("Knight2"));
        board.putUnitOnBoard(8,8,this.opponent.unitSet.get("Look2"));
        board.putUnitOnBoard(1,7,this.opponent.unitSet.get("Pawn1"));
        board.putUnitOnBoard(2,7,this.opponent.unitSet.get("Pawn2"));
        board.putUnitOnBoard(3,7,this.opponent.unitSet.get("Pawn3"));
        board.putUnitOnBoard(4,7,this.opponent.unitSet.get("Pawn4"));
        board.putUnitOnBoard(5,7,this.opponent.unitSet.get("Pawn5"));
        board.putUnitOnBoard(6,7,this.opponent.unitSet.get("Pawn6"));
        board.putUnitOnBoard(7,7,this.opponent.unitSet.get("Pawn7"));
        board.putUnitOnBoard(8,7,this.opponent.unitSet.get("Pawn8"));
    }

    public Map<String, Unit> makeUnitSet(Player player){
        UnitsCollection myCollection = new UnitsCollection();
        return myCollection.getUnits(player);
    }

    public void play(){
        System.out.println(this.getName()+"의 턴!");

        System.out.print("내 죽은 말들 : ");
        for(int i = 0; i<dieUnits.size(); i++){
            System.out.print(dieUnits.get(i).getName()+",");
        }
        System.out.println();

        board.viewGameBoard();

        Unit moveTarget = choiceUnit(board);

        System.out.println("다음중 움직일 좌표를 선택해주세요.");
        List<String> directions = moveTarget.canMoveCoordinate(board, this);
        for(String direction : directions){
            System.out.println(direction);
        }
        String input[] = scanner.nextLine().split(",");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        String oppTargetCoordinate[] = moveTarget.getCoordinate().getCoordinate().split(",");

        //플레이어는 각자 자신의 체스판과 자신이 움직일 말과 상대방이 움직일 말을 따로 갖고있다. 따라서, 내 말을 한번 움직일때 상대방의 말도 그에 맞는 위치에 옮겨주어야 한다.
        move(9-x, 9-y, opponent.board, opponent.board.getCoordinate(9-Integer.parseInt(oppTargetCoordinate[0]),9-Integer.parseInt(oppTargetCoordinate[1])).getUnit());
        move(x,y,board, moveTarget);



        this.isTurn = false;
        opponent.isTurn = true;
    }

    public void move(int x, int y, Board board, Unit unit){
        unit.getCoordinate().leave();
        if(board.getCoordinate(x,y).isUnit()){
            opponent.dieUnits.add(board.getCoordinate(x,y).getUnit());
        }

        board.putUnitOnBoard(x,y,unit);
    }

    public Unit choiceUnit(Board board){

        System.out.println("\n\n움직일 말의 좌표를 선택하세요 ex) 1,2 ");

        String input = scanner.nextLine();
        String temp[] = input.split(",");

        Unit target = board.getCoordinate(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])).getUnit();

        return target;
    }

}
