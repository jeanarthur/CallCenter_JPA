package view;

import model.Atendente;

import java.util.Scanner;

public class SistemaView {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("+= CallCenter =+");
                System.out.println("1. Administração");
                System.out.println("2. Atendimento");
                System.out.println("0. Sair");
                System.out.print("Acessar opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) { acessarAdministracao(); }
                else if (opcao == 2) { acessarAtendimento(); }
                else if (opcao == 0) { break; }

            } catch (Exception e){
                System.out.println("Operação inválida, tente novamente! " + e);
            }
        }

        System.out.println("Sistema finalizado!");
    }

    private static void acessarAdministracao(){
        // Solicitar credenciais login e senha
        // validar caso login = root e senha = root

        // iniciar view de Administração caso validado
        AdministracaoView administracaoView = new AdministracaoView();
        administracaoView.iniciar();
    }

    private static void acessarAtendimento(){
        // solicitar login do atendente
        // login = matricula

        // implementar lógica para validar existência do atendente
        // inicializar view de Atendimento passando o atendente como parâmetro
        AtendimentoView atendimentoView = new AtendimentoView(new Atendente());
        atendimentoView.iniciar();
    }

}
