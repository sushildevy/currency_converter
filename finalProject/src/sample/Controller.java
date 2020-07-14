package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;

public class Controller {
    public static JSONObject objCountryCode;

    ObservableList<String>obNameList= FXCollections.observableArrayList();
    ObservableList<String>obNameList1= FXCollections.observableArrayList();

    @FXML
    ComboBox nameComboBoxFrom;
    @FXML ComboBox nameComboBoxTo;
    @FXML ComboBox tempComboBoxFrom;
    @FXML ComboBox tempComboBoxTo;
    @FXML TextField txtAmount;
    @FXML TextField txtTempValue;
    @FXML Button btnCurrencyConverter;
    @FXML Button btnTempConverter;
    @FXML Button btnGraph;
    @FXML Button btnTempGraph;
    @FXML Button btnExit;
    @FXML Label lblFrom;
    @FXML Label lblTo;
    @FXML Label lblValue;
    @FXML Label lblResult;
    @FXML Label lblRate;
    @FXML Label lblTempFrom;
    @FXML Label lblTempTo;
    @FXML Label lblTempValue;
    @FXML Label lblTempResult;
    static Stage stage;
    static Stage stage1;
    static String selectedFromName;
    static String selectedTempFrom;
    static String selectedTempTo;
    static String selectedToName;
    static  String[] fromCurrency;
    static  String[] toCurrency;
    static String fromCode;
    static String selectedCurrency;
    static String toCode;
    static String selectedToCurrency;
    static String newTValue;
    static double rate;
    static double value;
    static double valueTempr;
    static double result;
    static double resultTempr;
    public void initialize(){
        btnGraph.setDisable(true);
        btnTempGraph.setDisable(true );
        btnCurrencyConverter.setDisable(true);
        btnTempConverter.setDisable(true);
        txtAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d{0,9}([\\.]\\d{0,2})?")) {
                    txtAmount.setText(oldValue);
                }
                if (!(nameComboBoxFrom.getSelectionModel().isEmpty())&&!(nameComboBoxTo.getSelectionModel().isEmpty())&&!(txtAmount.getText().isEmpty())){
                    btnCurrencyConverter.setDisable(false);
                }
                else{
                    btnCurrencyConverter.setDisable(true);
                }

            }

        });


        txtTempValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*if ((!newValue.matches("\\d{0,9}(([.]\\d{0,2}))?")) ||(!newValue.matches("[-]\\d{0,9}(([.]\\d{0,2}))?")) ) {
                    //txtTempValue.setText(oldValue);
                }*/
                if((!isValid(newValue))){
                    txtTempValue.setText(oldValue);
                }

                if (!(tempComboBoxFrom.getSelectionModel().isEmpty())&&!(tempComboBoxTo.getSelectionModel().isEmpty())&&!(txtTempValue.getText().isEmpty() )){
                    btnTempConverter.setDisable(false);
                }
                else{
                    btnTempConverter.setDisable(true);
                }
            }

        });

        try {
            getCountryCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tempComboBoxFrom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!(tempComboBoxFrom.getValue()==null)){
                    btnGraph.setDisable(true );
                    btnTempGraph.setDisable(true);
                    selectedTempFrom=tempComboBoxFrom.getValue().toString();
                    lblTempFrom.setText("");
                    lblTempTo.setText("");
                    lblTempValue.setText("");
                    lblTempResult.setText("");
                    lblFrom.setText("");
                    lblTo.setText("");
                    lblValue.setText("");
                    lblResult.setText("");
                    lblRate.setText("");
                    btnCurrencyConverter.setDefaultButton(false);
                    btnTempGraph.setDefaultButton(false);
                    btnGraph.setDefaultButton(false);
                    btnExit.setDefaultButton(false);
                    btnTempConverter.setDefaultButton(true);

                }

            }

        });

        tempComboBoxTo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!(tempComboBoxFrom.getValue()==null)){
                    selectedTempTo=tempComboBoxTo.getValue().toString();

                }

            }

        });

        nameComboBoxFrom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!(nameComboBoxFrom.getValue()==null)){
                    btnGraph.setDisable(true);
                    btnTempGraph.setDisable(true);
                    btnTempGraph.setDisable(true);
                    selectedFromName=nameComboBoxFrom.getValue().toString();
                    fromCurrency=selectedFromName.split(":");
                    fromCode=fromCurrency[0];
                    selectedCurrency=fromCurrency[1];
                    lblFrom.setText("");
                    lblTo.setText("");
                    lblValue.setText("");
                    lblResult.setText("");
                    lblRate.setText("");
                    lblTempFrom.setText("");
                    lblTempTo.setText("");
                    lblTempValue.setText("");
                    lblTempResult.setText("");
                    btnTempConverter.setDefaultButton(false);
                    btnTempGraph.setDefaultButton(false);
                    btnGraph.setDefaultButton(false);
                    btnExit.setDefaultButton(false);
                    btnCurrencyConverter.setDefaultButton(true);

                }


            }

        });

        nameComboBoxTo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(!(nameComboBoxTo.getValue()==null)){
                    selectedToName=nameComboBoxTo.getValue().toString();
                    toCurrency=selectedToName.split(":");
                    toCode=toCurrency[0];
                    selectedToCurrency=toCurrency[1];
                }

            }

        });

    }
    private boolean isValid( String value1){
        /*if(value1.length()==0 || value1.equals("-") || (!value1.matches("([-])\\d{0,9}(([.]\\d{0,2}))?")) ){
            return true;
        }*/
        if (value1.matches("([-]\\d{0,9})?(([.]\\d{0,2}))?") || value1.matches("\\d{0,9}(([.]\\d{0,2}))?")){

            return true;
        }
        try {
            Integer.parseInt(value1);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static  String getData(String url) throws IOException {
        URL obj =new URL((url));
        HttpURLConnection con=(HttpURLConnection) obj.openConnection();
        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            try {
                BufferedReader in =new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response=new StringBuilder();
                while( (inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }
                in.close();
                return (response.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return ("");
            }
        }else return ("");
    }


    public void getCountryCode() throws IOException,JSONException{
        final String strCountryCode="http://apilayer.net/api/list?access_key=a7d77154640c9fc981b2ad56eeda73a5&format=1";
        String response=getData(strCountryCode);
        objCountryCode=new JSONObject(response);
        JSONObject names=objCountryCode.getJSONObject("currencies");
        Iterator<String> keys = names.keys();
        while (keys.hasNext()){
            String code = keys.next();
            String country = names.getString(code);
            String totalName=(code+":"+country);
            obNameList.addAll(totalName);

        }
        obNameList1.addAll("kelvin","Fahrenheit","Celsius");
        obNameList=obNameList.sorted(Comparator.naturalOrder());
        nameComboBoxFrom.setItems(obNameList);
        obNameList1=obNameList1.sorted(Comparator.naturalOrder());
        nameComboBoxTo.setItems(obNameList);
        tempComboBoxFrom.setItems(obNameList1);
        tempComboBoxTo.setItems(obNameList1);
        nameComboBoxFrom.requestFocus();



    }

    public void getRate() throws IOException,JSONException{
        final String strRate="https://free.currencyconverterapi.com/api/v5/convert?q="+fromCode+"_"+toCode+"&compact=y";
        String response=getData(strRate);
        if(!(response.isEmpty())){
            JSONObject objRate=new JSONObject(response);
            JSONObject objFromTo=objRate.getJSONObject(fromCode+"_"+toCode);
             rate=objFromTo.getDouble("val");
        }

    }

    public void handleAction(ActionEvent event) throws IOException{
        if(event.getSource()==btnCurrencyConverter){
            btnGraph.setDefaultButton(true);
            btnTempGraph.setDefaultButton(false);
            btnTempConverter.setDefaultButton(false);
            btnCurrencyConverter.setDefaultButton(false);
            btnExit.setDefaultButton(false);
            btnGraph.setDisable(false);
            btnTempGraph.setDisable(true);
            try {
                getRate();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            value=Double.parseDouble(txtAmount.getText());
            result=value*rate;
            String newValue=String.format("%1.2f",result);
            String formatedRate=String.format("%1.3f",rate);
            lblFrom.setText(selectedCurrency);
            lblTo.setText(selectedToCurrency);
            lblValue.setText(String.valueOf(value+" "+fromCode));
            lblRate.setText(String.valueOf(formatedRate));
            lblResult.setText(newValue+" "+toCode);
            nameComboBoxTo.setValue(null);
            nameComboBoxFrom.setValue(null);
            txtAmount.clear();
            nameComboBoxFrom.requestFocus();

        }

        else if(event.getSource()==btnTempConverter){
            btnTempGraph.setDefaultButton(true);
            btnCurrencyConverter.setDefaultButton(false);
            btnGraph.setDefaultButton(false);
            btnTempConverter.setDefaultButton(false);
            btnExit.setDefaultButton(false);
            btnGraph.setDisable(true);
            btnTempGraph.setDisable(false);
            String infix="";
            String prefix="";

            if(selectedTempFrom==("kelvin") && selectedTempTo==("Fahrenheit")){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=(valueTempr-273.15)*(9.0/5)+32;
                infix=" °F";
                prefix=" °K";


            }

            else if(selectedTempFrom==("kelvin") && selectedTempTo==("Celsius")){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=valueTempr - 273.15;
                infix=" °C";
                prefix=" °K";


            }


            else if(selectedTempFrom==("Fahrenheit") && selectedTempTo==("Celsius")){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=(valueTempr-32)*(5.0/9);
                infix=" °C";
                prefix=" °F";

            }

            else if(selectedTempFrom==("Fahrenheit") && selectedTempTo==("kelvin")){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=(valueTempr-32)*(5.0/9)+273.15;
                infix=" °K";
                prefix=" °F";

            }

            else if(selectedTempFrom=="Celsius" && selectedTempTo=="kelvin"){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=valueTempr + 273.15;
                infix=" °K";
                prefix=" °C";

            }

            else if(selectedTempFrom==("Celsius") && selectedTempTo==("Fahrenheit")){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=(valueTempr*(9.0/5))+32;
                infix=" °F";
                prefix=" °C";

            }

            else if(selectedTempFrom==selectedTempTo){
                valueTempr=Double.parseDouble(txtTempValue.getText());
                resultTempr=valueTempr;
                if(selectedTempFrom=="Kelvin"){
                    infix=" °K";
                    prefix=" °K";
                }
                else if(selectedTempFrom=="Celsius"){
                    infix=" °C";
                    prefix=" °C";
                }
                else{
                    infix=" °F";
                    prefix=" °F";
                }
            }

            newTValue=String.format("%1.2f",resultTempr);
            lblTempFrom.setText(selectedTempFrom);
            lblTempTo.setText(selectedTempTo);
            lblTempValue.setText(String.valueOf(valueTempr + prefix));
            lblTempResult.setText(String.valueOf(newTValue+infix));
            //tempComboBoxFrom.setValue(null);
            //tempComboBoxFrom.getSelectionModel().clearSelection();
            //tempComboBoxTo.getSelectionModel().clearSelection();
            //tempComboBoxTo.setValue(null);
            tempComboBoxFrom.setPromptText("Select Temperature");
            tempComboBoxTo.setPromptText("Select Temperature");
            tempComboBoxFrom.getSelectionModel().clearSelection();
            tempComboBoxTo.getSelectionModel().clearSelection();
            txtTempValue.clear();
            btnTempConverter.setDisable(true);
            tempComboBoxFrom.requestFocus();

            }


        else if(event.getSource()==btnGraph){
             if (!(lblFrom.getText().isEmpty() )) {
                 try {

                     Main.stage.close();
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sampleGraph.fxml"));
                     Parent root;
                     root = (Parent) fxmlLoader.load();
                     stage = new Stage();
                     stage.setTitle("Currency Graph");
                     stage.setScene(new Scene(root));
                     stage.show();

                 } catch (Exception el) {
                     el.printStackTrace();
                 }
             }


        }

        else if(event.getSource()==btnTempGraph){
            if ( !(lblTempFrom.getText()).isEmpty()) {
                try {

                    Main.stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sampleTempGraph.fxml"));
                    Parent root;
                    root = (Parent) fxmlLoader.load();
                    stage1 = new Stage();
                    stage1.setTitle("Temperature Graph");
                    stage1.setScene(new Scene(root));
                    stage1.show();

                } catch (Exception el) {
                    el.printStackTrace();
                }
            }


        }

        else if(event.getSource()==btnExit){
            Main.stage.close();
        }




    }


}

