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

public class tempGraph implements Initializable {

    @FXML
    Button btnBack;
    @FXML Button btnExit;
    @FXML private LineChart<?, ?> lineChart1;
    @FXML private CategoryAxis X1;
    @FXML private NumberAxis Y1;
    @Override
    public void initialize (URL url, ResourceBundle rb){
        XYChart.Series series=new XYChart.Series();


            series.getData().add(new XYChart.Data<>(Controller.selectedTempFrom, Controller.valueTempr));
            series.getData().add(new XYChart.Data<>(Controller.selectedTempTo, Controller.resultTempr));




        lineChart1.getData().addAll(series);



    }
    public void handleAction(ActionEvent event) throws IOException {
        if(event.getSource()==btnBack){
            Controller.stage1.close();
            Main.stage.show();

        }
        else if(event.getSource()==btnExit){
            Controller.stage1.close();
        }
    }
}
