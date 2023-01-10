package com.attornatus.demo;

import com.attornatus.demo.Enum.EnderecoEnum;
import com.attornatus.demo.controller.ApiController;
import com.attornatus.demo.models.Endereco;
import com.attornatus.demo.models.Pessoa;
import com.attornatus.demo.repositories.RepositoryPessoa;

import com.attornatus.demo.repositories.RepositotyEndereco;
import com.attornatus.demo.services.PessoaService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApiController.class)
public class ControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    PessoaService pessoaService;

    @MockBean
    RepositoryPessoa repositoryPessoa;

    @MockBean
    RepositotyEndereco repositotyEndereco;


    @Test
    public void findPessoa() throws Exception {
      Pessoa pessoa = new Pessoa(1,"Deivid",LocalDate.of(2022,01,01),new ArrayList<Endereco>());

      Mockito.when(pessoaService.findALl()).thenReturn(List.of(pessoa));
      mvc.perform(get("/api/attornatus/pessoa"))
              .andDo(print())
              .andExpect(status().isOk());
    }
    @Test
    public void savePessoa() throws Exception {
        Pessoa pessoa = new Pessoa(1,"Deivid",LocalDate.of(2022,01,01),new ArrayList<Endereco>());

        String json = "{\"id\":1,\"nome\":\"Deivid\",\"data_nascimento\":\"2022-01-01\"" +
                ",\"enderecos\":[]}";

        Mockito.when(pessoaService.savePessoa(pessoa)).thenReturn(pessoa);

        mvc.perform(post("/api/attornatus/pessoa").contentType(
                MediaType.APPLICATION_JSON
                ).content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void AtualizarPessoa() throws Exception {
        Pessoa pessoa = new Pessoa(1,"Deivid",LocalDate.of(2022,01,01),new ArrayList<Endereco>());
        List<Endereco> enderecolist = new ArrayList<>();
        Endereco endereco = new Endereco(1,"Aqui","123","28","la", EnderecoEnum.PADRÃO,null);

        enderecolist.add(endereco);

        String json = "{\"id\":1,\"nome\":\"Deivid\",\"data_nascimento\":\"2022-01-01\"" +
                ",\"enderecos\":[{\"id\":1,\"logradouro\":\"Aqui\",\"caixapostal\":\"123\",\"numero\":\"28\",\"cidade\":\"la\",\"typeendereco\":\"PADRÃO\"}]}";

        Mockito.when(pessoaService.Atualizar(pessoa.getId(), pessoa)).thenReturn(pessoa);
        //Mockito.when(pessoaService.AtualizarEndereco(pessoa.getId(),enderecolist )).thenReturn(List.of());

        mvc.perform(post("/api/attornatus/endereco/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    public void GetAllEnderecos() throws Exception {
        Pessoa pessoa = new Pessoa(1,"Deivid",LocalDate.of(2022,01,01),new ArrayList<Endereco>());

        Mockito.when(pessoaService.listarAllEndereco(pessoa.getId())).thenReturn(List.of());

        mvc.perform(get("/api/attornatus/endereco/{id}",1))
                .andExpect(status().isOk());

    }

    @Test
    public void GetPessoaById() throws Exception {
        Pessoa pessoa = new Pessoa(1,"Deivid",LocalDate.of(2022,01,01),new ArrayList<Endereco>());

        Mockito.when(pessoaService.findById(pessoa.getId())).thenReturn(pessoa);

        mvc.perform(get("/api/attornatus/endereco/{id}",1))
                .andExpect(status().isOk());
    }

}
