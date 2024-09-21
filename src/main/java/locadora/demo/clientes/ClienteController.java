package locadora.demo.clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para cadastro de cliente
    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            // Chama o serviço para cadastrar o cliente
            String mensagem = clienteService.cadastrarCliente(cliente);
            return ResponseEntity.ok(mensagem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para listar todos os clientes (opcional)
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/alterar-status/{id}")
    public ResponseEntity<String> alterarStatusCliente(@PathVariable Long id,
                                                       @RequestParam boolean ativo) {
        try {
            clienteService.alterarStatusCliente(id, ativo);
            String status = ativo ? "ativado" : "desativado";
            return ResponseEntity.ok("Cliente " + status + " com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar todos os clientes
    @GetMapping("/todos")
    public List<Cliente> listarTodosClientes() {
        return clienteService.listarClientes();
    }

    // Listar apenas clientes ativos
    @GetMapping("/ativos")
    public List<Cliente> listarClientesAtivos() {
        return clienteService.listarClientesAtivos();
    }

    // Listar apenas clientes inativos
    @GetMapping("/inativos")
    public List<Cliente> listarClientesInativos() {
        return clienteService.listarClientesInativos();
    }
}