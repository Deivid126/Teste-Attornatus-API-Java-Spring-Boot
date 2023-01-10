package com.attornatus.demo.services;

import com.attornatus.demo.exceptionhandler.PessoaNotFoundException;
import com.attornatus.demo.models.Endereco;
import com.attornatus.demo.models.Pessoa;
import com.attornatus.demo.repositories.RepositoryPessoa;
import com.attornatus.demo.repositories.RepositotyEndereco;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    RepositoryPessoa repositoryPessoa;
    @Autowired
    RepositotyEndereco repositotyEndereco;




    public void deletePessoa(int id){

        repositoryPessoa.deleteById(id);
    }
    public Pessoa savePessoa(Pessoa pessoa){
        return repositoryPessoa.save(pessoa);
    }

    public Pessoa findById(int id){

            Optional<Pessoa> pessoa = repositoryPessoa.findById(id);

            return pessoa.orElseThrow(()-> new PessoaNotFoundException());

    }

    public List<Pessoa> findALl(){
        return repositoryPessoa.findAll();
    }

    public Pessoa Atualizar(int id, Pessoa pessoa) {

         Pessoa pessoanew = repositoryPessoa.findById(id).orElseThrow(()-> new PessoaNotFoundException());

         AtualizarEndereco(id, pessoa.getEnderecos());
         BeanUtils.copyProperties(pessoa,pessoanew,"id","enderecos");
         repositoryPessoa.save(pessoanew);

         return pessoanew;

    }

    public void  AtualizarEndereco(int id, List<Endereco> endereco){

        Optional<Pessoa> pessoa = repositoryPessoa.findById(id);


        int finalI = 1;

        while(finalI <= endereco.size()){

            finalI = finalI - 1;
            Optional<Endereco> endereconew = repositotyEndereco.findById(pessoa.get().getEnderecos().get(finalI).getId());
            Endereco endereco1 = endereco.get(finalI);
            BeanUtils.copyProperties(endereco1,endereconew.get(),"id","pessoa");
            repositotyEndereco.save(endereconew.get());
            finalI = finalI + 2;

        }

    }

    public List<Endereco> saveEndereco(int id, List<Endereco> endereco) {

        Pessoa pessoa = repositoryPessoa.findById(id).orElseThrow(()-> new PessoaNotFoundException());

        endereco.forEach(endereconew -> {

            endereconew.setPessoa(pessoa);
            repositotyEndereco.save(endereconew);
        });

        return endereco;

    }

    public List<Endereco> listarAllEndereco(int id){
        Pessoa pessoa = repositoryPessoa.findById(id).orElseThrow(()-> new PessoaNotFoundException());

        List<Endereco> enderecos = pessoa.getEnderecos();

        return enderecos;
    }

}
