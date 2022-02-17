package com.capgemini.mrchecker.selenium.Models;

import com.github.javafaker.Faker;

import java.awt.dnd.DropTarget;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class User {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String address;
    private String email;
    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private final Faker faker = new Faker();

    public User(){
        name = faker.name().firstName();
        surname = faker.name().lastName();
        address = faker.address().streetAddress() + faker.address().cityName();
        email = faker.internet().emailAddress();
        phoneNumber = faker.phoneNumber().cellPhone();
        dateOfBirth = faker.date().birthday(18, 60).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        password = faker.internet().password( 15,30,true,true,true) + "!1aA";
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFormattedDateOfBirth(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateOfBirth.format(formatter);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return email;
    }
}
