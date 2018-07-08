package io.github.marmer.protim.persistence.relational.usermanagement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "user")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleDBO> roles;
    @Column
    private boolean enabled;

    @Version
    private Long version;

    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
