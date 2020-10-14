import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AssetController implements Initializable {

    @FXML
    TableView <Asset> table;
    @FXML
    TableColumn <Asset, String> idNum, name, type, location, usedBy, state;
    @FXML
    TextArea textArea;
    @FXML
    TextField idTextField, nameTextField, stateTextField, typeTextField, locationTextField, usedByTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // associating the columns to their corresponding Asset attribute
        idNum.setCellValueFactory(new PropertyValueFactory<>("assetIdNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        type.setCellValueFactory(new PropertyValueFactory<>("assetType"));
        location.setCellValueFactory(new PropertyValueFactory<>("assetLocation"));
        usedBy.setCellValueFactory(new PropertyValueFactory<>("usedBy"));
        state.setCellValueFactory(new PropertyValueFactory<>("assetState"));

        //sets the cell factory (a renderer of a cell from the cell item) for the columns
        idNum.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        location.setCellFactory(TextFieldTableCell.forTableColumn());
        usedBy.setCellFactory(TextFieldTableCell.forTableColumn());
        state.setCellFactory(TextFieldTableCell.forTableColumn());

        table.setItems(readCSV());

        printNumPerAsset();
        setEditable();
    }

    private void setEditable() {
        table.setEditable(true);
        table.getSelectionModel().cellSelectionEnabledProperty().set(true);

        //setting each column to replace a cell's old value with its new value once the user edits it
        idNum.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                                //sets the value to the new value of the cell
                    ).setAssetIdNumber(t.getNewValue())
        );

        name.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                                //sets the value to the new value of the cell
                        ).setAssetName(t.getNewValue())
        );

        type.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                                //sets the value to the new value of the cell
                        ).setAssetType(t.getNewValue())
        );

        location.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                                //sets the value to the new value of the cell
                        ).setAssetLocation(t.getNewValue())
        );

        usedBy.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                                //sets the value to the new value of the cell
                        ).setUsedBy(t.getNewValue())
        );

        state.setOnEditCommit(
                //setting up instructions for when an Edit Event occurs in the TableColumn
                (TableColumn.CellEditEvent<Asset, String> t) ->
                        //access the TableView of the TableColumn
                        (t.getTableView().getItems().get(
                                //get the row of the cell
                                t.getTablePosition().getRow())
                        //sets the value to the new value of the cell
                        ).setAssetState(t.getNewValue())
        );
    }

    //read data in from csv file
    private ObservableList<Asset> readCSV() {
        try {
            //create an ObservableList of Assets
            ObservableList<Asset> row = FXCollections.observableArrayList();

            // CSV file delimiter
            String DELIMITER = ",";

            // create a scanner
            Scanner scanner = new Scanner(Paths.get("assets.csv").toFile());

            // read the file line by line
            String line;

            // consumes first line and ignores it
            scanner.nextLine();

            // reads starting on second row
            while (scanner.hasNext()) {
                Asset asset = new Asset();
                line = scanner.nextLine();
                String[] records = line.split(DELIMITER);

                asset.setAssetIdNumber(records[0]);
                asset.setAssetName(records[1]);
                asset.setAssetType(records[2]);
                asset.setAssetLocation(records[3]);
                asset.setUsedBy(records[4]);
                asset.setAssetState(records[5]);
                row.add(asset);
            }
            // close the scanner
            scanner.close();
            return row;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void printNumPerAsset(){
        try {
            int compNum = 0, printNum = 0, avNum = 0;

            // CSV file delimiter
            String DELIMITER = ",";

            // create a reader
            BufferedReader br = Files.newBufferedReader(Paths.get("assets.csv"));

            // read the file line by line
            String line;
            while ((line = br.readLine()) != null) {
                // convert line into array of Strings
                String[] records = line.split(DELIMITER);

                // go through list and count the number of instances of each category
                for (String record : records) {
                    switch (record) {
                        case "computer":
                        case "Computer":
                            compNum++;
                            break;
                        case "printer":
                        case "Printer":
                            printNum++;
                            break;
                        case "audio/video":
                        case "Audio/Video":
                        case "Audio/video":
                            avNum++;
                            break;
                    }
                }
            }
            // sets text for the text area
            textArea.setText("The number of assets that are computers is: " + compNum +
                    "\n"+ "The number of assets that are printers is: " + printNum +
                    "\n" + "The number of assets that are AV equipment is: " + avNum);

            // close the reader
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleComputerButton() {
        Computer.computerType();
    }

    public void handlePrinterButton() {
        Printer.printColor();
    }

    public void handleAVButton() {
        AV.equipmentType();
    }

    public void handleCreateButton() {
        String assetIDNumber = idTextField.getText(),
                assetName = nameTextField.getText(),
                assetType = typeTextField.getText(),
                assetLocation = locationTextField.getText(),
                usedBy = usedByTextField.getText(),
                assetState = stateTextField.getText();

        // creates new asset based on text in textfields
        Asset record = new Asset(assetIDNumber, assetName, assetType, assetLocation, usedBy, assetState);

        // adds the record to the records list
        ObservableList<Asset> records = table.getItems();
        records.add(record);
        table.setItems(records);
    }

    public void handleUpdateButton(){
        // write to CSV file
        BufferedWriter writer;
        try{
            String information = "";
            String header = "AssetIDNumber,AssetName,AssetType,AssetLocation,UsedBy,AssetState\n";
            File file = new File("assets.csv");
            writer = new BufferedWriter(new FileWriter(file));

            ObservableList<Asset> records = table.getItems();

            // for each asset, print their attributes
            for (Asset recordItem : records) {
                String text = recordItem.getAssetIdNumber() + "," + recordItem.getAssetName()
                        + "," + recordItem.getAssetType() + "," + recordItem.getAssetLocation()
                        + "," + recordItem.getUsedBy() + "," + recordItem.getAssetState();

                //passes Assets into a concatenating String
                information += text + "," + "\n";
            }
            writer.write(header + information);

            //closes the BufferedWriter
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        // recalculate the number of assets in each category
        printNumPerAsset();
    }

    public void handleDeleteButton(){
        Asset asset = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(asset);
    }
}



