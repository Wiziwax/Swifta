package com.rapid.swifta.Entities;

import com.rapid.swifta.UserProps.Role;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users us SET us.deleted = 1 WHERE us.user_id=?")
@Where(clause = "deleted=false")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer userId;

        @Column(nullable = false)
        @NotNull(message = "Please enter your first name")
        private String firstName;

        @Column
        private String middleName;

        @Column(nullable = false)
        @NotNull(message = "Please enter last name")
        private String lastName;

        @Column(nullable = false)
        private String username;

        @Column(nullable = false)
        @Email(message = "Email should be valid")
        private String email;

        @Column(nullable = false)
        private String password;

        @CreationTimestamp
        @Column(name = "created_at", nullable = false, updatable = false)
        private Timestamp createdDate;

        @Column
        private boolean deleted=false;

        @Column(nullable = false)
        @NotNull(message = "Please enter your mobile number")
        private String mobile;

        @Column
        private boolean isVerified;

        @Column()
        private String image;

        @OneToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "address_user_id")
        private Address address;

        @ManyToMany(fetch = EAGER)
        @JoinTable(
                name = "user_service_types",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "service_type_id")
        )
        private List<ServiceType> serviceType;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Column
        private float rating;

        @Column
        private int jobCount;

        @Column
        private int rateCount;




}
//        @OneToMany(mappedBy = "user")
//        private List<Token> tokens;
