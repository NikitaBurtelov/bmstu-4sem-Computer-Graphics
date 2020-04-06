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
    private double[] arrStart;
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

    private Pane drawBox(Pane pane, double[] arrPlace) throws IOException {
        Polygon box = new Polygon(getArrBox(arrPlace));
        pane.getChildren().addAll(box);

        return pane;
    }

    private Pane drawRhomb(Pane pane, double[] arrPlace) throws IOException {
        Polygon box = new Polygon(getArrRhomb(arrPlace));
        pane.getChildren().addAll(box);

        return pane;
    }

    private double[] getArrBox(double[] arrPlace) {
        double[] arr = new double[] {
                arrPlace[0], arrPlace[1], //A
                arrPlace[2], arrPlace[1], //C
                arrPlace[2], arrPlace[3], //B
                arrPlace[0], arrPlace[3], //D
        };

        System.out.println(arr.length);

        return arr;
    }

    private double[] getArrRhomb(double[] arrPlace) {
        double x1, x2, x3, x4, y1, y2, y3, y4;

        x1 = x2 = (arrPlace[0] + arrPlace[1]) * 0.5; // K
        y1 = arrPlace[1];
        y2 = arrPlace[3];
        x3 = arrPlace[0];
        y3 = y4 = (arrPlace[1] + arrPlace[3]) * 0.5;
        x4 = arrPlace[2];

        double[] arr = new double[] {
                x1, y1,
                x2, y2,
                x3, y3,
                x4, y4,
        };

        System.out.println(arr.length);

        return arr;
    }

    private Pane moveBox(Pane pane) {
        return pane;
    }

    @Override
    public void start(Stage primaryStage){

    }
}
