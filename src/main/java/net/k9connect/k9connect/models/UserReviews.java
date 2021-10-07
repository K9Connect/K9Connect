package net.k9connect.k9connect.models;

import javax.persistence.*;

@Entity
@Table(name = "user_reviews")
public class UserReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reviewed_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reviewing_id")
    private User commenter;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column
    private int stars;

    public UserReviews() {
    }

    public UserReviews(long id, User user, User commenter, String review, int stars) {
        this.id = id;
        this.user = user;
        this.commenter = commenter;
        this.review = review;
        this.stars = stars;
    }

    public UserReviews(User user, User commenter, String review, int stars) {
        this.user = user;
        this.commenter = commenter;
        this.review = review;
        this.stars = stars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
