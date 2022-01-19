package ru.test.model;


public class GameObjects {
    private Unit[][] units;

    public GameObjects(int level) {
        units = new Unit[3 * level][2 * level];
        int numberUnit = units[0].length * units.length;
        //процент заполнения поля минами, здесь это 15
        int numberMine = numberUnit * 15 / 100;

        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                units[i][j] = new Unit(i, j);
            }
        }

        // ставим мины
        int countMine = 0;
        while (countMine < numberMine)
            for (int i = 0; i < units.length; i++) {
                for (int j = 0; j < units[0].length; j++) {
                    if (countMine < numberMine && Math.random() * 100 > 99) {
                        units[i][j].setMine(true);
                        countMine++;
                    }
                    if (countMine >= numberMine)
                        break;
                }
            }

        //считаем сколько мин вокруг для каждой ячейки
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                if (!units[i][j].isMine()) {
                    if (i < units.length - 1 && units[i + 1][j].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);
                    if (i < units.length - 1 && j < units[0].length - 1 && units[i + 1][j + 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);
                    if (j < units[0].length - 1 && units[i][j + 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);

                    if (i > 0 && units[i - 1][j].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);
                    if (i > 0 && j > 0 && units[i - 1][j - 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);
                    if (j > 0 && units[i][j - 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);

                    if (i < units.length - 1 && j > 0 && units[i + 1][j - 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);
                    if (i > 0 && j < units[0].length - 1 && units[i - 1][j + 1].isMine())
                        units[i][j].setNumberMine(units[i][j].getNumberMine() + 1);

                }
            }
        }
    }

    public Unit[][] getUnits() {
        return units;
    }
}