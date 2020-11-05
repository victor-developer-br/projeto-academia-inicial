package dev.example.clientes.repository;

import dev.example.domain.Cliente;
import dev.example.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContainingIgnoreCaseAndCpfcnpjContaining(String nome, String cpfcnpj);
    List<Cliente> findByCpfcnpjContainingAndCidadeContainingIgnoreCase(String cpfcnpj, String cidade);
    List<Cliente> findByCidadeContainingIgnoreCaseAndUfContainingIgnoreCase(String cidade, String uf);

    @Query("FROM Cliente WHERE " +
            "LOWER(nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(cpfcnpj) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(cidade) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(uf) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Cliente> findAllCriteria(@Param("searchTerm") String search);
}
