package uz.o_rustamov.readium.files.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String originalName;

    @Column
    long fileSize;

    @Column
    String contentType;

    @Column
    String name;
}
