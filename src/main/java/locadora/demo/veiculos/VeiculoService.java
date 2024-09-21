package locadora.demo.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Metodo para cadastrar veículo
    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        // Verifica se a placa já está cadastrada
        Optional<Veiculo> veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());
        if (veiculoExistente.isPresent()) {
            throw new RuntimeException("Veículo com a placa '" + veiculo.getPlaca() + "' já está cadastrado.");
        }

        veiculo.setDisponivel(true); // Definindo como disponível ao cadastrar
        return veiculoRepository.save(veiculo);
    }

    // Metodo para listar todos os veículos
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // Metodo para listar veículos disponíveis
    public List<Veiculo> listarVeiculosDisponiveis() {
        return veiculoRepository.findByDisponivel(true);
    }

    // Metodo para listar veículos alugados
    public List<Veiculo> listarVeiculosAlugados() {
        return veiculoRepository.findByDisponivel(false);
    }
}
