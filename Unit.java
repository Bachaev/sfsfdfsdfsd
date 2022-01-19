package ru.test.model;

import java.awt.*;

public class Unit {
    private int x;
    private int y;
    private int width;
    private int height;
    //Это мина?
    private boolean mine;
    //Ячейка открыта?
    boolean open;
    //Количество мин вокруг
    int numberMine;
    //Ячейка отмечена?
    private boolean mark;


    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = Model.FIELD_CELL_SIZE;
        this.height = Model.FIELD_CELL_SIZE;
    }

    public void draw(Graphics graphics) {
        if (this.isOpen())
            if (this.isMine())
                graphics.setColor(new Color(125, 0, 29));
            else
                graphics.setColor(Color.WHITE);
        else
            graphics.setColor(Color.GRAY);
        graphics.fillRect(getX() * getWidth(), getY() * getHeight(), getWidth(), getHeight());

        if (this.isMark() && (!this.isOpen() || this.isMine())) {
            graphics.setColor(new Color(205, 0, 39));
            graphics.setFont(new Font("Times new roman", 0, getHeight()));
            graphics.drawString("D", getX() * getWidth() + getWidth() * 5 / 16, getY() * getHeight() + getHeight() * 7 / 8);
        }

        //рисуем клетки
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getX() * getWidth(), getY() * getHeight(), getWidth(), getHeight());

        //рисуем количество мин
        if (this.isOpen() && numberMine != 0) {
            Color colorNumber = null;
            switch (numberMine) {
                case 1:
                    colorNumber = Color.BLUE;
                    break;
                case 2:
                    colorNumber = new Color(0, 177, 56);
                    break;
                case 3:
                    colorNumber = new Color(214, 1, 0);
                    break;
                case 4:
                    colorNumber = new Color(2, 22, 99);
                    break;
                case 5:
                    colorNumber = new Color(137, 0, 21);
                    break;
                case 6:
                    colorNumber = new Color(0, 94, 46);
                    break;
                case 7:
                    colorNumber = new Color(255, 145, 0);
                    break;
                case 8:
                    colorNumber = new Color(123, 215, 212);
                    break;
            }
            graphics.setColor(colorNumber);
            graphics.setFont(new Font("Times new roman", 0, getHeight()));
            graphics.drawString(String.valueOf(numberMine), getX() * getWidth() + getWidth() * 5 / 16, getY() * getHeight() + getHeight() * 7 / 8);
        }
    }

    public void burst() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getNumberMine() {
        return numberMine;
    }

    public void setNumberMine(int numberMine) {
        this.numberMine = numberMine;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isMark() {
        return mark;
    }
}
