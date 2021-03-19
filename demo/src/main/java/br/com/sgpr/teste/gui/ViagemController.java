package br.com.sgpr.teste.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.sgpr.teste.business.entity.Viagem;
import br.com.sgpr.teste.business.entity.ViagemSimples;
import br.com.sgpr.teste.business.service.ViagemService;
import br.com.sgpr.teste.business.util.Mensagem;
import br.com.sgpr.teste.business.entity.VisaoViagens;
import br.com.sgpr.teste.business.exceptions.BusinessExceptions;

@RestController
@RequestMapping(path="viagem")
public class ViagemController {
	@Autowired
	private ViagemService viagemService;
    
	@GetMapping()
	public Iterable<VisaoViagens> getViagens(){
		return viagemService.getVisaoViagens();
    }
    
    @GetMapping(path = "/{viagemId}")
    public VisaoViagens getViagemById(@PathVariable("viagemId") int viagemId) {
        return viagemService.getViagemById(viagemId);
    }

    @GetMapping(path = "/motorista/{motoristaId}")
    public Iterable<ViagemSimples> getViagemByMotoristaId(@PathVariable("motoristaId") String motoristaId) {
        return viagemService.getViagemByMotorita(motoristaId);
    }

    @PostMapping()
    public Mensagem postViagem(@RequestBody Viagem novaViagem){
        try {
            viagemService.saveViagem(novaViagem);
            return new Mensagem("Sucesso");
        } catch (BusinessExceptions e) {
            Mensagem msg = new Mensagem("Error");
            msg.setErros(e.getListOfMenssagens());
            return msg;
        }
    }

    @DeleteMapping(path = "/{viagemId}")
    public String deleteViagem(@PathVariable int viagemId){
        return viagemService.deleteViagem(viagemId);
    }

    @GetMapping(path="busca")
	public Iterable<VisaoViagens> pesquisarViagens(@RequestParam String origem, @RequestParam(required = false) String destino){
		return viagemService.pesquisarViagens(origem, destino);
	}
}

