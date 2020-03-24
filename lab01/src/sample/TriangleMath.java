package sample;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TriangleMath {
    TriangleMath (double[] arrX1, double[] arrY1, double[] arrX2, double[] arrY2) {
        this.absDataPointOne = arrX1;
        this.ordDataPointOne = arrY1;
        this.absDataPointTwo = arrX2;
        this.ordDataPointTwo = arrY2;
    }

    double[] absDataPointOne, ordDataPointOne; //Множество 1
    double[] absDataPointTwo, ordDataPointTwo; //Множество 2

    double[][][] dataPointOne; //тупо-ый треугольник по точкам мн-ва 1
    double[][][] dataPointTwo; //тупо-ый треугольник по точкам мн-ва 2

    private double maxAngle = -1000; //Искомый угол
    private double eps = 0.00001;
    double[][] dataTriangleOne;
    double[][] dataTriangleTwo;

    //int[] arrSideLenOne; //длина сторон 1
    //int[] arrSideLenTwo; //длина сторон 2

    boolean trianglesSearch() {
        double angle;
        int idOne = -1, idTwo = -1;

        dataPointOne = getTriangle(absDataPointOne, ordDataPointOne, dataPointOne);
        dataPointTwo = getTriangle(absDataPointTwo, ordDataPointTwo, dataPointTwo);

        if (dataPointOne.length == 0 || dataPointTwo.length == 0)
            return false;

        for (int i = 0; i < dataPointOne.length; i++) {
            for (int j = 0; j < dataPointTwo.length; j++) {
                angle = Math.atan((double) (dataPointOne[i][0][1] - dataPointTwo[j][0][1])
                        / (double) (dataPointOne[i][0][0] - dataPointTwo[j][0][0]));
                if (180 - 2 * angle > eps)
                    angle = 180 - angle;

                if (angle - maxAngle > eps) {
                    maxAngle = angle;
                    idOne = i;
                    idTwo = j;
                }

            }
        }
        if (idOne != -1 & idTwo != -1) {
            dataTriangleOne = Arrays.copyOf(dataPointOne[idOne], dataPointOne[idOne].length);
            dataTriangleTwo = Arrays.copyOf(dataPointTwo[idTwo], dataPointTwo[idTwo].length);
            return true;
        }
        else
            return false;
    }

    double[][][] getTriangle(double[] absDataPoint, double[] ordDataPoint, double[][][] dataPoint) {
        double x1, x2, x3, y1, y2, y3, id;
        int count = 0;
        double[][] dataCord = new double[3][2];

        for (int i = 0; i < absDataPoint.length - 2; i++) {
            x1 = absDataPoint[i];
            y1 = ordDataPoint[i];
            for (int j = i + 1; j < absDataPoint.length - 1; j++) {
                x2 = absDataPoint[i];
                y2 = ordDataPoint[i];
                for (int k = j + 1; k < absDataPoint.length; k++) {
                    x3 = absDataPoint[i];
                    y3 = ordDataPoint[i];

                    if (checkExist(x1, x2, x3, y1, y2, y3)) {
                        id = getObtuseAngle(x1, x2, x3, y1, y2, y3);

                        if (id == 1)
                            dataCord = setArr(x1, y1, x2, y2, x3, y3, dataCord);
                        else if (id == 2)
                            dataCord = setArr(x2, y2, x1, y1, x3, y3, dataCord);
                        else if (id == 3)
                            dataCord = setArr(x3, y3, x1, y1, x2, y2, dataCord);

                        if (id != -1)
                            dataPoint = appendArr(dataPoint, dataCord);
                    }
                }
            }
        }
        return dataPoint;
    }
    double[][] setArr(double x1, double y1, double x2, double y2, double x3, double y3, double[][] dataCord) {
        dataCord[0][0] = x1;
        dataCord[0][1] = y1;

        dataCord[1][0] = x2;
        dataCord[1][1] = y2;

        dataCord[2][0] = x3;
        dataCord[2][1] = y3;

        return dataCord;
    }
    //Проверка на существование треугольника
    boolean checkExist (double x1, double x2, double x3, double y1, double y2, double y3) {
        double s = Math.abs((x1 - x2) * (y2 - y3) - (x2 - x3) * (y1 - y3));

        if (s - 0.00001 >= eps)
            return true;

        return false;
    }
    //проверка на наличие тупого угла
    int getObtuseAngle(double x1, double x2, double x3, double y1, double y2, double y3) {
        int index = -1;
        double a = Math.pow(getSideLength(x2, x3, y2, y3), 2);
        double b = Math.pow(getSideLength(x1, x3, y1, y3), 2);
        double c = Math.pow(getSideLength(x1, x2, y1, y2), 2);

        if (a > b + c)
            index = 1;
        else if (b > a + c)
            index = 2;
        else if (c > a + b)
            index = 3;

        return index;
    }

    //Подсчет длин сторон
    double getSideLength (double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.abs((x1 - x2)*(x1 - x2) - (y1 - y2)*(y1 - y2)));
    }
    //Поиск угла между осью ОХ и прямой
    double angleSearch() {
        double angle = 0d;

        return angle;
    }
    //Добавление данных о треугольнике в конец массива
    // dataPoint - данные о треугольнике мн-ва 1 или мн-ва 2
    // dataPoint = dataPointOne or dataPointTwo
    double[][][] appendArr(double[][][] dataPoint, double[][] dataCoord) {
        double[][][] arr = new double[dataPoint.length + 1][3][2];

        arr = Arrays.copyOf(dataPoint, dataPoint.length);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                arr[dataPoint.length][i][j] = dataCoord[i][j];
            }
        }

        return arr;
    }
}
