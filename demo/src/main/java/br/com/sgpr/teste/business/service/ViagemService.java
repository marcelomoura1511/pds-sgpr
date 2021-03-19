package br.com.sgpr.teste.business.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgpr.teste.business.entity.Rota;
import br.com.sgpr.teste.business.entity.TempOnibus;
import br.com.sgpr.teste.business.entity.Viagem;
import br.com.sgpr.teste.business.entity.ViagemSimples;
import br.com.sgpr.teste.business.entity.VisaoViagens;
import br.com.sgpr.teste.business.exceptions.BusinessExceptions;
import br.com.sgpr.teste.data.RotaRepository;
import br.com.sgpr.teste.data.TempOnibusRepository;
import br.com.sgpr.teste.data.ViagemRepository;
import br.com.sgpr.teste.data.ViagemSimplesRepository;
import br.com.sgpr.teste.data.VisaoViagensRepository;

@Service
public class ViagemService {
    @Autowired
	private ViagemRepository viagemRepository;
	@Autowired
	private ViagemSimplesRepository viagemSimplesRepository;
    @Autowired
	private VisaoViagensRepository viagensRepository;
	@Autowired
	private RotaRepository rotaRepository;
	@Autowired
	private TempOnibusRepository onibusRepository;

	public Iterable<Viagem> getViagens(){
        System.out.println("Pegando todas as viagens...");
		return viagemRepository.findAll();
    }

    public void saveViagem(Viagem novaViagem) throws BusinessExceptions{
		System.out.println("Salvando nova viagem...");
		validateViagem(novaViagem);
		TempOnibus onibus = onibusRepository.findById(novaViagem.getOnibus()).orElseGet(() -> null);
		novaViagem.setAsssentosDisponiveis(onibus.getNumAssentos());
		viagemRepository.save(novaViagem);
    }

    public String deleteViagem(int viagemId){
        System.out.println("Deletando viagem de id "+ viagemId + "...");
        viagemRepository.deleteById(viagemId);
        return new String("Viagem de id " + viagemId + " deletada");
    }

	//Visão das viagens

	public Iterable<VisaoViagens> getVisaoViagens(){
		System.out.println("Pegando todas as viagens...");
		return viagensRepository.findAll();
	}

	public VisaoViagens getViagemById(int viagemId) {
		Optional<VisaoViagens> viagem = viagensRepository.findById(viagemId);
		return viagem.orElseGet(() -> null);
	}

	public Iterable<ViagemSimples>  getViagemByMotorita(String motoristaId) {
		return viagemSimplesRepository.findAllViagensMotorista(motoristaId);
	}

	public Iterable<VisaoViagens> pesquisarViagens(String origem, String destino){
		if(destino == null){
			System.out.println("Começando a pesquisa pelas viagens com origem em " + origem + "...");
			return viagensRepository.findAllViagens(origem);
		}else{
			System.out.println("Começando a pesquisa pelas viagens com origem em " + origem + " e destino a " + destino + "...");
			return viagensRepository.findAllViagens(origem, destino);
		}
	}

	private void validateViagem(Viagem viagemToValidade) throws BusinessExceptions{
		ArrayList<String> listOfInvalidFeild = new ArrayList<>();

		System.out.println("Validando nova viagem...");
		if(viagemToValidade == null){
			listOfInvalidFeild.add("Viagem não fornecida");
			throw new BusinessExceptions(listOfInvalidFeild);
		}

		if(viagemToValidade.getData() == null || viagemToValidade.getData().isEmpty()){
			listOfInvalidFeild.add("Data não fornecida!");
		}else{
			LocalDate dateToSave = LocalDate.parse(viagemToValidade.getData());
			LocalDate today = LocalDate.now();
			if(dateToSave.isBefore(today)){
				listOfInvalidFeild.add("Data invalida, a data da viagem deve ser de hoje ou qualquer dia após.");
			}else {
				if(viagemToValidade.getHoraSaida() == null || viagemToValidade.getHoraSaida().isEmpty()){
					listOfInvalidFeild.add("Hora de saida não fornecida");
				}else{
					if(dateToSave.isEqual(today)) {
						LocalTime hourNow = LocalTime.now();
						LocalTime horaSaida = LocalTime.parse(viagemToValidade.getHoraSaida());
			
						if((horaSaida.getHour() < hourNow.getHour()) || ( (horaSaida.getHour() == hourNow.getHour()) && (horaSaida.getMinute() <= hourNow.getMinute()))) {
							listOfInvalidFeild.add("Hora de saida invalida");
						}
					}
				}
			}
		}

		if(viagemToValidade.getPreco() <= 0) {
			listOfInvalidFeild.add("Preço invalido!");
		}

		if(viagemToValidade.getRota() == null || viagemToValidade.getRota().isEmpty()) {
			listOfInvalidFeild.add("Rota não fornecida!");
		}else {
			ArrayList<Rota> rotas = rotaRepository.getRotaById(viagemToValidade.getRota());

			if(rotas.size() == 0) {
				listOfInvalidFeild.add("Rota não existe!");
			}
		}

		if(viagemToValidade.getMotorista() == null || viagemToValidade.getMotorista().isEmpty()){
			listOfInvalidFeild.add("Motorista não fornecido");
		}else{
			//TODO
			//Verificar se o cpf do motorista é valido
		}

		if(viagemToValidade.getOnibus() == null || viagemToValidade.getOnibus().isEmpty()){
			listOfInvalidFeild.add("Onibus não fornecido");
		}else{
			//TODO
			//Verificar se a placa do onibus é valida
		}

		if(viagemToValidade.getEmpresa() == null || viagemToValidade.getEmpresa().isEmpty()){
			listOfInvalidFeild.add("Empresa não fornecida");
		}else{
			//TODO
			//Verificar se a empresa é valida
		}

		if(listOfInvalidFeild.size() > 0){
			throw new BusinessExceptions(listOfInvalidFeild);
		}
	}
}
