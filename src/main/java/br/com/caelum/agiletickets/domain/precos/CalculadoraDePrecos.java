package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		int ingrecosDisponiveis = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		
		if(tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(ingrecosDisponiveis / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} 
		} else if(tipoEspetaculo.equals(TipoDeEspetaculo.BALLET)) {
			if(ingrecosDisponiveis / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} 
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(ingrecosDisponiveis / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  
		 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
//	public BigDecimal incrementaPreco(Sessao sessao,double percentual){
//		
//		sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)))
//		
//	}

}