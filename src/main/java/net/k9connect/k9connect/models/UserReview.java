package net.k9connect.k9connect.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "A review must be included.")
    @Getter @Setter private String review;

    @Column
    @NotNull
    @Min(1)
    @Max(5)
    @Getter @Setter private int stars;

}