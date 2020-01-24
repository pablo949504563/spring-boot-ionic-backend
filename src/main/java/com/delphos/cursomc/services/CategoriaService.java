package com.delphos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.delphos.cursomc.domain.Categoria;
import com.delphos.cursomc.dto.CategoriaDTO;
import com.delphos.cursomc.repositories.CategoriaRepository;
import com.delphos.cursomc.services.exception.DataIntegrityException;
import com.delphos.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria finder(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		finder(obj.getId());
		return repo.save(obj);
	}
	
	public void delete (Integer id) {
		finder(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e ) {
			
			throw new DataIntegrityException("Não e possivel excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> finderAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		
	return new Categoria(objDto.getId(), objDto.getNome());
	
	}
	
}
