package locadora.demo.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // Endpoint para cadastro de veículo
    @PostMapping
    public Veiculo cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        // Converte a placa para maiúsculas antes de passar para o serviço
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
        return veiculoService.cadastrarVeiculo(veiculo);
    }

    // Endpoint para listar todos os veículos
    @GetMapping
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    // Endpoint para listar veículos disponíveis
    @GetMapping("/disponiveis")
    public List<Veiculo> listarVeiculosDisponiveis() {
        return veiculoService.listarVeiculosDisponiveis();
    }

    // Endpoint para listar veículos alugados
    @GetMapping("/alugados")
    public List<Veiculo> listarVeiculosAlugados() {
        return veiculoService.listarVeiculosAlugados();
    }
}
