package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Hotel;
import com.example.model_practic.domain.Wrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WraperRepo {
    private String url;
    private String user;
    private String password;

    public WraperRepo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public Wrapper getWrapperFromResult (ResultSet resultSet) throws SQLException{
        String hotel = resultSet.getString("hotel");
        String location = resultSet.getString("locatie");
        Date startd = resultSet.getDate("startd");
        Date endd = resultSet.getDate("endd");
        Integer percents = resultSet.getInt("perc");

        return new Wrapper(hotel,location,startd, endd, percents);
    }
    public Iterable<Wrapper> getAll() {
        List<Wrapper> WrapperItems = new ArrayList<>();
        String sql = "SELECT h.hotel_name as hotel, l.name as locatie, s.start_date as startd, s.end_date as endd, s.percents as perc FROM hotels h, locations l, special s WHERE s.id_hotel = h.id_hotel AND h.id_location = l.id";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Wrapper WrapperItem = getWrapperFromResult(resultSet);
                WrapperItems.add(WrapperItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return WrapperItems;
    }
}
