package com.example.model_practic;

import com.example.model_practic.domain.Client;
import com.example.model_practic.domain.Hotel;
import com.example.model_practic.domain.Location;
import com.example.model_practic.domain.SpecialOffer;
import com.example.model_practic.repository.Repository;
import com.example.model_practic.repository.databases.WraperRepo;
import com.example.model_practic.repository.factory.RepositoryEntity;
import com.example.model_practic.repository.factory.RepositoryFactory;
import com.example.model_practic.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Repository<Float, Location> locationRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCATION);
        Repository<Float, Hotel> hotelRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.HOTEL);
        Repository<Float, SpecialOffer> specialOfferRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.SPECIAL);
        Repository<Long, Client> clientRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENT);
        WraperRepo wraperRepo = new WraperRepo("jdbc:postgresql://localhost:5432/ModelBD", "postgres", "postgres");
        Service service = new Service(locationRepository, hotelRepository, specialOfferRepository, clientRepository, wraperRepo);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        HelloController helloController =fxmlLoader.getController();
        helloController.setService(service);

        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();

        Parameters args = getParameters();
        List<String> argumente = args.getRaw();
        System.out.println(argumente);


        FXMLLoader fxmlLoaderc1 = new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
        Scene scene1 = new Scene(fxmlLoaderc1.load(), 300, 150);
        ClientController clientController = fxmlLoaderc1.getController();
        clientController.setService(service, Integer.valueOf(argumente.get(0)));
        Stage stage2 = new Stage();
        stage2.setTitle("Client " + argumente.get(0));
        stage2.setScene(scene1);
        stage2.show();

        FXMLLoader fxmlLoaderc2= new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
        Scene scene2 = new Scene(fxmlLoaderc2.load(), 300, 150);
        ClientController clientController2 = fxmlLoaderc2.getController();
        clientController2.setService(service, Integer.valueOf(argumente.get(1)));

        Stage stage3 = new Stage();
        stage3.setTitle("Client " + argumente.get(1));
        stage3.setScene(scene2);
        stage3.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}