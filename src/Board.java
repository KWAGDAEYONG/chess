
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String,Coordinate> coordinates = new HashMap<String, Coordinate>();

    public void readyBoard(){
        for(int j = 1; j<=8; j++){
            for(int i = 1; i<=8; i++){
                coordinates.put((i+","+j),new Coordinate(i,j));
            }
        }
    }

    public Coordinate getCoordinate(int x, int y){
        return coordinates.get(x+","+y);
    }

    public void viewGameBoard(){
        for(int j = 8; j>=1; j--){
            System.out.print(j);
            for(int i = 1; i<=8; i++){
                Coordinate coordinate = getCoordinate(i,j);
                if(coordinate.isUnit){
                    System.out.print(" "+coordinate.unit.getOwner().getName()+coordinate.unit.getName());
                }else{
                    System.out.print("  X");
                }
            }
            System.out.println();
        }

        for(int i = 0; i<=8; i++){
            System.out.print(i+"  ");
        }
    }

    public void putUnitOnBoard(int x, int y, Unit unit){
        Coordinate coordinate = getCoordinate(x,y);
        coordinate.setUnit(unit);
        unit.setCoordinate(coordinate);
    }

    class Coordinate{

        private int x;
        private int y;
        private boolean isUnit;
        private Unit unit;

        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void setUnit(Unit unit){
            this.unit = unit;
            isUnit = true;
        }

        public void leave(){
            this.unit = null;
            isUnit = false;
        }

        public Unit getUnit() {
            return unit;
        }

        public String getCoordinate(){
            return x+","+y;
        }

        public boolean isUnit() {
            return isUnit;
        }
    }
}
