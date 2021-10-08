package net.k9connect.k9connect.models;

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
    @Getter @Setter private long id;

    @OneToOne(mappedBy = "details")
    @Getter @Setter private User user;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String bio;

    @Column
    @Getter @Setter private long zipcode;

    @Column(length = 500)
    @Getter @Setter private String pfp;

    @Column
    @Getter @Setter private String phone_number;

}
