package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kafi.beautysalonapicommon.enums.Gender;

import java.sql.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;
    @Column(name = "last_name", length = 32)
    private String lastName;
    @Column(name = "middle_name", length = 32)
    private String middleName;
    @Column(name = "email", length = 254, unique = true)
    private String email;
    @Column(name = "gender", length = 12)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "telegram_id", unique = true)
    private Long telegramId;
    @JoinColumn(name = "position_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;
    @JoinColumn(name = "avatar_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Image avatar;
}
