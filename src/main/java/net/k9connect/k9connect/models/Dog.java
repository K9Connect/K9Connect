package net.k9connect.k9connect.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private char gender;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    private List<Photos> photos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedDog")
    private List<DogReports> reportsagainst;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportingUser")
    private List<DogReports> reportsfiled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    private List<DogReviews> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commenter")
    private List<DogReviews> reviews_made;

    @Column
    private int reputation;

    @Column
    private int status_id;

    @OneToOne
    @JoinColumn(name = "dog")
    private DogDetails details;

    public Dog() {
    }

    public Dog(long id, String name, String breed, char gender, User owner, List<Photos> photos, List<DogReports> reportsagainst, List<DogReports> reportsfiled, List<DogReviews> reviews, List<DogReviews> reviews_made, int reputation, int status_id, DogDetails details) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.owner = owner;
        this.photos = photos;
        this.reportsagainst = reportsagainst;
        this.reportsfiled = reportsfiled;
        this.reviews = reviews;
        this.reviews_made = reviews_made;
        this.reputation = reputation;
        this.status_id = status_id;
        this.details = details;
    }

    public Dog(String name, String breed, char gender, User owner, List<Photos> photos, List<DogReports> reportsagainst, List<DogReports> reportsfiled, List<DogReviews> reviews, List<DogReviews> reviews_made, int reputation, int status_id, DogDetails details) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.owner = owner;
        this.photos = photos;
        this.reportsagainst = reportsagainst;
        this.reportsfiled = reportsfiled;
        this.reviews = reviews;
        this.reviews_made = reviews_made;
        this.reputation = reputation;
        this.status_id = status_id;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public List<DogReports> getReportsagainst() {
        return reportsagainst;
    }

    public void setReportsagainst(List<DogReports> reportsagainst) {
        this.reportsagainst = reportsagainst;
    }

    public List<DogReports> getReportsfiled() {
        return reportsfiled;
    }

    public void setReportsfiled(List<DogReports> reportsfiled) {
        this.reportsfiled = reportsfiled;
    }

    public List<DogReviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<DogReviews> reviews) {
        this.reviews = reviews;
    }

    public List<DogReviews> getReviews_made() {
        return reviews_made;
    }

    public void setReviews_made(List<DogReviews> reviews_made) {
        this.reviews_made = reviews_made;
    }

    public DogDetails getDetails() {
        return details;
    }

    public void setDetails(DogDetails details) {
        this.details = details;
    }
}