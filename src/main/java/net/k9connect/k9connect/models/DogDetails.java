package net.k9connect.k9connect.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dogs_details")
@NoArgsConstructor
@AllArgsConstructor
public class DogDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    @Getter
    @Setter
    private Dog dog;

    @Column(nullable = false)
    @Getter
    @Setter
    private int age;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean has_certs;

    @Column(length = 500)
    @Getter
    @Setter
    private String cert_url;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String bio;

    @Override
    public String toString() {
        return "DogDetails{" +
                "id=" + id +
                ", dog=" + dog +
                ", age=" + age +
                ", has_certs=" + has_certs +
                ", cert_url='" + cert_url + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}