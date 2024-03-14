package br.com.senai.restapicompleta.service;

import br.com.senai.restapicompleta.entity.Cliente;
import br.com.senai.restapicompleta.entity.Endereco;
import br.com.senai.restapicompleta.repository.ClienteRepository;
import br.com.senai.restapicompleta.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping(value = "/cliente")
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;


    // Retorna lista de clientes
    @GetMapping("/todos")
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    //Retorna o cliente pelo ID
    @PostMapping
    public void insert(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.checkCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;});
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @PostMapping("/atualiza/{id}")
    public void update(@PathVariable Long id, Cliente cliente){
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent())
            this.insert(cliente);
    }
    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

}
