package etsisi.ems.trabajo3.tarjetas;

import java.time.LocalDate;

import etsisi.ems.trabajo3.banco.Cuenta;

public class Tarjeta {
	private Cuenta mCuentaAsociada;
	private String mNumero, mTitular;
	private LocalDate mFechaDeCaducidad;
	
	public Tarjeta(String numero, String titular, LocalDate fechaCaducidad) {
		mNumero = numero;
		mTitular = titular;
		mFechaDeCaducidad = fechaCaducidad;
	}

	public Cuenta getmCuentaAsociada() {
		return mCuentaAsociada;
	}

	public void setCuenta(Cuenta c) {
		this.mCuentaAsociada = c;
	}

	public String getmNumero() {
		return mNumero;
	}

	public void setmNumero(String mNumero) {
		this.mNumero = mNumero;
	}

	public String getmTitular() {
		return mTitular;
	}

	public void setmTitular(String mTitular) {
		this.mTitular = mTitular;
	}

	public LocalDate getmFechaDeCaducidad() {
		return mFechaDeCaducidad;
	}

	public void setmFechaDeCaducidad(LocalDate mFechaDeCaducidad) {
		this.mFechaDeCaducidad = mFechaDeCaducidad;
	}
}
