package lk.ijse.dep10.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MainSceneController {
    public AnchorPane root;
    public Canvas cnvMain;
    public Button imgPencil;
    public Button cirOval;
    public Label lblDragPoint;
    public VBox vBoxToolKit;
    public TextField txtField;
    public Button rectCornered;
    public Button rectRounded;
    public Button imgEraser;
    public Button txtLabel;
    public Label lblText;
    public ColorPicker clrFill;
    public ColorPicker clrStroke;

    private double x1;
    private double y1;
    private boolean isRectCorneredClicked;
    private boolean isOvalClicked;
    private boolean isPencilClicked;
    private boolean isErasorClicked;
    private boolean isRectRoundedClicked;
    private boolean istxtLabelClicked;
    private double getMouseX;
    private double getMouseY;
    private String text="";
    private WritableImage snapshot;
    @FXML
    private void initialize(){
        cnvMain.widthProperty().bind(root.widthProperty());
        cnvMain.heightProperty().bind(root.heightProperty());
        cnvMain.getGraphicsContext2D().setStroke(clrStroke.getValue());
        cnvMain.getGraphicsContext2D().setFill(clrFill.getValue());
    }

    public void cnvMainOnMouseClicked(MouseEvent mouseEvent) {
        snapshot = cnvMain.snapshot(new SnapshotParameters(), null);
        cnvMain.requestFocus();
        if (istxtLabelClicked) {
            lblText.setLayoutX(mouseEvent.getSceneX());
            lblText.setLayoutY(mouseEvent.getSceneY());
        }
        if (isPencilClicked){
            cnvMain.getGraphicsContext2D().beginPath();
        }
    }
    public void cnvMainOnMouseDragged(MouseEvent mouseEvent) {
        double x2 = mouseEvent.getX();
        double y2 = mouseEvent.getY();
        double width = x2 - x1;
        double height = y2 - y1;

        GraphicsContext gc = cnvMain.getGraphicsContext2D();  //
        if (isRectCorneredClicked) {
            gc.clearRect(0 , 0, cnvMain.getWidth(), cnvMain.getHeight());
            gc.drawImage(snapshot, 0,0);
            gc.strokeRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height));
            gc.fillRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height));
        }
        if (isRectRoundedClicked) {
            gc.clearRect(0, 0, cnvMain.getWidth(), cnvMain.getHeight());
            gc.drawImage(snapshot, 0,0);
            gc.strokeRoundRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height), 10, 10);
            gc.fillRoundRect(width < 0 ? x2 : x1, height < 0 ? y2 : y1, Math.abs(width), Math.abs(height), 10, 10);
        }
        if (isOvalClicked) {

            gc.clearRect(0, 0,cnvMain.getWidth(), cnvMain.getHeight());
            gc.drawImage(snapshot, 0,0);
            gc.strokeOval(x1, y1, Math.abs(width), Math.abs(height));
            gc.fillOval(x1, y1, Math.abs(width), Math.abs(height));
        }
        if (isPencilClicked) {
            gc.lineTo(x2,y2);
            gc.stroke();
//            gc.strokeLine(x1,y1,x2,y2);
//            x1=x2;
//            y1=y2;
        }
        if (isErasorClicked) {
            gc.clearRect(x2, y2, 10, 10);
            x1=x2;
            x2=y2;
        }
    }
    public void rectCorneredOnMouseClicked(MouseEvent mouseEvent) {
        istxtLabelClicked = false;
        isRectRoundedClicked = false;
        isRectCorneredClicked = true;
        isOvalClicked = false;
        isErasorClicked = false;
        isPencilClicked=false;
    }

    public void cnvMainOnMousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
    }
    public void rectRoundedOnMouseClicked(MouseEvent mouseEvent) {
        istxtLabelClicked = false;
        isRectRoundedClicked = true;
        isRectCorneredClicked = false;
        isOvalClicked = false;
        isErasorClicked = false;
        isPencilClicked=false;
    }

    public void cirOvalOnMouseClicked(MouseEvent mouseEvent) {
        istxtLabelClicked = false;
        isRectRoundedClicked = false;
        isRectCorneredClicked = false;
        isOvalClicked = true;
        isErasorClicked = false;
        isPencilClicked=false;
    }

    public void imgPencilOnMouseClicked(MouseEvent mouseEvent) {
        istxtLabelClicked = false;
        isRectRoundedClicked = false;
        isRectCorneredClicked = false;
        isOvalClicked = false;
        isErasorClicked = false;
        isPencilClicked=true;
    }


    public void txtLabelOnMouseClicked(MouseEvent mouseEvent) {
        istxtLabelClicked = true;
        isRectRoundedClicked = false;
        isRectCorneredClicked = false;
        isOvalClicked = false;
        isErasorClicked = false;
        isPencilClicked=false;
    }

    public void lblDragPointOnMouseDragged(MouseEvent mouseEvent) {
        vBoxToolKit.setLayoutX(mouseEvent.getSceneX()-getMouseX);
        vBoxToolKit.setLayoutY(mouseEvent.getSceneY()-getMouseY);
    }

    public void lblDragPointOnMousePressed(MouseEvent mouseEvent) {
        getMouseX=mouseEvent.getX();
        getMouseY=mouseEvent.getY();
    }

    public void imgEraserOnMouseClicked(MouseEvent mouseEvent) {


        istxtLabelClicked = false;
        isRectRoundedClicked = false;
        isRectCorneredClicked = false;
        isOvalClicked = false;
        isErasorClicked = true;
        isPencilClicked=false;
    }

    public void cnvMainOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()==KeyCode.BACK_SPACE){
            if (text.length()>=2) {
                text = text.substring(0, text.length() - 1);
                lblText.setText(text);
                return;
            }
            else if (text.length()==1){
                text=text.substring(0,0);
            }
            else return;
        }
        text+=keyEvent.getText();
        lblText.setText(text);
    }
    public void clrStrokeOnAction(ActionEvent event) {
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
        gc.setStroke(clrStroke.getValue());
    }
    public void clrFillOnAction(ActionEvent event) {
        GraphicsContext gc = cnvMain.getGraphicsContext2D();
        gc.setFill((clrFill.getValue()==null)? Color.ORANGE:clrFill.getValue());
    }
}


