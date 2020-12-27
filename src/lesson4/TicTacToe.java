package lesson4;

import com.sun.prism.shader.Solid_ImagePattern_Loader;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3; // final означает, что поле меняться больше не будет
    private static final char[][] map = new char[SIZE][SIZE]; //создали статическую переменную чар, которая двумерный масси

    private static final char DOT_EMPTY ='•';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    private static final Scanner SCANNER= new Scanner(System.in);

    public static void main(String[] args) { // инициализируем состояние нашей игры
        intializeGame();                     //нажать Alt + Enter
        printMap();                          // создали метод, который будет выводить на экран

        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X, "Человек победил!")) break;

            computerTurn();
            printMap();
            if (checkEndGame(DOT_0, "Комп победил!")) break;
        }
    }

    private static boolean checkEndGame(char dot0, String s) {
        if (checkWin(dot0)) {
            System.out.println(s);
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean isMapFull(){
        for (char[] rows : map) {
            for (char cellValue : rows) {
                if (cellValue == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin (char symbol){ //переписать циклами, для строк, столбцов и диагоналей отдельно
        //проверяем победу по строкам

        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            int stringCol = 0;
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                if (map[rowIndex][colIndex] == symbol) {
                    stringCol++;
                }
            } if (stringCol == SIZE) return true;
        }

//        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
//        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
//        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;

        //проверяем победу по столбцам

        for (int colIndex = 0; colIndex < SIZE; colIndex++) {
            int stringCol = 0;
            for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
                if (map[rowIndex][colIndex] == symbol) {
                    stringCol++;
                }
            }
            if (stringCol == SIZE) return true;
        }

//        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
//        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
//        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;

        //проверяем правую диагональ
        int stringCol = 0;
        for (int index = 0; index < SIZE; index++) {
            if (map[index][index] == symbol){
                stringCol ++;
            }
        }
        if (stringCol == SIZE) return true;
        //проверяем левую диагональ
        stringCol = 0;
        int colIndex = SIZE - 1;
        for (int rowIndex = SIZE - 1; rowIndex >= 0; rowIndex-- ) {
            if (map[rowIndex][colIndex] == symbol){
                stringCol ++;
            } colIndex--;
        }
        if (stringCol == SIZE) return true;
//        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
//        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;

        return false;
    }


    private static void humanTurn() {
        int rowIndex; //вызываем сканнер, который прочитает из консоли номер строки
        int colIndex;
        System.out.println("Ваш ход!");//вызываем сканнер, который прочитает из консоли номер столбца
        do {
            //цикл ввода адреса ячейки
            System.out.print("Введите номер строки: ");
            rowIndex = SCANNER.nextInt() - 1;

            System.out.print("Введите номер столбца: ");
            colIndex = SCANNER.nextInt() - 1;
        } while (!isCellValid(rowIndex, colIndex, DOT_X)); //будет продолжаться, пока ячейка будет вне диапазона

        map[rowIndex][colIndex] = DOT_X;
    }
private static void computerTurn() {
        int rowIndex; //вызываем сканнер, который прочитает из консоли номер строки
        int colIndex; //вызываем сканнер, который прочитает из консоли номер столбца
        System.out.println("Ход компьютера!");
        Random rand = new Random();
        do {
             rowIndex = rand.nextInt(SIZE);
             colIndex = rand.nextInt(SIZE);
        } while (!isCellValid(rowIndex, colIndex, DOT_0)); //будет продолжаться, пока ячейка будет вне диапазона

        map[rowIndex][colIndex] = DOT_0;
    }

    private static boolean isCellValid(int rowIndex, int colIndex, char symbol) {
        if (!isArrayIndexValid(rowIndex) | !isArrayIndexValid(colIndex)){
            System.out.println("Индексы строки и колонки должны быть в диапазоне от 1 до " + SIZE);
            return false;
        }

        if (map[rowIndex][colIndex] != DOT_EMPTY){
            if (isHumanTurn(symbol)) {
                System.out.println("Данная ячейка уже занята");
            }
            return false;
        }
        return true;
    }

    private static boolean isHumanTurn(char symbol) {
        return symbol == DOT_X;
    }

    private static boolean isArrayIndexValid(int index) {
        return index >= 0 && index < SIZE;
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
        for (int i = 0; i <= SIZE; i++) {
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
