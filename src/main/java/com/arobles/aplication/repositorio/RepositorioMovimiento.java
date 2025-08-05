
package com.arobles.aplication.repositorio;
import com.arobles.aplication.modelo.Cuenta;
import com.arobles.aplication.modelo.Movimiento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioMovimiento extends CrudRepository<Movimiento, Integer> {

   List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDateTime inicio, LocalDateTime fin);
   
}
