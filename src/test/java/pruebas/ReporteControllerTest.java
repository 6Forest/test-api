
package pruebas;

import com.arobles.aplication.controlador.ReporteController;
import com.arobles.aplication.dto.ReporteEstadoCuentaDTO;
import com.arobles.aplication.servicio.ServicioReporte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioReporte servicioReporte;

    @Test
    public void testObtenerReporteEstadoCuenta() throws Exception {
        ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
        reporte.setClienteId(1);
        reporte.setNombreCliente("Luis");
        reporte.setCuentas(new ArrayList<>());

        when(servicioReporte.generarReporte(any(Integer.class), any(), any()))
                .thenReturn(reporte);

        mockMvc.perform(get("/reportes")
                .param("clienteId", "1")
                .param("fechaInicio", "2023-01-01")
                .param("fechaFin", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(1))
                .andExpect(jsonPath("$.nombreCliente").value("Luis"));
    }
}