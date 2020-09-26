package projeto_conta_corrente;

public class ContaCorrente {

	private int numeroConta;
	private double saldo;
	private double limiteEspecial;
	private String nomeCliente;

	public boolean limitar(double rendaMensal) {
		if (rendaMensal < 0)
			return false;
		if (rendaMensal >= 6000)
			this.limiteEspecial = 3000;
		else
			this.limiteEspecial = rendaMensal / 2;
		return true;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimiteEspecial() {
		return limiteEspecial;
	}

	public void setLimiteEspecial(double limiteEspecial) {
		this.limiteEspecial = limiteEspecial;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Override
	public String toString() {
		String s = new String();
		s += "Conta corrente: " + numeroConta + "\n";
		s += "Nome do titular: " + nomeCliente + "\n";
		s += "Limite do especial: " + limiteEspecial + "\n";
		s += "Saldo: " + saldo;

		return s;
	}
}