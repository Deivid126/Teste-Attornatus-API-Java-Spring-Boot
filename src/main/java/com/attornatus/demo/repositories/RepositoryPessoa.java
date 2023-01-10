package com.attornatus.demo.repositories;

import com.attornatus.demo.models.Endereco;
import com.attornatus.demo.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryPessoa extends JpaRepository<Pessoa,Integer> {

}
