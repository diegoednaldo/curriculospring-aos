package universidade.example.curriculo.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class InformacoesDTO {

    private Long id;
    private String instituicao;
    private String curso;
    private Date dataInicio;
    private Date dataFim;
}
