package dev.example.services;

import dev.example.Utils.Utilities;
import dev.example.clientes.repository.ClienteRepository;
import dev.example.domain.Cliente;
import dev.example.domain.LivroCaixa;
import dev.example.domain.Usuario;
import dev.example.livrocaixa.repository.LivroCaixaRepository;
import dev.example.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroCaixaRepository livroCaixaRepository;

    public void iniciarConstrucaoDB()
    {
        //inserção na tabela usuario

        Usuario usu1 = new Usuario(null, Utilities.ConvertDate("10/10/2020"), "Thiago Alcantara Gonçalves", "thithi", "123456",
                "81944765212", "thiagoalcantara@gmail.com", "A", "A");

        Usuario usu2 = new Usuario(null, Utilities.ConvertDate("13/10/2020"), "Patricia Marques França", "paty2020", "65432",
                "81984865212", "paty2020@gmail.com", "O", "A");

        Usuario usu3 = new Usuario(null, Utilities.ConvertDate("13/10/2020"), "Wagner Silva e Souza", "waguinho", "321654",
                "8134765542", "paty1999@gmail.com", "O", "C");

        usuarioRepository.saveAll(Arrays.asList(usu1, usu2, usu3));

        //inserção na tabela clientes
        Cliente cli1 = new Cliente(null, Utilities.ConvertDate("30/08/2020"), "Melânio Albuquerque", "06432730007",
                "Rua Alfredo Lisboa", "Carpina", "SP", "54932121", "81978795732", "AlfAlb@gmail.com");

        //criar livro caixa

        LivroCaixa cli1Livro1 = new LivroCaixa(null, Utilities.ConvertDate("02/09/2020"), "Confecção de Roupas", "C", 500.00);
        cli1Livro1.setCliente(cli1);
        LivroCaixa cli1livro2 = new LivroCaixa(null, Utilities.ConvertDate("06/09/2020"), "Aluguel de Carro", "D", 100.00);
        cli1livro2.setCliente(cli1);
        LivroCaixa cli1livro3 = new LivroCaixa(null, Utilities.ConvertDate("03/10/2020"), "Compra de Pneu Carro", "D", 150.00);
        cli1livro3.setCliente(cli1);


        Cliente cli2 = new Cliente(null, Utilities.ConvertDate("02/09/2020"), "Carlos Prates Filho", "06932830807",
                "Rua Padre Jenova", "Cordeiro", "PE", "54532321", "81978792342", "Carlos@gmail.com");

        //criar livro caixa

        LivroCaixa cli2Livro1 = new LivroCaixa(null, Utilities.ConvertDate("10/10/2020"), "Deposito Industrias JB", "C", 1800.00);
        cli2Livro1.setCliente(cli2);
        LivroCaixa cli2livro2 = new LivroCaixa(null, Utilities.ConvertDate("15/10/2020"), "Aluguel de Muck", "D", 250.00);
        cli2livro2.setCliente(cli2);

        Cliente cli3 = new Cliente(null, Utilities.ConvertDate("05/09/2020"), "Fernanda Miranda Costa", "06734336707",
                "Rua Quarenta e Oito", "Recife", "RN", "54872221", "81978795544", "fecosta@gmail.com");

        //criar livro caixa

        LivroCaixa cli3Livro1 = new LivroCaixa(null, Utilities.ConvertDate("15/10/2020"), "Emprestimo Crefisa", "C", 15000.00);
        cli3Livro1.setCliente(cli3);

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));

        livroCaixaRepository.saveAll(Arrays.asList(cli1Livro1, cli1livro2, cli1livro3,
                cli2Livro1, cli2livro2, cli3Livro1));

    }


}
