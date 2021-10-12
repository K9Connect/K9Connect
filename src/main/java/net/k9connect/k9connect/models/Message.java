package net.k9connect.k9connect.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="messages")
public class Message implements Comparable<Message> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name = "sending_user_id")
    @Getter
    @Setter
    public User sending_user;

    @ManyToOne
    @JoinColumn(name = "receiving_user_id")
    @Getter
    @Setter
    public User receiving_user;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String content;

    @Column(updatable = false)
    @Getter @Setter
    private LocalDateTime sent_date = LocalDateTime.now();

    @Column
    @Getter @Setter private boolean reported;

    @Override
    public int compareTo(Message o) {
        return this.sent_date.compareTo(o.getSent_date());
    }

    public String getReadableDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        String S = this.sent_date.format(formatter); // formats to 09/23/2009 13:53
        return S;
    }
}

