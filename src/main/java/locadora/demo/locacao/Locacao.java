package locadora.demo.locacao;

import locadora.demo.helpers.EntityId;
import locadora.demo.clientes.Cliente;
import locadora.demo.veiculos.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "locacao")
public class Locacao extends EntityId {

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_locacao", nullable = false)
    private LocalDateTime dataLocacao;

    @Column(name = "data_devolucao_prevista", nullable = false)
    private LocalDateTime dataDevolucaoPrevista;

    @Column(name = "data_devolucao_real")
    private LocalDateTime dataDevolucaoReal;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Transient
    private static final double JUROS_POR_DIA_ATRASO = 0.02; // 2% por dia de atraso

    public void calcularValorTotal() {
        double valorInicial = veiculo.getValorDiaria() * Duration.between(dataLocacao, dataDevolucaoPrevista).toDays();

        if (dataDevolucaoReal != null && dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = Duration.between(dataDevolucaoPrevista, dataDevolucaoReal).toDays();
            double juros = valorInicial * JUROS_POR_DIA_ATRASO * diasAtraso;
            valorTotal = valorInicial + juros;
        } else {
            valorTotal = valorInicial;
        }
    }
}