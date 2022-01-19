package ru.test.view;

import ru.test.controller.Controller;
import ru.test.model.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JPanel {
    private View view;
    private Controller controller;

    public Field(View view) {
        this.view = view;
        this.addMouseListener(new MouseHandler());
        this.setFocusable(true);
    }

    public void paint(Graphics g) {
        Unit[][] units = view.getGameObjects().getUnits();
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[0].length; j++) {
                units[i][j].draw(g);
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    controller.openUnit(e.getX(), e.getY());
                    break;
                case MouseEvent.BUTTON3:
                    controller.markUnit(e.getX(), e.getY());
                    break;
            }
        }
    }
}
