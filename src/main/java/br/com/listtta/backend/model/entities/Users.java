package br.com.listtta.backend.model.entities;

import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "userId")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String puid;

    @Column(name = "user_tag")
    private String userTag;

    @Column(name = "full_name")
    private String fullName;

//    @Lob
//    @Column(name = "profile_picture")
//    private byte[] profilePicture;
    private String email;
    private String password;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsapp_contact")
    private String whatsappContact;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private UserGender userGender;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PROFESSIONAL"), new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.role == UserRoles.PROFESSIONAL) {return List.of(new SimpleGrantedAuthority("ROLE_PROFESSIONAL"), new SimpleGrantedAuthority("ROLE_USER"));}
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
