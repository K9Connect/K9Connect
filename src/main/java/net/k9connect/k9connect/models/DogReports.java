package net.k9connect.k9connect.models;


import javax.persistence.*;

@Entity
@Table(name = "dog_reports")
public class DogReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User reportingUser;

    @OneToOne
    @JoinColumn(name = "id")
    private Dog reportedDog;

    @Column(columnDefinition = "TEXT")
    private String reportText;

    public DogReports() {
    }

    public DogReports(long id, User reportingUser, Dog reportedDog, String reportText) {
        this.id = id;
        this.reportingUser = reportingUser;
        this.reportedDog = reportedDog;
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

    public Dog getReportedDog() {
        return reportedDog;
    }

    public void setReportedDog(Dog reportedDog) {
        this.reportedDog = reportedDog;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }
}
