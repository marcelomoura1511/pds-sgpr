package br.com.sgpr.teste.business.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgpr.teste.business.entity.PassagemUsada;
import br.com.sgpr.teste.business.entity.PassagensViagem;
import br.com.sgpr.teste.business.entity.TempPassagem;
import br.com.sgpr.teste.business.entity.Viagem;
import br.com.sgpr.teste.business.exceptions.BusinessExceptions;
import br.com.sgpr.teste.data.PassagemUsadaRepository;
import br.com.sgpr.teste.data.PassagensViagemsRepository;
import br.com.sgpr.teste.data.TempPassagemRepository;
import br.com.sgpr.teste.data.ViagemRepository;

@Service
public class PassagemService {
    @Autowired
    private PassagensViagemsRepository passagensViagensRepository;
    @Autowired
    private TempPassagemRepository passagemRepository;
    @Autowired
    private PassagemUsadaRepository passagemUsadaRepository;
    @Autowired
    private ViagemRepository viagemRepository;

    public Iterable<PassagensViagem> getPassagensViagem(String viagemId){
        return passagensViagensRepository.getPassagens(viagemId);
    }

    public Iterable<PassagensViagem> getUserPass(String userId) {
        return passagensViagensRepository.getUserPass(userId);
    }

    public void cancelarViagem(String passId) throws BusinessExceptions{
        System.out.println("Cancelando a passagem " + passId);
        TempPassagem pass = passagemRepository.findById(passId).orElseGet(() -> null);
        Viagem viagem = viagemRepository.findById(pass.getViagem()).orElseGet(() -> null);

        LocalDate viagemDate = LocalDate.parse(viagem.getData());
        LocalDate today = LocalDate.now();

        ArrayList<String> listOfErros = new ArrayList<>();
        if(today.isBefore(viagemDate)) {
            String mouth = viagemDate.getMonthValue() > 9 ? "" + viagemDate.getMonthValue() : "0" + viagemDate.getMonthValue();
            LocalDate dayBeforeViagem = LocalDate.parse(viagemDate.getYear() + "-" + mouth + "-" + (viagemDate.getDayOfMonth() - 1));

            if(today.isEqual(dayBeforeViagem)) {
                LocalTime timeNow = LocalTime.now();
                LocalTime horaSiadaViagem = LocalTime.parse(viagem.getHoraSaida());

                int timeNowInt = (timeNow.getHour() * 100) + timeNow.getMinute();
                int horaSiadaViagemInt = (horaSiadaViagem.getHour() * 100) + horaSiadaViagem.getMinute();
                if((timeNowInt - horaSiadaViagemInt) <= 0) {
                    //pode cancelar a viagem
                    deletePassagemOnDB(passId, viagem.getId());
                }else {
                    listOfErros.add("Passagem não pode ser cancelada, menos de 24h para a viagem.");
                }
            }else {
                //pode cancelar a viagem
                deletePassagemOnDB(passId, viagem.getId());
            }

        }else {
            if(today.isEqual(viagemDate)) {
                listOfErros.add("Passagem não pode ser cancelada, menos de 24h para a viagem.");
            }else {
                listOfErros.add("Passagem não pode ser cancelada.");
            }
        }

        if(listOfErros.size() > 0) {
            throw new BusinessExceptions(listOfErros);
        }
    }
    
    private void deletePassagemOnDB(String passId, int viagemOfPassagemToDeleteId){
        Viagem viagem = viagemRepository.findById(viagemOfPassagemToDeleteId).orElseGet(() -> null);
        if(viagem.getAsssentosDisponiveis() > 0) {
            passagemRepository.deleteById(passId);
            viagemRepository.updateAssentosDisponiveis(viagem.getId(), viagem.getAsssentosDisponiveis() + 1);
        }else {
            //to do, criar exeção para esse caso.
            System.out.println("Viagem não tem passagens");
        }
    }

    public void validetedPassagem(PassagensViagem passToValidate) throws Exception{
        System.out.println("Validando a passagem de id " + passToValidate.getCodValidacao() + " da viagem " +  passToValidate.getViagem());
        TempPassagem pass = passagemRepository.findById(passToValidate.getCodValidacao()).orElseGet(() -> null);
        Viagem viagem = viagemRepository.findById(pass.getViagem()).orElseGet(() -> null);

        if(pass == null || viagem.getId() != passToValidate.getViagem()) {
            throw new Exception("Passagem Inválida");
        }else{
            PassagemUsada oldPass = new PassagemUsada(pass);
            passagemRepository.deleteById(pass.getCodValidacao());
            passagemUsadaRepository.save(oldPass);
        }
    }
}
