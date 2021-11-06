package net.k9connect.k9connect.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dog_reviews")
@NoArgsConstructor
@AllArgsConstructor
public class DogReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @ManyToOne
    @JoinColumn(name = "reviewed_dog_id")
    @Getter @Setter private Dog dog;

    @ManyToOne
    @JoinColumn(name = "reviewing_user_id")
    @Getter @Setter private User commenter;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String review;

    @Column
    @Getter @Setter private int stars;
}
