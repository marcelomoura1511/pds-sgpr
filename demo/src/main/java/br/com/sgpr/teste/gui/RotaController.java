package br.com.sgpr.teste.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgpr.teste.business.entity.AuxRotaCidade;
import br.com.sgpr.teste.business.entity.Rota;
import br.com.sgpr.teste.business.entity.VisaoRotas;
import br.com.sgpr.teste.business.exceptions.BusinessExceptions;
import br.com.sgpr.teste.business.service.RotaCidadesService;
import br.com.sgpr.teste.business.service.RotaService;
import br.com.sgpr.teste.business.util.Mensagem;
import br.com.sgpr.teste.business.entity.VisaoRotaCidade;

@RestController
@RequestMapping(path="rota")
public class RotaController {

    @Autowired
    private RotaService rotaServices;
    @Autowired
    private RotaCidadesService rotaCidadesService;

    @GetMapping()
    public Iterable<VisaoRotas> getRotas(){
        return rotaServices.getRotas();
    }

    @PostMapping()
    public Mensagem postRota(@RequestBody Rota rota){
        try {
            rotaServices.saveNewRota(rota);
            return new Mensagem("Sucesso");
        } catch (BusinessExceptions e) {
            Mensagem msg = new Mensagem("Error");
            msg.setErros(e.getListOfMenssagens());
            return msg;
        }
    }

    @DeleteMapping(path = "/{rotaId}")
    public String deleteRota(@PathVariable String rotaId){
        return rotaServices.deleteRota(rotaId);
    }

    //cidades da rota

    @GetMapping(path="/{rotaId}/cidades")
    public Iterable<VisaoRotaCidade> getCidades(@PathVariable String rotaId){
        return rotaCidadesService.getCidades(rotaId);
    }

    @PostMapping(path="/cidades")
    public Mensagem postRotaCidades(@RequestBody AuxRotaCidade rotaCidades){
        try {
            rotaCidadesService.saveCidadesDaRota(rotaCidades);
            return new Mensagem("Sucesso");
        } catch (BusinessExceptions e) {
            Mensagem msg = new Mensagem("Error");
            msg.setErros(e.getListOfMenssagens());
            return msg;
        }
    }

    @DeleteMapping(path = "/{rotaId}/cidades")
    public String deleteCidadesDaRota(@PathVariable String rotaId){
        return rotaCidadesService.deleteCidadesDaRota(rotaId);
    }
}
