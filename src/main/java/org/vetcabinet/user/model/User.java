package org.vetcabinet.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.vetcabinet.address.model.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "phone_id", nullable = false,
            foreignKey = @ForeignKey(name = "USERS_PHONES_FK"))
    private List<Phone> phone;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "email_id", nullable = false,
            foreignKey = @ForeignKey(name = "USERS_EMAILS_FK"))
    private List<Email> email;

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