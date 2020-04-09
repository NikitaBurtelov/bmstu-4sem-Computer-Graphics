package sample;

import com.sun.javafx.geom.Shape;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

public class ShapeBox extends Application{
    double[] arrStart;
    private Color color;
    private double cutOne;
    private double cutTwo;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param arrPlace - координаты начальной и конечной позиции отрезка
     * */
    ShapeBox(double[] arrPlace, Color color, double cutOne, double cutTwo){
        this.arrStart = arrPlace;
        this.color = color;
        this.cutOne = cutOne;
        this.cutTwo = cutTwo;
    }

    Pane drawBox(Pane pane, double[] arrPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrBox(arrPlace);

        pane.getChildren().addAll(shapeLine.drawLine(arr[0]), shapeLine.drawLine(arr[1]),
                shapeLine.drawLine(arr[2]), shapeLine.drawLine(arr[3]));

        return pane;
    }

    Pane drawRhomb(Pane pane, double[] arrPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrRhomb(arrPlace);

        pane.getChildren().addAll(shapeLine.drawLine(arr[0]), shapeLine.drawLine(arr[1]),
                shapeLine.drawLine(arr[2]), shapeLine.drawLine(arr[3]));

        return pane;
    }

    private double[][] getArrBox(double[] arrPlace) {
        double[][] arr = new double[][] {
                {arrPlace[0], arrPlace[1], arrPlace[2], arrPlace[1]}, //AC
                {arrPlace[2], arrPlace[1], arrPlace[2], arrPlace[3]}, //CB
                {arrPlace[2], arrPlace[3], arrPlace[0], arrPlace[3]}, //BD
                {arrPlace[0], arrPlace[3], arrPlace[0], arrPlace[1]}, //DA
        };

        System.out.println(arr.length);

        return arr;
    }

    private double[][] getArrRhomb(double[] arrPlace) {
        double x1, x2, x3, x4, y1, y2, y3, y4;

        x1 = x2 = (arrPlace[0] + arrPlace[2]) * 0.5; // K
        y1 = arrPlace[1];
        y2 = arrPlace[3];
        x3 = arrPlace[0];
        y3 = y4 = (arrPlace[1] + arrPlace[3]) * 0.5;
        x4 = arrPlace[2];
        //this.arrStart[0] = x1;
        //this.arrStart[1] = y3;

        double[][] arr = new double[][] {
                {x1, y1, x3, y3},
                {x2, y2, x3, y3},
                {x2, y2, x4, y4},
                {x4, y4, x1, y1},
        };

        System.out.println(arr.length);

        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " " + arr[i][3]);

        return arr;
    }

    Pane moveBox(Pane pane, double[] arrPlace, double[] arrOffset) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrBox(arrPlace);

        for (int i = 0; i < 4; i++)
            pane.getChildren().addAll(shapeLine.moveLine(arr[i], arrOffset));

        return pane;
    }

    Pane moveRhomb(Pane pane, double[] arrPlace, double[] arrOffset) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrRhomb(arrPlace);

        for (int i = 0; i < 4; i++)
            pane.getChildren().addAll(shapeLine.moveLine(arr[i], arrOffset));

        return pane;
    }

    Pane scaleBox(Pane pane, double[] arrPlace, double size, double[] centerPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrBox(arrPlace);

        for (int i = 0; i < 4; i++)
            pane.getChildren().addAll(shapeLine.scaleLine(arr[i], size, centerPlace));

        return pane;
    }

    Pane scaleRhomb(Pane pane, double[] arrPlace, double size, double[] centerPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrRhomb(arrPlace);
        System.out.println("\nRhomd\n");

        for (int i = 0; i < 4; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " " + arr[i][3]);
            pane.getChildren().addAll(shapeLine.scaleLine(arr[i], size, centerPlace));
        }

        return pane;
    }

    Pane rotateBox(Pane pane, double[] arrPlace, double angle, double[] centerPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrBox(arrPlace);

        for (int i = 0; i < 4; i++)
            pane.getChildren().addAll(shapeLine.rotateLine(arr[i], angle, centerPlace));

        return pane;
    }

    Pane rotateRhomb(Pane pane, double[] arrPlace, double angle, double[] centerPlace) {
        ShapeLine shapeLine = new ShapeLine(this.arrStart, this.color);
        double[][] arr = getArrRhomb(arrPlace);

        for (int i = 0; i < 4; i++)
            pane.getChildren().addAll(shapeLine.rotateLine(arr[i], angle, centerPlace));

        return pane;
    }

    @Override
    public void start(Stage primaryStage){

    }
}
