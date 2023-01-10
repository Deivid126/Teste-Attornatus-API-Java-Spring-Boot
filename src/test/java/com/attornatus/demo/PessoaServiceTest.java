package com.attornatus.demo;

import com.attornatus.demo.models.Endereco;
import com.attornatus.demo.models.Pessoa;
import com.attornatus.demo.repositories.RepositoryPessoa;
import com.attornatus.demo.repositories.RepositotyEndereco;
import com.attornatus.demo.services.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.attornatus.demo.Enum.EnderecoEnum.PADRÃO;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    RepositoryPessoa repositoryPessoa;
    @Mock
    RepositotyEndereco repositotyEndereco;

    @InjectMocks
    PessoaService pessoaService;


    @Test
    public void findAll() {

        List<Pessoa> pessoa = new ArrayList<Pessoa>();
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Pessoa pessoa1 = new Pessoa(1,"Deivid", LocalDate.of(2022,01,01),enderecos);
        pessoa.add(pessoa1);

        Mockito.when(repositoryPessoa.findAll()).thenReturn((List<Pessoa>) pessoa);

        pessoaService.findALl();

        Mockito.verify(repositoryPessoa).findAll();

    }

    @Test
    public void FindById(){

        List<Endereco> enderecos = new ArrayList<Endereco>();
        Pessoa pessoa1 = new Pessoa(1,"Deivid", LocalDate.of(2022,01,01),enderecos);

        Mockito.when(repositoryPessoa.findById(pessoa1.getId())).thenReturn(Optional.of(pessoa1));
        pessoaService.findById(pessoa1.getId());
        Mockito.verify(repositoryPessoa).findById(pessoa1.getId());


    }

    @Test
    public void savePessoa(){
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Pessoa pessoa1 = new Pessoa(1,"Deivid", LocalDate.of(2022,01,01),enderecos);

        Mockito.when(repositoryPessoa.save(pessoa1)).thenReturn(pessoa1);

        pessoaService.savePessoa(pessoa1);

        Mockito.verify(repositoryPessoa).save(pessoa1);
    }

    @Test
    public void saveEndereco(){
        List<Endereco> enderecoList = new ArrayList<Endereco>();
        List<Endereco> enderecoAQUI = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa(1,"Deivid", LocalDate.of(2022,01,01),enderecoAQUI);
        Endereco endereco = new Endereco(1,"AQUI","123","12","ALI",PADRÃO,pessoa1);
        enderecoList.add(endereco);

        Mockito.when(repositoryPessoa.findById(pessoa1.getId())).thenReturn(Optional.of(pessoa1));

        Mockito.when(repositotyEndereco.save(enderecoList.get(0))).thenReturn(endereco);

        pessoaService.saveEndereco(pessoa1.getId(), enderecoList);

        Mockito.verify(repositotyEndereco).save(endereco);

    }

    @Test
    public void listarAllEnderecos(){
        List<Endereco> enderecoList = new ArrayList<Endereco>();
        List<Endereco> enderecoAQUI = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa(1,"Deivid", LocalDate.of(2022,01,01),enderecoAQUI);
        Endereco endereco = new Endereco(1,"AQUI","123","12","ALI",PADRÃO,pessoa1);
        enderecoList.add(endereco);

        Mockito.when(repositoryPessoa.findById(pessoa1.getId())).thenReturn(Optional.of(pessoa1));

        pessoaService.listarAllEndereco(pessoa1.getId());

        Mockito.verify(repositoryPessoa).findById(pessoa1.getId());
    }


}
