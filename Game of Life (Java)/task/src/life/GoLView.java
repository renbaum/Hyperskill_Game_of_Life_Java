package life;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GoLView extends JFrame {

    GoLMatrix matrix;
    ControllGoL ctrl;

    JPanel panelLabels;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    JLabel GenerationText;
    JLabel AliveText;

    JPanel GridPanel;
    JPanel[][] GridCells;

    JToggleButton ButtonPause;
    JButton Reset;

    public GoLView() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        //setVisible(true);
        setResizable(false);


    }

    public void setMatrix(GoLMatrix matrix) {
        this.matrix = matrix;
    }

    public void initialise(){
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        panelLabels = new JPanel();
        add(panelLabels, BorderLayout.NORTH);
        GenerationLabel = new JLabel("0");
        GenerationLabel.setName("GenerationLabel");
        GenerationText = new JLabel("Generation: ");
        AliveLabel = new JLabel("0");
        AliveLabel.setName("AliveLabel");
        AliveText = new JLabel("Alive: ");
        panelLabels.add(GenerationText);
        panelLabels.add(GenerationLabel);
        panelLabels.add(AliveText);
        panelLabels.add(AliveLabel);

        ButtonPause = new JToggleButton("Pause");
        ButtonPause.setName("PlayToggleButton");
        ButtonPause.addActionListener(e -> {
            if (ButtonPause.isSelected()) {
                ButtonPause.setText("Play");
                ctrl.stop();
            } else {
                ButtonPause.setText("Pause");
                ctrl.start();
            }
        });
        Reset = new JButton("Reset");
        Reset.setName("ResetButton");
        Reset.addActionListener(e -> {
           ctrl.reset();
        });
        panelLabels.add(Box.createHorizontalGlue());
        panelLabels.add(ButtonPause);
        panelLabels.add(Reset);

        GridPanel = new JPanel();
        GridPanel.setLayout(new GridLayout(matrix.getDimension(), matrix.getDimension()));
        GridCells = new JPanel[matrix.getDimension()][matrix.getDimension()];
        for (int i = 0; i < matrix.getDimension(); i++) {
            for (int j = 0; j < matrix.getDimension(); j++) {
                GridCells[i][j] = new JPanel();
                GridCells[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                GridCells[i][j].setBackground(Color.GRAY);
                GridPanel.add(GridCells[i][j]);
            }
        }
        add(GridPanel, BorderLayout.CENTER);
        setVisible(true);
        //repaint();
    }

    public void print(int generation) {
        GenerationLabel.setText(String.valueOf(generation));
        AliveLabel.setText(String.valueOf(matrix.getAliveCells()));


        for (int i = 0; i < matrix.getDimension(); i++) {
            for (int j = 0; j < matrix.getDimension(); j++) {
                switch (matrix.getCell(i, j)) {
                    case ' ':
                        GridCells[i][j].setBackground(Color.GRAY);
                        break;
                    case 'O':
                        GridCells[i][j].setBackground(Color.DARK_GRAY);
                        break;

                }
            }
        }
        repaint();
    }

}
