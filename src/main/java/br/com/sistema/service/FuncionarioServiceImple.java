package br.com.sistema.service;

import br.com.sistema.model.Funcionario;
import br.com.sistema.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImple implements FuncionarioService{

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll(Sort.by("nome"));
    }

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).get();
    }

    @Override
    public Funcionario findByEmail(String email){
        return funcionarioRepository.findByEmail(email);
    }

    @Override
    public Funcionario findByNome(String nome){
        return funcionarioRepository.findByNome(nome);
    }

    @Override
    public boolean save(Funcionario funcionario){
        try {
            if (funcionario != null){
                funcionarioRepository.save(funcionario);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    /*@Override
    public boolean delete(Long id){
        return false;
    }*/

    @Override
    public String validarFuncionario(Funcionario funcionario){
        String erro = null;
        Funcionario f;

        if(funcionario.getId() == null){//Novo Funcionario

            f = funcionarioRepository.findByNome(funcionario.getNome());
            if(f != null){
                erro = "Nome já existente!";
            }

            f = funcionarioRepository.findByEmail(funcionario.getEmail());
            if(f != null){
                if(erro != null) erro += " ";
                erro = "Email já existente!";
            }

        } else {//Funcionario Existente

            f = funcionarioRepository.findByIdNotAndNome(funcionario.getId(), funcionario.getNome());
            if(f != null){
                erro = " Nome já existente!";
            }

            f = funcionarioRepository.findByIdNotAndEmail(funcionario.getId(), funcionario.getEmail());
            if(f != null){
                if(erro != null) erro += " ";
                erro = "Email já existente!";
            }

        }
        return erro;
    }

    public boolean deleteById(Long id){
        try{
            funcionarioRepository.deleteById(id);
            return  true;
        }catch(Exception e){
            return false;
        }
    }
}
