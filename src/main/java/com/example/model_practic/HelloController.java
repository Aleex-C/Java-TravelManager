package com.example.model_practic;

import com.example.model_practic.domain.Hotel;
import com.example.model_practic.domain.Location;
import com.example.model_practic.domain.SpecialOffer;
import com.example.model_practic.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private Service service;
    @FXML
    TableView<Hotel> hotelTableView;
    ObservableList<Hotel> hotelObservableList;

    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn roomsColumn;
    @FXML
    TableColumn priceColumn;

    @FXML
    ComboBox locationsComboBox;
    ObservableList<Location> locationObservableList;

    @FXML
    public void setService(Service service){
        this.service = service;
        initView();
        //updateView();
    }

    private void initView() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        roomsColumn.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("noRooms"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Hotel, Float>("pricePerNight"));

        List<Location> locations = ((List<Location>) this.service.getAllLocations());
        locationObservableList = FXCollections.observableArrayList(locations);
        locationsComboBox.setItems(locationObservableList);

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(locationsComboBox.getValue());
                        updateViewHotels((Location) locationsComboBox.getValue());
                    }
                };
        locationsComboBox.setOnAction(event);

    }
    private void updateViewHotels(Location location){
        List<Hotel> hotels =((List<Hotel>) this.service.getAllHotels()).stream().filter(hotel -> hotel.getLocationId().equals(location.getId())).toList();
        hotelObservableList = FXCollections.observableArrayList(hotels);

        hotelTableView.setItems(hotelObservableList);
    }

    @FXML
    public void onItemSelected() throws IOException {
        System.out.println("clicked on " + hotelTableView.getSelectionModel().getSelectedItem());
        if (hotelTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("special-view.fxml"));
            Scene scene = new Scene(loader.load(), 400, 250);
            Stage specialStage = new Stage();
            SpecialController specialController = loader.getController();
            specialController.setService(this.service, hotelTableView.getSelectionModel().getSelectedItem());
            specialStage.setTitle("Special");
            specialStage.setScene(scene);
            specialStage.show();
        }
    }

}