package net.k9connect.k9connect.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_details")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Getter @Setter private long id;

    @OneToOne(mappedBy = "details")
    @JsonBackReference
    @Getter @Setter private User user;

    @Column(columnDefinition = "TEXT")
    @JsonIgnore
    @Getter @Setter private String bio;

    @Column
    @Getter @Setter private long zipcode;

    @Column(length = 500)
    @JsonIgnore
    @Getter @Setter private String pfp;

    @Column
    @JsonIgnore
    @Getter @Setter private String phone_number;

}
