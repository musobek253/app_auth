package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class User {
     private int id;
     private String FristName;
     private String LastName;
     private String userName;
     private String phoneNumber;
     private String password;

     public User(int id, String fristName, String lastName, String userName, String phoneNumber) {
          this.id = id;
          FristName = fristName;
          LastName = lastName;
          this.userName = userName;
          this.phoneNumber = phoneNumber;
     }

     public User(String userName, String password) {
          this.userName = userName;
          this.password = password;
     }

     public User(String fristName, String lastName, String userName, String phoneNumber, String password) {
          FristName = fristName;
          LastName = lastName;
          this.userName = userName;
          this.phoneNumber = phoneNumber;
          this.password = password;
     }

     public User(String userName) {
          this.userName = userName;
     }
}
