package locadora.demo.locacao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocacaoRequestDTO {

    private Long clienteId;
    private Long veiculoId;
    private String dataDevolucaoPrevista;

}