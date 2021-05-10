package br.com.sistema.service;

import br.com.sistema.model.Cargo;

import java.util.List;

public interface CargoService {

    public Cargo findById(long id);
    public Cargo findByNome(String nome);
    public List<Cargo> findAll();

    public String save(Cargo cargo);
    public String deleteById();
}
