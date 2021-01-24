package lesson4;

public class Player extends AGamer{
    MainGameField gameField;
    int isShotReady = 1;
    //конструктор
    public Player(String sign){
        this.sign = sign;
    }
    //ход игрока
    boolean humanTurn(int x, int y){
        gameField = MainGameField.getInstance();
        if(!gameField.isCellBusy(x,y)){
            gameField.cell[x][y] = sign;
            return true;
        }
        return false;
    }

    @Override
    boolean shot(int x, int y) {
        return false;
    }

    boolean checkWin(){
        gameField = MainGameField.getInstance();
        return gameField.checkWin(this.sign);
    }
}
