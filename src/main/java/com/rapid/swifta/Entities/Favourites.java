package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favouritesId;
    @Column
    private Integer createdBy;
    @Column
    private Integer userFavourite;
}
