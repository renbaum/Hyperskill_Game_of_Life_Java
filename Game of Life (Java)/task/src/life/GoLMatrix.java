package life;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GoLMatrix {
    int dimension;
    private char[][] matrix;
    private Random random;

    public GoLMatrix(int dimension) {
        long seed = System.currentTimeMillis();
        this.dimension = dimension;
        random = new Random(seed);

        initialiseMatrix();

    }

    private int checkIndex(int i) {
        int index = i % dimension;
        if (index < 0) index = dimension + index;
        return index;
    }
    char getCell(int i, int j) {

        return matrix[checkIndex(i)][checkIndex(j)];
    }

    int getDimension() {
        return dimension;
    }

    char[][] createEmptyMatrix() {
        char[][] x = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                x[i][j] = ' ';
            }
        }
        return x;
    }

    int getAliveCells() {
        int aliveCells = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (matrix[i][j] == 'O') {
                    aliveCells++;
                }
            }
        }
        return aliveCells;
    }

    void initialiseMatrix(){
        matrix = createEmptyMatrix();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (random.nextBoolean()) {
                    matrix[i][j] = 'O';
                }
            }
        }
    }

    void generateNextGeneration() {
        char[][] nextGeneration = createEmptyMatrix();
        char[][] neighbours = createEmptyMatrix();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Set<String> aliveNeighbours = new HashSet<>();
                //int aliveNeighbours = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k == i && l == j) continue;
                        //if (!(k == i || l == j)) continue;

                        if(getCell(k, l) == 'O') {
                            aliveNeighbours.add(checkIndex(k) + "," + checkIndex(l));
                            //aliveNeighbours++;
                        }
                    }
                }
                switch (getCell(i, j)) {
                    case 'O':
                        if (aliveNeighbours.size() == 2 || aliveNeighbours.size() == 3) {
                            nextGeneration[i][j] = 'O';
                        }
                    break;
                    case ' ':
                        if (aliveNeighbours.size() == 3) {
                            nextGeneration[i][j] = 'O';
                        }
                }
                neighbours[i][j] = Character.forDigit(aliveNeighbours.size(), 10);
            }
        }
        //dummyPrint(matrix, neighbours, nextGeneration);

        matrix = nextGeneration;
    }

    private void dummyPrintLine(char[] matrix, char[] neighbours, char[] nextGeneration){
        String m, n, g;
        m = String.valueOf(matrix);
        n = String.valueOf(neighbours);
        g = String.valueOf(nextGeneration);

        System.out.println(m + " " + n + " " + g);
    }

    private void dummyPrint(char[][] matrix, char[][] neighbours, char[][] nextGeneration){
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            dummyPrintLine(matrix[i], neighbours[i], nextGeneration[i]);
        }
        System.out.println();
    }
}

