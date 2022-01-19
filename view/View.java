package ru.test.view;


import ru.test.controller.Controller;
import ru.test.model.GameObjects;
import ru.test.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;


public class View extends JFrame {
    private GameState state;
    private Level level;
    private GameObjects gameObjects;
    private Controller controller;
    private Field field;
    private JFrame frame;
    private long mSeconds; // ??здесь буду хранить секунды
    private Object[][] data = new Object[99][3];
    private int rows = 0;
    private int col = 0;
    private JTable jt = null;
    public View(Controller controller) {
        this.controller = controller;
        start();
    }
    public View(){
        this.level = getLevel();
    }

    public Level getLevel() {
        return level;
    }

    public void init() {

        field = new Field(this);
        field.setController(controller);
        field.setPreferredSize(new Dimension((getGameObjects().getUnits().length) * getGameObjects().getUnits()[0][0].getWidth()+1,
                (getGameObjects().getUnits()[0].length) * getGameObjects().getUnits()[0][0].getHeight()+1));
        JPanel jPanel = new JPanel();
        jPanel.add(field);
        add(jPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Игра");
        JMenu difficulty = new JMenu("Сложность");
        JMenuItem easy = new JMenuItem("Легкий");
        JMenuItem medium = new JMenuItem("Средний");
        JMenuItem hard = new JMenuItem("Сложный");
        JMenuItem menuItem1 = new JMenuItem("Начать заново");
        JMenuItem menuItem2 = new JMenuItem("Выход");
        JMenuItem menuItem3 = new JMenuItem("Статистика");
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level = Level.EASY;
                new Controller(level);
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level = Level.MEDIUM;
                new Controller(level);
            }
        });
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                level = Level.HARD;
                new Controller(level);
            }
        });
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.restart();
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuItem3.addMouseListener(new MouseAdapter() { //добавляем слушатель мышки (адаптер мышки)
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    initTable();
                }
            }
        });
        menu.add(menuItem1);
        menu.addSeparator();
        menu.add(menuItem3);
        menu.add(menuItem2);
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        difficulty.addSeparator();
        menu.add(difficulty);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Сапёр");
        setVisible(true);
    }
    public void start(){
        mSeconds = System.currentTimeMillis();
    }
    public void initTable() {
        frame = new JFrame("Статистика");
        if (jt != null){
            frame.setVisible(false);
        }
        else {
            frame.setVisible(true);
        }
        String column[]={"Время игры(c)","Время","Результат"};
        frame.setBounds(30,40,300,300);
        jt=new JTable(data,column);
        jt.setDefaultEditor(Object.class, null);
        JScrollPane sp=new JScrollPane(jt);
        frame.getContentPane().add(sp);

    }
    public void initData(){
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        data[rows][col] = mSeconds;
        data[rows][++col] = dt.format(date);
        data[rows][++col] = state;
        rows++;
        col = 0;
    }
    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        state = GameState.WINNER;
        mSeconds = (System.currentTimeMillis() - mSeconds) / 1000;
        initData();
        int result = JOptionPane.showConfirmDialog(this, "Уровень завершен!","Молодец",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION)
            controller.startNextLevel();
        else if (result == JOptionPane.NO_OPTION)
            controller.restart();
    }

    public void gameOver() {
        update();
        state=GameState.BOMBED;
        mSeconds = (System.currentTimeMillis()-mSeconds) / 1000;
        initData();
        JOptionPane.showMessageDialog(this, "Вы проиграли.");
        controller.restart();
    }
}
