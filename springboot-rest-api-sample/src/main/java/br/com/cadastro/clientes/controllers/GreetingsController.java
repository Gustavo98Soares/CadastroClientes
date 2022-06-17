package br.com.cadastro.clientes.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.clientes.model.Cliente;
import br.com.cadastro.clientes.repository.ClienteRepository;

@RestController
public class GreetingsController {

	@Autowired
	private ClienteRepository clienteRepository;

	// -------------------------------------------Listar todos os clientes-----------------------------------------------------
	@GetMapping(value = "listatodos")
	@ResponseBody() /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Cliente>> listaCliente() {

		List<Cliente> cliente = clienteRepository.findAll();/* Retorna um JSON */

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}

	// ---------------------------------------------------Salvar----------------------------------------------------------------
	@PostMapping(value = "salvar") /* Mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente)/* Recebe os dados para salvar */ {

		Cliente client = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(client, HttpStatus.CREATED);
	}

	// --------------------------------------------------Deletar----------------------------------------------------------------
	@DeleteMapping(value = "delete") /* Mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<String> delete(@RequestParam Long idLong)/* Recebe os dados para salvar */ {

		clienteRepository.deleteById(idLong);
		return new ResponseEntity<String>("Cliente Deletado com Sucesso", HttpStatus.OK);
	}

	// ----------------------------------------------Busca Id-----------------------------------------------------------------

	@GetMapping(value = "buscaclienteid") /* Mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Cliente> buscaclienteid(@RequestParam(name = "idLong") Long idLong){/* Recebe os dados para salvar */ 

		Cliente cliente = clienteRepository.findById(idLong).get();

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	//------------------------------------------------Atualizar----------------------------------------------------------------
	@PutMapping(value = "atualizar") /* Mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente)/* Recebe os dados para salvar */ {

		Cliente client = clienteRepository.saveAndFlush(cliente);
		return new ResponseEntity<Cliente>(client, HttpStatus.OK);
	}
	
	//------------------------------------------------Buscar por Nome---------------------------------------------------------
	@GetMapping(value = "buscaPorNome") /* Mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<List<Cliente>> buscaPorNome(@RequestParam(name = "name") String name){/* Recebe os dados para salvar */ 

		List<Cliente> cliente = clienteRepository.buscaPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}

}
