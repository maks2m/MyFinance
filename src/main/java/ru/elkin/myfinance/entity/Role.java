package ru.elkin.myfinance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
@NamedQuery(
        name = "find_all_roles",
        query = "SELECT r FROM Role r"
)
@NamedEntityGraph(
        name = "get_users_by_role",
        attributeNodes = @NamedAttributeNode("users"))
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_text", unique = true, nullable = false)
    private String role;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "buffer_role_fk")),
            inverseJoinColumns = @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "buffer_user_fk"))
    )
    @ToString.Exclude
    private Set<User> users;

    @Override
    public String getAuthority() {
        return role;
    }
}
