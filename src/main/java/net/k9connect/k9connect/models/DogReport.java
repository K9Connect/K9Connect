package net.k9connect.k9connect.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dog_reports")
@NoArgsConstructor
@AllArgsConstructor
public class DogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reporting_user_id")
    @Getter
    @Setter
    private User reportingUser;

    @ManyToOne
    @JoinColumn(name = "reported_dog_id")
    @Getter
    @Setter
    private Dog reportedDog;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String reportText;
}