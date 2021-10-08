package net.k9connect.k9connect.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_reviews")
@NoArgsConstructor
@AllArgsConstructor
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @ManyToOne
    @JoinColumn(name = "reviewed_user_id")
    @Getter @Setter private User reviewed;

    @ManyToOne
    @JoinColumn(name = "reviewer_user_id")
    @Getter @Setter private User reviewer;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String review;

    @Column
    @Getter @Setter private int stars;

}