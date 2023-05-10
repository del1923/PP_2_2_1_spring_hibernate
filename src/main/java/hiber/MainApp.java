package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasiliy = new User( "Василий", "Васин", "vasin@email.rr" );
      User petr = new User( "Пётр", "Петров", "petrov@mail.rr");
      User sweta = new User( "Света", "Светова", "sweta@mail.ru");

      Car lada = new Car( "LADA", 2107);
      Car bmw =new Car ( "BMW", 7);

      vasiliy.setCar( lada );
      userService.add(vasiliy);

      petr.setCar( bmw );
      userService.add( petr );

      sweta.setCar( lada );
      userService.add( sweta );

        // выводим пользователей с машинами
      System.out.println("выводим пользователей с машинами");
      for (User user : userService.listUsers()) {
           System.out.println(user + " " + user.getCar());
       }

      System.out.println("выводим пользователя с LADA, 2107");
      System.out.println( userService.getUserByCar( "LADA", 2107 ));

      context.close();
   }
}
