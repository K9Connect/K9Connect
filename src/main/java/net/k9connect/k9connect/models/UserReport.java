package net.k9connect.k9connect.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_reports")
@NoArgsConstructor
@AllArgsConstructor
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reporting_user_id")
    @Getter @Setter private User reportingUser;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    @Getter @Setter private User reportedUser;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String reportText;

}
