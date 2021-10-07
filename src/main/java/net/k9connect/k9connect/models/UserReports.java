package net.k9connect.k9connect.models;


import javax.persistence.*;

@Entity
@Table(name = "user_reports")
public class UserReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reporting_id")
    private User reportingUser;

    @ManyToOne
    @JoinColumn(name = "reported_id")
    private User reportedUser;

    @Column(columnDefinition = "TEXT")
    private String reportText;

    public UserReports() {
    }

    public UserReports(long id, User reportingUser, User reportedUser, String reportText) {
        this.id = id;
        this.reportingUser = reportingUser;
        this.reportedUser = reportedUser;
        this.reportText = reportText;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(User reportingUser) {
        this.reportingUser = reportingUser;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}