package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Location;
import com.example.model_practic.domain.SpecialOffer;
import com.example.model_practic.exceptions.InexistentEntityException;
import com.example.model_practic.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpecialDbRepository implements Repository<Float, SpecialOffer> {
    private String url;
    private String user;
    private String password;

    public SpecialDbRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private SpecialOffer getSpecialFromResultSet(ResultSet resultSet) throws SQLException{
        Float id = resultSet.getFloat("id");
        Float id_hotel = resultSet.getFloat("id_hotel");
        Date start_date = resultSet.getDate("start_date");
        Date end_date = resultSet.getDate("end_date");
        Integer percents = resultSet.getInt("percents");
        return  new SpecialOffer(id, id_hotel, start_date, end_date, percents);
    }
    @Override
    public SpecialOffer find(Float id) {
        if(user == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM special WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getSpecialFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
    @Override
    public Iterable<SpecialOffer> getAll() {
        List<SpecialOffer> specials = new ArrayList<>();
        String sql = "SELECT * FROM special";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                SpecialOffer specialItem = getSpecialFromResultSet(resultSet);
                specials.add(specialItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return specials;
    }
}
