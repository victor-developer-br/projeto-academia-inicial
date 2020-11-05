package dev.example.livrocaixa.repository;

import dev.example.domain.LivroCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LivroCaixaRepository extends JpaRepository<LivroCaixa, Integer> {

    List<LivroCaixa> findByClienteId(Integer id);
    List<LivroCaixa> findByDataLancamentoBetweenAndClienteId(Date start, Date end, Integer id);

}
