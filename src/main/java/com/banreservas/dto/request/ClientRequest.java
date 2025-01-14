package com.banreservas.dto.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


public class ClientRequest {

    @NotNull(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    public String firstName;

    @Size(max = 50, message = "Second last name must not exceed 50 characters")
    public String secondLastName;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    public String lastName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    public String middleName;

    @NotNull(message = "Email is required")
    @Email(message = "Must be a valid email address")
    public String email;

    @NotNull(message = "Address is required")
    @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
    public String address;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9\\s\\-]{10,15}$", message = "Phone number must be numeric, with optional spaces or hyphens, and between 10 and 15 characters")
    public String phone;

    @Pattern(regexp = "^[A-Za-z]{2}$", message = "Country code must be exactly 2 alphabetic characters")
    public String countryCode;

    @NotNull(message = "Demonym is required")
    @Size(max = 50, message = "Demonym must not exceed 50 characters")
    public String demonym;

 }
