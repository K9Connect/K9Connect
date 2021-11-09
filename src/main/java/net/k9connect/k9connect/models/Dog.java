package net.k9connect.k9connect.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dogs")
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private String breed;

    @Column(nullable = false)
    @Getter @Setter private char gender;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    @Getter @Setter private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    @JsonManagedReference
    @Getter @Setter private List<Photo> photos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedDog")
    @JsonIgnore
    @Getter @Setter private List<DogReport> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    @JsonIgnore
    @Getter @Setter private List<DogReview> reviews;

    @Column
    @Getter @Setter private int reputation;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Getter
    @Setter
    private Statuses status;

    @OneToOne
    @JoinColumn(name = "dog_details_id")
    @JsonManagedReference
    @Getter @Setter private DogDetails details;

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", gender=" + gender +
                ", owner=" + owner +
                ", photos=" + photos +
                ", reports=" + reports +
                ", reviews=" + reviews +
                ", reputation=" + reputation +
                ", status=" + status +
                ", details=" + details +
                '}';
    }
}