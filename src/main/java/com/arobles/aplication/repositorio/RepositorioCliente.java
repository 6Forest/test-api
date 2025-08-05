
package com.arobles.aplication.repositorio;
import com.arobles.aplication.modelo.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioCliente extends CrudRepository<Cliente, Integer> {

   
}
