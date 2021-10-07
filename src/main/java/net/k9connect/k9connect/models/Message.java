package net.k9connect.k9connect.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sending_user_id")
    @Getter
    @Setter
    private User sending_user;

    @ManyToOne
    @JoinColumn(name = "receiving_user_id")
    @Getter
    @Setter
    private User receiving_user;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String content;

    @Column
    @Getter @Setter private boolean reported;
}

