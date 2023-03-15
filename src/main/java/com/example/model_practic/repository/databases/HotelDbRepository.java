package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Hotel;
import com.example.model_practic.domain.Location;
import com.example.model_practic.domain.PeopleType;
import com.example.model_practic.exceptions.InexistentEntityException;
import com.example.model_practic.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDbRepository implements Repository<Float, Hotel> {
    private String url;
    private String user;
    private String password;

    public HotelDbRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private Hotel getHotelFromResultSet(ResultSet resultSet) throws SQLException {
        Float id = resultSet.getFloat("id_hotel");
        Float locationId = resultSet.getFloat("id_location");
        String hotel_name = resultSet.getString("hotel_name");
        Integer noRooms = resultSet.getInt("nr_rooms");
        Float pricePerNight = resultSet.getFloat("price");
        String type = resultSet.getString("type");
        PeopleType typeHotel = PeopleType.valueOf(type);

        return  new Hotel(id, locationId,hotel_name,noRooms,pricePerNight, typeHotel);

    }
    @Override
    public Hotel find(Float id) {
        if(user == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM hotels WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getHotelFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
    @Override
    public Iterable<Hotel> getAll() {
        List<Hotel> HotelItems = new ArrayList<>();
        String sql = "SELECT * FROM hotels";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Hotel HotelItem = getHotelFromResultSet(resultSet);
                HotelItems.add(HotelItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return HotelItems;
    }
}
