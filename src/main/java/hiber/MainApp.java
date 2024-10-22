package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "User1", "User1@gmail.com");
      User user2 = new User("User2", "User2", "User2@gmail.com");
      User user3 = new User("User3", "User3", "User3@gmail.com");
      User user4 = new User("User4", "User4", "User4@gmail.com");
      Car car1 = new Car("VAZ", 2107);
      Car car2 = new Car("VAZ", 2105);
      Car car3 = new Car("VAZ", 2101);
      Car car4 = new Car("VAZ", 2110);
      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user);
      }
      System.out.println("______________________________________________________");

      try {
         System.out.println(userService.getUserByCar("VAZ", 2107));
      } catch (NoResultException e) {
         System.out.println("ТАКОЙ МАШИНЫ ТУТОЧКИ НЕТУ - НЕ НАДО ШУТИТЬ С ТАЗАМИ!!!");
      }

      context.close();
   }
}