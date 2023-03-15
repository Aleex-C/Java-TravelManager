package com.example.model_practic.repository.factory;

import com.example.model_practic.config.ApplicationContext;
import com.example.model_practic.domain.*;

import com.example.model_practic.repository.Repository;
import com.example.model_practic.repository.databases.ClientDbRepository;
import com.example.model_practic.repository.databases.HotelDbRepository;
import com.example.model_practic.repository.databases.LocationDbRepository;
import com.example.model_practic.repository.databases.SpecialDbRepository;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RepositoryFactory {

    public static String url = ApplicationContext.getPROPERTIES().getProperty("url");
    public static String user  = ApplicationContext.getPROPERTIES().getProperty("user");
    public static String password = ApplicationContext.getPROPERTIES().getProperty("password");
    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Float, Location> createLocationRepository() {
        return new LocationDbRepository(url,
                user, password);
    }
    private static Repository<Float, Hotel> createHotelRepository() {
        return new HotelDbRepository(url,
                user, password);
    }
    private static Repository<Float, SpecialOffer> createSpecialRepository() {
        return new SpecialDbRepository(url,
                user, password);
    }
    private static Repository<Long, Client> createClientRepository() {
        return new ClientDbRepository(url,
                user, password);
    }

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case LOCATION:
                return createLocationRepository();
            case HOTEL:
                return createHotelRepository();
            case SPECIAL:
                return createSpecialRepository();
            case CLIENT:
                return createClientRepository();
            default:
                return createLocationRepository();
        }
    }

    /**
     * Method that retuns the instance of the repository factory
     * @return the repository factory
     */
    public static RepositoryFactory getInstance() {
        return instance;
    }
}