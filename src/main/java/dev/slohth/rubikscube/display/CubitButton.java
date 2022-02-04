package dev.slohth.rubikscube.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CubitButton extends JButton implements ActionListener {

    private DisplayColor color;

    public CubitButton(int x, int y, int width, int height, DisplayColor color) {
        this.color = color;

        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusPainted(false);
        this.setBackground(color.getColor());
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

    public void cycleColor() {
        this.color = color.next();
    }
    public DisplayColor getColor() { return this.color; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            this.cycleColor();
            this.setBackground(color.getColor());
        }
    }
}
