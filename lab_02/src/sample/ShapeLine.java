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
    //private double cutOne;
    //private double cutTwo;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param arrPlace - координаты начальной и конечной позиции отрезка
     * */
    ShapeLine(double[] arrPlace, Color color){ //double cutOne, double cutTwo
        this.arrStart = arrPlace;
        this.color = color;
        //this.cutOne = cutOne;
        //this.cutTwo = cutTwo;
    }

    Line drawLine(double[] arrPlace){
        Line line = new Line(arrPlace[0], arrPlace[1], arrPlace[2], arrPlace[3]);
        line.setStroke(color);
        line.setStrokeWidth(1);

        return line;
    }

    Line moveLine(double[] arrPlace, double[] arrOffset) {
        double dxOne, dyOne, dxTwo, dyTwo;

        dxOne = arrOffset[0] - arrPlace[0];
        dyOne = arrOffset[1] - arrPlace[1];
        dxTwo = arrOffset[2] - arrPlace[2];
        dyTwo = arrOffset[3] - arrPlace[3];

        Line line = new Line(arrPlace[0] + dxOne, arrPlace[1] + dyOne,
                arrPlace[2] + dxTwo, arrPlace[3] + dyTwo);

        line.setStroke(color);
        line.setStrokeWidth(1);

        return line;
    }

    Line scaleLine(double[] arrPlace, double size, double[] centerPlace) {
        double x1, y1, x2, y2;

        x1 = arrPlace[0] * size + (1 - size) * centerPlace[0];
        x2 = arrPlace[2] * size + (1 - size) * centerPlace[0];

        y1 = arrPlace[1] * size + (1 - size) * centerPlace[1];
        y2 = arrPlace[3] * size + (1 - size) * centerPlace[1];

        Line line = new Line(x1, y1, x2, y2);

        line.setStroke(color);
        line.setStrokeWidth(1);


        return line;
    }

    Line rotateLine(double[] arrPlace, double angle, double[] centerPlace) {
        double x1, y1, x2, y2;

        x1 = centerPlace[0] + (arrPlace[0] - centerPlace[0]) * Math.cos(angle)
                + (arrPlace[1] - centerPlace[1]) * Math.sin(angle);
        x2 = centerPlace[0] + (arrPlace[2] - centerPlace[0]) * Math.cos(angle)
                + (arrPlace[3] - centerPlace[1]) * Math.sin(angle);
        y1 = centerPlace[1] + (arrPlace[1] - centerPlace[1]) * Math.cos(angle)
                + (arrPlace[0] - centerPlace[1]) * Math.sin(angle);
        y2 = centerPlace[1] + (arrPlace[3] - centerPlace[1]) * Math.cos(angle)
                + (arrPlace[2] - centerPlace[1]) * Math.sin(angle);

        Line line = new Line(x1, y1, x2, y2);

        line.setStroke(color);
        line.setStrokeWidth(1);

        return line;
    }
}
