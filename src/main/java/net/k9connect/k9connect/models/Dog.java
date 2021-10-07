package net.k9connect.k9connect.models;


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
    @Getter @Setter private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    @Getter @Setter private List<Photo> photos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedDog")
    @Getter @Setter private List<DogReport> reportsagainst;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportingUser")
    @Getter @Setter private List<DogReport> reportsfiled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    @Getter @Setter private List<DogReview> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commenter")
    @Getter @Setter private List<DogReview> reviews_made;

    @Column
    @Getter @Setter private int reputation;

    @OneToOne
    @JoinColumn(name = "status_id")
    @Getter @Setter private Status status;

    @OneToOne
    @JoinColumn(name = "dog_details_id")
    @Getter @Setter private DogDetails details;

   }