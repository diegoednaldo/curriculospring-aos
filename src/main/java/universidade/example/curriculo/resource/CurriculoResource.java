package universidade.example.curriculo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import universidade.example.curriculo.domain.entity.Curriculo;
import universidade.example.curriculo.repository.CurriculoRepository;

@RestController
@RequestMapping("/curriculos")
public class CurriculoResource {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @GetMapping
    public List<Curriculo> getAllCurriculos() {
        return curriculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> getCurriculoById(@PathVariable Long id) {
        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(id);

        return curriculoOptional.map(curriculo -> ResponseEntity.ok().body(curriculo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curriculo> createCurriculo(@RequestBody Curriculo curriculo) {
        Curriculo savedCurriculo = curriculoRepository.save(curriculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurriculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> updateCurriculo(@PathVariable Long id, @RequestBody Curriculo curriculo) {
        Optional<Curriculo> existingCurriculoOptional = curriculoRepository.findById(id);

        if (existingCurriculoOptional.isPresent()) {
            Curriculo existingCurriculo = existingCurriculoOptional.get();
            existingCurriculo.setNome(curriculo.getNome());
            existingCurriculo.setExperiencias(curriculo.getExperiencias());
            existingCurriculo.setEducacoes(curriculo.getEducacoes());
            // Atualize outras propriedades conforme necessário
            Curriculo updatedCurriculo = curriculoRepository.save(existingCurriculo);
            return ResponseEntity.ok().body(updatedCurriculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculo(@PathVariable Long id) {
        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(id);

        if (curriculoOptional.isPresent()) {
            curriculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

