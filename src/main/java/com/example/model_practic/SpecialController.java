package com.example.model_practic;

import com.example.model_practic.domain.Hotel;
import com.example.model_practic.domain.SpecialOffer;
import com.example.model_practic.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import org.controlsfx.control.action.Action;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SpecialController {

    private Service service;
    private Hotel hotelSelected;

    @FXML
    public void setService(Service service, Hotel hotelSelected){
        this.service = service;
        this.hotelSelected = hotelSelected;
        initView();
        //updateView();
    }

    @FXML
    ListView<SpecialOffer> specialOfferListView;
    ObservableList<SpecialOffer> specialOffers;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;

    LocalDate startt;
    LocalDate endd;
    public void initView(){
        List<SpecialOffer> specials =((List<SpecialOffer>) this.service.getAllSpecials()).stream().filter(special -> special.getHotelId().equals(hotelSelected.getId())).toList();
        specialOffers = FXCollections.observableArrayList(specials);

        specialOfferListView.setItems(specialOffers);
    }
    @FXML
    public void getStart(ActionEvent event){
        startt = startDatePicker.getValue();
        System.out.println(startt);
    }
    @FXML
    public void getEnd(ActionEvent event){
        endd = endDatePicker.getValue();
        System.out.println(endd);
        if (startt!=null){
            List<SpecialOffer> specials =((List<SpecialOffer>) this.service.getAllSpecials()).stream().filter(special -> special.getHotelId().equals(hotelSelected.getId()) && special.getStartDate().compareTo(Date.valueOf(startt))>0 && special.getEndDate().compareTo(Date.valueOf(endd))<0).toList();
            specialOffers = FXCollections.observableArrayList(specials);
            specialOfferListView.setItems(specialOffers);
        }
    }

}
