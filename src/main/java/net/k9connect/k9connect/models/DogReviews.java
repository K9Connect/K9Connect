package net.k9connect.k9connect.models;

import javax.persistence.*;

@Entity
@Table(name = "dog_reviews")
public class DogReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Dog dog;

    @OneToOne
    @JoinColumn(name = "id")
    private User commenter;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column
    private int stars;

    public DogReviews() {
    }

    public DogReviews(long id, Dog dog, User commenter, String review, int stars) {
        this.id = id;
        this.dog = dog;
        this.commenter = commenter;
        this.review = review;
        this.stars = stars;
    }

    public DogReviews(Dog dog, User commenter, String review, int stars) {
        this.dog = dog;
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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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
