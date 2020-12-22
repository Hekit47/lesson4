package lesson4;

import java.util.SimpleTimeZone;

public class TicTacToe {

    private static final int SIZE = 3; // final означает, что поле меняться больше не будет
    private static final char[][] map = new char[SIZE][SIZE]; //создали статическую переменную чар, которая двумерный масси

    private static final char DOT_EMPTY ='•';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    public static void main(String[] args) { // инициализируем состояние нашей игры
        intializeGame();                     //нажать Alt + Enter
        printMap();                          // создали метод, который будет выводить на экран
    }

    private static void printMap() {        //в этом методе должны пробежаться по массиву, но при этом вывести номера строк и колонок
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            System.out.print((rowIndex + 1) + " ");
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                System.out.print(map[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i < 1 + SIZE; i++) {
            System.out.print(i + " ");
        } //прогнали циклом вывод номеров колонок, теперь переходим на след строку
        System.out.println();
    }

    private static void intializeGame() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
// инициализируем данные в ячейках. присваиваем им значение DOT_EMPTY
                map[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
    }
}
