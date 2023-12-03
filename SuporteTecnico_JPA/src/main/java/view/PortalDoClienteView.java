package view;

import model.Cliente;
import model.Ticket;
import service.ClienteService;
import service.OcorrenciaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class PortalDoClienteView {
    private Cliente cliente;
    private static OcorrenciaService ocorrenciaService;
    private static ClienteService clienteService;

    public  PortalDoClienteView(Cliente cliente){
        this.cliente = cliente;

        if (ocorrenciaService == null || clienteService == null){
            ocorrenciaService = new OcorrenciaService();
            clienteService = new ClienteService();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public void iniciar(){
        while (true) {
            try {
                System.out.println("+= Portal do Cliente =+");
                System.out.println("1. Criar Ticket");
                System.out.println("2. Consultar Ticket");
                System.out.println("3. Atualizar Ticket");
                System.out.println("4. Consultar Perfil");
                System.out.println("5. Atualizar Perfil");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) { this.criarTicket(); }
                else if (opcao == 2) { this.consultarTicket(); }
                else if (opcao == 3) { this.atualizarTicket(); }
                else if (opcao == 4) { this.consultarPerfil(); }
                else if (opcao == 5) { this.atualizarPerfil(); }
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e.getMessage());
            }
        }
    }

    private void criarTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Criação de Ticket");

        //  solicita os dados do Ticket
        System.out.print("Tipo de ticket: ");
        String tipo = scanner.nextLine();
        System.out.print("Descrição do Ticket: ");
        String descricao = scanner.nextLine();
        System.out.print("Status do Ticket: ");
        String status = scanner.nextLine();

        // Cria o Ticket
        Ticket ocorrencia = new Ticket();
        LocalDateTime data = LocalDateTime.now();
        ocorrencia.setProtocolo(String.format("%d%d%d-%d%d%d", data.getYear(), data.getMonthValue(), data.getDayOfMonth(), data.getHour(), data.getMinute(), data.getSecond()));
        ocorrencia.setTipo(tipo);
        ocorrencia.setDescricao(descricao);
        ocorrencia.setStatus(status);
        ocorrencia.setData(new Date());

        // Associa o cliente atual ao ticket
        ocorrencia.setCliente(this.cliente);

        // Salva no banco de dados usando o serviço do ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        ocorrenciaService.persistir(ocorrencia);

        System.out.println("Ticket criado com sucesso!");
    }

    private void consultarTicket(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Consulta de Ticket");
        System.out.print("Protocolo do Ticket: ");
        String protocolo = scanner.nextLine();

        // Consulta o registro usando o service do Ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ticket = ocorrenciaService.consultar(protocolo);

        if (ticket != null) {
            // Imprime o resultado
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

        // Consulta o registro usando o service da Ticket
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ticket = ocorrenciaService.consultar(protocolo);

        if (ticket != null) {
            System.out.println("Ticket encontrado. Atributos disponíveis para atualização:");

            // Lista os atributos para alteração
            System.out.println("1. Tipo: " + ticket.getTipo());
            System.out.println("2. Descrição: " + ticket.getDescricao());
            System.out.println("3. Status: " + ticket.getStatus());


            System.out.println("0. Salvar e Sair");

            //  permite o usuário escolher atributos para atualização
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

    private void consultarPerfil(){
        if (this.cliente != null) {
            System.out.println("Perfil do Cliente:");
            System.out.println("CPF: " + this.cliente.getCpf());
            System.out.println("Nome: " + this.cliente.getNome());
            System.out.println("Email: " + this.cliente.getEmail());
            System.out.println("Telefone: " + this.cliente.getTelefone());
            System.out.println("Endereço: " + this.cliente.getEndereco());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void atualizarPerfil(){
        Scanner scanner = new Scanner(System.in);

        // Listar atributos para alteração
        System.out.println("Atualização de Perfil");
        System.out.println("1. Nome: " + this.cliente.getNome());
        System.out.println("2. Email: " + this.cliente.getEmail());
        System.out.println("3. Telefone: " + this.cliente.getTelefone());
        System.out.println("4. Endereço: " + this.cliente.getEndereco());


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
                this.cliente.setNome(novoNome);
            } else if (escolha == 2) {
                System.out.print("Novo Email: ");
                String novoEmail = scanner.nextLine();
                this.cliente.setEmail(novoEmail);
            } else if (escolha == 3) {
                System.out.print("Novo Telefone: ");
                Integer novoTelefone = scanner.nextInt();
                this.cliente.setTelefone(novoTelefone);
            } else if (escolha == 4) {
                System.out.print("Novo Endereço: ");
                String novoEndereco = scanner.nextLine();
                this.cliente.setEndereco(novoEndereco);
            }

        }

        // Atualiza o registro usando o service do Cliente
        ClienteService clienteService = new ClienteService();
        clienteService.atualizar(this.cliente);

        System.out.println("Perfil atualizado com sucesso!");
    }
}
