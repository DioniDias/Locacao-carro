package locadora.demo.locacao;

import locadora.demo.clientes.Cliente;
import locadora.demo.clientes.ClienteRepository;
import locadora.demo.veiculos.Veiculo;
import locadora.demo.veiculos.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Locacao locarVeiculo(Long clienteId, Long veiculoId, LocalDateTime dataDevolucaoPrevista) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        if (!cliente.isAtivo()) {
            throw new IllegalArgumentException("Cliente inativo, não pode locar veículos.");
        }

        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));

        if (!veiculo.getDisponivel()) {
            throw new IllegalArgumentException("Veículo não está disponível para locação.");
        }

        veiculo.setDisponivel(false);
        veiculoRepository.save(veiculo);

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setDataLocacao(LocalDateTime.now());
        locacao.setDataDevolucaoPrevista(dataDevolucaoPrevista);

        return locacaoRepository.save(locacao);
    }

    public Locacao devolverVeiculo(Long locacaoId) {

        Locacao locacao = locacaoRepository.findById(locacaoId)
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada"));

        locacao.setDataDevolucaoReal(LocalDateTime.now());
        locacao.calcularValorTotal(); // Calcula o valor total com juros, se houver
        Veiculo veiculo = locacao.getVeiculo();
        veiculo.setDisponivel(true);
        veiculoRepository.save(veiculo);

        return locacaoRepository.save(locacao);
    }
}
