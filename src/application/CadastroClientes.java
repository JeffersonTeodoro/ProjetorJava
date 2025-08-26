package application;

import java.io.*;
import java.util.*;


class Cliente {
        String nome;
        String email;
        String telefone;

        Cliente(String nome, String email, String telefone) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
        }
        @Override
        public String toString() {
            return nome + " ; " + email + " ; " + telefone;
        }
        public static Cliente fromString(String linha) {
            String[] dados = linha.split(" ; ");
            return new Cliente(dados[0], dados[1], dados[2]);
        }
}

public class CadastroClientes {
    static final String ARQUIVO = "clientes.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       while (true) {
        System.out.println("\n=== Sistema de Cadastro de Cleintes ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2 listar Cleintes");
        System.out.println("3. Sair");
        System.out.println("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha

        if (opcao == 1) {
            cadastrarCliente(sc);
        } else if (opcao == 2) {
            listarClientes();
        } else if (opcao == 3) {
            System.out.println("Encerrando o sistema...");
            break;
        } else {
            System.out.println("Opção inválida. Tente novamente.");
       }
        sc.close();
    }
}

    // 🔹 Cadastrar e salvar no arquivo
    public static void cadastrarCliente(Scanner sc) {
        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
             
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

          Cliente c = new Cliente(nome, email, telefone);
            bw.write(c.toString());
            bw.newLine();

            System.out.println (" ✅ Cliente cadastrado e salvo em arquivo! ");
            } catch (IOException e) {
                System.out.println("❌ Erro ao salvar o cliente: " + e.getMessage());
            }

        }
    // 🔹 Listar clientes do arquiv
    public static void listarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            System.out.println("\n=== Lista de Clientes ===");
            while ((linha = br.readLine()) != null) {
                Cliente c = Cliente.fromString(linha);
                System.out.println("Nome: " + c.nome + ", Email: " + c.email + ", Telefone: " + c.telefone);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum cliente cadastrado ainda.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            
        }
    }

}
