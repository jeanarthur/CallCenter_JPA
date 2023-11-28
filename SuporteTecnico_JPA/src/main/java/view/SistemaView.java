package view;

import model.Atendente;
import model.Cliente;
import service.AtendenteService;
import service.ClienteService;

import java.util.Scanner;

public class SistemaView {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("+= CallCenter =+");
                System.out.println("1. Administração");
                System.out.println("2. Atendimento");
                System.out.println("3. Portal do Cliente");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) { acessarAdministracao(); }
                else if (opcao == 2) { acessarAtendimento(); }
                else if (opcao == 3) { acessarPortalDoCliente(); }
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e);
            }
        }

        System.out.println("Sistema finalizado!");
    }

    private static void acessarAdministracao(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Acesso à Administração");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Validar credenciais
        if (login.equals("root") && senha.equals("root")) {
            System.out.println("Credenciais válidas. Iniciando Administração...");

            // Iniciar view de Administração
            AdministracaoView administracaoView = new AdministracaoView();
            administracaoView.iniciar();
        } else {
            System.out.println("Credenciais inválidas. Acesso negado.");
        }

    }

    private static void acessarAtendimento(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Acesso ao Atendimento");
        System.out.print("Matrícula do Atendente: ");
        Long matricula = scanner.nextLong();
        scanner.nextLine();


        AtendenteService atendenteService = new AtendenteService();
        Atendente atendente = atendenteService.consultar(matricula);

        if (atendente != null) {
            System.out.println("Atendente encontrado. Iniciando Atendimento...");

            // Inicializar view de Atendimento passando o atendente como parâmetro
            AtendimentoView atendimentoView = new AtendimentoView(atendente);
            atendimentoView.iniciar();
        } else {
            System.out.println("Atendente não encontrado. Acesso negado.");
        }


    }

    private static void acessarPortalDoCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Acesso ao Portal do Cliente");
        System.out.print("CPF: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();  // Limpar o buffer
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Implementa a lógica para validar existência do cliente
        ClienteService clienteService = new ClienteService();
        Cliente cliente = clienteService.consultar(cpf);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.println("Cliente encontrado. Iniciando Portal do Cliente...");

            // Inicializa a view do Portal do Cliente passando o cliente como parâmetro
            PortalDoClienteView portalDoClienteView = new PortalDoClienteView(cliente);
            portalDoClienteView.iniciar();
        } else {
            System.out.println("Cliente não encontrado ou senha incorreta.");

            // Solicita o cadastro caso o cliente não exista
            System.out.print("Deseja cadastrar um novo cliente? (Digite 'sim' ou 'nao'): ");
            String cadastrarNovoCliente = scanner.nextLine();

            if ("sim".equalsIgnoreCase(cadastrarNovoCliente)) {
                // Solicita os dados para o novo cliente e realiza o cadastro
                System.out.println("Cadastro de Novo Cliente:");
                System.out.print("Nome: ");
                String nomeNovoCliente = scanner.nextLine();
                System.out.print("Email: ");
                String emailNovoCliente = scanner.nextLine();
                System.out.print("Telefone: ");
                Integer telefoneNovoCliente = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer
                System.out.print("Endereço: ");
                String enderecoNovoCliente = scanner.nextLine();
                System.out.print("Nova Senha: ");
                String senhaNovoCliente = scanner.nextLine();

                // Cria a instância do novo cliente
                Cliente novoCliente = new Cliente(cpf, nomeNovoCliente, emailNovoCliente, senhaNovoCliente,
                        telefoneNovoCliente, enderecoNovoCliente);

                // Salva o novo cliente no banco de dados
                clienteService.persistir(novoCliente);

                System.out.println("Novo cliente cadastrado com sucesso. Iniciando Portal do Cliente...");
                // Inicializa view do Portal do Cliente passando o novo cliente como parâmetro
                PortalDoClienteView portalDoClienteView = new PortalDoClienteView(novoCliente);
                portalDoClienteView.iniciar();
            } else {
                System.out.println("Acesso negado. Saindo do Portal do Cliente.");
            }
        }
    }

}
