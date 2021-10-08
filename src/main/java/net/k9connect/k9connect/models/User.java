package net.k9connect.k9connect.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(nullable = false)
    @Getter @Setter private String username;

    @Column(nullable = false)
    @Getter @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @Getter @Setter private List<Dog> dogs;

    @OneToOne
    @JoinColumn(name = "status_id")
    @Getter @Setter private Status status;

    @Column
    @Getter @Setter private boolean is_admin;

    @OneToOne
    @JoinColumn(name = "userdetails_id")
    @Getter @Setter private UserInfo details;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedUser")
    @Getter @Setter private List<UserReport> reportsagainst;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportingUser")
    @Getter @Setter private List<UserReport> reportsfiled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sending_user")
    @Getter @Setter private List<Message> message_sent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiving_user")
    @Getter @Setter private List<Message> message_received;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewed")
    @Getter @Setter private List<UserReview> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
    @Getter @Setter private List<UserReview> reviews_made;

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

}
