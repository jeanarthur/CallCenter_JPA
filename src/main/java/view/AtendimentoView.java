package view;

import model.Atendente;
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
                System.out.println("1. Criar Ocorrência");
                System.out.println("2. Consultar Ocorrência");
                System.out.println("3. Atualizar Ocorrência");
                System.out.println("4. Consultar Cliente");
                System.out.println("5. Atualizar Cliente");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) {}
                else if (opcao == 2) {}
                else if (opcao == 3) {}
                else if (opcao == 4) {}
                else if (opcao == 5) {}
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e.getMessage());
            }
        }

        // Encerra serviços
        scanner.close();
        ocorrenciaService.close();
        clienteService.close();
    }

    private void criarOcorrencia(){
        // Solicitar dados da ocorreencia

        // Ao solicitar contato, pedir CPF, para consultar se existe
        // se não existir solicitar dados adicionai para cadastrá-lo

        // usar this.atendente no atributo atendente da ocorrência

        // Salvar no banco de dados usando o sevice do Atendente
    }

    private void consultarOcorrencia(){
        // Solicitar protocolo da ocorrência

        // consultar registro usando o service da Ocorrência

        // Imprimir resultado
    }

    private void atualizarOcorrencia(){
        // Solicitar protocolo da ocorrência

        // Listar atributos para alteração. Ex:
        // 1. Tipo: Atualização de limite
        // 2. Status: Em Andamento
        // ...
        // 0. Salvar
        // Quando seleciona um atributo, solicita novo valor

        // atualizar registro usando o service do Atendente
    }

    private void consultarCliente(){
        // Solicitar CPF do cliente

        // consultar registro usando o service do Cliente

        // Imprimir resultado
    }

    private void atualizarCliente(){
        // Solicitar CPF do cliente

        // Listar atributos para alteração. Ex:
        // Nome: Cliente 1
        // Email: cliente1@gmail.com
        // ...
        // 0. Salvar
        // Quando seleciona um atributo, solicita novo valor

        // atualizar registro usando o service do Atendente
    }

}
