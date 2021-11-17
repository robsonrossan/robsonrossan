package com.br.itapemirim.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor
public class RoleEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER, ADMIN, etc
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> users;

    @Override
    public String getAuthority() {
        return nome;
    }
}
