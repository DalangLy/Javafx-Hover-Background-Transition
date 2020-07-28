package hoverbackgroundtransition;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HoverBackgroundTransition extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();

        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(box(), box());
        

        AnchorPane anchorPane = new AnchorPane(vbox);
        Scene scene = new Scene(anchorPane, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    private HBox box(){
        String darkGrey = "#212121";
        String lightGrey = "#424242";
        
        
        HBox box = new HBox();
        box.setPrefSize(200, 50);
        box.setBackground(new Background(new BackgroundFill(Paint.valueOf(darkGrey), new CornerRadii(20), Insets.EMPTY)));
        box.setCursor(Cursor.HAND);
        box.setAlignment(Pos.CENTER);
        Label text = new Label("Hello World");
        text.setStyle("-fx-text-fill: white");
        box.getChildren().add(text);
        
        

        Rectangle rect = new Rectangle();
        rect.setFill(Paint.valueOf(darkGrey));
        FillTransition tr = new FillTransition();
        tr.setShape(rect);
        tr.setDuration(Duration.millis(100));
        tr.setFromValue((Color) Paint.valueOf(darkGrey));
        tr.setToValue((Color) Paint.valueOf(lightGrey));
        tr.setInterpolator(new Interpolator() {
            @Override
            protected double curve(double t) {
                box.setBackground(new Background(new BackgroundFill(rect.getFill(), new CornerRadii(20), Insets.EMPTY)));
                return t;
            }
        });
        
        Rectangle rect1 = new Rectangle();
        rect1.setFill(Paint.valueOf(lightGrey));
        FillTransition tr1 = new FillTransition();
        tr1.setShape(rect1);
        tr1.setDuration(Duration.millis(100));
        tr1.setFromValue((Color) Paint.valueOf(lightGrey));
        tr1.setToValue((Color) Paint.valueOf(darkGrey));
        tr1.setInterpolator(new Interpolator() {
            @Override
            protected double curve(double t) {
                box.setBackground(new Background(new BackgroundFill(rect1.getFill(), new CornerRadii(20), Insets.EMPTY)));
                return t;
            }
        });
        
        
        box.setOnMouseEntered(e -> {
            tr.playFromStart();
        });
        box.setOnMouseExited(e -> {
            tr1.playFromStart();
        });
        return box;
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
