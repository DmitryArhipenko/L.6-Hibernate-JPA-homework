import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    private static RestaurantMenu restaurantMenu;

    public static void addDB(Scanner scanner, EntityManager em) {
        String str;
        String name;
        double price;
        int weight;
        boolean sale;

        System.out.println("Insert name: ");
        name = scanner.nextLine();

        System.out.println("Insert price: ");
        price = scanner.nextDouble();

        System.out.println("Dishes weight: ");
        weight = scanner.nextInt();

        System.out.println("Sale y/n");
        while (true) {
            str = scanner.nextLine();

            if ("yes".equals(str)) {
                sale = true;
                break;
            }

            if ("no".equals(str)) {
                sale = false;
                break;
            }
        }

        try {
            restaurantMenu = new RestaurantMenu(name, price, weight, sale);
            em.getTransaction().begin();
            try {
                em.persist(restaurantMenu);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void showSale(EntityManager em) {
        System.out.println("\nDishes in sale\n");
        Query query = em.createQuery("FROM RestaurantMenu WHERE sale = true", RestaurantMenu.class);
        List<RestaurantMenu> list = query.getResultList();

        for (RestaurantMenu menu : list) {
            System.out.println(menu);
        }

    }

    public static void showDB(EntityManager em) {
        System.out.println("\nDishes list\n");
        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);
        List<RestaurantMenu> list = query.getResultList();

        for (RestaurantMenu menu : list) {
            System.out.println(menu);
        }

    }

    public static void sortPrise(EntityManager em) {

        System.out.println("\nDishes sorted for the price\n");
        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);
        List<RestaurantMenu> list = query.getResultList();
        list.stream()
                .sorted(Comparator.comparingDouble(RestaurantMenu::getPrice))
                .forEach(System.out::println);

    }

    public static void selectionSetDishes(Scanner scanner, EntityManager em) {
        List<RestaurantMenu> restaurantMenus = new ArrayList<>();
        String name;
        int count = 0;

        Query query = em.createQuery("FROM RestaurantMenu", RestaurantMenu.class);
        List<RestaurantMenu> list = query.getResultList();

        System.out.println("Press enter to exit\n");
        while (count <= 1000) {
            System.out.println("Dish name");
            name = scanner.nextLine();

            if (name.isEmpty())
                break;

            for (RestaurantMenu menu : list) {
                if (name.equals(menu.getName())) {
                    count += menu.getWeight();
                    if (count <= 1000) {
                        restaurantMenus.add(menu);
                    }

                }
            }
        }

        if (count > 1000) {
            System.out.println("Sorry, it weights more then 1kg");
            restaurantMenus.forEach(System.out::println);
        } else {
            System.out.println("Dishes which you chose");
            System.out.println("Current weight: " + count);
            restaurantMenus.forEach(System.out::println);
        }
    }

    public static void generateBD(EntityManager em) {

        em.getTransaction().begin();
        try {
            for (int i = 1; i <= 5; i++) {
                em.persist(new RestaurantMenu("name" + i, 600 - (100 * i), 100 * i, i % 2 == 0));
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }


}
