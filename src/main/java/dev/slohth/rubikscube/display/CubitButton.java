package dev.slohth.rubikscube.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CubitButton extends JButton implements ActionListener {

    private final Display display;
    private DisplayColor color = DisplayColor.BLANK;

    public CubitButton(Display display, int x, int y, int width, int height) {
        this.display = display;

        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusPainted(false);
        this.setBackground(display.getColor().getColor());
        this.setBounds(x, y, width, height);
        this.addActionListener(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                CubitButton.this.setBackground(
                        new Color(
                                Math.min(CubitButton.this.color.getColor().getRed() + 20, 255),
                                Math.min(CubitButton.this.color.getColor().getGreen() + 20, 255),
                                Math.min(CubitButton.this.color.getColor().getBlue() + 20, 255)
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent event) {
                CubitButton.this.setBackground(CubitButton.this.color.getColor());
            }
        });
    }

    public DisplayColor getColor() { return this.color; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            if (display.getColor() == this.color) {
                this.color = DisplayColor.BLANK;
            } else {
                this.color = display.getColor();
            }
            this.setBackground(color.getColor());
        }
    }
}


