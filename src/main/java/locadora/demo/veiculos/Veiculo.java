package locadora.demo.veiculos;

import locadora.demo.helpers.EntityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "veiculo")
public class Veiculo extends EntityId {

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "placa", unique = true, nullable = false)
    private String placa;

    @Column(name = "valor_diaria", nullable = false)
    private Double valorDiaria;

    @Column(name = "disponivel")
    private Boolean disponivel;
}
