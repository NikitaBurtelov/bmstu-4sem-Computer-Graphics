package sample;

import com.github.sh0nk.matplotlib4j.*;
import com.github.sh0nk.matplotlib4j.builder.HistBuilder;
import com.github.sh0nk.matplotlib4j.builder.ScaleBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Window extends Application {
    Group root = new Group();

    private static final boolean DRY_RUN = true;

    private double eps = 0.00001;
    private double maxAngle; //Искомый угол

    double[][] dataTriangleOne;
    double[][] dataTriangleTwo;
    double[] absDataPointOne, ordDataPointOne; //Множество 1
    double[] absDataPointTwo, ordDataPointTwo; //Множество 2

    double x0, y0;
    boolean flag = false;

    public static void main(String[] args) { launch(args); }

    private void drawLine(Pane pane, int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2);
        getLine(line, Color.BLACK, 2);
        pane.getChildren().add(line);
    }

    private void readTextArea(TextArea taOne, TextArea taTwo) {
        System.out.println(taOne.getText() + taTwo.getText());
        String[] str1 = taOne.getText().split(" ");
        String[] str2 = taTwo.getText().split(" ");

        double[] absDataOne = new double[str1.length / 2];
        double[] ordDataOne = new double[str1.length / 2];
        double[] absDataTwo = new double[str2.length / 2];
        double[] ordDataTwo = new double[str2.length / 2];

        for (int i = 0, j = 0; j < str1.length; i++, j += 2) {
            absDataOne[i] = Double.valueOf(str1[j]);
            ordDataOne[i] = Double.valueOf(str1[j + 1]);
        }
        for (int i = 0, j = 0; j < str2.length; i++, j += 2) {
            absDataTwo[i] = Double.valueOf(str2[j]);
            ordDataTwo[i] = Double.valueOf(str2[j + 1]);
        }
        absDataPointOne = absDataOne;
        absDataPointTwo = absDataTwo;
        ordDataPointOne = ordDataOne;
        ordDataPointTwo = ordDataTwo;
    }

    private void inputTextArea(TextArea textArea, int x, int y) {
        textArea.setLayoutX(x);
        textArea.setLayoutY(y);
        textArea.setPrefRowCount(2);
        textArea.setPrefColumnCount(13);
    }

    private void getButton(Button btn, int x, int y, int width, int height) {
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(width);
        btn.setPrefHeight(height);
    }

    private Line getLine (Line line, Color color, int width) {
        line.setStroke(color);
        line.setStrokeWidth(width);

        return line;
    }

    private void getLabel(Label label, int x, int y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("Arial", 14));
        label.setTextFill(Color.web("#0076a3"));
    }

    Pane drawTriangleLine(Pane pane) {
        drawLine(pane, 10,10, 100,10);
        drawLine(pane, 100,10, 15,40);
        drawLine(pane, 15, 40, 10,10);

        return pane;
    }

    private void drawTriangle(double[][] dataArrOne, double[][] dataArrTwo) {
        try {
            Plot plt = Plot.create();

            System.out.println();
            for (int i = 0; i < dataArrOne.length; i++){
                System.out.println(dataArrOne[i][0] + " " + dataArrOne[i][1]);
            }

            System.out.println();
            for (int i = 0; i < dataArrTwo.length; i++){
                System.out.println(dataArrTwo[i][0] + " " + dataArrTwo[i][1]);
            }

            plt.plot()
                    .add(Arrays.asList(dataArrOne[0][0], dataArrOne[1][0], dataArrOne[2][0], dataArrOne[0][0]),
                            Arrays.asList(dataArrOne[0][1], dataArrOne[1][1], dataArrOne[2][1], dataArrOne[0][1]))
                    .label("label_1")
                    .linestyle("-");

            plt.plot()
                    .add(Arrays.asList(dataArrTwo[0][0], dataArrTwo[1][0], dataArrTwo[2][0], dataArrTwo[0][0]),
                            Arrays.asList(dataArrTwo[0][1], dataArrTwo[1][1], dataArrTwo[2][1], dataArrTwo[0][1]))
                    .label("label_2")
                    .linestyle("-");

            plt.plot()
                    .add(Arrays.asList(dataArrOne[0][0], dataArrTwo[0][0]),
                            Arrays.asList(dataArrOne[0][1], dataArrTwo[0][1]))
                    .label("label")
                    .linestyle("-");

            //plt.xscale(ScaleBuilder.Scale.log);
            //plt.yscale(ScaleBuilder.Scale.log);
            plt.xlabel("x");
            plt.ylabel("y");
            plt.text(0.5, 0.2, "text");
            plt.title("Lab_01");
            //plt.legend();
            plt.savefig("C:/Users/user/Pictures/doc_bmstu/histogram.png").dpi(500);
            plt.executeSilently();
            //plt.show();
        }
        catch (IOException exp) {
            exp.printStackTrace();
        }
        catch ( PythonExecutionException pexp) {
            pexp.printStackTrace();
        }
    }

    private void recordPlot(Plot plot) throws PythonExecutionException, IOException{
        Random rand = new Random();
        List<Double> x = IntStream.range(0, 1000).mapToObj(i -> rand.nextGaussian())
                .collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.hist().add(x).orientation(HistBuilder.Orientation.horizontal);
        plt.ylim(-5, 5);
        plt.title("вооооот");
        plt.savefig("C:/Users/user/Pictures/doc_bmstu/histogram.png").dpi(1000);
        plt.executeSilently();
    }

    Pane inputImage(Pane pane) {
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\user\\Pictures\\doc_bmstu\\histogram.png"));
            ImageView imageView = new ImageView(image);

            imageView.setX(50);
            imageView.setY(25);

            //setting the fit height and width of the image view
            imageView.setFitHeight(480);
            imageView.setFitWidth(620);

            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);

            pane.getChildren().add(imageView);
        }
        catch (FileNotFoundException fnexp) {
            fnexp.printStackTrace();
        }
        return pane;
    }

    void hui(IOException io) throws IOException, PythonExecutionException {
        drawTriangle(dataTriangleOne, dataTriangleTwo);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, PythonExecutionException {
        BorderPane root = new BorderPane();//FXMLLoader.load(getClass().getResource("sample.fxml"));

        Pane pane = new Pane();
        Plot plot = Plot.create();
        primaryStage.setTitle("Lab_01");
        recordPlot(plot);
        //drawTriangle(dataTriangleOne, dataTriangleTwo);

        TextArea textAreaOne = new TextArea("Введите точки мн-ва А");
        TextArea textAreaTwo = new TextArea("Введите точки мн-ва B");

        inputTextArea(textAreaOne, 710, 40);
        inputTextArea(textAreaTwo, 710, 140);

        Line line = new Line(690, 0, 690, 600);
        getLine(line, Color.GRAY, 3);

        Label label1 = new Label("Введите точки мн-ва А");
        Label label2 = new Label("Введите точки мн-ва B");

        getLabel(label1, 710, 10);
        getLabel(label2, 710, 110);

        Button runBtn = new Button("Run");
        Button cleanBtn = new Button("Clean");
        getButton(runBtn, 800, 210, 80, 37);
        getButton(cleanBtn, 800, 260, 80, 37);

        double[][] data1 = new double[3][2];
        double[][] data2 = new double[3][2];



        runBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                readTextArea(textAreaOne, textAreaTwo);

                TriangleMath triangleMath = new TriangleMath(absDataPointOne,
                        ordDataPointOne, absDataPointTwo, ordDataPointTwo);
                for (int i = 0; i < triangleMath.absDataPointOne.length; i++) {
                    System.out.println(triangleMath.absDataPointOne[i] + " " + triangleMath.ordDataPointOne[i] + "Len " + triangleMath.absDataPointOne.length);
                }

                triangleMath.trianglesSearch();
                dataTriangleOne = triangleMath.dataTriangleOne;
                dataTriangleTwo = triangleMath.dataTriangleTwo;

                drawTriangle(dataTriangleOne, dataTriangleTwo);
                inputImage(pane);
            }
        });

        cleanBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaOne.clear();
                textAreaTwo.clear();
            }
        });

        pane.getChildren().addAll(runBtn, cleanBtn, textAreaOne,
                textAreaTwo, line, label1, label2);

        //pane = inputImage(pane);
        root.setTop(pane);

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
