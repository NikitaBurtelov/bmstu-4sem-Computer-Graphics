package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Group root = new Group();
    Pane pane = new Pane();
    private Color headColor = Color.rgb(245, 245, 245);
    double[] start = new double[] {350, 270};
    double angle;
    double[] move;
    double[] size;
    private double[] startLineOne = new double[] {275, 220, 275, 250};
    private double[] startLineTwo = new double[] {260, 235, 290, 235};
    private ShapeLine shapeLine = new ShapeLine(start, Color.BLACK);
    private ShapeBox shapeBox = new ShapeBox(start, Color.BLACK, 10, 10);
    private ShapeArc shapeArc = new ShapeArc(headColor, start);


    public static void main(String[] args) {
        launch(args);
    }

    private Pane drawShape(Pane pane) {
        double[] arr = new double[] {200, 200, start[0], start[1]};

        shapeBox.drawBox(pane, arr);
        shapeBox.drawRhomb(pane, arr);
        pane.getChildren().add(shapeArc.drawArc(new double[] {275, 235}, 90, 360, new double[] {15, 15})); //double[] dataArr, double angle, double len, double[] radArr
        pane.getChildren().add(shapeArc.drawArc(new double[] {200, 235}, 90, 180, new double[] {20, 35}));
        pane.getChildren().add(shapeArc.drawArc(new double[] {start[0], 235}, -90, 180, new double[] {20, 35}));
        pane.getChildren().addAll(shapeLine.drawLine(startLineOne));
        pane.getChildren().addAll(shapeLine.drawLine(startLineTwo));
        return pane;
    }

    private Pane moveShape(Pane pane, double[] moveArr) {
        double[] arr = new double[] {200, 200, start[0], start[1]};

        shapeBox.moveBox(pane, arr, moveArr);
        shapeBox.moveRhomb(pane, arr, moveArr);
        shapeArc.moveArc(pane, new double[] {275, 235}, 90, 360, new double[] {15, 15}, moveArr); //double[] dataArr, double angle, double len, double[] radArr
        shapeArc.moveArc(pane, new double[] {200, 235}, 90, 180, new double[] {20, 35}, moveArr);
        shapeArc.moveArc(pane, new double[] {start[0], 235}, -90, 180, new double[] {20, 35}, moveArr);
        pane.getChildren().addAll(shapeLine.moveLine(startLineOne, moveArr));
        pane.getChildren().addAll(shapeLine.moveLine(startLineTwo, moveArr));

        return pane;
    }

    private Pane scaleShape(Pane pane, double size) {
        double[] arr = new double[] {200, 200, start[0], start[1]};

        shapeBox.scaleBox(pane, arr, size, new double[] {start[0], start[1]});
        shapeBox.scaleRhomb(pane, arr, size, new double[] {start[0], start[1]});
        //shapeArc.arrStart = shapeBox.arrStart;
        shapeArc.scaleArc(pane, new double[] {275, 235}, 90, 360, new double[] {15, 15}, new double[] {size, size}, new double[] {start[0], start[1]}); //double[] dataArr, double angle, double len, double[] radArr
        shapeArc.scaleArc(pane, new double[] {200, 235}, 90, 180, new double[] {20, 35}, new double[] {size, size}, new double[] {start[0], start[1]});
        shapeArc.scaleArc(pane, new double[] {start[0], 235}, -90, 180, new double[] {20, 35}, new double[] {size, size}, new double[] {start[0], start[1]});
        pane.getChildren().addAll(shapeLine.scaleLine(startLineOne, size, new double[] {start[0], start[1]}));
        pane.getChildren().addAll(shapeLine.scaleLine(startLineTwo, size, new double[] {start[0], start[1]}));

        return pane;
    }

    private Pane rotateShape(Pane pane, double angle) {
        double[] arr = new double[] {200, 200, start[0], start[1]};

        shapeBox.rotateBox(pane, arr, angle, new double[] {start[0], start[1]});
        shapeBox.rotateRhomb(pane, arr, angle, new double[] {start[0], start[1]});
        //shapeArc.arrStart = shapeBox.arrStart;
        shapeArc.rotateArc(pane, new double[] {275, 235}, 90, 360, new double[] {15, 15}, angle); //double[] dataArr, double angle, double len, double[] radArr
        shapeArc.rotateArc(pane, new double[] {200, 235}, 90, 180, new double[] {20, 35}, angle);
        shapeArc.rotateArc(pane, new double[] {start[0], 235}, -90, 180, new double[] {20, 35}, angle);
        pane.getChildren().addAll(shapeLine.rotateLine(startLineOne, angle, new double[] {start[0], start[1]}));
        pane.getChildren().addAll(shapeLine.rotateLine(startLineTwo, angle, new double[] {start[0], start[1]}));

        return pane;
    }

    private void readTextArea(TextArea taOne, TextArea taTwo, TextArea taThree) {
        System.out.println(taOne.getText() + taTwo.getText());
        String[] str1 = taOne.getText().split(" ");
        String[] str2 = taTwo.getText().split(" ");
        String str3 = taThree.getText();

        for (int i = 0, j = 0; j < str2.length; i++, j += 2)
            this.move[i] = Double.valueOf(str1[i]);

        for (int i = 0, j = 0; j < str1.length; i++, j += 2)
            this.size[i] = Double.valueOf(str2[i]);

        this.angle = Double.valueOf(str3);
    }

    private void inputTextArea(TextArea textArea, int x, int y, int row) {
        textArea.setLayoutX(x);
        textArea.setLayoutY(y);
        textArea.setPrefRowCount(row);
        textArea.setPrefColumnCount(17);
    }

    private void getButton(Button btn, int x, int y, int width, int height) {
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(width);
        btn.setPrefHeight(height);
    }

    private Line getLine(Line line, Color color, int width) {
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

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();//FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Pane pane = new Pane();

        pane = drawShape(pane);
        //pane = moveShape(pane, new double[] {150, 200});
        //pane = scaleShape(pane, 1.5);
        //pane = rotateShape(pane, 90);

        primaryStage.setTitle("Lab_02");

        TextArea textAreaOne = new TextArea("Перенос");
        TextArea textAreaTwo = new TextArea("Масштабирование");
        TextArea textAreaThree = new TextArea("Поворот");
        TextArea textAreaFour = new TextArea();

        inputTextArea(textAreaOne, 710, 40, 2);
        inputTextArea(textAreaTwo, 710, 140, 2);
        inputTextArea(textAreaThree, 710, 240, 2);
        inputTextArea(textAreaFour, 710, 410, 7);


        Line line = new Line(690, 0, 690, 600);
        getLine(line, Color.GRAY, 3);

        Label label1 = new Label("Введите параметры переноса");
        Label label2 = new Label("Введите параметр масштабирования");
        Label label3 = new Label("Введите угол поворота");

        getLabel(label1, 710, 10);
        getLabel(label2, 710, 110);
        getLabel(label3, 710, 210);

        Button runBtn = new Button("Run");
        Button cleanBtn = new Button("Clean");

        getButton(runBtn, 850, 310, 80, 37);
        getButton(cleanBtn, 850, 360, 80, 37);

        runBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //readTextArea(textAreaOne, textAreaTwo, textAreaThree);
                //pane = moveShape(pane, new double[] {100, 100});
            }
        });

        cleanBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaOne.clear();
                textAreaTwo.clear();
                textAreaThree.clear();
            }
        });

        pane.getChildren().addAll(runBtn, cleanBtn, textAreaOne,
                textAreaTwo, textAreaThree, textAreaFour, line, label1, label2, label3);
        root.setTop(pane);

        root.setBackground(new Background(new BackgroundFill(this.headColor, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setScene(new Scene(root, 956, 600));
        primaryStage.show();
    }
}
