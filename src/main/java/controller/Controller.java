package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.Logic;
import logic.Point;

import java.util.List;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;
    @FXML
    private TextField startField;
    @FXML
    private TextField finishField;
    @FXML
    private TextField stepField;

    Logic logic = new Logic();
    List<Point> points;

    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        canvas.widthProperty().addListener((observable, oldValue, newValue) -> draw());
        canvas.heightProperty().addListener((observable, oldValue, newValue) -> draw());
    }

    public void build() {
        double start = Double.parseDouble(startField.getText());
        double finish = Double.parseDouble(finishField.getText());
        double step = Double.parseDouble(stepField.getText());
        points = logic.tabulate(start, finish, step);
        draw();
    }

    public void draw() {
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        g2.setFill(Color.BLACK);
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2.setStroke(Color.WHITE);
        g2.strokeLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2);
        g2.strokeLine(canvas.getWidth() / 2 , 0, canvas.getWidth() / 2 , canvas.getHeight());
        if (points != null) {
            double cx = canvas.getWidth() / 2;
            double cy = canvas.getHeight() / 2;
            int M = 100;
            for (int i = 0; i < points.size() - 1; i++) {
                double x1 = points.get(i).getX() * M + cx;
                double y1 = cy - points.get(i).getY() * M;
                double x2 = points.get(i+1).getX() * M + cx;
                double y2 = cy - points.get(i+1).getY() * M;
                g2.strokeLine(x1, y1, x2, y2);
            }
        }
    }


}
