package dev.slohth.rubikscube.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintButton extends JButton implements ActionListener {

    private final Display display;
    private final DisplayColor color;

    public PaintButton(Display display, DisplayColor color, int x, int y, int width, int height) {
        this.display = display;
        this.color = color;

        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusPainted(false);
        this.setBackground(PaintButton.this.color.getColor());
        this.setBounds(x, y, width, height);
        this.addActionListener(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                PaintButton.this.setBackground(
                        new Color(
                                Math.min(PaintButton.this.color.getColor().getRed() + 20, 255),
                                Math.min(PaintButton.this.color.getColor().getGreen() + 20, 255),
                                Math.min(PaintButton.this.color.getColor().getBlue() + 20, 255)
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent event) {
                PaintButton.this.setBackground(PaintButton.this.color.getColor());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            display.setColor(PaintButton.this.color);
        }
    }
}

