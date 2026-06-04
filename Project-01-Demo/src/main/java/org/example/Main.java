package org.example;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{

  public static void main(String[] args)
  {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.printf("Hello and welcome!");
    SessionFactory factory = new Configuration().configure()
        .addAnnotatedClass(Student.class)
        .buildSessionFactory();
    Session session = factory.openSession();

    // Add student db
//    session.beginTransaction();
//    Student student = new Student("CC",99);
//    session.persist(student);
//    session.getTransaction().commit();

    //get details from DB
    Student student = session.find(Student.class, 3L);
    System.out.println(student.getName());
    System.out.println(student.getMarks());

    //Update Data
    session.beginTransaction();
    Student updateStudent = session.find(Student.class, 3L);

    if(updateStudent != null)
    {
      System.out.println(
          "Before Update: " + updateStudent.getName() + " " + updateStudent.getMarks());

      updateStudent.setMarks(100);
      session.getTransaction().commit();
      System.out.println(
          "After Updated : " + updateStudent.getName() + " " + updateStudent.getMarks());
    }
    else{
        System.out.println("Student not found for update.");
    }

    //Del Data
    session.beginTransaction();
    Student deleteStudent = session.find(Student.class, 3L);
    if(deleteStudent != null)
    {
      session.remove(deleteStudent);
      session.getTransaction().commit();
      System.out.println("Student with ID 3 deleted successfully.");
    }
    else{
        System.out.println("Student not found for deletion.");
    }



    session.close();
    factory.close();

  }
}