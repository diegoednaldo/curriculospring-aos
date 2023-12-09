package universidade.example.curriculo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import universidade.example.curriculo.domain.dto.CurriculoDTO;
import universidade.example.curriculo.domain.dto.InformacoesDTO;
import universidade.example.curriculo.domain.dto.ExpProfissionalDTO;
import universidade.example.curriculo.domain.entity.Curriculo;
import universidade.example.curriculo.domain.entity.Informacoes;
import universidade.example.curriculo.domain.entity.ExpProfissional;
import universidade.example.curriculo.repository.CurriculoRepository;
import universidade.example.curriculo.repository.InformacoesRepository;
import universidade.example.curriculo.repository.ExpProfissionalRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurriculoServiceImpl implements CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private ExpProfissionalRepository experienciaRepository;

    @Autowired
    private InformacoesRepository educacaoRepository;

    @Override
    public List<CurriculoDTO> getAllCurriculos() {
        return curriculoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurriculoDTO> getCurriculoById(Long id) {
        return curriculoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public CurriculoDTO createCurriculo(CurriculoDTO curriculoDTO) {
        Curriculo curriculo = convertToEntity(curriculoDTO);
        Curriculo savedCurriculo = curriculoRepository.save(curriculo);
        return convertToDTO(savedCurriculo);
    }

    @Override
    public Optional<CurriculoDTO> updateCurriculo(Long id, CurriculoDTO curriculoDTO) {
        Optional<Curriculo> existingCurriculoOptional = curriculoRepository.findById(id);

        if (existingCurriculoOptional.isPresent()) {
            Curriculo existingCurriculo = existingCurriculoOptional.get();
            existingCurriculo.setNome(curriculoDTO.getNome());

            Curriculo updatedCurriculo = curriculoRepository.save(existingCurriculo);
            return Optional.of(convertToDTO(updatedCurriculo));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteCurriculo(Long id) {
        curriculoRepository.deleteById(id);
    }

    private CurriculoDTO convertToDTO(Curriculo curriculo) {
        CurriculoDTO curriculoDTO = new CurriculoDTO();
        curriculoDTO.setId(curriculo.getId());
        curriculoDTO.setNome(curriculo.getNome());

        List<ExpProfissionalDTO> experienciaDTOList = curriculo.getExperiencias().stream()
                .map(this::convertExperienciaToDTO)
                .collect(Collectors.toList());
        curriculoDTO.setExperiencias(experienciaDTOList);

        List<InformacoesDTO> educacaoDTOList = curriculo.getEducacoes().stream()
                .map(this::convertEducacaoToDTO)
                .collect(Collectors.toList());
        curriculoDTO.setEducacoes(educacaoDTOList);



        return curriculoDTO;
    }

    private ExpProfissionalDTO convertExperienciaToDTO(ExpProfissional experiencia) {
        ExpProfissionalDTO experienciaDTO = new ExpProfissionalDTO();
        experienciaDTO.setId(experiencia.getId());
        experienciaDTO.setCargo(experiencia.getCargo());
        experienciaDTO.setEmpresa(experiencia.getEmpresa());
        experienciaDTO.setDataInicio(experiencia.getDataInicio());
        experienciaDTO.setDataFim(experiencia.getDataFim());

        return experienciaDTO;
    }

    private InformacoesDTO convertEducacaoToDTO(Informacoes educacao) {
        InformacoesDTO educacaoDTO = new InformacoesDTO();
        educacaoDTO.setId(educacao.getId());
        educacaoDTO.setInstituicao(educacao.getInstituicao());
        educacaoDTO.setCurso(educacao.getCurso());
        educacaoDTO.setDataInicio(educacao.getDataInicio());
        educacaoDTO.setDataFim(educacao.getDataFim());

        return educacaoDTO;
    }

    private Curriculo convertToEntity(CurriculoDTO curriculoDTO) {
        Curriculo curriculo = new Curriculo();
        curriculo.setId(curriculoDTO.getId());
        curriculo.setNome(curriculoDTO.getNome());

        List<ExpProfissional> experienciaList = curriculoDTO.getExperiencias().stream()
                .map(this::convertExperienciaDTOToEntity)
                .collect(Collectors.toList());
        curriculo.setExperiencias(experienciaList);

        List<Informacoes> educacaoList = curriculoDTO.getEducacoes().stream()
                .map(this::convertEducacaoDTOToEntity)
                .collect(Collectors.toList());
        curriculo.setEducacoes(educacaoList);


        return curriculo;
    }

    private ExpProfissional convertExperienciaDTOToEntity(ExpProfissionalDTO experienciaDTO) {
        ExpProfissional experiencia = new ExpProfissional();
        experiencia.setId(experienciaDTO.getId());
        experiencia.setCargo(experienciaDTO.getCargo());
        experiencia.setEmpresa(experienciaDTO.getEmpresa());
        experiencia.setDataInicio(experienciaDTO.getDataInicio());
        experiencia.setDataFim(experienciaDTO.getDataFim());

        return experiencia;
    }

    private Informacoes convertEducacaoDTOToEntity(InformacoesDTO educacaoDTO) {
        Informacoes educacao = new Informacoes();
        educacao.setId(educacaoDTO.getId());
        educacao.setInstituicao(educacaoDTO.getInstituicao());
        educacao.setCurso(educacaoDTO.getCurso());
        educacao.setDataInicio(educacaoDTO.getDataInicio());
        educacao.setDataFim(educacaoDTO.getDataFim());

        return educacao;
    }
}