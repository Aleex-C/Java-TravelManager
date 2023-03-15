package com.example.model_practic.domain;

import java.util.Objects;

public class Client extends Entity<Long>{
    private String name;
    private int fidGrd;
    private int age;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", fidGrd=" + fidGrd +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return fidGrd == client.fidGrd && age == client.age && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, fidGrd, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFidGrd() {
        return fidGrd;
    }

    public void setFidGrd(int fidGrd) {
        this.fidGrd = fidGrd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Client(Long aLong, String name, int fidGrd, int age) {
        super(aLong);
        this.name = name;
        this.fidGrd = fidGrd;
        this.age = age;
    }
}
