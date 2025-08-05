package pruebas;

import com.arobles.aplication.controlador.ClienteController;
import com.arobles.aplication.modelo.Cliente;
import com.arobles.aplication.servicio.ServicioCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioCliente servicioCliente;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGuardarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setEstado(Boolean.TRUE);
        cliente.setContrasena("123");

        when(servicioCliente.guardar(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testBuscarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1);

        when(servicioCliente.buscar(1)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }
}