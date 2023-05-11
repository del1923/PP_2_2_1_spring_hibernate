package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   //реализуем создаём метод поиска пользователя по авто
   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByCar(String model, int series) {
      //запрос HQL с передачей параметров model и series
      String hql = "FROM User user where user.car.modelCar = :model and user.car.seriesCar = :series";
      //получаем текущую сессию для запроса
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      //формируем запрос
      query.setParameter("model", model).setParameter("series", series);
      //возвращаем список пользователей с требуемым авто
      return query.getResultList();
      /*
      если нужен один пользователь можно вернуть один экземпляр пользователя (первый встреченный в БД) изменив
       в public List<User> getUserByCar(...) на public User getUserByCar(...) в DAO и SERVICE;
       и return query.setMaxResults(1).getSingleResult();
       Также необходимо обработать исключения при вызове userService.getUserByCar(...)
       При выводе списка - нужно проверять список на пустой/не пустой
       */
   }
}
