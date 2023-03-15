package com.example.model_practic.service;

import com.example.model_practic.domain.*;
import com.example.model_practic.repository.Repository;
import com.example.model_practic.repository.databases.WraperRepo;

public class Service {
    private Repository<Float, Location> locationRepository;
    private Repository<Float, Hotel> hotelRepository;

    private Repository <Float, SpecialOffer> specialOfferRepository;
    private Repository<Long, Client> clientRepository;
    private WraperRepo wraperRepo;

    public Service(Repository<Float, Location> locationRepository, Repository<Float, Hotel> hotelRepository, Repository<Float, SpecialOffer> specialOfferRepository, Repository<Long, Client> clientRepository, WraperRepo wraperRepo) {
        this.locationRepository = locationRepository;
        this.hotelRepository = hotelRepository;
        this.specialOfferRepository = specialOfferRepository;
        this.clientRepository = clientRepository;
        this.wraperRepo = wraperRepo;
    }

    public Iterable<Location> getAllLocations() {return locationRepository.getAll();}
    public Iterable<Hotel> getAllHotels() {return hotelRepository.getAll();}
    public Iterable<SpecialOffer> getAllSpecials() {return specialOfferRepository.getAll();}
    public Client find_client(Long id_client) {return clientRepository.find(id_client);}
    public Iterable<Wrapper> getAllWrappers() {return wraperRepo.getAll();}
}
