package universidade.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import universidade.example.curriculo.domain.entity.Informacoes;

public interface InformacoesRepository extends JpaRepository<Informacoes, Long> {
}