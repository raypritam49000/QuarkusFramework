//package com.rest.api.main;
//
//import com.rest.api.entity.Person;
//import io.quarkus.runtime.QuarkusApplication;
//import io.quarkus.runtime.annotations.QuarkusMain;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import javax.enterprise.context.control.ActivateRequestContext;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@QuarkusMain
//public class QuarkusApplicationImpl implements QuarkusApplication {
//
//    @Inject
//    EntityManager entityManager;
//
//    @Inject
//    SessionFactory sessionFactory;
//
//    @ActivateRequestContext
//    @Transactional
//    @Override
//    public int run(String... args) throws Exception {
//        try {
//
//
//            System.out.println("Hello Pritam Ray");
//
//            List<Person> persons = List.of(
//                    new Person("Pritam Ray", "Ropar", "56000"),
//                    new Person("Amit Kumar", "Rail", "26000"),
//                    new Person("Omi Verma", "Ropar", "86000"),
//                    new Person("Ajay Kumar", "Prem Nagar", "46000")
//            );
//
//            persons.stream().forEach(person -> {
//                this.entityManager.persist(person);
//            });
//
//            Query q = entityManager.createQuery("from Person p", Person.class);
//            List<Person> personList = q.getResultList();
//            System.out.println(personList);
//
//            Session session = sessionFactory.openSession();
//            Person person = session.get(Person.class,1L);
//            System.out.println(person);
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return 0;
//    }
//}