package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car firstCar = new Car("Ford", 1);
      Car secondCar = new Car("Chevrolet", 2);
      Car thirdCar = new Car("Lincoln", 3);
      Car fourthCar = new Car("Chrysler", 4);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", firstCar));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", secondCar));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", thirdCar));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",fourthCar));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      List<User> usersByCar = userService.getUserByCar("Chrysler", 4);
      System.out.println("User with car model Chrysler: " + usersByCar);

      context.close();
   }
}
