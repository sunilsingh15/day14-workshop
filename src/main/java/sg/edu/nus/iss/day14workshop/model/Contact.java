package sg.edu.nus.iss.day14workshop.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import org.springframework.format.annotation.DateTimeFormat;



public class Contact implements Serializable {

    @NotNull(message =  "Name can not be empty")
    @Size(min= 3, max = 64, message = "Name should be between 3 to 15 characters")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email")
    private String email;

    @Size(min =7, message = "Invalid Phone Number")
    private String phoneNumber;

    @Past(message = "Date of birth should not be from future")
    @NotNull(message = "Date of birth is mandatory")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;
    

    @Min(value = 10, message = "Must be above 10 yrs old")
    @Max(value = 100, message = "Must be below 100 yrs old")
    private int age;


    private String id;

   

    public Contact(){
        this.id = generateId();  
    }
    
    
    public Contact(String id,
             String name,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth
            ) {
                this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        
    }


    private String generateId() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < 8){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 8);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        //calculate the age
      int calculatedAge = 0;
      if(dateOfBirth != null){
        calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
      } 
      this.age = calculatedAge;
      this.dateOfBirth = dateOfBirth;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

    

}