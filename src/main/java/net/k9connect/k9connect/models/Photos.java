package net.k9connect.k9connect.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="photos")
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @Column
    private String url;

    public Photos() {
    }

    public Photos(long id, Dog dog, String url) {
        this.id = id;
        this.dog = dog;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
