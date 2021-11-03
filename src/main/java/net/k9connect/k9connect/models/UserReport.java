package net.k9connect.k9connect.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_reports")
@NoArgsConstructor
@AllArgsConstructor
public class UserReport implements Comparable<UserReport>{
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

    @Column(updatable = false)
    @Getter @Setter
    private LocalDateTime report_date = LocalDateTime.now();

    @Override
    public int compareTo(UserReport o) {
        return this.report_date.compareTo(o.getReport_date());
    }

}
