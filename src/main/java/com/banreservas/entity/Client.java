package com.banreservas.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.UUID;



@Entity
@Table(name = "clients")
public class Client extends PanacheEntityBase{

   //uuid generator
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstName", nullable = false, length = 50)
    public String firstName;

    @Size(max = 50)
    @Column(name = "middleName", length = 50)
    public String middleName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastName", nullable = false, length = 50)
    public String lastName;

    @Size(max = 50)
    @Column(name = "secondLastName", length = 50)
    public String secondLastName;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @NotNull
    @Size(min = 5, max = 200)
    @Column(name = "address", nullable = false, length = 200)
    public String address;

    @NotNull
    @Pattern(regexp = "\\d{10,25}")
    @Column(name = "phone", nullable = false, length = 20)  
    public String phone;

    @NotNull
    @Column(name = "countryCode", nullable = false, length = 5)
    public String countryCode;

    @Size(max = 50)
    @Column(name = "demonym",  length = 20)
    public String demonym;

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }   

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryCode() {
        return countryCode;
    }   

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

}
