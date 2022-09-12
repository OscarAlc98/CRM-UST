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
import org.ust.proyecto.controllers.ProductoController;
import org.ust.proyecto.model.Producto;
import org.ust.proyecto.services.ProductoService;

import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

@Autowired
private MockMvc mockMvc;

@MockBean
private ProductoService productoService;

@Test
void getProducto()throws Exception{
    given(productoService.obtenProducto(anyLong())).willReturn(Optional.of(Producto.builder().id(1L).nombre("productoPrueba").categoria("categoriaPrueba").precio(1).numeroRegistro("1").fechaCreacion(LocalDate.now()).build()));
    mockMvc.perform(get("/producto/{productoId}", 1)
    .content(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(status().isOk())
    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.id", is(1)))
    .andExpect(jsonPath("$.nombre", is("productoPrueba")))
    .andExpect(jsonPath("$.categoria", is("categoriaPrueba")))
    .andExpect(jsonPath("$.precio", is(1.0)))
    .andExpect(jsonPath("$.numeroRegistro", is("1")))
    .andExpect(jsonPath("$.fechaCreacion", is("2022-09-12")))
    .andDo(document("producto/get-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del cliente"),
                                fieldWithPath("nombre").description("nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("numero de registro"),
                                fieldWithPath("fechaCreacion").description("fecha de creacion")

                                )));
}

@Test
void getProductos()throws Exception{
    List<Producto> productos = Arrays.asList(
        Producto.builder().id(1L).nombre("nombre").categoria("categoria1").precio(1).numeroRegistro("1").fechaCreacion(LocalDate.now().plusDays(2)).build(),
        Producto.builder().id(2L).nombre("nombre2").categoria("categoria2").precio(2).numeroRegistro("2").fechaCreacion(LocalDate.now().plusDays(5)).build(),
        Producto.builder().id(3L).nombre("nombre3").categoria("categoria3").precio(3).numeroRegistro("3").fechaCreacion(LocalDate.now().plusDays(11)).build()
);

given(productoService.obtenProductos()).willReturn(productos);

mockMvc.perform(get("/producto")
        .content(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id", is(1))) 
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[2].id", is(3)))
        .andExpect(jsonPath("$[0].nombre", is("nombre")))
        .andExpect(jsonPath("$[1].nombre", is("nombre2")))
        .andExpect(jsonPath("$[2].nombre", is("nombre3")))
        .andExpect(jsonPath("$[0].categoria", is("categoria1")))
        .andExpect(jsonPath("$[1].categoria", is("categoria2")))
        .andExpect(jsonPath("$[2].categoria", is("categoria3")))
        .andExpect(jsonPath("$[0].precio", is(1.0)))
        .andExpect(jsonPath("$[1].precio", is(2.0)))
        .andExpect(jsonPath("$[2].precio", is(3.0)))
        .andExpect(jsonPath("$[0].numeroRegistro", is("1")))
        .andExpect(jsonPath("$[1].numeroRegistro", is("2")))
        .andExpect(jsonPath("$[2].numeroRegistro", is("3")))
        .andExpect(jsonPath("$[0].fechaCreacion", is("2022-09-14")))
        .andExpect(jsonPath("$[1].fechaCreacion", is("2022-09-17")))
        .andExpect(jsonPath("$[2].fechaCreacion", is("2022-09-23")))
        .andDo(document("etapa/get-etapas",
                        responseFields(
                            fieldWithPath("[].id").description("identificador del cliente"),
                                fieldWithPath("[].nombre").description("nombre del producto"),
                                fieldWithPath("[].categoria").description("categoria del producto"),
                                fieldWithPath("[].precio").description("precio del producto"),
                                fieldWithPath("[].numeroRegistro").description("numero de registro"),
                                fieldWithPath("[].fechaCreacion").description("fecha de creacion")
)));
        
}

@Test
    void creaProducto() throws Exception {

       Producto productoParametro=Producto.builder().id(1L).nombre("nombre").categoria("categoria1").precio(1).numeroRegistro("123-231-1123").build();
       Producto productoRespuesta=Producto.builder().id(1L).nombre("nombre").categoria("categoria1").precio(1).numeroRegistro("123-231-1123").build();

        given(productoService.guardaProducto(productoParametro)).willReturn(productoRespuesta);
        mockMvc.perform(post("/producto")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(productoParametro)))
        .andExpect(status().isCreated())
        .andDo(document("produto/post-producto",
                requestFields(
                    fieldWithPath("id").description("identificador del cliente"),
                    fieldWithPath("nombre").description("nombre del producto"),
                    fieldWithPath("categoria").description("categoria del producto"),
                    fieldWithPath("precio").description("precio del producto"),
                    fieldWithPath("numeroRegistro").description("numero de registro"),
                    fieldWithPath("fechaCreacion").description("fecha de creacion")
 ),
                responseHeaders(
                        headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                )));
    }

    @Test
    void actualizaProducto() throws Exception {

        Producto productoParametro = Producto.builder().id(1L).nombre("nombre").categoria("categoria").precio(1.0f).numeroRegistro("122-213-1231").build();

        mockMvc.perform(put("/producto/{productoId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productoParametro)))
                .andExpect(status().isNoContent());
                
                
    }

    @Test
    void eliminaProducto() throws Exception {
        mockMvc.perform(delete("/producto/{productoId}", 1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("producto/delete-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        )));
    }

    
}
