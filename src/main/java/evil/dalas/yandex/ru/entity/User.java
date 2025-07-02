package evil.dalas.yandex.ru.entity;

import evil.dalas.yandex.ru.database.entity.BaseEntity;
import evil.dalas.yandex.ru.database.entity.Company;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "userChats")
@EqualsAndHashCode(of = "username")
@NamedEntityGraph(
        name = "User.company",
        attributeNodes = @NamedAttributeNode("company"))
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "users")
@Entity
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotAudited
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats=new ArrayList<>();
}
