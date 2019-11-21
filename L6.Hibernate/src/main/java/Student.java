import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne

    @JoinColumn(name = "group_id")
    private Group group;

    public Student() {
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, Group group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, group);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + group +
                '}';
    }
}
