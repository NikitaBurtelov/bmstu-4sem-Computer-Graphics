package sample;

import com.sun.javafx.css.StyleCacheEntry;
import com.sun.javafx.geom.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.IOException;

public class ShapeLine {
    private double[] arrStart;
    private Color color;
    double cutOne;
    double cutTwo;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param arrPlace - координаты начальной и конечной позиции отрезка
     * */
    ShapeLine(double[] arrPlace, Color color, double cutOne, double cutTwo){
        this.arrStart = arrPlace;
        this.color = color;
        this.cutOne = cutOne;
        this.cutTwo = cutTwo;
    }

    Line drawLine(double[] arrPlace) throws IOException {
        Line line = new Line(arrPlace[0], arrPlace[1], arrPlace[2], arrPlace[3]);
        line.setStroke(color);
        line.setStrokeWidth(1);

        return line;
    }

//    double[] moveLine(double[] arrPlace, double[] arrOffset) {
//        Line line = new Line(arrPlace[0] , arrPlace[1], arrPlace[2], arrPlace[3]);
//        line.setStroke(color);
//        line.setStrokeWidth(1);
//
//        return line;
//
//
//        System.out.println(arrStart[0]);
//        return arrPlace;
//    }

    double[] scaleLine(double[] arrPlace, double size) {
        double[] arrLine = new double[arrPlace.length];

        return arrPlace;
    }

    double[] rotateLine(double[] arrPlace, double size) {
        double[] arrLine = new double[arrPlace.length];

        return arrPlace;
    }
}
