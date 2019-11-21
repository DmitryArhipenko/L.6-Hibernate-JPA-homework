import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students_group_table")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "num")
    private int number;


    public Group() {
    }

    public Group(int number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                number == group.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", num=" + number +
                '}';
    }

}
