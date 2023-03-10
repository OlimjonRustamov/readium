package uz.o_rustamov.readium.files.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    byte[] bytes;

    @OneToOne
    Attachment attachment;
}
