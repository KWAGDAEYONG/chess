public class Play {

    Player p1;
    Player p2;
    boolean isGameSet = false;

    public void setPlayer() {
        p1 = new Player("b");
        p2 = new Player("w");
    }

    public void match() {
        //게임준비
        p1.setOpponent(p2);
        p2.setOpponent(p1);

        p1.init();
        p2.init();

        p1.setTurn(true);
        p2.setTurn(false);

        //게임 시작
        while(!isGameSet) {
            play(p1, p2);
            checkGameSet();
        }
    }

    public void checkGameSet(){
        if(p1.isWin()){
            isGameSet = true;
            System.out.println("경기 종료! 승자:"+p1.getName());
        }

        if(p2.isWin()){
            isGameSet = true;
            System.out.println("경기 종료! 승자:"+p2.getName());
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
