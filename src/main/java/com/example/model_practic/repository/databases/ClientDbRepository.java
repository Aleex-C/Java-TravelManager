package com.example.model_practic.repository.databases;

import com.example.model_practic.domain.Client;
import com.example.model_practic.domain.Hotel;
import com.example.model_practic.exceptions.InexistentEntityException;
import com.example.model_practic.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDbRepository implements Repository<Long, Client> {
    private String url;
    private String user;
    private String password;

    public ClientDbRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Integer fidgrd = resultSet.getInt("fidgrd");
        Integer age = resultSet.getInt("age");

        return new Client(id, name, fidgrd, age);
    }
    @Override
    public Client find(Long id) {
        if(user == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM clients WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getClientFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
    @Override
    public Iterable<Client> getAll() {
        List<Client> ClientItems = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try(Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Client ClientItem = getClientFromResultSet(resultSet);
                ClientItems.add(ClientItem);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ClientItems;
    }

}
