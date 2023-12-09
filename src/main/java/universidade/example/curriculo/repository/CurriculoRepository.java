package universidade.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import universidade.example.curriculo.domain.entity.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
}