package lesson4;

import java.util.Random;

public class Computer extends AGamer{
    MainGameField gameField;
    String computerSing = "";

    public Computer(String sign,String computerSing){
        this.sign = sign;
        this.computerSing = computerSing;
    }

    //ход компа
    boolean computerTurn(int x, int y){
        gameField = MainGameField.getInstance();
        x = -1;
        y = -1;
        boolean compWin = false;
        boolean humanWin = false;
        if (!compWin && !humanWin){
            do {
                Random rand = new Random();
                x = rand.nextInt(gameField.cellSize);
                y = rand.nextInt(gameField.cellSize);
            }
            while (gameField.isCellBusy(x,y));
        }
        gameField.cell[x][x] = sign;
        return true;
    }
    boolean win(){
        gameField = MainGameField.getInstance();
        return gameField.checkWin(this.sign);
    }

    @Override
    boolean shot(int x, int y) {
        return false;
    }

    @Override
    boolean checkWin() {
        return false;
    }
}
