package com.example.model_practic;

import com.example.model_practic.domain.*;
import com.example.model_practic.service.Service;
import com.example.model_practic.utils.observer.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ClientController {
    private Service service;
    private Integer id_client;
    @FXML
    TableView<Wrapper> tblView;
    ObservableList<Wrapper> wrapperObservableList;

    @FXML
    ListView<SpecialOffer> specialOfferListView;
    ObservableList<SpecialOffer> specialOfferObservableList;

    @FXML
    TableColumn hotelCol;
    @FXML
    TableColumn locationCol;
    @FXML
    TableColumn startCol;
    @FXML
    TableColumn endCol;
    @FXML
    public void setService(Service service, Integer id_client){
        this.service = service;
        this.id_client = id_client;
        initView();
        //updateView();
    }
    public void initView(){
        //hotelCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        List<SpecialOffer> specials =((List<SpecialOffer>) this.service.getAllSpecials()).stream().filter(offer -> offer.getPercents() < this.service.find_client(Long.valueOf(id_client)).getFidGrd()).toList();
        specialOfferObservableList = FXCollections.observableArrayList(specials);
        specialOfferListView.setItems(specialOfferObservableList);

        hotelCol.setCellValueFactory(new PropertyValueFactory<Wrapper,String>("hotel"));
        locationCol.setCellValueFactory(new PropertyValueFactory<Wrapper,String>("location"));
        startCol.setCellValueFactory(new PropertyValueFactory<Wrapper, Date>("startD"));
        endCol.setCellValueFactory(new PropertyValueFactory<Wrapper, Date>("endD"));

        List<Wrapper> wraps = ((List<Wrapper>) this.service.getAllWrappers()).stream().filter(w -> w.getPercents() < this.service.find_client(Long.valueOf(id_client)).getFidGrd()).toList();
        wrapperObservableList=FXCollections.observableArrayList(wraps);
        tblView.setItems(wrapperObservableList);
    }

}
