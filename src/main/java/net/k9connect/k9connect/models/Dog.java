package net.k9connect.k9connect.models;


import javax.persistence.*;

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

    @Column
    private int reputation;

    @Column
    private int status_id;

    public Dog() {
    }

    public Dog(String name, String breed, char gender, User owner, int reputation, int status_id) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.owner = owner;
        this.reputation = reputation;
        this.status_id = status_id;
    }

    public Dog(long id, String name, String breed, char gender, User owner, int reputation, int status_id) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.owner = owner;
        this.reputation = reputation;
        this.status_id = status_id;
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
}