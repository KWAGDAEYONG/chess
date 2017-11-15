public class Rule {
    /*public boolean commonMove(Board board, String direction) {
        String temp[] = direction.split(",");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        return true;
    }

    public boolean specialMove(Unit unit, Board board, String direction) {
        String unitName = unit.getName();
        String temp[] = direction.split(",");
        String type = "";
        int x = 0;
        int y = 0;

        if (unitName.equals("P")) {
            String temp2[] = temp[0].split(" ");
            type = temp2[0];
            x = Integer.parseInt(temp2[1]);
        } else {
            x = Integer.parseInt(temp[0]);
        }
        y = Integer.parseInt(temp[1]);

        switch (unitName) {
            case "P":
                //앞으로 한칸이동(그냥 움직이는 경우)
                if ("move".equals(type)) {
                    if (board.getCoordinate(x, y).isUnit()) {
                        return false;
                    } else {
                        return true;
                    }
                    //대각 이동(다른 말을 잡는경우)
                } else {
                    if (board.getCoordinate(x, y).isUnit()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case ""
        }
    }*/
}
