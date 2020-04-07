package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Arrays;

public class ShapeArc {
    private Color arcColor;
    private double[] arrStart;

    ShapeArc(Color color, double[] arrStart) {
        this.arcColor = color;
        this.arrStart = arrStart;
    }

    //dataArr - массив, содержит координаты центра,
    //angle - startAngle
    //Length
    //radArr
    Arc drawArc(double[] dataArr, double angle, double len, double[] radArr) {
        Arc arc = new Arc();

        arc.setCenterX(dataArr[0]);
        arc.setCenterY(dataArr[1]);
        arc.setRadiusX(radArr[0]);
        arc.setRadiusY(radArr[1]);
        arc.setStartAngle(angle);
        arc.setLength(len);
        arc.setFill(arcColor);
        arc.setStroke(javafx.scene.paint.Color.BLACK);
        arc.setType(ArcType.OPEN);

        return arc;
    }
    //moveArr - dx and dy
    Pane moveArc(Pane pane, double[] dataArr, double angle, double len, double[] radArr, double[] moveArr) {
        dataArr[0] += moveArr[0];
        dataArr[1] += moveArr[1];
        this.arrStart[0] += moveArr[0];
        this.arrStart[1] += moveArr[1];

        pane.getChildren().add(drawArc(dataArr, angle, len, radArr));

        return pane;
    }

    Pane rotateArc(Pane pane, double[] dataArr, double angle, double len, double[] radArr, double rotAngle) {
        angle += rotAngle;
        double x, y;

        x = arrStart[0] + (dataArr[0] - arrStart[0]) * Math.cos(angle)
                + (dataArr[1] - dataArr[1]) * Math.sin(angle);

        y = arrStart[1] + (dataArr[1] - arrStart[1]) * Math.cos(angle)
                + (dataArr[0] - arrStart[1]) * Math.sin(angle);

        dataArr[0] = x;
        dataArr[1] = y;

        pane.getChildren().add(drawArc(dataArr, angle, len, radArr));

        return pane;
    }
    //scaleArr - kx, ky
    Pane scaleArc(Pane pane, double[] dataArr, double angle, double len, double[] radArr, double[] sizeArr) {
        double x, y;
        x = dataArr[0] - radArr[0];
        y = dataArr[1] - radArr[1];

        x = x * sizeArr[0] + (1 - sizeArr[0]) * arrStart[0];
        y = y * sizeArr[1] + (1 - sizeArr[1]) * arrStart[1];

        radArr[0] = dataArr[0] - x; //rx
        radArr[1] = dataArr[1] - y; //ry
        dataArr[0] = dataArr[0] * sizeArr[0] + (1 - sizeArr[0]) * arrStart[0];
        dataArr[1] = dataArr[1] * sizeArr[1] + (1 - sizeArr[1]) * arrStart[0];

        pane.getChildren().add(drawArc(dataArr, angle, len, radArr));

        return pane;
    }
}