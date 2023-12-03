package view;

import model.Atendente;
import model.Cliente;
import model.Ticket;
import service.AtendenteService;
import service.ClienteService;
import service.OcorrenciaService;

import java.util.Scanner;

public class AdministracaoView {

    static Scanner scanner;
    static AtendenteService atendenteService;
    static OcorrenciaService ocorrenciaService;
    static ClienteService clienteService;

    public AdministracaoView(){
        if (
            scanner == null
            || atendenteService == null
            || ocorrenciaService == null
            || clienteService == null
        ){
            scanner = new Scanner(System.in);
            atendenteService = new AtendenteService();
            ocorrenciaService = new OcorrenciaService();
            clienteService = new ClienteService();
        }
    }

    public void iniciar(){
        while (true) {
            try {
                System.out.println("+= Administração =+");
                System.out.println("1. Cadastrar Atendente");
                System.out.println("2. Consultar Atendente");
                System.out.println("3. Atualizar Atendente");
                System.out.println("4. Deletar Atendente");
                System.out.println("5. Deletar Ocorrência");
                System.out.println("6. Deletar Cliente");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) { this.cadastrarAtendente(); }
                else if (opcao == 2) { this.consultarAtendente(); }
                else if (opcao == 3) { this.atualizarAtendente(); }
                else if (opcao == 4) { this.deletarAtendente(); }
                else if (opcao == 5) { this.deletarOcorrencia(); }
                else if (opcao == 6) { this.deletarCliente(); }
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e.getMessage());
            }
        }

    }

    private void cadastrarAtendente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Atendente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Atendente novoAtendente = new Atendente( nome, email, senha);


        AtendenteService atendenteService = new AtendenteService();
        atendenteService.persistir(novoAtendente);

        System.out.println("Atendente cadastrado com sucesso!");



    }

    private void consultarAtendente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Consulta de Atendente");
        System.out.print("Matrícula do Atendente: ");
        Long matricula = scanner.nextLong();
        scanner.nextLine();


        AtendenteService atendenteService = new AtendenteService();
        Atendente atendente = atendenteService.consultar(matricula);

        if (atendente != null) {

            System.out.println("Matrícula: " + atendente.getMatricula());
            System.out.println("Nome: " + atendente.getNome());
            System.out.println("Email: " + atendente.getEmail());

        } else {
            System.out.println("Atendente não encontrado.");
        }

    }

    private void atualizarAtendente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Atualização de Atendente");
        System.out.print("Matrícula do Atendente: ");
        Long matricula = scanner.nextLong();


        AtendenteService atendenteService = new AtendenteService();
        Atendente atendente = atendenteService.consultar(matricula);

        if (atendente != null) {
            System.out.println("Atendente encontrado. Atributos disponíveis para atualização:");


            System.out.println("1. Nome: " + atendente.getNome());
            System.out.println("2. Email: " + atendente.getEmail());
            System.out.println("3. Senha: " + atendente.getSenha());


            System.out.println("0. Salvar e Sair");


            while (true) {
                System.out.print("Escolha o número do atributo para atualizar (ou 0 para salvar): ");
                int escolha = scanner.nextInt();
                scanner.nextLine();

                if (escolha == 0) {

                    break;
                } else if (escolha == 1) {
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    atendente.setNome(novoNome);
                } else if (escolha == 2) {
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    atendente.setEmail(novoEmail);
                } else if (escolha == 3) {
                    System.out.print("Nova Senha: ");
                    String novaSenha = scanner.nextLine();
                    atendente.setSenha(novaSenha);
                }

            }


            atendenteService.atualizar(atendente);

            System.out.println("Atendente atualizado com sucesso!");
        } else {
            System.out.println("Atendente não encontrado.");
        }

    }

    private void deletarAtendente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exclusão de Atendente");
        System.out.print("Matrícula do Atendente: ");
        Long matricula = scanner.nextLong();


        AtendenteService atendenteService = new AtendenteService();
        Atendente atendente = atendenteService.consultar(matricula);

        if (atendente != null) {

            System.out.println("Nome: " + atendente.getNome());
            System.out.println("Email: " + atendente.getEmail());


            System.out.println("Deseja mesmo deletar?");
            System.out.println("1. Sim");
            System.out.println("0. Não");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {

                atendenteService.deletar(atendente);
                System.out.println("Atendente deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Atendente não encontrado.");
        }

    }

    private void deletarOcorrencia(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exclusão de Ocorrência");
        System.out.print("Protocolo da Ocorrência: ");
        String protocolo = scanner.nextLine();

        // Consulta o registro usando o service da Ocorrência
        OcorrenciaService ocorrenciaService = new OcorrenciaService();
        Ticket ocorrencia = ocorrenciaService.consultar(protocolo);

        if (ocorrencia != null) {
            // Exibe as  informações
            System.out.println("Protocolo: " + ocorrencia.getProtocolo());
            System.out.println("Tipo: " + ocorrencia.getTipo());
            System.out.println("Descrição: " + ocorrencia.getDescricao());
            System.out.println("Data: " + ocorrencia.getData());
            System.out.println("Status: " + ocorrencia.getStatus());
            System.out.println("Cliente: " + ocorrencia.getCliente().getNome());
            System.out.println("Atendente: " + ocorrencia.getAtendente().getNome());

            // Solicita a confirmação
            System.out.println("Deseja mesmo deletar?");
            System.out.println("1. Sim");
            System.out.println("0. Não");

            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (escolha == 1) {
                // Deleta o registro usando o service da Ocorrência
                ocorrenciaService.deletar(ocorrencia);
                System.out.println("Ocorrência deletada com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Ocorrência não encontrada.");
        }

    }

    private void deletarCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exclusão de Cliente");
        System.out.print("CPF do Cliente: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();

        // Consulta o registro usando o service do Cliente
        ClienteService clienteService = new ClienteService();
        Cliente cliente = clienteService.consultar(cpf);

        if (cliente != null) {
            // Exibi as  informações
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());


            // Solicita a confirmação
            System.out.println("Deseja mesmo deletar?");
            System.out.println("1. Sim");
            System.out.println("0. Não");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                // Deleta o registro usando o service do Cliente
                clienteService.deletar(cliente);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }

    }
    }


