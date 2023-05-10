package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasiliy = new User( "Василий", "Васин", "vasin@email.rr" );
      User petr = new User( "Пётр", "Петров", "petrov@mail.rr");
      User sweta = new User( "Света", "Светова", "sweta@mail.ru");
      User valery = new User ( "Валерий", "Владимиров", "valery@mail.rr");
      User dmitry = new User ( "Дмитрий", "Дёмин", "demin@mail.rr");

      Car lada = new Car( "LADA", 2107);
      Car bmw = new Car ( "BMW", 7);
      Car volvo = new Car ( "Volvo", 9);


      vasiliy.setCar( lada );
      userService.add(vasiliy);

      petr.setCar( bmw );
      userService.add( petr );

      sweta.setCar( lada );
      userService.add( sweta );

      valery.setCar( volvo );
      userService.add( valery );

      dmitry.setCar( lada );
      userService.add( dmitry );

        // выводим добавленных пользователей с машинами
      System.out.println("выводим пользователей с машинами");
      for (User user : userService.listUsers()) {
           System.out.println(user + " " + user.getCar());
       }
      System.out.println();
      // выводим пользователей с искомым авто
      System.out.println("выводим пользователей с LADA, 2107");
      for ( User user : userService.getUserByCar( "LADA", 2107 ) ) {
         System.out.println( user.getFirstName() + " " + user.getLastName() + " " + user.getEmail());
      }
      context.close();
   }
}
