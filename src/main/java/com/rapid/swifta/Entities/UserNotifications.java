package com.rapid.swifta.Entities;

import com.rapid.swifta.Enums.EnumNotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNotifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @Column
    private Integer userId;

    @Column
    private String notificationBody;

    @Column
    @CreatedDate
    private final Date createdDate=new Date();

    @Column
    private Integer orderNumber;

    @Column
    private Integer createdBy;

    @Column
    private boolean isRead;
}
