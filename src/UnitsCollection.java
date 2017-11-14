import java.util.HashMap;
import java.util.Map;

public class UnitsCollection {
    public void setUnits(Player player){
        Map<String, Unit> Units = new HashMap<String, Unit>();

        Units.put("King", new King());
        Units.put("Queen", new Queen());
        Units.put("Look1", new Look());
        Units.put("Look2", new Look());
        Units.put("Bishop1", new Bishop());
        Units.put("Bishop2", new Bishop());
        Units.put("Knight1", new Knight());
        Units.put("Knight2", new Knight());
        Units.put("Pawn1", new Pawn());
        Units.put("Pawn2", new Pawn());
        Units.put("Pawn3", new Pawn());
        Units.put("Pawn4", new Pawn());
        Units.put("Pawn5", new Pawn());
        Units.put("Pawn6", new Pawn());
        Units.put("Pawn7", new Pawn());
        Units.put("Pawn8", new Pawn());

        player.setUnitSet(Units);
    }
    public class King implements Unit{
        private String name = "K";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }

    public class Queen implements Unit{

        private String name = "Q";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }

    public class Look implements Unit{

        private String name = "L";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }

    public class Bishop implements Unit{

        private String name = "B";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }

    public class Knight implements Unit{

        private String name = "N";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }

    public class Pawn implements Unit{

        private String name = "P";

        @Override
        public boolean move() {
            return false;
        }

        @Override
        public boolean die() {
            return false;
        }

        public String getName() {
            return name;
        }
    }


}
