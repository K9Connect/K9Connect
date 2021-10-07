package net.k9connect.k9connect.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Dog> dogs;

    @Column
    private int status_id;

    @Column
    private boolean is_admin;

    @Column
    private boolean verified;

    @Column
    private String verification_code;

    @OneToOne
    @JoinColumn(name = "user")
    private UserDetails details;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportedUser")
    private List<UserReports> reportsagainst;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportingUser")
    private List<UserReports> reportsfiled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sending_user")
    private List<Messages> messages_sent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiving_user")
    private List<Messages> messages_received;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserReviews> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commenter")
    private List<UserReviews> reviews_made;


    public User() {
    }

    public User(long id, String username, String password, String email, List<Dog> dogs, int status_id, boolean is_admin, boolean verified, String verification_code, UserDetails details, List<UserReports> reportsagainst, List<UserReports> reportsfiled, List<Messages> messages_sent, List<Messages> messages_received, List<UserReviews> reviews, List<UserReviews> reviews_made) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dogs = dogs;
        this.status_id = status_id;
        this.is_admin = is_admin;
        this.verified = verified;
        this.verification_code = verification_code;
        this.details = details;
        this.reportsagainst = reportsagainst;
        this.reportsfiled = reportsfiled;
        this.messages_sent = messages_sent;
        this.messages_received = messages_received;
        this.reviews = reviews;
        this.reviews_made = reviews_made;
    }

    public User(String username, String password, String email, List<Dog> dogs, int status_id, boolean is_admin, boolean verified, String verification_code, UserDetails details, List<UserReports> reportsagainst, List<UserReports> reportsfiled, List<Messages> messages_sent, List<Messages> messages_received, List<UserReviews> reviews, List<UserReviews> reviews_made) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dogs = dogs;
        this.status_id = status_id;
        this.is_admin = is_admin;
        this.verified = verified;
        this.verification_code = verification_code;
        this.details = details;
        this.reportsagainst = reportsagainst;
        this.reportsfiled = reportsfiled;
        this.messages_sent = messages_sent;
        this.messages_received = messages_received;
        this.reviews = reviews;
        this.reviews_made = reviews_made;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public List<UserReports> getReportsagainst() {
        return reportsagainst;
    }

    public void setReportsagainst(List<UserReports> reportsagainst) {
        this.reportsagainst = reportsagainst;
    }

    public List<UserReports> getReportsfiled() {
        return reportsfiled;
    }

    public void setReportsfiled(List<UserReports> reportsfiled) {
        this.reportsfiled = reportsfiled;
    }
}
