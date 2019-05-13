package simple_pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Path;

public class Login {
  private List<Users> users;
  private String userName;
  private String password;
  private String name;

  BufferedReader br = null;
  String line = "";

  private static List<Users> Login(String name) {
      List<Users> users = new ArrayList<>();
      java.nio.file.Path pathToFile = Paths.get("Login.csv");

      try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
          String line = br.readLine();

          while(line != null) {
              String[] attributes = line.split(",");
              Users user = createUser(attributes);

              users.add(user);

              line = br.readLine();

              ((Login) users).authenticate(attributes);
          }
      } catch (IOException ioe) {
          ioe.printStackTrace();
      }
      return users;
  }

  private static Users createUser(String[] metadata) {
     String name = metadata[0];
     String userName = metadata[1];
     String password = metadata[2];

     return new Users(name, userName, password);
 }

  public String authenticate(String[] metadata) {
     String userName = metadata[0];
     String password = metadata[1];


     for (int i = 0; i < users.size(); i++) {
         if (((Users) users.get(i)).getUserName().equals(userName) && ((Users) users.get(i)).getPassword().equals(password)) {
             return users.get(i).getName();
         }
     }
      return "";
  }
  
}