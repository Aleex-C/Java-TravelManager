package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Location;
import com.example.model_practic.domain.Reservation;
import com.example.model_practic.exceptions.InexistentEntityException;
import com.example.model_practic.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDbRepository implements Repository<Float, Reservation> {
    private String url;
    private String user;
    private String password;

    public ReservationDbRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Reservation getReservationFrom (ResultSet resultSet) throws SQLException {
        Float id = resultSet.getFloat("id");
        Float id_client = resultSet.getFloat("id_client");
        Float id_hotel = resultSet.getFloat("id_hotel");
        LocalDateTime startd = resultSet.getTimestamp("startd").toLocalDateTime();
        Integer nights = resultSet.getInt("nights");

        return new Reservation(id, id_client, id_hotel, startd, nights);
    }
    @Override
    public Reservation find(Float id) {
        if(user == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM reservations WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getReservationFrom(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
    @Override
    public Iterable<Reservation> getAll() {
        List<Reservation> ReservationItems = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Reservation ReservationItem = getReservationFrom(resultSet);
                ReservationItems.add(ReservationItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ReservationItems;
    }
}
