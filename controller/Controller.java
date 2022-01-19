package ru.test.controller;


import ru.test.model.GameObjects;
import ru.test.model.Model;
import ru.test.view.Level;
import ru.test.view.View;

public class Controller {
    private View view;
    private Model model;
    private View md = new View();
    private int currentLevel=5;

    public Controller(){
        model = new Model(this);
        model.restart(currentLevel);
        view = new View(this);
        view.init();
    }
    public Controller(Level level) {
        model = null;
        view = null;
        if(level == Level.EASY)
            {
                currentLevel =3;
            }
            else if (level == Level.MEDIUM)
                    currentLevel =7;
            else if (level==Level.HARD)
                    currentLevel =10;
        model = new Model(this);
        model.restart(currentLevel);
        view = new View(this);
        view.init();
    }

    public static void main(String[] args) {
        new Controller();
    }


    public void restart() {
        model.restart(currentLevel);
        view.update();
        view.start();
    }

    public void startNextLevel() {
        model.startNextLevel();
        view = new View(this);
        view.init();
    }

    public void levelCompleted(int level) {
        view.completed(level);
    }

    public void openUnit(int x, int y) {
        model.openUnit(x, y);
        view.update();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public void markUnit(int x, int y) {
        model.markUnit(x, y);
        view.update();
    }

    public void gameOver() {
        view.gameOver();
    }
}
