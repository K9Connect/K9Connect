package net.k9connect.k9connect.models;


import javax.persistence.*;

@Entity
@Table(name = "dogs_details")
public class DogDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Dog dog;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean has_certs;

    @Column(length = 500)
    private String cert_url;

    @Column(columnDefinition = "TEXT")
    private String bio;

    public DogDetails() {
    }

    public DogDetails(Dog dog, int age, boolean has_certs, String cert_url, String bio) {
        this.dog = dog;
        this.age = age;
        this.has_certs = has_certs;
        this.cert_url = cert_url;
        this.bio = bio;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHas_certs() {
        return has_certs;
    }

    public void setHas_certs(boolean has_certs) {
        this.has_certs = has_certs;
    }

    public String getCert_url() {
        return cert_url;
    }

    public void setCert_url(String cert_url) {
        this.cert_url = cert_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
