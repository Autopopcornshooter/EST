package EST.messageSender.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private long id;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="grade",nullable = false)
    private int grade;
}
