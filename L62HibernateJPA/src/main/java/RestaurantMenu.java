
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "restaurant_menu")
public class RestaurantMenu {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    private double price;

    @Column(name = "weight", nullable = false)
    @NotNull
    private int weight;

    @Column(name = "sale", nullable = false)
    @NotNull
    private boolean sale;

    public RestaurantMenu() {}

    public RestaurantMenu(String name, double price, int weight, boolean sale) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }


    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {

        String str = sale ? "sale price" : "current price";
        return  "Name: " + name +
                " Price: " + price +
                " Weight: " + weight +
                " Sale: " + str;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantMenu that = (RestaurantMenu) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                weight == that.weight &&
                sale == that.sale &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, weight, sale);
    }
}
