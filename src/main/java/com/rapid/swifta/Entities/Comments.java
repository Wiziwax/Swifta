package com.rapid.swifta.Entities;


import com.rapid.swifta.Enums.EnumRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column
    private Integer createdBy;

    @Column
    private Integer commentedUserId;

    @Column
    @CreatedDate
    private Integer createdDate;

    @Column
    private EnumRating enumRating;


}
