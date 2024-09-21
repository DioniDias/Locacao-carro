package locadora.demo.helpers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}