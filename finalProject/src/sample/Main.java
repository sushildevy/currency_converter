package sample;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application {

    public static Button btnLogin;
    public static Button btnExit;
    public static TextField txtName;
    public static PasswordField txtPassword;
    static Stage primaryStage;
    static Stage stage;
    public static Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rect1 = new Rectangle(0, 0, 600, 1);
        rect1.setFill(Color.BLUEVIOLET);
        Rectangle rect2 = new Rectangle(0, 599, 600, 1);
        rect2.setFill(Color.CHOCOLATE);

        ScaleTransition rect1T = new ScaleTransition(Duration.seconds(1), rect1);
        rect1T.setToY(600);
        rect1T.setCycleCount(2);
        rect1T.play();

        ScaleTransition rect2T = new ScaleTransition(Duration.seconds(1), rect2);
        rect2T.setToY(-600);
        rect2T.setCycleCount(2);
        rect2T.play();


        btnLogin = new Button("Login");
        btnLogin.setDefaultButton(true);
        btnLogin.setLayoutX(120);
        btnLogin.setLayoutY(400);

        RotateTransition btnRotate = new RotateTransition(Duration.seconds(2), btnLogin);
        btnRotate.setByAngle(360);
        btnRotate.setDelay(Duration.seconds(1));
        btnRotate.setRate(10);
        btnRotate.setCycleCount(10);
        btnRotate.play();

        TranslateTransition btnTransition = new TranslateTransition(Duration.seconds(2), btnLogin);
        btnTransition.setDelay(Duration.seconds(1));
        btnTransition.setToX(300);
        btnTransition.setCycleCount(1);
        btnTransition.play();

        btnExit = new Button("Exit");
        btnExit.setDefaultButton(false);
        btnExit.setLayoutX(170);
        btnExit.setLayoutY(400);

        RotateTransition btnExitRotate = new RotateTransition(Duration.seconds(2), btnExit);
        btnExitRotate.setByAngle(360);
        btnExitRotate.setDelay(Duration.seconds(1));
        btnExitRotate.setRate(10);
        btnExitRotate.setCycleCount(10);
        btnExitRotate.play();

        TranslateTransition btnExitTransition = new TranslateTransition(Duration.seconds(2), btnExit);
        btnExitTransition.setDelay(Duration.seconds(1));
        btnExitTransition.setToX(300);
        btnExitTransition.setCycleCount(1);
        btnExitTransition.play();

        Label lblName = new Label("UserName: ");
        lblName.setTextFill(Color.RED);
        lblName.setLayoutX(-200);
        lblName.setLayoutY(280);

        RotateTransition lblNameRotate = new RotateTransition(Duration.seconds(2), lblName);
        lblNameRotate.setByAngle(360);
        lblNameRotate.setDelay(Duration.seconds(1));
        lblNameRotate.setRate(10);
        lblNameRotate.setCycleCount(10);
        lblNameRotate.play();

        TranslateTransition lblNameTransition = new TranslateTransition(Duration.seconds(2), lblName);
        lblNameTransition.setDelay(Duration.seconds(1));
        lblNameTransition.setToX(400);
        lblNameTransition.setCycleCount(1);
        lblNameTransition.play();

        Label lblPassword = new Label("Password: ");
        lblPassword.setTextFill(Color.BLUE);
        lblPassword.setLayoutX(-200);
        lblPassword.setLayoutY(330);

        RotateTransition lblPRotate = new RotateTransition(Duration.seconds(2), lblPassword);
        lblPRotate.setByAngle(360);
        lblPRotate.setDelay(Duration.seconds(1));
        lblPRotate.setRate(10);
        lblPRotate.setCycleCount(10);
        lblPRotate.play();

        TranslateTransition lblPTransition = new TranslateTransition(Duration.seconds(2), lblPassword);
        lblPTransition.setDelay(Duration.seconds(1));
        lblPTransition.setToX(400);
        lblPTransition.setCycleCount(1);
        lblPTransition.play();


        txtName = new TextField();
        txtName.setLayoutX(-90);
        txtName.setLayoutY(270);

        RotateTransition txtRotate = new RotateTransition(Duration.seconds(2), txtName);
        txtRotate.setByAngle(360);
        txtRotate.setDelay(Duration.seconds(1));
        txtRotate.setRate(10);
        txtRotate.setCycleCount(10);
        txtRotate.play();

        TranslateTransition txtTransition = new TranslateTransition(Duration.seconds(2), txtName);
        txtTransition.setDelay(Duration.seconds(1));
        txtTransition.setToX(400);
        txtTransition.setCycleCount(1);
        txtTransition.play();


        txtPassword = new PasswordField();
        txtPassword.setLayoutX(-90);
        txtPassword.setLayoutY(320);

        RotateTransition txtPRotate = new RotateTransition(Duration.seconds(2), txtPassword);
        txtPRotate.setByAngle(360);
        txtPRotate.setDelay(Duration.seconds(1));
        txtPRotate.setRate(10);
        txtPRotate.setCycleCount(10);
        txtPRotate.play();

        TranslateTransition txtPTransition = new TranslateTransition(Duration.seconds(2), txtPassword);
        txtPTransition.setDelay(Duration.seconds(1));
        txtPTransition.setToX(400);
        txtPTransition.setCycleCount(1);
        txtPTransition.play();

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = new Pane();
        root.getChildren().addAll(rect1, rect2, btnLogin, txtName, lblName, lblPassword, txtPassword, btnExit);
        primaryStage.setTitle("Animation");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();


        txtName.requestFocus();
        btnLogin.setDisable(true);
        txtPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (txtName.getText() != "" && txtPassword.getText() != "") {
                    btnLogin.setDisable(false);

                } else if (txtName.getText() == "" && txtPassword.getText() == "") {
                    btnLogin.setDisable(true);


                }
                btnLogin.setDefaultButton(true);
                btnExit.setDefaultButton(false);
            }

        });

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtName.getText().equals("admin") && txtPassword.getText().equals("password")) {
                    try {
                        primaryStage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
                        Parent root;
                        root = (Parent) fxmlLoader.load();
                        stage = new Stage();
                        stage.setTitle("Your Conversion App");
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();

                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                } else {
                    Alert errorMsg = new Alert(Alert.AlertType.ERROR);
                    errorMsg.setTitle("Invalid");
                    errorMsg.setHeaderText("UserName or Password Invalid");
                    errorMsg.setContentText("Please contact admin !!");
                    errorMsg.showAndWait();
                    txtName.clear();
                    txtPassword.clear();
                    txtName.requestFocus();
                    btnLogin.setDisable(true);
                }

            }
        });
        btnExit.setDefaultButton(false);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}

