package etsisi.ems.trabajo3.banco;

public enum EntidadEmisoraTipo {
	MASTERCARD(1, 0.05),
	MAESTRO(2, 0.05),
    VISA_CLASICA(3, 0.03),
    VISA_ELECTRON(4, 0.02);
	
	private final int mTipo;
    private final double mComision;

    private EntidadEmisoraTipo(int tipo, double comision) {
        this.mTipo = tipo;
        this.mComision = comision;
    }
    
    public static EntidadEmisoraTipo getByTipo(double tipo) {
    	for (EntidadEmisoraTipo emt : EntidadEmisoraTipo.values()) {
            if (emt.mTipo == tipo) return emt;
        }
        throw new IllegalArgumentException("EntidadEmisoraTipo not found.");
    }
    
    public int getmTipo() {
    	return this.mTipo;
    }
    
    public double getmComision() {
    	return this.mComision;
    }
}
