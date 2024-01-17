package com.example.springproject.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.dto.ResumoDTO;
import com.example.springproject.model.entity.DespesaEntity;
import com.example.springproject.model.entity.ReceitaEntity;
import com.example.springproject.repository.DespesaRepository;
import com.example.springproject.repository.ReceitaRepository;

@Service
public class ResumoService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	// Método para calcular um resumo mensal de receitas, despesas e gastos por categoria
	public ResumoDTO resumoMensal(int ano, int mes) {
	    
	    // Criando duas instâncias de LocalDateTime representando o início e o fim do mês fornecido
	    LocalDateTime data1 = LocalDateTime.of(ano, Month.of(mes), 1, 0, 0);
	    LocalDateTime data2 = LocalDateTime.of(ano, Month.of(mes), Month.of(mes).length(Year.of(ano).isLeap()), 0, 0);

	    // Obtendo listas de receitas e despesas do repositório para o período especificado
	    List<ReceitaEntity> listaReceitas = receitaRepository.findAllByDataBetween(data1, data2);
	    List<DespesaEntity> listaDespesas = despesaRepository.findAllByDataBetween(data1, data2);
	    
	    // Criando uma instância de DespesaEntity para obter todas as categorias disponíveis
	    DespesaEntity despesaEntity = new DespesaEntity();
	    List<String> categorias = despesaEntity.AllCategorias();

	    // Calculando o total de receitas e despesas para o período especificado
	    BigDecimal totalReceita = listaReceitas.stream().map(ReceitaEntity::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	    BigDecimal totalDespesas = listaDespesas.stream().map(DespesaEntity::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

	    // Calculando o saldo final subtraindo as despesas das receitas
	    BigDecimal saldoFinal = totalReceita.subtract(totalDespesas);
	    
	    // Criando um mapa para armazenar os gastos por categoria
	    Map<String, BigDecimal> mapaGastos = new HashMap<>();
	    
	    // Iterando sobre todas as categorias disponíveis
	    for (String categoria : categorias) {
	        // Filtrando as despesas para a categoria específica e calculando o total
	        BigDecimal totalGastosCategoria = listaDespesas.stream()
	                .filter(p -> p.getCategoria().contains(categoria))
	                .map(DespesaEntity::getValor)
	                .reduce(BigDecimal.ZERO, BigDecimal::add);

	        // Armazenando o total de gastos no mapa, associado à categoria
	        mapaGastos.put(categoria, totalGastosCategoria);
	    }
	    
	    // Criando uma instância de ResumoDTO para armazenar os resultados calculados
	    ResumoDTO resumoMensal = new ResumoDTO();
	    
	    // Configurando os valores calculados no objeto ResumoDTO
	    resumoMensal.setValorTotalReceitas(totalReceita);
	    resumoMensal.setValorTotalDespesas(totalDespesas);
	    resumoMensal.setSaldoFinal(saldoFinal);
	    resumoMensal.setGastosPorCategoria(mapaGastos);
	    
	    // Retornando o objeto ResumoDTO completo
	    return resumoMensal;
	}
	
}
