package etsisi.ems.trabajo3.tarjetas;

public class Debito extends Tarjeta {
	
	public Debito(Tarjeta tarjeta) {
		super(tarjeta.getmNumero(), tarjeta.getmTitular(), tarjeta.getmFechaDeCaducidad());
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