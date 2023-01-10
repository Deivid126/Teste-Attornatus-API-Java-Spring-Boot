package com.attornatus.demo.repositories;

import com.attornatus.demo.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositotyEndereco extends JpaRepository<Endereco, Integer> {
}
