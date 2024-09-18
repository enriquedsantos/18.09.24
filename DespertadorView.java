package view;
import java.util.*;
import controller.*;

public class DespertadorView {
    private static final String RESET = "\033[0m";    
    private static final String RED = "\033[0;31m";      
    private static final String YELLOW = "\033[0;33m"; 
    private static final String MAGENTA = "\033[0;35m";
    private static final String FBRANCO = "\033[47m";
    private static final String VERDE = "\033[0;32m";
   

    

    public static int horaDespertar;
    public static int minutoDespertar;
    public static int segundoDespertar;
    public static int adiar = 10; 
    public static int[] adiamentos = {5, 10, 15, 20};

    public static Date date = null;
    public static Calendar calendar = null;

    public static int horaAtual;
    public static int minutoAtual;
    public static int segundoAtual;

    public static void main(String[] args) {
        boolean sair = false;

        Scanner scnDespertador = new Scanner(System.in);
        int respostaUsuario;
        Scanner scnConfigurar = new Scanner(System.in);

        DespertadorController.getHMS();

        System.out.println(
            String.format(
                "%sAgora são: %02d:%02d:%02d.%s", 
                FBRANCO, horaAtual, minutoAtual, segundoAtual, RESET
            )
        );

        System.out.println(YELLOW + "Digite abaixo a hora que você deseja configurar o despertador e tecle Enter:");
        horaDespertar = scnConfigurar.nextInt();

        System.out.println(MAGENTA + "Digite abaixo o minuto que você deseja configurar o despertador e tecle Enter:");
        minutoDespertar = scnConfigurar.nextInt();

        System.out.println(VERDE + "Digite abaixo o segundo que você deseja configurar o despertador e tecle Enter:");
        segundoDespertar = scnConfigurar.nextInt();

        while (!sair) {
            DespertadorController.getHMS();

            System.out.println(
                String.format(
                    "%sAgora são: %02d:%02d:%02d. O próximo alarme irá despertar às %02d:%02d:%02d%s", 
                    FBRANCO, horaAtual, minutoAtual, segundoAtual, horaDespertar, minutoDespertar, segundoDespertar, RESET
                )
            );

            int totalSegundosAtual = horaAtual * 3600 + minutoAtual * 60 + segundoAtual;
            int totalSegundosDespertar = horaDespertar * 3600 + minutoDespertar * 60 + segundoDespertar;
            int tempoRestante = totalSegundosDespertar - totalSegundosAtual;

            if (tempoRestante > 0) {
                int horasRestantes = tempoRestante / 3600;
                int minutosRestantes = (tempoRestante % 3600) / 60;
                int segundosRestantes = tempoRestante % 60;

                System.out.println(String.format( "Tempo restante até o despertador: %02d:%02d:%02d", horasRestantes, minutosRestantes, segundosRestantes));
            } else {
                System.out.println(RED + "Acorda, seu despertador está chamando." + RESET);
                System.out.println(VERDE + "Digite abaixo uma opção númerica:");
                String[] opcoes = DespertadorController.verOpcoes();

                for (int c = 0; c < opcoes.length; c++) {
                    System.out.println(
                        String.format(
                            "%s[%d] >> %s%s", 
                            YELLOW, c + 1, opcoes[c], RESET
                        )
                    );
                }

                respostaUsuario = scnDespertador.nextInt();
                System.out.println(DespertadorController.acaoDespertador(respostaUsuario));
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }

        scnDespertador.close();
        scnConfigurar.close();
    }
}
