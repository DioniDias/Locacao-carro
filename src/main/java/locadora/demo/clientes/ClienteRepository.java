package locadora.demo.clientes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca cliente pelo CPF
    Optional<Cliente> findByCpf(String cpf);

    // Busca por clientes ativos ou inativos
    List<Cliente> findByAtivo(boolean ativo);

}
