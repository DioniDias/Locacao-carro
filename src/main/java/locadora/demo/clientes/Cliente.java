package locadora.demo.clientes;

import locadora.demo.helpers.EntityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cliente")
public class Cliente extends EntityId {

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_aniversario")
    private LocalDate dataAniversario;

    @Column(name = "credito", precision = 10, scale = 2)
    private BigDecimal credito;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "ativo")
    private boolean ativo;
}
