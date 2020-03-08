package com.delphos.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.delphos.cursomc.domain.Categoria;
import com.delphos.cursomc.domain.Cidade;
import com.delphos.cursomc.domain.Cliente;
import com.delphos.cursomc.domain.Endereco;
import com.delphos.cursomc.domain.Estado;
import com.delphos.cursomc.domain.ItemPedido;
import com.delphos.cursomc.domain.Pagamento;
import com.delphos.cursomc.domain.PagamentoComBoleto;
import com.delphos.cursomc.domain.PagamentoComCartao;
import com.delphos.cursomc.domain.Pedido;
import com.delphos.cursomc.domain.Produto;
import com.delphos.cursomc.domain.enums.EstadoPagamento;
import com.delphos.cursomc.domain.enums.TipoCliente;
import com.delphos.cursomc.repositories.CategoriaRepository;
import com.delphos.cursomc.repositories.CidadeRepository;
import com.delphos.cursomc.repositories.ClienteRepository;
import com.delphos.cursomc.repositories.EnderecoRepository;
import com.delphos.cursomc.repositories.EstadoRepository;
import com.delphos.cursomc.repositories.ItemPedidoRepository;
import com.delphos.cursomc.repositories.PagamentoRepository;
import com.delphos.cursomc.repositories.PedidoRepository;
import com.delphos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
}
}
