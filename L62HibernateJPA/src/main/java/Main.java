import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMySQL");
        EntityManager em = emf.createEntityManager();

        try {

            while (true) {

                System.out.println("1.Full the menu\n" +
                        "2.Add to the menu\n" +
                        "3.Show all the menues\n" +
                        "4.Sort the menu for the price\n" +
                        "5.Choose the dish which weighs less then 1kg\n" +
                        "6.Show me dishes for the sale\n");
                number = scanner.nextInt();
                scanner.nextLine();

                switch (number){
                    case 1:
                        App.generateBD(em);
                        break;

                    case 2:
                        App.addDB(scanner,em);
                        break;

                    case 3:
                        App.showDB(em);
                        break;

                    case 4:
                        App.sortPrise(em);
                        break;

                    case 5:
//                        scanner.nextLine();
                        App.selectionSetDishes(scanner,em);
                        break;

                    case 6:
                        App.showSale(em);
                        break;

                        default:
                            System.out.println("Make a choise again");
                }
            }

        } finally {
            scanner.close();
            em.close();
            emf.close();
        }

    }
}
