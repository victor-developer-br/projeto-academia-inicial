package dev.example.clientes.repository;

import dev.example.domain.Cliente;
import dev.example.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome);
    List<Cliente> findByEmailContaining(String email);
 //   List<Cliente> findByNomeAndcpf_CnpjContaining(String nome, String cpfcnpj);
}
