package locadora.demo.veiculos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByDisponivel(boolean disponivel);

    // Metodo para buscar veículo pela placa
    Optional<Veiculo> findByPlaca(String placa);
}
