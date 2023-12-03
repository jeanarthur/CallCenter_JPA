package view;

import model.Atendente;
import model.Cliente;
import model.Ticket;
import service.ClienteService;
import service.OcorrenciaService;

import java.util.Scanner;

public class AtendimentoView {

    private Atendente atendente;
    private static OcorrenciaService ocorrenciaService;
    private static ClienteService clienteService;

    public AtendimentoView(Atendente atendente){
        this.atendente = atendente;

        if (ocorrenciaService == null || clienteService == null){
            ocorrenciaService = new OcorrenciaService();
            clienteService = new ClienteService();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public void iniciar(){
        while (true) {
            try {
                System.out.println("+= Atendimento =+");
                System.out.println("1. Atender Ticket");
                System.out.println("2. Consultar Ticket");
                System.out.println("3. Atualizar Ticket");
                System.out.println("4. Consultar Cliente");
                System.out.println("5. Atualizar Cliente");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) { this.atenderTicket(); }
                else if (opcao == 2) { this.consultarTicket(); }
                else if (opcao == 3) { this.atualizarTicket(); }
                else if (opcao == 4) { this.consultarCliente(); }
                else if (opcao == 5) { this.atualizarCliente(); }
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e.getMessage());
            }
        }

    }

    private void atenderTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Atendimento de Ticket");
        System.out.print("Protocolo do Ticket: ");
        String protocolo = scanner.nextLine();

        // Consultar registro usando o service da Ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ticket = ocorrenciaService.consultar(protocolo);

        if (ticket != null) {
            // Imprimir resultado
            System.out.println("Ticket atribuído para você!");
            System.out.println("Protocolo: " + ticket.getProtocolo());
            System.out.println("Tipo: " + ticket.getTipo());
            System.out.println("Descrição: " + ticket.getDescricao());
            System.out.println("Data: " + ticket.getData());
            System.out.println("Status: " + ticket.getStatus());
            System.out.println("Cliente: " + ticket.getCliente().getNome());
            System.out.println("Atendente: " + this.atendente);

            ticket.setAtendente(this.atendente);
            ocorrenciaService.atualizar(ticket);
        } else {
            System.out.println("Ticket não encontrado.");
        }
    }

    private void consultarTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Consulta de Ticket");
        System.out.print("Protocolo do Ticket: ");
        String protocolo = scanner.nextLine();

        // Consultar registro usando o service da Ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ticket = ocorrenciaService.consultar(protocolo);

        if (ticket != null) {
            // Imprimir resultado
            System.out.println("Protocolo: " + ticket.getProtocolo());
            System.out.println("Tipo: " + ticket.getTipo());
            System.out.println("Descrição: " + ticket.getDescricao());
            System.out.println("Data: " + ticket.getData());
            System.out.println("Status: " + ticket.getStatus());
            System.out.println("Cliente: " + ticket.getCliente().getNome());
            if (ticket.getAtendente() != null) System.out.println("Atendente: " + ticket.getAtendente().getNome());
        } else {
            System.out.println("Ticket não encontrado.");
        }

    }

    private void atualizarTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Atualização de Ticket");
        System.out.print("Protocolo do Ticket: ");
        String protocolo = scanner.nextLine();

        // Consultar registro usando o service da Ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ticket = ocorrenciaService.consultar(protocolo);

        if (ticket != null) {
            System.out.println("Ticket encontrado. Atributos disponíveis para atualização:");

            // Listar atributos para alteração
            System.out.println("1. Tipo: " + ticket.getTipo());
            System.out.println("2. Descrição: " + ticket.getDescricao());
            System.out.println("3. Status: " + ticket.getStatus());


            System.out.println("0. Salvar e Sair");

            //  permitir o usuário escolher atributos para atualização
            while (true) {
                System.out.print("Escolha o número do atributo para atualizar (ou 0 para salvar): ");
                int escolha = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                if (escolha == 0) {
                    // Salvar e sair do loop
                    break;
                } else if (escolha == 1) {
                    System.out.print("Novo Tipo: ");
                    String novoTipo = scanner.nextLine();
                    ticket.setTipo(novoTipo);
                } else if (escolha == 2) {
                    System.out.print("Nova Descrição: ");
                    String novaDescricao = scanner.nextLine();
                    ticket.setDescricao(novaDescricao);
                } else if (escolha == 3) {
                    System.out.print("Novo Status: ");
                    String novoStatus = scanner.nextLine();
                    ticket.setStatus(novoStatus);
                }

            }

            // Atualiza o registro usando o service da Ticket
            ocorrenciaService.atualizar(ticket);

            System.out.println("Ticket atualizado com sucesso!");
        } else {
            System.out.println("Ticket não encontrado.");
        }

    }

    private void consultarCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Consulta de Cliente");
        System.out.print("CPF do Cliente: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();  // Limpar o buffer

        // Consultar registro usando o service do Cliente
        ClienteService clienteService = new ClienteService();
        Cliente cliente = clienteService.consultar(cpf);

        if (cliente != null) {
            // Imprimi o resultado
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());

        } else {
            System.out.println("Cliente não encontrado.");
        }

    }

    private void atualizarCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Atualização de Cliente");
        System.out.print("CPF do Cliente: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();

        // Consulta o  registro usando o service do Cliente
        ClienteService clienteService = new ClienteService();
        Cliente cliente = clienteService.consultar(cpf);

        if (cliente != null) {
            System.out.println("Cliente encontrado. Atributos disponíveis para atualização:");

            // Lista os atributos para alteração
            System.out.println("1. Nome: " + cliente.getNome());
            System.out.println("2. Email: " + cliente.getEmail());
            System.out.println("3. Telefone: " + cliente.getTelefone());
            System.out.println("4. Endereço: " + cliente.getEndereco());


            System.out.println("0. Salvar e Sair");

            //  permite o usuário escolher atributos para atualização
            while (true) {
                System.out.print("Escolha o número do atributo para atualizar (ou 0 para salvar): ");
                int escolha = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                if (escolha == 0) {
                    // Salva e sai do loop
                    break;
                } else if (escolha == 1) {
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    cliente.setNome(novoNome);
                } else if (escolha == 2) {
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    cliente.setEmail(novoEmail);
                } else if (escolha == 3) {
                    System.out.print("Novo Telefone: ");
                    int novoTelefone = scanner.nextInt();
                    scanner.nextLine();
                    cliente.setTelefone(novoTelefone);
                } else if (escolha == 4) {
                    System.out.print("Novo Endereço: ");
                    String novoEndereco = scanner.nextLine();
                    cliente.setEndereco(novoEndereco);
                }

            }

            // Atualiza o registro usando o service do Cliente
            clienteService.atualizar(cliente);

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }



    }

}
