package br.com.springboot.curso_jdev_treinamento.controllers;

//import java.awt.List;
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
import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRespository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*/ic/CD ou CDI - injeção de dependencia */
	private UsuarioRespository ususarioRepository;

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API:  " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	ususarioRepository.save(usuario);/*Vai gravar no banco de dados com o nome*/
    	
    	return "Ola mundo :".concat(nome);//retorno do método 
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody /*retorna os dados para o corpo da resposta=*/
    public ResponseEntity<java.util.List<Usuario>> listaUsuario(){ 
    
    	java.util.List<Usuario> usuarios = ususarioRepository.findAll();
    	return new ResponseEntity<java.util.List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    //metodo salvar no banco de dados
    @PostMapping(value = "salvar") /*mapeamento da url*/
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){
    	
    	Usuario user = ususarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED); 
    }
    
    
  //metodo atualizar
    @PutMapping(value = "atualizar") /*mapeamento da url*/
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){
    	
    	//Condição para atualizar, somente recebendo o id
    	if(usuario.getId() == null) { 
    		return new ResponseEntity<String>("Informe o id do usuário: ", HttpStatus.OK); 
    	}
    	
    	Usuario user = ususarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK); 
    }
    
    
    //Método de delelar 
    @DeleteMapping(value = "delete") /*mapeamento da url*/
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser){
    	
    	ususarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso: ", HttpStatus.OK); 
    }
    
    //Método buscar
    @GetMapping(value = "buscaruserid") /*mapeamento da url*/
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser){
    	
    	Usuario usuario =	ususarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    //Método de pesquisar
    @GetMapping(value = "buscarPorNome") /*mapeamento da url*/
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name){
    	
    	List<Usuario> usuario =	ususarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
    
    
    
    
    
    
}




















