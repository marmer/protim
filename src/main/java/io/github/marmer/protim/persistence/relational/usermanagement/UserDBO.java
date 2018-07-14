package io.github.marmer.protim.persistence.relational.usermanagement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"id", "version"})
public class UserDBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
    @JoinColumn(name = "fk_user")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleDBO> roles;
    @Column
    private boolean enabled;

    @Version
    private Long version;
}
