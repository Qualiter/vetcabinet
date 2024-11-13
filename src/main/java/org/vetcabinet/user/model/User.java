package org.vetcabinet.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;

    private String surname;

    private String patronymic;

    private String phone;

    private String[] additionalPhones;

    private String email;

    private LocalDate birthday;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "USERS_ROLES_FK"))
    private Role role;

    @OneToMany
    @JoinColumn(name = "user_UUID")
    private List<Address> addresses;
}