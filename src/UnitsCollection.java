import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitsCollection {
    private Map<String, Unit> units = new HashMap<String, Unit>();

    public Map<String, Unit> getUnits(Player player){

        units.put("King", new King(player));
        units.put("Queen", new Queen(player));
        units.put("Look1", new Look(player));
        units.put("Look2", new Look(player));
        units.put("Bishop1", new Bishop(player));
        units.put("Bishop2", new Bishop(player));
        units.put("Knight1", new Knight(player));
        units.put("Knight2", new Knight(player));
        units.put("Pawn1", new Pawn(player));
        units.put("Pawn2", new Pawn(player));
        units.put("Pawn3", new Pawn(player));
        units.put("Pawn4", new Pawn(player));
        units.put("Pawn5", new Pawn(player));
        units.put("Pawn6", new Pawn(player));
        units.put("Pawn7", new Pawn(player));
        units.put("Pawn8", new Pawn(player));

        return units;
    }

    public boolean isEmptyCoordinate(int x, int y, Board board, Player player){
        //이동할 자리에 말이 없다
        if(!board.getCoordinate(x,y).isUnit()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isMyUnit(int x, int y, Board board, Player player){
        //그자리에 있는 말이 내 말인가
        if(board.getCoordinate(x,y).getUnit().getOwner().getName().equals(player.getName())){
            return true;
        }else{
            return false;
        }
    }


    public List<String> lookMoveCoordinate(int x, int y, Board board, Player player, List<String> directions){
        //이동가능한 좌표일때, 앞길이 비었다면 그길을 이동 가능한 좌표로 넣고 계속 이동하고 앞길이 막혔다면 내말인지 확인하고 내말이라면 패스/적 말이라면 잡고 루프 탈출
        int directionX = x;
        int directionY = y;
        while (1<=(directionX-1)){
            if(isEmptyCoordinate(directionX-1,directionY,board,player)){
                directions.add((directionX-1)+","+directionY);
                directionX -= 1;
            }else{
                if(isMyUnit(directionX-1,directionY,board,player)){
                    break;
                }else{
                    directions.add((directionX-1)+","+directionY);
                    break;
                }
            }
        }
        directionX = x;
        directionY = y;
        while ((directionX+1)<=8){
            if(isEmptyCoordinate(directionX+1,directionY,board,player)){
                directions.add((directionX+1)+","+directionY);
                directionX += 1;
            }else{
                if(isMyUnit(directionX+1,directionY,board,player)){
                    break;
                }else{
                    directions.add((directionX+1)+","+directionY);
                    break;
                }
            }
        }
        directionX = x;
        directionY = y;
        while (1<=(directionY-1)){
            if(isEmptyCoordinate(directionX,directionY-1,board,player)){
                directions.add(directionX+","+(directionY-1));
                directionY -= 1;
            }else{
                if(isMyUnit(directionX,directionY-1,board,player)){
                    break;
                }else{
                    directions.add(directionX+","+(directionY-1));
                    break;
                }
            }
        }
        directionX = x;
        directionY = y;
        while ((directionY+1)<=8){
            if(isEmptyCoordinate(directionX,directionY+1,board,player)){
                directions.add(directionX+","+(directionY+1));
                directionY += 1;
            }else{
                if(isMyUnit(directionX,directionY+1,board,player)){
                    break;
                }else{
                    directions.add(directionX+","+(directionY+1));
                    break;
                }
            }
        }

        return directions;
    }

    public List<String> bishopMoveCoordinate(int x, int y, Board board, Player player, List<String> directions){
        int directionX = x;
        int directionY = y;
        //이동가능한 좌표일때, 앞길이 비었다면 그길을 이동 가능한 좌표로 넣고 계속 이동하고 앞길이 막혔다면 내말인지 확인하고 내말이라면 패스/적 말이라면 잡고 루프 탈출
        while (1<=(directionX-1)&&(directionY+1)<=8){
            if(isEmptyCoordinate(directionX-1,directionY+1,board,player)){
                directions.add((directionX-1)+","+(directionY+1));
                directionX -= 1;
                directionY += 1;
            }else{
                if(isMyUnit(directionX-1,directionY+1,board,player)){
                    break;
                }else{
                    directions.add((directionX-1)+","+(directionY+1));
                    break;
                }
            }
        }

        directionX = x;
        directionY = y;

        while ((directionX+1)<=8&&(directionY+1)<=8){
            if(isEmptyCoordinate(directionX+1,directionY+1,board,player)){
                directions.add((directionX+1)+","+(directionY+1));
                directionX += 1;
                directionY += 1;
            }else{
                if(isMyUnit(directionX+1,directionY+1,board,player)){
                    break;
                }else{
                    directions.add((directionX+1)+","+(directionY+1));
                    break;
                }
            }
        }

        directionX = x;
        directionY = y;

        while ((directionX+1)<=8&&1<=(directionY-1)){
            if(isEmptyCoordinate(directionX+1,directionY-1,board,player)){
                directions.add((directionX+1)+","+(directionY-1));
                directionX += 1;
                directionY -= 1;
            }else{
                if(isMyUnit(directionX+1,directionY-1,board,player)){
                    break;
                }else{
                    directions.add((directionX+1)+","+(directionY-1));
                    break;
                }
            }
        }

        directionX = x;
        directionY = y;

        while (1<=(directionX-1)&&1<=(directionY-1)){
            if(isEmptyCoordinate(directionX-1,directionY-1,board,player)){
                directions.add((directionX-1)+","+(directionY-1));
                directionX -= 1;
                directionY -= 1;
            }else{
                if(isMyUnit(directionX-1,directionY-1,board,player)){
                    break;
                }else{
                    directions.add((directionX-1)+","+(directionY-1));
                    break;
                }
            }
        }

        return directions;
    }

    public class King implements Unit{
        private String name = "K";
        private Player owner;
        private Board.Coordinate coordinate;

        public King(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {

            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            //왕의 경우, 상하좌우 대각 전부다 움직일 수 있다. 이때, 8방향 모두 체크할 필요가 없다. 상하좌우만 체크하면 된다. 예를들어 12시/9시 모두 갈 수 있어야만 11시도 갈 수 있기 때문

            //12시방향
            boolean top = false;
            boolean right = false;
            boolean left = false;
            boolean bottom = false;

            //이동할 수 있는 경우,
            if((y+1)<=8){
                top = true;
                if(isEmptyCoordinate(x,y+1,board,player)||!isMyUnit(x,y+1,board,player)){
                    directions.add(x+","+(y+1));
                }
            }

            if(1<=(y-1)){
                bottom = true;
                if(isEmptyCoordinate(x,y-1,board,player)||!isMyUnit(x,y-1,board,player)){
                    directions.add(x+","+(y-1));
                }
            }

            if((x+1)<=8){
                right = true;
                if(isEmptyCoordinate(x+1,y,board,player)||!isMyUnit(x+1,y,board,player)){
                    directions.add((x+1)+","+y);
                }
            }

            if(1<=(x-1)){
                left = true;
                if(isEmptyCoordinate(x-1,y,board,player)||!isMyUnit(x-1,y,board,player)){
                    directions.add((x-1)+","+y);
                }
            }

            if(top&&right){
                if(isEmptyCoordinate(x+1,y+1,board,player)||!isMyUnit(x+1,y+1,board,player)){
                    directions.add((x+1)+","+(y+1));
                }
            }

            if(right&&bottom){
                if(isEmptyCoordinate(x+1,y-1,board,player)||!isMyUnit(x+1,y-1,board,player)){
                    directions.add((x+1)+","+(y-1));
                }
            }

            if(bottom&&left){
                if(isEmptyCoordinate(x-1,y-1,board,player)||!isMyUnit(x-1,y-1,board,player)){
                    directions.add((x-1)+","+(y-1));
                }
            }

            if(left&&top){
                if(isEmptyCoordinate(x-1,y+1,board,player)||!isMyUnit(x-1,y+1,board,player)){
                    directions.add((x-1)+","+(y+1));
                }
            }

            return directions;
        }

        public String getName() {
            return name;
        }

        public Player getOwner() {
            return owner;
        }

        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public Board.Coordinate getCoordinate() {
            return coordinate;
        }
    }

    public class Queen implements Unit{

        private String name = "Q";
        private Player owner;
        private Board.Coordinate coordinate;

        public Queen(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {
            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            directions = lookMoveCoordinate(x,y,board,player,directions);
            directions = bishopMoveCoordinate(x,y,board,player,directions);
            return directions;
        }

        public String getName() {
            return name;
        }
        public Player getOwner() {
            return owner;
        }
        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public Board.Coordinate getCoordinate() {
            return coordinate;
        }
    }

    public class Look implements Unit{

        private String name = "L";
        private Player owner;
        private Board.Coordinate coordinate;

        public Look(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {
            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            directions = lookMoveCoordinate(x,y,board,player,directions);
            return directions;
        }

        public String getName() {
            return name;
        }

        public Player getOwner() {
            return owner;
        }

        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public Board.Coordinate getCoordinate() {
            return coordinate;
        }
    }

    public class Bishop implements Unit{

        private String name = "B";
        private Player owner;
        private Board.Coordinate coordinate;

        public Bishop(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {
            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            directions = bishopMoveCoordinate(x,y,board,player,directions);
            return directions;
        }

        public String getName() {
            return name;
        }
        public Player getOwner() {
            return owner;
        }
        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public Board.Coordinate getCoordinate() {
            return coordinate;
        }
    }

    public class Knight implements Unit{
        private Player owner;
        private String name = "N";
        private Board.Coordinate coordinate;

        public Knight(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {
            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            //위로 두칸 갈 수 있다면
            if((y+2)<=8){
                //왼쪽으로 한번 갈 수 있는지 확인 후
                if(1<=(x-1)){
                    //그 칸이 비었는지, 칸이 비어있지 않다면 차지하고있는게 상대방 유닛인지
                    if(isEmptyCoordinate(x-1,y+2,board,player)||!isMyUnit(x-1,y+2,board,player)){
                        directions.add((x-1)+","+(y+2));
                    }
                }
                if((x+1)<=8){
                    if(isEmptyCoordinate(x+1,y+2,board,player)||!isMyUnit(x+1,y+2,board,player)){
                        directions.add((x+1)+","+(y+2));
                    }
                }
            }

            //우로 두칸 갈 수 있다면
            if((x+2)<=8){
                //위쪽으로 한번 갈 수 있는지 확인 후
                if((y+1)<=8){
                    //그 칸이 비었는지, 칸이 비어있지 않다면 차지하고있는게 상대방 유닛인지
                    if(isEmptyCoordinate(x+2,y+1,board,player)||!isMyUnit(x+2,y+1,board,player)){
                        directions.add((x+2)+","+(y+1));
                    }
                }
                //아래쪽으로 한번 갈 수 있는지
                if(1<=(y-1)){
                    if(isEmptyCoordinate(x+2,y-1,board,player)||!isMyUnit(x+2,y-1,board,player)){
                        directions.add((x+2)+","+(y-1));
                    }
                }
            }
            // 이하 동일


            if(1<=(y-2)){
                if((x+1)<=8){
                    if(isEmptyCoordinate(x+1,y-2,board,player)||!isMyUnit(x+1,y-2,board,player)){
                        directions.add((x+1)+","+(y-2));
                    }
                }
                if(1<=(x-1)){
                    if(isEmptyCoordinate(x-1,y-2,board,player)||!isMyUnit(x-1,y-2,board,player)){
                        directions.add((x-1)+","+(y-2));
                    }
                }
            }

            if(1<=(x-2)){
                if(1<=(y-1)){
                    if(isEmptyCoordinate(x-2,y-1,board,player)||!isMyUnit(x-2,y-1,board,player)){
                        directions.add((x-2)+","+(y-1));
                    }
                }
                if((y+1)<=8){
                    if(isEmptyCoordinate(x-2,y+1,board,player)||!isMyUnit(x-2,y+1,board,player)){
                        directions.add((x-2)+","+(y+1));
                    }
                }
            }

            return directions;
        }

        public String getName() {
            return name;
        }
        public Player getOwner() {
            return owner;
        }
        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public Board.Coordinate getCoordinate() {
            return coordinate;
        }
    }

    public class Pawn implements Unit{
        private Player owner;
        private String name = "P";
        private Board.Coordinate coordinate;
        private boolean isFirstMove = true;

        public Pawn(Player owner){
            this.owner = owner;
        }

        @Override
        public List<String> canMoveCoordinate(Board board, Player player) {
            String now[] = coordinate.getCoordinate().split(",");
            List<String> directions = new ArrayList<String>();
            int x = Integer.parseInt(now[0]);
            int y = Integer.parseInt(now[1]);
            //앞으로 한칸전진하는 경우 해당 칸에 유닛이 없어야 함.
            if((y+1)<=8){
                if(isEmptyCoordinate(x,y+1,board,player)) {
                    directions.add(x + "," + (y + 1));
                }
            }

            //대각으로 전진하는 경우 유닛이 있어야 하며(상대 유닛을 잡음), 그 유닛은 상대방의 유닛이어야 하고, 그 좌표는 1~8까지만 이어야 한다.
            if(1<=(x+1)&&(x+1)<=8&&(y+1)<=8){
                if(!isEmptyCoordinate(x+1,y+1,board,player)&&!isMyUnit(x+1,y+1,board,player)) {
                    directions.add((x + 1) + "," + (y + 1));
                }
            }

            if(1<=(x-1)&&(x-1)<=8&&(y+1)<=8){
                if(!isEmptyCoordinate(x-1,y+1,board,player)&&!isMyUnit(x-1,y+1,board,player)) {
                    directions.add((x - 1) + "," + (y + 1));
                }
            }

            if(isFirstMove){
                if(isEmptyCoordinate(x,y+2,board,player)){
                    directions.add(x+","+(y+2));
                }
            }
            return directions;
        }

        public String getName() {
            return name;
        }

        public Player getOwner() {
            return owner;
        }

        public void setCoordinate(Board.Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public Board.Coordinate getCoordinate() {
            return coordinate;
        }

        public void setFirstMove(boolean firstMove) {
            isFirstMove = firstMove;
        }
    }


}
