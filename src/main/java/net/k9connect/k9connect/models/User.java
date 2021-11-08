package net.k9connect.k9connect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(nullable = false)
    @Getter @Setter private String username;

    @Column(nullable = false)
    @JsonIgnore
    @Getter @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference
    @Getter @Setter private List<Dog> dogs;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Getter
    @Setter
    private Statuses status;

    @Column
    @JsonIgnore
    @Getter @Setter private boolean is_admin;

    @OneToOne
    @JoinColumn(name = "userdetails_id")
    @JsonManagedReference
    @Getter @Setter private UserInfo details;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedUser")
    @JsonIgnore
    @Getter @Setter private List<UserReport> reportsagainst;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportingUser")
    @JsonIgnore
    @Getter @Setter private List<UserReport> reportsfiled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sending_user")
    @JsonIgnore
    @Getter @Setter private List<Message> message_sent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiving_user")
    @JsonIgnore
    @Getter @Setter private List<Message> message_received;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewed")
    @JsonIgnore
    @Getter @Setter private List<UserReview> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
    @JsonIgnore
    @Getter @Setter private List<UserReview> reviews_made;

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dogs=" + dogs +
                ", status=" + status +
                ", is_admin=" + is_admin +
                ", details=" + details +
                ", reportsagainst=" + reportsagainst +
                ", reportsfiled=" + reportsfiled +
                ", message_sent=" + message_sent +
                ", message_received=" + message_received +
                ", reviews=" + reviews +
                ", reviews_made=" + reviews_made +
                '}';
    }
}
