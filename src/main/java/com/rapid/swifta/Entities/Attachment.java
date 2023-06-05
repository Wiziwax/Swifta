package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attachmentId;

    @Column
    private String attachmentLink;


}
