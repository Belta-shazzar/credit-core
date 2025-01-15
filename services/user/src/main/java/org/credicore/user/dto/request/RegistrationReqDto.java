package org.credicore.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationReqDto {
  @Size(max = 40, message = "First name must not exceed 40 characters.")
  private String firstName;

  @Size(max = 40, message = "Last name must not exceed 40 characters.")
  private String lastName;

  @NotBlank(message = "Email cannot be blank.")
  @Email(message = "Please provide a valid email address.")
//  @Pattern(
//          regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
//          message = "Invalid email format."
//  )
  private String email;

  @NotBlank(message = "Password cannot be blank.")
  @Size(min = 8, max = 12, message = "Password must be between 8 and 12 " +
          "characters.")
//  @Pattern(
//          regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])
//          [A-Za-z\\d@$!%*?&]{8,12}$",
//          message = "Password must contain at least one uppercase letter,
//          one lowercase letter, one digit, and one special character."
//  )
  private String password;


  @NotBlank(message = "Phone number cannot be blank.")
//  @Pattern(
//          regexp = "^(\\+\\d{1,3})?\\d{10,15}$",
//          message = "Phone number must be 10–15 digits long and may include
//          an optional '+' prefix for international numbers."
//  )
  private String phoneNumber;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "RegistrationReqDto{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
  }

}
