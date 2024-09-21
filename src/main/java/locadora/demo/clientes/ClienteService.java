package locadora.demo.clientes;
import locadora.demo.clientes.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Metodo para cadastrar um novo cliente com validação de CPF
    public String cadastrarCliente(Cliente cliente) {
        // Verifica se o CPF já está cadastrado
        if (cpfJaCadastrado(cliente.getCpf())) {
            throw new RuntimeException("Erro: CPF já cadastrado.");
        }

        // Define como ativo e configura data de cadastro
        cliente.setAtivo(true);
        cliente.setDataCadastro(LocalDate.now());

        // Salva o cliente no repositório
        clienteRepository.save(cliente);

        return "Cliente cadastrado com sucesso!";
    }

    // Verificar se um cliente já existe pelo CPF
    public boolean cpfJaCadastrado(String cpf) {
        return clienteRepository.findByCpf(cpf).isPresent();
    }

    // Outros métodos de lógica de negócios, como desativar cliente, listar clientes etc.
    public void alterarStatusCliente(Long id, boolean ativo) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(ativo);
        clienteRepository.save(cliente);
    }

    // Metodo para listar todos os clientes (opcional)
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Listar apenas clientes ativos
    public List<Cliente> listarClientesAtivos() {
        return clienteRepository.findByAtivo(true);
    }

    // Listar apenas clientes inativos
    public List<Cliente> listarClientesInativos() {
        return clienteRepository.findByAtivo(false);
    }

    // Verificar se um cliente já existe pelo CPF
    public boolean clienteJaExiste(String cpf) {
        return clienteRepository.findByCpf(cpf).isPresent();
    }


}
