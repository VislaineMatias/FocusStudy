package org.example;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class EstudosEPremiações {
    static int pontos = 0;

    public static void main (String [] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("----------FocusStudy----------");

            LocalDate hoje = LocalDate.now();
            System.out.println("----------" + hoje + "----------");

            System.out.println("Qual será o seu método de estudos hoje?");
            System.out.println("1 - Pomodoro");
            System.out.println("2 - Interleaving");
            System.out.println("3 - Trocar pontos por prêmios");
            System.out.print("Sua escolha: ");

            int escolhaMetodo = scanner.nextInt();

            if (escolhaMetodo == 1){
                System.out.println("O método Pomodoro é uma técnica de gestão de tempo criada por Francisco Cirillo. \n" +
                        "Consiste em estudo focado por 25 minutos e pausa curta de 5 minutos. Sendo 4 pomodoros. \n" +
                        "No caso desse protótipo, o tempo foi reduzido para 5s de estudos e 2s de pausa.");
                System.out.println("Deseja iniciar? (s)");
                String iniciar = scanner.next();
                if(iniciar.equalsIgnoreCase("s")){
                    pomodoro();
                    pontos += 200;
                    System.out.println("Parabéns! Você ganhou 200 pontos com os seus estudos!.");
                    System.out.println("Seu total de pontos agora é de: " + pontos);
                }
            } else if (escolhaMetodo == 2) {
                System.out.println("O método Interleaving consiste em estudar assuntos diferentes alternadamente. \n" +
                        "Surgiu a partir de pesquisas em psicologia cognitiva sobre aprendizagem. \n" +
                        "No caso desse protótipo, o tempo de estudos ficou definido em 5s.");
                interleaving();
            } else if (escolhaMetodo == 3) {
                trocarPontos(scanner);
            } else {
                System.out.println("Por favor, escolha um numeral válido.");
            }

            System.out.print("Deseja retornar ao menu? (s/n): ");
            String resposta = scanner.next();
            if (!resposta.equalsIgnoreCase("s")) {
                continuar = false; // sai do laço
                System.out.println("Até logo!");
            }
        }
    }

    public static void pomodoro() throws InterruptedException{
        int ciclos = 4;

        for (int i = 1; i<=ciclos; i++){
            System.out.println(" --- Ciclo " + i + " ---");

            System.out.println("Tempo de estudo:");
            contagemRegressiva(5);

            System.out.println("Hora da pausa:");
            contagemRegressiva(2);
        }
        System.out.println("Pomodoro finalizados!");
    }

    public static void interleaving () throws InterruptedException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos assuntos você irá estudar? ");
        int quantdAssuntos = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> assuntos = new ArrayList<>();
        for (int i = 1; i <= quantdAssuntos; i++) {
            System.out.print("Digite o nome do assunto " + i + ": ");
            assuntos.add(scanner.nextLine());
        }

        System.out.print("Quantas repetições de estudo você quer fazer? ");
        int ciclos = scanner.nextInt();

        for (int i = 1; i <= ciclos; i++) {
            System.out.println("--- Ciclo " + i + " ---");

            for (String assunto : assuntos) {
                System.out.println("Estudando " + assunto + "...");
                contagemRegressiva(5);
            }
        }
        System.out.println("Estudos finalizados! Bom descanso.");

        int pontosGanhos = ciclos * 50;
        pontos += pontosGanhos;

        System.out.println("Você ganhou " + pontosGanhos + " pontos com o Interleaving.");
        System.out.println("Seu total de pontos agora é: " + pontos);
    }

    public static void contagemRegressiva(int segundos) throws InterruptedException {
        for (int t = segundos; t >= 1; t--) {
            System.out.print(t + "s... \r");
            Thread.sleep(1000);
        }
        System.out.println();
    }

    public static void trocarPontos(Scanner scanner) {
        System.out.println("----- Loja de Recompensas -----");
        System.out.println("1 - 100 pontos: Curso na Linux Tips");
        System.out.println("2 - 150 pontos: 1 mês de plano premium");
        System.out.println("3 - 300 pontos: Bolsa no Programe como uma Garota");
        System.out.println("Seu saldo atual: " + pontos + " pontos");
        System.out.print("Escolha sua recompensa: ");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                if (pontos >= 100) {
                    pontos -= 100;
                    System.out.println("Parabéns! Você resgatou um curso na Linux Tips.");
                } else {
                    System.out.println("Pontos insuficientes!");
                }
                break;
            case 2:
                if (pontos >= 150) {
                    pontos -= 150;
                    System.out.println("Parabéns! Você resgatou 1 mês de plano premium.");
                } else {
                    System.out.println("Pontos insuficientes!");
                }
                break;
            case 3:
                if (pontos >= 300) {
                    pontos -= 300;
                    System.out.println("Parabéns! Você resgatou uma bolsa no Programe como uma Garota.");
                } else {
                    System.out.println("Pontos insuficientes!");
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }

        System.out.println("Saldo restante: " + pontos + " pontos");
    }
}
