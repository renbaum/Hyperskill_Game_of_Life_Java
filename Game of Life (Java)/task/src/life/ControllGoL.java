package life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;

public class ControllGoL {
    int dimension = 0;
    int generation = 0;

    GoLMatrix matrix = null;
    GoLView view = null;
    Timer timer = null;

    public ControllGoL() {
        dimension = 30;
        generation = 0;

        matrix = new GoLMatrix(dimension);
        view = new GoLView();

        view.setMatrix(matrix);
        view.ctrl = this;

        view.initialise();

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix.generateNextGeneration();
                view.print(++generation);
            }
        });

        start();
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    public void reset(){
        timer.stop();
        generation = 0;
        matrix.initialiseMatrix();
        view.print(++generation);
        timer.start();
    }
}
