package locadora.demo.locacao;

import locadora.demo.clientes.Cliente;
import locadora.demo.clientes.ClienteRepository;
import locadora.demo.veiculos.Veiculo;
import locadora.demo.veiculos.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @PostMapping("/locar")
    public Locacao locarVeiculo(@RequestBody LocacaoRequestDTO locacaoRequest) {
        // Use a instância `locacaoRequest` para acessar os valores dos campos
        LocalDateTime dataPrevista = LocalDateTime.parse(locacaoRequest.getDataDevolucaoPrevista()); // Corrigido

        // Utilize os métodos de instância para obter os valores de `clienteId` e `veiculoId`
        return locacaoService.locarVeiculo(
                locacaoRequest.getClienteId(),
                locacaoRequest.getVeiculoId(),
                dataPrevista
        );
    }

    @PostMapping("/devolver/{id}")
    public Locacao devolverVeiculo(@PathVariable Long id) {
        return locacaoService.devolverVeiculo(id);
    }
}