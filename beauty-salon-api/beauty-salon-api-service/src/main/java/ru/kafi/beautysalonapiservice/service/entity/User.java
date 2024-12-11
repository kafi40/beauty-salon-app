package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kafi.beautysalonapicommon.enums.Gender;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
