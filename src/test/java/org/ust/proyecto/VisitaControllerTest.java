package org.ust.proyecto;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import org.ust.proyecto.controllers.VisitaController;
import org.ust.proyecto.model.Cliente;
import org.ust.proyecto.model.Visita;
import org.ust.proyecto.services.VentaService;
import org.ust.proyecto.services.VisitaService;


import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
@WebMvcTest(VisitaController.class)
public class VisitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitaService visitaService;

    @Test
    void getVisita() throws Exception{
        given(visitaService.obtenVisita(anyLong()))
                .willReturn(Optional.of(Visita.builder().id(1L).direccion("Av. siempre viva").proposito("Carnita asada").vendedor("Vendedor 1").build()));
        mockMvc.perform(get("/visita/{id}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.direccion", is("Av. siempre viva")))
                .andExpect(jsonPath("$.proposito", is("Carnita asada")))
                .andExpect(jsonPath("$.vendedor", is("Vendedor 1")))
                .andDo(document("visita/get-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la visita"),
                                fieldWithPath("cliente").description("cliente de la visita"),
                                fieldWithPath("direccion").description("direccion de la visita"),
                                fieldWithPath("proposito").description("proposito de la visita"),
                                fieldWithPath("vendedor").description("vendedor de la visita"),
                                fieldWithPath("fechaProgramada").description("fecha de la visita")

                                
                                   )));
  

    }

    @Test
    void getVisitas() throws Exception{
        List<Visita> visitas = Arrays.asList(
            Visita.builder().id(1L).direccion("Av. Siempre Viva").proposito("Carnita asada y bebidas").vendedor("Vendedor 1").build(),
            Visita.builder().id(2L).direccion("Av. Siempre Viva2").proposito("Carnita asada y bebidas2").vendedor("Vendedor 2").build()
        );

        given(visitaService.obtenVisitas()).willReturn(visitas);

        mockMvc.perform(get("/visita")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].direccion", is("Av. Siempre Viva")))
                .andExpect(jsonPath("$[0].proposito", is("Carnita asada y bebidas")))
                .andExpect(jsonPath("$[0].vendedor", is("Vendedor 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].direccion", is("Av. Siempre Viva2")))
                .andExpect(jsonPath("$[1].proposito", is("Carnita asada y bebidas2")))
                .andExpect(jsonPath("$[1].vendedor", is("Vendedor 2")))
                .andDo(document("visita/get-visita",
                        responseFields(
                            fieldWithPath("[].id").description("identificador de la visita"),
                            fieldWithPath("[].cliente").description("cliente de la visita"),
                            fieldWithPath("[].direccion").description("direccion de la visita"),
                            fieldWithPath("[].proposito").description("proposito de la visita"),
                            fieldWithPath("[].vendedor").description("vendedor de la visita"),
                            fieldWithPath("[].fechaProgramada").description("fecha de la visita")
)));

    }

    @Test
    void creaVisita() throws Exception{
        Visita visitaParametro = Visita.builder().id(1L).direccion("Av. Siempre Viva").proposito("Carnita asada y bebidas").vendedor("Vendedor 1").build();
        Visita visitaRespuesta = Visita.builder().id(1L).direccion("Av. Siempre Viva").proposito("Carnita asada y bebidas").vendedor("Vendedor 1").build();

        given(visitaService.guardaVisita(visitaParametro)).willReturn(visitaRespuesta);

        mockMvc.perform(post("/visita")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(visitaParametro)))
        .andExpect(status().isCreated())
        .andDo(document("visita/post-visita",
                requestFields(
                    fieldWithPath("id").description("identificador de la visita"),
                    fieldWithPath("cliente").description("cliente de la visita"),
                    fieldWithPath("direccion").description("direccion de la visita"),
                    fieldWithPath("proposito").description("proposito de la visita"),
                    fieldWithPath("vendedor").description("vendedor de la visita"),
                    fieldWithPath("fechaProgramada").description("fecha de la visita")
),
                responseHeaders(
                        headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                )));
    }

    @Test
    void actualizaVisita() throws Exception{
    
        Visita visitaParametro = Visita.builder().id(1L).direccion("Av. Siempre Viva").proposito("Carnita asada y bebidas").vendedor("Vendedor 1").build();

        mockMvc.perform(put("/visita/{id}",1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(visitaParametro)))
        .andExpect(status().isNoContent())
        .andDo(document("visita/put-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        ),
                        requestFields(
                            fieldWithPath("id").description("identificador de la visita"),
                            fieldWithPath("cliente").description("cliente de la visita"),
                            fieldWithPath("direccion").description("direccion de la visita"),
                            fieldWithPath("proposito").description("proposito de la visita"),
                            fieldWithPath("vendedor").description("vendedor de la visita"),
                            fieldWithPath("fechaProgramada").description("fecha de la visita")
        )
                ));
    }

    @Test
    void eliminaVisita() throws Exception{
        mockMvc.perform(delete("/visita/{id}",1)
        .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNoContent())
        .andDo(document("visita/delete-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        )));
    }
}