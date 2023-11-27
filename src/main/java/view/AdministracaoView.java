package view;

import model.Ocorrencia;
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

        // Encerra services
        scanner.close();
        atendenteService.close();
        ocorrenciaService.close();
        clienteService.close();
    }

    private void cadastrarAtendente(){
        // Solicitar dados do atendente

        // Salvar no banco de dados usando o sevice do Atendente
    }

    private void consultarAtendente(){
        // Solicitar matricula do atendente

        // consultar registro usando o service do Atendente

        // Imprimir resultado
    }

    private void atualizarAtendente(){
        // Solicitar matricula do atendente

        // Listar atributos para alteração. Ex:
        // 1. Nome: Atendente 1
        // 2. Email: atendente1@gmail.com
        // 0. Salvar
        // Quando seleciona um atributo, solicita novo valor

        // atualizar registro usando o service do Atendente
    }

    private void deletarAtendente(){
        // Solicitar matricula do atendente

        // Exibe informações e solicita confirmação. Ex:
        // Nome: Atendente 1
        // Email: atendente1@gmail.com
        // Deseja mesmo deletar?
        // 1. Sim
        // 0. Não

        // deletar registro usando o service do Atendente
    }

    private void deletarOcorrencia(){
        // Solicitar protocolo da ocorrencia

        // Exibe informações e solicita confirmação. Ex:
        // Protocolo: 20231127-00001
        // Atendente: Atendente 1
        // ...
        // Deseja mesmo deletar?
        // 1. Sim
        // 0. Não

        // deletar registro usando o service da Ocorrência
    }

    private void deletarCliente(){
        // Solicitar cpf do cliente

        // Exibe informações e solicita confirmação. Ex:
        // Nome: Cliente 1
        // Email: cliente1@gmail.com
        // ...
        // Deseja mesmo deletar?
        // 1. Sim
        // 0. Não

        // deletar registro usando o service do Cliente
    }

}
