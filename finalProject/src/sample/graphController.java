package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class graphController implements Initializable {
    @FXML Button btnBack;
    @FXML Button btnExit;
    @FXML private LineChart<?, ?> lineChart;
    @FXML private LineChart<?, ?> lineChart1;
    @FXML private CategoryAxis X;
    @FXML private NumberAxis Y;
    @Override public void initialize(URL url, ResourceBundle rb){
        XYChart.Series series1=new XYChart.Series();



            series1.getData().add(new XYChart.Data<>(Controller.fromCode, Controller.value));
            series1.getData().add(new XYChart.Data<>(Controller.toCode, Controller.result));


        lineChart.getData().addAll(series1);




    }
    public void handleAction(ActionEvent event) throws IOException{
        if(event.getSource()==btnBack){
            Controller.stage.close();
            Main.stage.show();

        }
        else if(event.getSource()==btnExit){
            Controller.stage.close();
        }
    }

}
