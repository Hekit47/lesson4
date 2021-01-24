package lesson4;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainGameField extends JPanel {
    private static MainGameField instance = null;
    //задаем размер игрового поля
    public static final int FIELD_SIZE = 450;
    protected static final String DOT_EMPTY = "*";
    boolean gameOver = false;
    String gameOverMessage = "";
    static final int linesCount = 3;
    int cellSize;
    int x;
    int y;
    Player player;
    Computer computer;
    public String[][] cell;


    //получение экземпляра игрового поля
    public static synchronized MainGameField getInstance(){
        if(instance == null)
            instance = new MainGameField();
        return instance;
    }


    //запуск игры
    void startNewGame(){
        gameOver = false;
        gameOverMessage = "";
        cellSize = FIELD_SIZE / linesCount;
        cell = new String[linesCount][linesCount];
        repaint();
        //инициализация поля
        for (int rowIndex = 0; rowIndex < linesCount; rowIndex++) {
            for (int colIndex = 0; colIndex < linesCount; colIndex++) {
                cell[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
       setVisible(true);
    }

    //конструктор
    protected MainGameField() {
        setVisible(false);
        player = new Player("X");
        computer = new Computer("O", null);
        // Считываем координаты клика мышью
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                System.out.println("Mouse clicked on " + e.getX() + " " + e.getY());
                modeAgainstAI();
            }
        });
    }



void modeAgainstAI(){
            Player player = new Player("X");
            Computer computer = new Computer("O", player.sign);
            if(!gameOver){
                if(player.shot(x,y)){
                    if(player.win()){
                        System.out.println("Игрок победил!!!");
                        gameOver = true;
                        gameOverMessage = "Игрок победил!!!";
                    }
                    if (isFieldFull()){
                        gameOver = true;
                        gameOverMessage = "Ничья!";
                    }
                    repaint();
                    if(!gameOver){
                        computer.shot(x,y);
                    }
                    if(computer.win()){
                        System.out.println("Комп победил!!!");
                        gameOver = true;
                        gameOverMessage = "Комп победил!!!";
                    }
                    repaint();
                    if(isFieldFull() && !computer.win()){
                        gameOver = true;
                        gameOverMessage = "Ничья!";
                    }
                }
            }
        }

        //проверка ячейки на занятость
        boolean isCellBusy(int x, int y) {
            if (x < 0 || y < 0 || x > linesCount - 1 || y > linesCount - 1) {
                return false;
            }
            return cell[x][y] != DOT_EMPTY;
    }

        // прроверка поля на заполнение
        public boolean isFieldFull() {
            for (int i = 0; i < linesCount; i++) {
                for (int j = 0; j < linesCount; j++) {
                    if (cell[i][j] == DOT_EMPTY)
                        return false;
                }
            }
            return true;
        }

        //проверка на равенство значений
    public boolean checkLine(int start_x, int start_y, int dx, int dy, String sign){
        for (int i = 0; i < linesCount; i++) {
            if (cell[start_x + i*dx][start_y + i*dy] != sign)
                return false;
        }
        return true;
    }

    // Проверка победы
    public boolean checkWin(String sign) {
        for (int i = 0; i < linesCount; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, sign)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, linesCount - 1, 1, -1, sign)) return true;
        return false;
    }

    // Метод, который занимается отрисовкой всей графики на форме
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем линии, которые представляют собой сетку
        for (int i = 0; i <= this.linesCount; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] != DOT_EMPTY) {
                    if (cell[i][j] == "X") {
                        // Рисуем крестик
                        g.setColor(Color.RED);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (cell[i][j] == "O") {
                        // Рисуем нолик
                        g.setColor(Color.BLUE);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }

        if (gameOver) {
            // Отрисовка сообщения при завершении игры
            g.setColor(Color.BLACK);
            g.fillRect(0, FIELD_SIZE / 2, FIELD_SIZE, FIELD_SIZE / 8);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", 10, 40));
            g.drawString(gameOverMessage, 0, 19 * FIELD_SIZE / 32);
        }
    }
}


