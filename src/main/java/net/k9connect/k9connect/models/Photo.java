package net.k9connect.k9connect.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="photos")
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    @Getter @Setter private Dog dog;

    @Column()
    @Getter @Setter private String url;


    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", dog=" + dog +
                ", url='" + url + '\'' +
                '}';
    }
}