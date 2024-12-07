package klu.klef.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Insert records
            insertProjects(session);

            // Aggregate functions using HQL
            // Count of Projects
            Long count = (Long) session.createQuery("SELECT COUNT(p) FROM Project p").uniqueResult();
            System.out.println("Count of Projects: " + count);

            // Maximum Budget
            Double maxBudget = (Double) session.createQuery("SELECT MAX(p.budget) FROM Project p").uniqueResult();
            System.out.println("Maximum Budget: " + maxBudget);

            // Minimum Budget
            Double minBudget = (Double) session.createQuery("SELECT MIN(p.budget) FROM Project p").uniqueResult();
            System.out.println("Minimum Budget: " + minBudget);

            // Sum of Budgets
            Double sumBudget = (Double) session.createQuery("SELECT SUM(p.budget) FROM Project p").uniqueResult();
            System.out.println("Sum of Budgets: " + sumBudget);

            // Average Budget
            Double avgBudget = (Double) session.createQuery("SELECT AVG(p.budget) FROM Project p").uniqueResult();
            System.out.println("Average Budget: " + avgBudget);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertProjects(Session session) {
        Project p1 = new Project();
        p1.setProjectName("AI Development");
        p1.setDuration(12);
        p1.setBudget(50000.0);
        p1.setTeamLead("Alice");

        Project p2 = new Project();
        p2.setProjectName("Web App");
        p2.setDuration(8);
        p2.setBudget(30000.0);
        p2.setTeamLead("Bob");

        Project p3 = new Project();
        p3.setProjectName("Data Analysis");
        p3.setDuration(6);
        p3.setBudget(20000.0);
        p3.setTeamLead("Charlie");

        session.save(p1);
        session.save(p2);
        session.save(p3);
    }
}
