package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer favouritesId;

    @Column
    private Integer createdBy;

    @Column
    private Integer userFavourite;
}
