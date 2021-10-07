package net.k9connect.k9connect.models;

import javax.persistence.*;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Statuses name;


//    public static void main(String[] args) {
//        Status newStat = new Status();
//        newStat.name = Statuses.banned;
//
//        System.out.println();
//    }
}
