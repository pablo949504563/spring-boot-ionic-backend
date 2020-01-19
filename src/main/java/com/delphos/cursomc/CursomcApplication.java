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
import com.delphos.cursomc.repositories.PagamentoRepository;
import com.delphos.cursomc.repositories.PedidoRepository;
import com.delphos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository  pagamentoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Administração");
		Categoria cat2 = new Categoria(null, "Departamento Pessoal");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 60.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Agenor Barbosa", "agenorbsn853@gmail.com", "35016207801", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("11949504563", "11965313357"));
		
		Endereco e1 = new Endereco(null, "Rua Sergipe", "22", "Bloco a","Centro", "45725-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Bloco b","Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("19/01/2020 01:24"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/01/2020 10:24"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDETE, ped2, sdf.parse("03/01/2020 11:24"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

}
}
