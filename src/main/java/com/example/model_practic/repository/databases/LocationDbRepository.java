package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Location;
import com.example.model_practic.exceptions.InexistentEntityException;
import com.example.model_practic.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDbRepository implements Repository<Float, Location> {
    private String url;
    private String user;
    private String password;

    public LocationDbRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Location getLocationFromResultSet(ResultSet resultSet) throws SQLException{
        Float id = resultSet.getFloat("id");
        String locationName = resultSet.getString("name");

        return new Location(id,locationName);
    }
    @Override
    public Location find(Float id) {
        if(user == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM locations WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getLocationFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
    @Override
    public Iterable<Location> getAll() {
        List<Location> LocationsItems = new ArrayList<>();
        String sql = "SELECT * FROM locations";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Location LocationsItem = getLocationFromResultSet(resultSet);
                LocationsItems.add(LocationsItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return LocationsItems;
    }
}
