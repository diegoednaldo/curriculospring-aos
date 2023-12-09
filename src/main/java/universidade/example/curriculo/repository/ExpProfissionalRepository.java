package universidade.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import universidade.example.curriculo.domain.entity.ExpProfissional;

public interface ExpProfissionalRepository extends JpaRepository<ExpProfissional, Long> {
}