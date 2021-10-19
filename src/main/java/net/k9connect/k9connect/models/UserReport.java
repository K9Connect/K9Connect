package net.k9connect.k9connect.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(updatable = false)
    @Getter @Setter
    private LocalDateTime sent_date = LocalDateTime.now();

    public String getReadableDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        String S = this.sent_date.format(formatter); // formats to 09/23/2009 13:53
        return S;
    }

}
