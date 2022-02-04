package dev.slohth.rubikscube.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

public class Display implements ActionListener {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private final Font font = new Font("SansSerif", Font.BOLD, 20);
    private final JButton solve = new JButton("Solve");

    private final LinkedList<CubitButton> buttons = new LinkedList();

    public Display() {
        frame.setSize(665, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Rubiks Cube Solver");

        panel.setLayout(null);
        panel.setBackground(new Color(31, 31, 31));

        solve.setFont(font);
        solve.setBorder(BorderFactory.createEmptyBorder());
        solve.setFocusPainted(false);
        solve.setBackground(new Color(50, 50, 50));
        solve.setForeground(Color.lightGray);
        solve.setBounds(515, 420, 100, 45);
        solve.addActionListener(this);

        panel.add(solve);

        paintCube(25, 25);
        for (CubitButton button : buttons) panel.add(button);

        frame.setVisible(true);
    }

    private void paintFace(int x, int y, DisplayColor color) {
        buttons.add(new CubitButton(x, y, 40, 40, color));
        buttons.add(new CubitButton(x + 50, y, 40, 40, color));
        buttons.add(new CubitButton(x + 100, y, 40, 40, color));
        buttons.add(new CubitButton(x, y + 50, 40, 40, color));
        buttons.add(new CubitButton(x + 50, y + 50, 40, 40, color));
        buttons.add(new CubitButton(x + 100, y + 50, 40, 40, color));
        buttons.add(new CubitButton(x, y + 100, 40, 40, color));
        buttons.add(new CubitButton(x + 50, y + 100, 40, 40, color));
        buttons.add(new CubitButton(x + 100, y + 100, 40, 40, color));
    }

    private void paintCube(int x, int y) {
        paintFace(x + 150, y, DisplayColor.RED);
        paintFace(x, y + 150, DisplayColor.BLUE);
        paintFace(x + 150, y + 150, DisplayColor.WHITE);
        paintFace(x + 300, y + 150, DisplayColor.GREEN);
        paintFace(x + 450, y + 150, DisplayColor.YELLOW);
        paintFace(x + 150, y + 300, DisplayColor.ORANGE);
    }

    public int[] getInput() {
        int[] arr = new int[54];
        for (int i = 0; i < 54; i++) arr[i] = buttons.get(i).getColor().getData();
        return arr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(solve)) {
            System.out.println(Arrays.toString(this.getInput()));
        }
    }

}
