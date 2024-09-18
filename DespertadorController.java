package controller;
import model.*;
import view.*;
import java.util.*;

public class DespertadorController extends DespertadorView {
    public static String[] verOpcoes() {
        return DespertadorModel.mostrarOpcoes();
    }

    public static String acaoDespertador(int opcaoUsuario) {
        final String FPRETO = "\033[0;40m";
        final String VERDE = "\033[0;32m";
        final String RESET = "\033[0m";   
        final String RED = "\033[0;31m";      
 



        String resposta = "";
        Scanner scnAdiar = new Scanner(System.in);
        int respostaAdiar;
        switch (opcaoUsuario) {
            case 1:
                System.out.println(VERDE + "Digite o número da opção abaixo e tecle Enter:" + RESET);
                for (int a = 0; a < adiamentos.length; a++) {
                    System.out.println(RED +
                        String.format(
                             "[%d] >> adiar %d minutos", 
                            a + 1, 
                            adiamentos[a]
                        )
                    );
                }
                respostaAdiar = scnAdiar.nextInt();
                adiar = adiamentos[respostaAdiar - 1];

                resposta = "Ok! Alarme adiado em: " + adiar + " minutos.";
                minutoDespertar += adiar;
                if (minutoDespertar > 59) {
                    minutoDespertar = 59;
                }
                break;

            case 2:
                System.out.println(FPRETO + "Ok! Alarme parado.");
                System.exit(0);
                break;

            default:
                resposta = "Opção inválida.";
                break;
        }
        scnAdiar.close();
        return resposta;
    }

    public static void getHMS() {
        date = new Date();
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
        minutoAtual = calendar.get(Calendar.MINUTE);
        segundoAtual = calendar.get(Calendar.SECOND);
    }
}
