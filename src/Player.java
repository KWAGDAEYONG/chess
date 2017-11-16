import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Player {
    Scanner scanner = new Scanner(System.in);
    private Map<String, Unit> unitSet;
    private boolean isTurn;
    private boolean isWin;
    private Board board;
    private String name;
    private Player opponent;
    private List<Unit> dieUnits = new ArrayList<Unit>();
    private List<Unit> oppDieUnits = new ArrayList<Unit>();

    public Player(String name) {
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

    public void setWin(boolean win) {
        isWin = win;
    }

    public Map<String, Unit> getUnitSet() {
        return unitSet;
    }

    public boolean isTurn() {
        return isTurn;
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

    public void init() {
        this.board = makeBoard();
        this.unitSet = makeUnitSet(this);
        this.opponent.unitSet = makeUnitSet(opponent);

        board.putUnitOnBoard(1, 1, this.unitSet.get("Look1"));
        board.putUnitOnBoard(2, 1, this.unitSet.get("Knight1"));
        board.putUnitOnBoard(3, 1, this.unitSet.get("Bishop1"));
        board.putUnitOnBoard(4, 1, this.unitSet.get("King"));
        board.putUnitOnBoard(5, 1, this.unitSet.get("Queen"));
        board.putUnitOnBoard(6, 1, this.unitSet.get("Bishop2"));
        board.putUnitOnBoard(7, 1, this.unitSet.get("Knight2"));
        board.putUnitOnBoard(8, 1, this.unitSet.get("Look2"));
        board.putUnitOnBoard(1, 2, this.unitSet.get("Pawn1"));
        board.putUnitOnBoard(2, 2, this.unitSet.get("Pawn2"));
        board.putUnitOnBoard(3, 2, this.unitSet.get("Pawn3"));
        board.putUnitOnBoard(4, 2, this.unitSet.get("Pawn4"));
        board.putUnitOnBoard(5, 2, this.unitSet.get("Pawn5"));
        board.putUnitOnBoard(6, 2, this.unitSet.get("Pawn6"));
        board.putUnitOnBoard(7, 2, this.unitSet.get("Pawn7"));
        board.putUnitOnBoard(8, 2, this.unitSet.get("Pawn8"));

        board.putUnitOnBoard(1, 8, this.opponent.unitSet.get("Look1"));
        board.putUnitOnBoard(2, 8, this.opponent.unitSet.get("Knight1"));
        board.putUnitOnBoard(3, 8, this.opponent.unitSet.get("Bishop1"));
        board.putUnitOnBoard(4, 8, this.opponent.unitSet.get("Queen"));
        board.putUnitOnBoard(5, 8, this.opponent.unitSet.get("King"));
        board.putUnitOnBoard(6, 8, this.opponent.unitSet.get("Bishop2"));
        board.putUnitOnBoard(7, 8, this.opponent.unitSet.get("Knight2"));
        board.putUnitOnBoard(8, 8, this.opponent.unitSet.get("Look2"));
        board.putUnitOnBoard(1, 7, this.opponent.unitSet.get("Pawn1"));
        board.putUnitOnBoard(2, 7, this.opponent.unitSet.get("Pawn2"));
        board.putUnitOnBoard(3, 7, this.opponent.unitSet.get("Pawn3"));
        board.putUnitOnBoard(4, 7, this.opponent.unitSet.get("Pawn4"));
        board.putUnitOnBoard(5, 7, this.opponent.unitSet.get("Pawn5"));
        board.putUnitOnBoard(6, 7, this.opponent.unitSet.get("Pawn6"));
        board.putUnitOnBoard(7, 7, this.opponent.unitSet.get("Pawn7"));
        board.putUnitOnBoard(8, 7, this.opponent.unitSet.get("Pawn8"));
    }

    public Map<String, Unit> makeUnitSet(Player player) {
        UnitsCollection myCollection = new UnitsCollection();
        return myCollection.getUnits(player);
    }

    public void play() {
        System.out.println(this.getName() + "의 턴!");

        System.out.print("내 죽은 말들 : ");
        for(int i = 0; i<dieUnits.size(); i++){
            System.out.print(dieUnits.get(i).getName()+",");
        }
        System.out.println();

        System.out.print("상대 죽은 말들 : ");
        for(int i = 0; i<oppDieUnits.size(); i++){
            System.out.print(oppDieUnits.get(i).getName()+",");
        }
        System.out.println();

        board.viewGameBoard();

        Unit moveTarget = choiceUnit(board);

        if (moveTarget == null) {
            System.out.println("해당 좌표에는 말이 없습니다.");
            play();
            return;
        }

        if (!moveTarget.getOwner().getName().equals(this.getName())) {
            System.out.println("선택하신 좌표의 말은 상대방의 말입니다.");
            play();
            return;
        }

        List<String> directions = moveTarget.canMoveCoordinate(board, this);

        if (directions.isEmpty()) {
            System.out.println("해당 말은 움직일 수 없습니다.");
            play();
            return;
        }

        System.out.println("다음중 움직일 좌표를 선택해주세요.");
        for (String direction : directions) {
            System.out.println(direction);
        }
        String input[] = scanner.nextLine().split(",");

        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        if(x < 1 || 8 < x || y < 1 || y > 8||!directions.contains(x+","+y)){
            System.out.println("잘못된 입력입니다.");
            play();
            return;
        }
        String oppTargetCoordinate[] = moveTarget.getCoordinate().getCoordinate().split(",");

        //플레이어는 각자 자신의 체스판과 자신이 움직일 말과 상대방의 말을 따로 갖고있다. 따라서, 내 말을 한번 움직일때 '상대방입장에서의 상대방체스판의 상대방 말'도 그에 맞는 위치에 옮겨주어야 한다.
        move(9 - x, 9 - y, opponent.board, opponent.board.getCoordinate(9 - Integer.parseInt(oppTargetCoordinate[0]), 9 - Integer.parseInt(oppTargetCoordinate[1])).getUnit());
        move(x, y, board, moveTarget);

        //턴을 끝낼때, 상대방의 왕이 잡히면 게임 끝.
        if (opponent.dieUnits.contains(opponent.unitSet.get("King"))) {
            this.isWin = true;
        }

        directions.clear();
        this.isTurn = false;
        opponent.isTurn = true;

    }

    public void move(int x, int y, Board board, Unit unit) {
        System.out.println(unit.getCoordinate().getCoordinate()+"에서 "+x+","+y+"로 옮긴다.");
        unit.getCoordinate().leave();
        //상대방 판의 상대방 무덤에 상대방 말을 넣는다.
        if (board.getCoordinate(x, y).isUnit()&&board.equals(opponent.board)){
            opponent.dieUnits.add(board.getCoordinate(x, y).getUnit());
        }
        //내 판의 상대방 무덤에 상대방 말을 넣는다.
        if (board.getCoordinate(x, y).isUnit()&&board.equals(this.board)){
            oppDieUnits.add(board.getCoordinate(x, y).getUnit());
        }


        board.putUnitOnBoard(x, y, unit);

        //폰이 맨 끝에 도착했으면 다른말로 바꿀 수가 있다.
        if(unit.getName().equals("P")&&(y==8)){
            changeFromPawn(x, y);
        }
    }

    public void changeFromPawn(int x, int y){
        System.out.println("폰이 더이상 움직일 수 없습니다. 잡힌 말중, 교환할 말을 선택해주세요. ex)Q");
        if(dieUnits.isEmpty()){
            System.out.println("바꿀수 있는 말이 없습니다,");
            return;
        }
        List<String> diedName = new ArrayList<String>();
        for(Unit died:this.dieUnits){
            if(!died.getName().equals("P")){
                diedName.add(died.getName());
                System.out.println(died.getName());
            }
        }

        String input = scanner.nextLine();
        if((!input.equals("Q")&&!input.equals("L")&&!input.equals("N")&&!input.equals("B"))||!diedName.contains(input)){
            System.out.println("잘못된 입력입니다.");
            diedName.clear();
            changeFromPawn(x,y);
            return;
        }

        for(Unit died:opponent.oppDieUnits){
            if(died.getName().equals(input)){
                move(9-x,9-y,opponent.board,died);
                opponent.oppDieUnits.remove(died);
                break;
            }
        }

        for(Unit died:dieUnits){
            if(died.getName().equals(input)){
                move(x,y,board,died);
                dieUnits.remove(died);
                break;
            }
        }

    }

    public Unit choiceUnit(Board board) {

        System.out.println("\n\n움직일 말의 좌표를 선택하세요 ex) 1,2 ");

        String input = scanner.nextLine();
        String temp[] = input.split(",");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        if (x < 1 || 8 < x || y < 1 || y > 8) {
            return null;
        }
        Unit target = board.getCoordinate(x, y).getUnit();

        return target;
    }

}
