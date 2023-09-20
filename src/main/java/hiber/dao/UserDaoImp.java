package hiber.dao;

import hiber.model.User;
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
   public List<User> getUserByCar(String model, int series){
      String HQLQuery = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
      TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery(HQLQuery, User.class);
      typedQuery.setParameter("model", model);
      typedQuery.setParameter("series", series);
      return typedQuery.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

}
