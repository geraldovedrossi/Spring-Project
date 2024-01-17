package com.example.springproject.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ResumoDTO {

	 private BigDecimal valorTotalReceitas;
	 private BigDecimal valorTotalDespesas;
	 private BigDecimal saldoFinal;
	 private Map<String, BigDecimal> gastosPorCategoria;
	 

	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}
	public void setValorTotalReceitas(BigDecimal valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}
	public BigDecimal getValorTotalDespesas() {
		return valorTotalDespesas;
	}
	public void setValorTotalDespesas(BigDecimal valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public Map<String, BigDecimal> getGastosPorCategoria() {
		return gastosPorCategoria;
	}
	public void setGastosPorCategoria(Map<String, BigDecimal> gastosPorCategoria) {
		this.gastosPorCategoria = gastosPorCategoria;
	}
	 
	 
}
