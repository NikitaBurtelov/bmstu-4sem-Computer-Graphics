package sample;

import com.github.sh0nk.matplotlib4j.*;
import com.github.sh0nk.matplotlib4j.builder.ContourBuilder;
import com.github.sh0nk.matplotlib4j.builder.HistBuilder;
import com.github.sh0nk.matplotlib4j.builder.ScaleBuilder;
import javafx.scene.image.ImageView;
import org.gradle.cli.*;
import org.gradle.wrapper.Install;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.gradle.wrapper.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.UnexpectedException;
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
    double x0, y0;

    public static void main(String[] args) { launch(args); }

    Pane drawTriangle(Pane pane) {
        drawLine(pane, 10,10, 100,10);
        drawLine(pane, 100,10, 15,40);
        drawLine(pane, 15, 40, 10,10);

        return pane;
    }

    Pane drawLine(Pane pane, int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2);
        line = getLine(line, Color.BLACK, 2);
        pane.getChildren().add(line);

        return pane;
    }



    private TextArea inputTextArea(TextArea textArea, int x, int y) {
        textArea.setLayoutX(x);
        textArea.setLayoutY(y);
        textArea.setPrefRowCount(2);
        textArea.setPrefColumnCount(13);

        return textArea;
    }

    private Button getButton(Button btn, int x, int y, int width, int height) {
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(width);
        btn.setPrefHeight(height);

        return btn;
    }

    private Line getLine (Line line, Color color, int width) {
        line.setStroke(color);
        line.setStrokeWidth(width);

        return line;
    }

    private Label getLabel (Label label, int x, int y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("Arial", 14));
        label.setTextFill(Color.web("#0076a3"));

        return label;
    }
    public void testPlot() throws PythonExecutionException, IOException{
        Random rand = new Random();
        List<Double> x = IntStream.range(0, 1000).mapToObj(i -> rand.nextGaussian())
                .collect(Collectors.toList());

        Plot plt = Plot.create();
        plt.hist().add(x).orientation(HistBuilder.Orientation.horizontal);
        plt.ylim(-5, 5);
        plt.title("пися");
        plt.savefig("C:/Users/user/Pictures/doc_bmstu/histogram.png").dpi(1000);

// Don't miss this line to output the file!

// Don't miss this line to output the file!
        plt.executeSilently();
    }

    Pane inputImage(Pane pane) throws FileNotFoundException {
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

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws IOException, PythonExecutionException {
        BorderPane root = new BorderPane();//FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane pane = new Pane();
        primaryStage.setTitle("Lab_01");

        testPlot();

        TextArea textAreaOne = new TextArea("Введите точки мн-ва А");
        TextArea textAreaTwo = new TextArea("Введите точки мн-ва B");

        textAreaOne = inputTextArea(textAreaOne, 710, 40);
        textAreaTwo = inputTextArea(textAreaTwo, 710, 140);


        Button runBtn = new Button("Run");
        Button cleanBtn = new Button("Clean");
        runBtn = getButton(runBtn, 800, 210, 80, 37);
        cleanBtn = getButton(cleanBtn, 800, 260, 80, 37);

        Line line = new Line(690, 0, 690, 600);
        line = getLine(line, Color.GRAY, 3);

        Label label1 = new Label("Введите точки мн-ва А");
        Label label2 = new Label("Введите точки мн-ва B");

        label1 = getLabel(label1, 710, 10);
        label2 = getLabel(label2, 710, 110);

        pane.getChildren().addAll(runBtn, cleanBtn, textAreaOne,
                textAreaTwo, line, label1, label2);

        pane = inputImage(pane);
        root.setTop(pane);

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
