public class Play {

    Player p1;
    Player p2;

    public void setPlayer() {
        p1 = new Player("b");
        p2 = new Player("w");
    }

    public void match() {
        p1.setOpponent(p2);
        p2.setOpponent(p1);

        p1.init();
        p2.init();

        p1.setTurn(true);
        p2.setTurn(false);

        while(true) {
            play(p1, p2);
        }
    }

    public void play(Player p1, Player p2) {
        if (p1.isTurn()) {
            p1.play();
        } else {
            p2.play();
        }
    }

}
