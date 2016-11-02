package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by Olga on 11/1/16.
 */
public class DbHelper extends HelperBase {

  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    super(app);
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }


  public List<Users> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Users> result = session.createQuery( "from Users" ).list();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}
