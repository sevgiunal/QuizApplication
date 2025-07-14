package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class addToCSVFile {
    public static void main(String[] args) {
        Session session = null;
        List<Option> options = new ArrayList<>();
//
        List<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(Testing.class.getClassLoader().getResourceAsStream("questions.csv"));
        // skip first line - heading
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            // read in line and split
            String[] line = scanner.nextLine().split(";");
            //  create object and add to arraylist
            Question que = new Question();
            que.SetMarks(Integer.parseInt(line[0]));
            que.SetQuestion(line[1]);
            que.SetTopic(line[2]);
            que.SetType(line[3]);
            que.SetAnswer(line[4]);
            if (que.GetType().equals("MCQ")) {
                Option o1 = new Option(line[5]);
                Option o2 = new Option(line[6]);
                Option o3 = new Option(line[7]);
                options.add(o1);
                options.add(o2);
                options.add(o3);
                o1.setQuestion(que);
                o2.setQuestion(que);
                o3.setQuestion(que);
            }

            questions.add(que);

        }

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // add to database
            for (Question q : questions) {

                session.persist(q);
            }


            session.getTransaction().commit();
        } catch (
                HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // add to database

            for (Option o : options) {
                session.persist(o);
            }

            session.getTransaction().commit();
        } catch (
                HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
}
