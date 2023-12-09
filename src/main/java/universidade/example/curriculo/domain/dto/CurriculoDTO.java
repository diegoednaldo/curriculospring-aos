package universidade.example.curriculo.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CurriculoDTO {

    private Long id;
    private String nome;
    private List<ExpProfissionalDTO> experiencias;
    private List<InformacoesDTO> educacoes;
}
