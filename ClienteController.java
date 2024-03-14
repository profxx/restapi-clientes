package br.com.senai.restapicompleta.controller;

import br.com.senai.restapicompleta.entity.Cliente;
import br.com.senai.restapicompleta.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente){
        service.insert(cliente);
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        service.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
