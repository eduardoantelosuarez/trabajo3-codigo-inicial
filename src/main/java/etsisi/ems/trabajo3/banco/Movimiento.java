package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;

public class Movimiento {
	public String mConcepto;
	protected LocalDate mFecha;
	private double mImporte;
	private boolean mLiquidado;

	public Movimiento(String concepto, double importe, LocalDate fecha) {
		this.mConcepto = concepto;
		this.mImporte = importe;
		this.mFecha = fecha;
		setLiquidado(false); //lo necesito para los movimientos de las tarjetas de crédito
	}

	public double getImporte() {
		return mImporte;
	}

	public String getConcepto() {
		return mConcepto;
	}

	public void setConcepto(String newMConcepto) {
		mConcepto = newMConcepto;
	}

	public LocalDate getFecha() {
		return mFecha;
	}

	public void setFecha(LocalDate newMFecha) {
		mFecha = newMFecha;
	}

	public void setImporte(double newMImporte) {
		mImporte = newMImporte;
	}

	public boolean isLiquidado() {
		return mLiquidado;
	}

	public void setLiquidado(boolean mliquidado) {
		this.mLiquidado = mliquidado;
	}
}