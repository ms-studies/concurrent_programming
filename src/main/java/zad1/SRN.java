package zad1;

import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.matrix.functor.MatrixFunction;

import java.util.Random;

/**
 * Created by Marcin Szalek on 28.10.17.
 */
public class SRN {

    Matrix inputs = new Basic1DMatrix(1, 12, new double[]{0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0});
    Matrix inputLayer = new Basic1DMatrix(4, 1);
    Matrix weights1 = new Basic2DMatrix(4, 3);
    Matrix hiddenLayer = new Basic1DMatrix(3, 1);
    Matrix weights2 = new Basic1DMatrix(3, 1);
    Matrix outputLayer = new Basic2DMatrix(1, 1);

    public void makeThingsHappen() {
        //init
        weights1 = randomlyInitWeights(4,3);
        weights2 = randomlyInitWeights(3,1);
        //make things happen
        for (int inputNr=0; inputNr<12 ;inputNr++) {
            //set input
            inputLayer.set(0, 0, inputs.get(0,inputNr));
            for (int ii=0; ii<3; ii++) {
                inputLayer.set(ii+1, 0, hiddenLayer.get(ii, 0));
            }
            //forward
            hiddenLayer = weights1.transpose().multiply(inputLayer);
            outputLayer = weights2.transpose().multiply(hiddenLayer);


        }
    }

    public Matrix randomlyInitWeights(int rows, int columns) {
        double epsilonInit = Math.sqrt(6) / (Math.sqrt(rows) + Math.sqrt(columns));
        Matrix matrix = new Basic2DMatrix(rows, columns);
        matrix = matrix.transform(new MatrixFunction() {
            @Override
            public double evaluate(int i, int j, double value) {
                return new Random().nextDouble() * 2 * epsilonInit - epsilonInit;
            }
        });
        return matrix;
    }
}
