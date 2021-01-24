package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;


public class GameWindow extends JFrame {
    public GameWindow() {
        // название игры в заголовке
        setTitle("The TicTacToe game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //размеры и начальное положение окна
        setBounds(300, 300, 455, 525);
        // фиксируем размер окна
        setResizable(false);
        // создадим экземпляр игрового поля
        MainGameField gameField = MainGameField.getInstance();
        //создаем панел кнопок таблицей
        JPanel buttonPanel = new JPanel(new GridLayout());
        // добавим игровое поле
        add(gameField, BorderLayout.CENTER);
        // добавим кнопок начала и конца игры
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnStart = new JButton("Новая игра");
        JButton btnEnd = new JButton("Закончить игру");
        buttonPanel.add(btnStart);
        buttonPanel.add(btnEnd);

        //добавим обработку событий для закрытия игры
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // событие, если нажали на новую игру
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(btnEnd.getText());
                MainGameField mainGameField = new MainGameField();
            }
        });
        setVisible(true);
    }
}