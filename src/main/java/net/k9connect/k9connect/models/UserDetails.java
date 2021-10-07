package net.k9connect.k9connect.models;

import javax.persistence.*;

@Entity
@Table(name = "users_details")
public class UserDetails {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column
    private long zipcode;

    @Column(length = 500)
    private String pfp;

    @Column
    private String phone_number;

    public UserDetails() {
    }

    public UserDetails(User user, String bio, long zipcode, String pfp, String phone_number) {
        this.user = user;
        this.bio = bio;
        this.zipcode = zipcode;
        this.pfp = pfp;
        this.phone_number = phone_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
