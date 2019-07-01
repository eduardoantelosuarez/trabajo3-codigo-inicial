package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	public Debito(String numero, String titular, LocalDate fechaCaducidad) {
		super(numero, titular, fechaCaducidad);
	}

	public void retirar(double x) throws Exception {
		super.getmCuentaAsociada().retirar("Retirada en cajero automático", x);
	}

	public void ingresar(double x) throws Exception {
		super.getmCuentaAsociada().ingresar("Ingreso en cajero automático", x);
	}

	public void pagoEnEstablecimiento(String datos, double x) throws Exception {
		super.getmCuentaAsociada().retirar("Compra en :" + datos, x);
	}

	public double getSaldo() {
		return super.getmCuentaAsociada().getSaldo();
	}
}