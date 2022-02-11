package dev.slohth.rubikscube.display;

import dev.slohth.rubikscube.cube.Cube;
import dev.slohth.rubikscube.solver.CubeSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Display implements ActionListener {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private final Font font = new Font("SansSerif", Font.BOLD, 20);
    private final JButton solve = new JButton("Solve");

    private final LinkedList<CubitButton> buttons = new LinkedList();

    private DisplayColor color = DisplayColor.BLANK;

    public Display() {
        frame.setResizable(false);
        frame.setSize(665, 530);
        panel.setSize(665, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Rubiks Cube Solver");

        panel.setLayout(null);
        panel.setBackground(new Color(31, 31, 31));

        //TODO: UIManager.put("Button.select", new Color(119, 119, 119, 61));

        solve.setFont(font);
        solve.setBorder(BorderFactory.createEmptyBorder());
        solve.setFocusPainted(false);
        solve.setBackground(new Color(50, 50, 50));
        solve.setForeground(Color.lightGray);
        solve.setBounds(475, 425, 140, 40);
        solve.addActionListener(this);

        panel.add(solve);

        paintCube(25, 25);
        for (CubitButton button : buttons) panel.add(button);

        addPaintButtons(475, 25);

        frame.setVisible(true);
    }

    private void paintFace(int x, int y, DisplayColor color) {
        buttons.add(new CubitButton(this, x, y, 40, 40));
        buttons.add(new CubitButton(this, x + 50, y, 40, 40));
        buttons.add(new CubitButton(this, x + 100, y, 40, 40));
        buttons.add(new CubitButton(this, x, y + 50, 40, 40));
        buttons.add(new CubitButton(this, x + 50, y + 50, 40, 40));
        buttons.add(new CubitButton(this, x + 100, y + 50, 40, 40));
        buttons.add(new CubitButton(this, x, y + 100, 40, 40));
        buttons.add(new CubitButton(this, x + 50, y + 100, 40, 40));
        buttons.add(new CubitButton(this, x + 100, y + 100, 40, 40));
    }

    private void paintCube(int x, int y) {
        paintFace(x + 150, y, DisplayColor.RED);
        paintFace(x, y + 150, DisplayColor.BLUE);
        paintFace(x + 150, y + 150, DisplayColor.WHITE);
        paintFace(x + 300, y + 150, DisplayColor.GREEN);
        paintFace(x + 450, y + 150, DisplayColor.YELLOW);
        paintFace(x + 150, y + 300, DisplayColor.ORANGE);
    }

    private void addPaintButtons(int x, int y) {
        panel.add(new PaintButton(this, DisplayColor.RED, x, y, 40, 40));
        panel.add(new PaintButton(this, DisplayColor.ORANGE, x + 50, y, 40, 40));
        panel.add(new PaintButton(this, DisplayColor.YELLOW, x + 100, y, 40, 40));
        panel.add(new PaintButton(this, DisplayColor.GREEN, x, y + 50, 40, 40));
        panel.add(new PaintButton(this, DisplayColor.BLUE, x + 50, y + 50, 40, 40));
        panel.add(new PaintButton(this, DisplayColor.WHITE, x + 100, y + 50, 40, 40));
    }

    public int[] getInput() {
        int[] arr = new int[54];
        for (int i = 0; i < 54; i++) arr[i] = buttons.get(i).getColor().getData();
        return arr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(solve)) {
            frame.setSize(1100, 530);

            JPanel movesPanel = new JPanel();
            movesPanel.setLayout(null);
            movesPanel.setBackground(new Color(25, 25, 25));
            movesPanel.setBounds(665, 0, 1100 - 665, 510);

            JTextArea text = new JTextArea(10, 20);
            JScrollPane pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            text.setBounds(665, 0, 450, 490);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setFont(new Font("SansSerif", Font.BOLD, 15));
            text.setBackground(new Color(25, 25, 25));
            text.setForeground(Color.white);
            text.setBorder(BorderFactory.createEmptyBorder());


            pane.setBounds(665, 0, 1100 - 665, 490);
            pane.setBorder(BorderFactory.createEmptyBorder());

            movesPanel.add(pane);
            frame.add(movesPanel);
            frame.setVisible(true);
            solve.setText("Solved!");

            Cube cube = new Cube(this.getInput());
            CubeSolver solver = new CubeSolver(cube);

            solver.solveBottomCross();
            String bottomCross = cube.getMovesDisplay();
            cube.resetMoves();

            solver.solveBottomLayer();
            String bottomLayer = cube.getMovesDisplay();
            cube.resetMoves();

            solver.firstTwoLayers();
            String f2l = cube.getMovesDisplay();
            cube.resetMoves();

            solver.solveTopCross();
            String topCross = cube.getMovesDisplay();
            cube.resetMoves();

            solver.solveTopCorners();
            String topLayer = cube.getMovesDisplay();
            cube.resetMoves();

            solver.orientLastLayer();
            String oll = cube.getMovesDisplay();
            cube.resetMoves();

            text.setText(
                    "Bottom Cross\n\n" + bottomCross + "\n\n" +
                    "Bottom Layer\n\n" + bottomLayer + "\n\n" +
                    "F2L\n\n" + f2l + "\n\n" +
                    "Top Cross\n\n" + topCross + "\n\n" +
                    " Top Layer\n\n" + topLayer + "\n\n" +
                    "OLL\n\n" + oll
            );
        }
    }

    public DisplayColor getColor() { return color; }
    public void setColor(DisplayColor color) { this.color = color; }

}

