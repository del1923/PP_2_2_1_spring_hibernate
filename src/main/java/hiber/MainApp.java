/*
С работой ядра Спринг мы разобрались, теперь самое время подключить к нему пару модулей для комфортной работы.
Начнем с ORM.
Для работы с hibernate нам понадобится зависимость hibernate-core, корректным взаимодействием со Спрингом
 озаботится зависимость spring-orm.
Как вы можете видеть, зависимость spring-core пропала, это произошло из-за того, что она является транзитной
 для всех модулей Спринга и дублировать ее смысла нет.
У нас появились пакеты model, service, теперь сервисы и DAO объявлены бинами с помощью аннотаций @Repository и
 @Service.
В методе main будет происходить тестирование работоспособности нашего приложения. Класс Car аннотирован как
стандартная сущность hibernate. В AppConfig теперь присутствует базовая настройка hibernate, берущая данные из файла db.properties. Обратите внимание, что для ее работы используется аннотация @PropertySource("classpath:db.properties"), обращающаяся к папке ресурсов.
На этом настройка приложения окончена.

Задание:
1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи
 one-to-one.
3. Добавьте этот класс в настройки hibernate.
4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели
 и серии.
 */


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

      // создаём экземпляры пользователей с именами, фамилиями, почтой - всё просто
      User vasiliy = new User( "Василий", "Васин", "vasin@email.rr" );
      User petr = new User( "Пётр", "Петров", "petrov@mail.rr");
      User sweta = new User( "Света", "Светова", "sweta@mail.ru");
      User valery = new User ( "Валерий", "Владимиров", "valery@mail.rr");
      User dmitry = new User ( "Дмитрий", "Дёмин", "demin@mail.rr");
      // написав класс Car создаём экземпляры авто с моделями и сериями - тоже самое
      Car lada = new Car( "LADA", 2107);
      Car bmw = new Car ( "BMW", 7);
      Car volvo = new Car ( "Volvo", 9);

      //вносим пользователей и их авто в БД посредством хибера
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
      String modelCar = "LADA"; // тут указываем какое авто ищем
      int seriesCar = 2107;    // хотя это вводится обычно с клавиатуры
      // проверяем пустой/не пустой список
      System.out.println("выводим пользователей с " + modelCar + " " + seriesCar );
      if ( userService.getUserByCar( modelCar, seriesCar ).isEmpty() ) {
         System.out.println( "Нет таких пользователей" );
      }
      // выводим список
      for ( User user : userService.getUserByCar( modelCar, seriesCar ) ) {
         System.out.println( user.getFirstName() + " " + user.getLastName() + " " + user.getEmail());
      }
      context.close();
   }
}
