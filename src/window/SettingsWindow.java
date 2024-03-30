package window;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Стартовое окно
 */
public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGTH = 230; // изначально
//    private static final int WINDOW_HEIGTH = 450; // пришлось сделать так
    private static final int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Start new game");
//    JButton btnRadio = new Bu("New Game");
    JPanel panBottom;
    SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGTH);
        // возня с наполнением панели gameMode
        JLabel gameMode = new JLabel("Выберите режим игры");
        // описываем элементы, связанные с установкой размера поля
        JLabel gameFieldLabel = new JLabel("Выберите размеры поля"); // лейбл-приглашение к выбору размера поля
        JLabel gameSetFieldLabel = new JLabel("Установленный размер поля:");
        JSlider gameSetFieldSlider = new JSlider(3, 10, 3);


        JLabel winLineLengthLabel = new JLabel("Выберите длину для победы");
        JLabel winSetLineLengthLabel = new JLabel("Установленная длина: ");
        JSlider winLineLengthSlider = new JSlider(3, 10, 3);


        // создание группы из 2-х радиокнопок
        // !!! группа не добавляется в panBottom, кнопки добавляются в panBottom,
        // связь кнопок образуется за счет их присутствия в группе
        JRadioButton humanVsAi = new JRadioButton("Человек против компьютера", true);
        JRadioButton humanVsHuman = new JRadioButton("Человек против человека", false);
        ButtonGroup gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(humanVsAi);
        gameModeButtonGroup.add(humanVsHuman);

        panBottom = new JPanel(new GridLayout(10, 1));

        panBottom.add(gameMode);
        panBottom.add(humanVsAi);
        panBottom.add(humanVsHuman);
        panBottom.add(gameFieldLabel);
//        panBottom.add(gameSetFieldPanel); // вставили в главную панель панель с индикатором состояния
        // слайдера размера поля
        panBottom.add(gameSetFieldLabel);
        panBottom.add(gameSetFieldSlider);
        panBottom.add(winLineLengthLabel);
        panBottom.add(winSetLineLengthLabel);
        panBottom.add(winLineLengthSlider);
        panBottom.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {

                gameWindow.startNewGame(humanVsAi.isSelected() ? 0 : 1,
                        gameSetFieldSlider.getValue(),
                        gameSetFieldSlider.getValue(),
                        winLineLengthSlider.getValue());
                setVisible(false);
            }

        });
        gameSetFieldSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = gameSetFieldSlider.getValue();
                gameSetFieldLabel.setText("Установленный размер поля: " + String.valueOf(sliderValue));

            }
        });
        winLineLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = winLineLengthSlider.getValue();
                winSetLineLengthLabel.setText("Установленная длина: " + String.valueOf(sliderValue));

            }
        });

        add(panBottom);
//        add(btnStart);

    }
}
