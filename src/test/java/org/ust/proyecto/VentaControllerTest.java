package org.ust.proyecto;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ust.proyecto.controllers.VentaController;
import org.ust.proyecto.model.Venta;
import org.ust.proyecto.services.VentaService;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
@WebMvcTest(VentaController.class)
public class VentaControllerTest {

@Autowired
private MockMvc mockMvc;

@MockBean
private VentaService ventaService;

@Test
void getVenta()throws Exception{
    given(ventaService.obtenVenta(anyLong())).willReturn(Optional.of(Venta.builder().ventaId(1L).monto(233.2f).build()));
    mockMvc.perform(get("/venta/{ventaId}", 1)
    .content(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(status().isOk())
    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.ventaId", is(1)))
    .andExpect(jsonPath("$.monto", is(233.2)))
    .andDo(document("venta/get-venta",
                        pathParameters(
                                parameterWithName("ventaId").description("Identificador de la venta")
                        ),
                        responseFields(
                                fieldWithPath("ventaId").description("identificador de la venta"),
                                fieldWithPath("monto").description("costo de la venta"),
                                fieldWithPath("productos").description("productos de la venta"),
                                fieldWithPath("cliente").description("cliente que hizo la venta"),
                                fieldWithPath("fechaCreacion").description("fecha de la venta")
                                   )));
  
}


@Test
    void creaVenta() throws Exception {
       Venta ventaParametro=Venta.builder().monto(146.2f).build();
       Venta ventaRespuesta=Venta.builder().ventaId(1L).monto(146.2f).build();


        given(ventaService.guardaVenta(ventaParametro)).willReturn(ventaRespuesta);

        mockMvc.perform(post("/venta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ventaParametro)))
                .andExpect(status().isCreated())
                .andDo(document("venta/post-venta",
                requestFields(
                    fieldWithPath("ventaId").description("identificador de la venta"),
                                fieldWithPath("monto").description("costo de la venta"),
                                fieldWithPath("productos").description("productos de la venta"),
                                fieldWithPath("cliente").description("cliente que hizo la venta"),
                                fieldWithPath("fechaCreacion").description("fecha de la venta")
                                ),
                responseHeaders(
                        headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                )));
    }

    @Test
    void actualizaVenta() throws Exception {
        Venta ventaParametro = Venta.builder().ventaId(1L).monto(164.4f).build();

        mockMvc.perform(put("/venta/{ventaId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ventaParametro)))
                .andExpect(status().isNoContent())
                .andDo(document("venta/put-venta",
                pathParameters(
                        parameterWithName("ventaId").description("Identificador de la venta")
                ),
                requestFields(
                    fieldWithPath("ventaId").description("identificador de la venta"),
                    fieldWithPath("monto").description("costo de la venta"),
                    fieldWithPath("productos").description("productos de la venta"),
                    fieldWithPath("cliente").description("cliente que hizo la venta"),
                    fieldWithPath("fechaCreacion").description("fecha de la venta")
                    )
        ));
    }

    @Test
    void eliminaVenta() throws Exception {
        mockMvc.perform(delete("/venta/{ventaId}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("venta/delete-venta",
                        pathParameters(
                                parameterWithName("ventaId").description("Identificador de la venta")
                        )));
    }

    
}
