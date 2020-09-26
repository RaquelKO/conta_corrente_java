package projeto_conta_corrente;

import java.util.ArrayList;
import java.util.Scanner;

public class TesteContaCorrente {

	public static void main(String[] args) {

		// Objetos
		Scanner scan = new Scanner(System.in);
		ContaCorrente c = new ContaCorrente();
		ArrayList<ContaCorrente> contas = new ArrayList<ContaCorrente>();

		// Variáveis
		int opcao = 0;
		Boolean bool = false;
		double v = 0;
		double saldoTotal = 0;

		do {
			// MENU
			System.out.println();
			System.out.println("Bem vindo! Selecione a opção desejada no menu a seguir.\n");
			System.out.println("1: CADASTRAR NOVA CONTA CORRENTE");
			System.out.println("2: DEPOSITAR");
			System.out.println("3: SACAR");
			System.out.println("4: CONFERIR SALDO DA CONTA");
			System.out.println("5: CONFERIR SOMATÓRIO SALDO CLIENTES SEM USO DO ESPECIAL");
			System.out.println("6: SAIR");
			System.out.println("\nDigite o número da opção desejada: ");

			opcao = tryParseInt(scan.nextLine(), 0);
			System.out.println();

			switch (opcao) {
				case 1: // CADASTRAR
					c = new ContaCorrente();

					System.out.println("Digite seu nome: ");
					c.setNomeCliente(scan.nextLine());
					System.out.println();

					do {
						System.out.println("Digite sua renda mensal: ");
						if (!c.limitar(tryParseDouble(scan.nextLine(), -1))) {
							bool = true;
							System.out.println("Erro. Informe uma renda mensal superior a R$ 0,00.");
						} else
							bool = false;
						System.out.println();
					} while (bool);

					c.setSaldo(0.00);
					c.setNumeroConta(contas.size() + 1000);
					contas.add(c);
					System.out.println("Conta criada com sucesso! Informações:");
					System.out.println(c);
					break;

				case 2: // DEPOSITAR
					c = getNumeroConta(contas, scan);
					v = verificaValor(contas, scan);
					c.setSaldo(c.getSaldo() + v);
					System.out.println("Depósito realizado com sucesso! Informações:");
					System.out.println(c);
					break;

				case 3: // SACAR
					c = getNumeroConta(contas, scan);
					do {
						v = verificaValor(contas, scan);
						if ((c.getSaldo() + c.getLimiteEspecial()) < v) {
							bool = true;
							System.out.println("Saldo insuficiente. Digite um novo valor.");
						} else {
							bool = false;
							c.setSaldo(c.getSaldo() - v);
						}
					} while (bool);
					System.out.println("Saque realizado com sucesso! Informações:");
					System.out.println(c);
					break;

				case 4: // CONFERIR SALDO
					c = getNumeroConta(contas, scan);
					System.out.println("Seu saldo é de R$ " + c.getSaldo() + ".");
					break;

				case 5: // SOMATÓRIO SALDO CLIENTES
					saldoTotal = 0;
					for (ContaCorrente conta : contas) {
						if (conta.getSaldo() > 0)
							saldoTotal += conta.getSaldo();
					}

					System.out.println("A somatória do saldos dos clientes é de R$ " + saldoTotal + ".");
					break;
			}

		} while (opcao != 6); // SAIR SE OPÇÃO = 6

		scan.close();
	}

	// Busca por conta válida
	public static ContaCorrente getNumeroConta(ArrayList<ContaCorrente> contas, Scanner scan) {
		Boolean bool = false;
		ContaCorrente c = new ContaCorrente();

		do {
			System.out.println("Digite o número da Conta Corrente: ");
			c = verificaNumeroConta(tryParseInt(scan.nextLine(), 0), contas);
			System.out.println();
			if (c == null) {
				bool = true;
				System.out.println("Erro. Informe um número de Conta Corrente existente.");
			} else
				bool = false;
		} while (bool);

		return c;
	}

	// Verifica se o número da conta existe
	public static ContaCorrente verificaNumeroConta(int numeroConta, ArrayList<ContaCorrente> contas) {
		for (ContaCorrente conta : contas) {
			if (conta.getNumeroConta() == numeroConta)
				return conta;
		}
		return null;
	}

	// Impede que o cliente saque/deposite um valor negativo ou igual a zero
	public static double verificaValor(ArrayList<ContaCorrente> contas, Scanner scan) {
		Boolean bool = false;
		double valor = 0;

		do {
			System.out.println("Digite o valor desejado: ");
			valor = tryParseDouble(scan.nextLine(), 0);
			System.out.println();
			if (valor <= 0) {
				bool = true;
				System.out.println("Erro. Informe um valor válido.");
			} else {
				bool = false;
			}
		} while (bool);

		return valor;
	}

	// Impede que o cliente digite algo diferente de um int
	public static int tryParseInt(String valorAVerificar, int valorSeErro) {
		try {
			return Integer.parseInt(valorAVerificar);
		} catch (NumberFormatException excecao) {
			return valorSeErro;
		}
	}

	// Impede que o cliente digite algo diferente de um double
	public static double tryParseDouble(String valorAVerificar, double valorSeErro) {
		try {
			return Double.parseDouble(valorAVerificar);
		} catch (NumberFormatException excecao) {
			return valorSeErro;
		}
	}

}
