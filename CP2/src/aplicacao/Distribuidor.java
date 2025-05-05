package aplicacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entidades.Encomenda;
import entidades.Produto;

import filas.FilaEncomendas;
import filas.FilaProdutos;

public class Distribuidor {
	public static Scanner le = new Scanner(System.in);
	public static void main(String[] args) {
	
		FilaEncomendas filaEncomendas = new FilaEncomendas();
		FilaProdutos filaProdutos = new FilaProdutos();
		geraFilaEncomendas(filaEncomendas);

		int opcao;
		do {
			System.out.println("0 - Encerrar atendimento");
			System.out.println("1 - Inserir encomenda na fila para aguarda atendimento");
			System.out.println("2 - Atender uma encomenda");
			System.out.println("Opcao: ");
			opcao = le.nextInt();
			switch (opcao) {
			case 0:
				break;
			case 1:
				System.out.print("Informe ID do cliente: ");
				int clienteID = le.nextInt();
				System.out.print("Nome do arquivo de produtos encomendados: ");
				String nomeArquivo = le.next();
		
				Encomenda encomenda = new Encomenda(clienteID, nomeArquivo);
				filaEncomendas.enqueue(encomenda);
				break;
			case 2:
				if(filaEncomendas.isEmpty())
					System.out.println("Nao ha encomendas para serem atendidas");
				else {
					Encomenda enc = filaEncomendas.dequeue();

					boolean validacao = geraFilaProdutos(filaProdutos, enc.getNomeArquivo());

					if(!validacao) {
						System.out.println("Arquivo de encomenda nao presente");
            			break;
					}

					System.out.println("Atendimento do pedido do cliente " + enc.getClienteID() + " esta iniciando");
					System.out.println();

					int tamanhoFila = filaProdutos.cont;
					double total = 0;

					for(int i = 0; i < tamanhoFila; i++){
						Produto prod = filaProdutos.dequeue();

						System.out.println("Produto [codigo= " + prod.getCodigo() + ", descricao= " + prod.getDescricao() + ", preco= " + prod.getPreco() + ", localizacao= " + prod.getLocalizacao() + "]");
						
						System.out.println("O produto foi encontrado na prateira? (1-sim):");
						int opcaoProduto = le.nextInt();

						if(opcaoProduto != 1) {
							filaProdutos.enqueue(prod);
							tamanhoFila++;
							System.out.println("Voltar depois para colocar no carrinho");
						} else {
							tamanhoFila--;
							i--;
							total += prod.getPreco();
						}
					}

					System.out.println("Atendimento da encomenda foi finalizada com sucesso");
            		System.out.println("	Valor total da compra: R$" + String.format("%.2f", total));
				}
				break;
			default:
				System.out.println("Opcao Invalida");
			}
		} while (opcao != 0);
		le.close();
			}

	public static void geraFilaEncomendas(FilaEncomendas fila) {
		String caminhoDoArquivo = "src\\arquivos\\ListaEncomendas.txt";
		
		try {
			File arquivo = new File(caminhoDoArquivo);
			Scanner leArq = new Scanner(arquivo);

			while (leArq.hasNextLine()) {
				String linha = leArq.nextLine();
				String[] partes = linha.split(",");

				int clientID = Integer.parseInt(partes[0].trim());
				String nomeArquivo = partes[1].trim();

				Encomenda obj = new Encomenda(clientID, nomeArquivo);
				System.out.println(obj);
				System.out.println();
				fila.enqueue(obj);
			}
			leArq.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Erro ao converter clienteID para inteiro: " + e.getMessage());
		}
	}

	public static boolean geraFilaProdutos(FilaProdutos filaProd, String nomeArquivo) {
		try {
			File arquivo = new File("src\\arquivos\\" + nomeArquivo);
			Scanner leArq = new Scanner(arquivo);

			while (leArq.hasNextLine()) {
				String linha = leArq.nextLine();
				String[] partes = linha.split(",");

				String codigo = partes[0].trim();
				String descricao = partes[1].trim();
				double preco = Double.parseDouble(partes[2].trim());
				String localizacao = partes[3].trim();

				Produto produto = new Produto(codigo, descricao, preco, localizacao);
				filaProd.enqueue(produto);
			}
			leArq.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: " + e.getMessage());
			return false;
		} catch (NumberFormatException e) {
			System.out.println("Erro ao converter preco para double: " + e.getMessage());
			return false;
		}
	}
}


