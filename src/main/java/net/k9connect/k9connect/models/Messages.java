package net.k9connect.k9connect.models;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sending_id")
    private User sending_user;

    @ManyToOne
    @JoinColumn(name = "receiving_id")
    private User receiving_user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private boolean reported;

    public Messages(long id, User sending_user, User receiving_user, String content, boolean reported) {
        this.id = id;
        this.sending_user = sending_user;
        this.receiving_user = receiving_user;
        this.content = content;
        this.reported = reported;
    }

    public Messages() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSending_user() {
        return sending_user;
    }

    public void setSending_user(User sending_user) {
        this.sending_user = sending_user;
    }

    public User getReceiving_user() {
        return receiving_user;
    }

    public void setReceiving_user(User receiving_user) {
        this.receiving_user = receiving_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }
}

