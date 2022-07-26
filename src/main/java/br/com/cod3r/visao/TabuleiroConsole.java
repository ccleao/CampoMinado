package br.com.cod3r.visao;

import br.com.cod3r.excecao.ExplosaoException;
import br.com.cod3r.excecao.LeaveException;
import br.com.cod3r.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner scan = new Scanner(System.in);
    public TabuleiroConsole(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try{
            boolean continuar = true;

            while(continuar){
                cicloDoJogo();

                System.out.println("Another match ? (Y/n) ");
                String resposta = scan.nextLine();
                scan.close();
                if ("Y".equalsIgnoreCase(resposta)){
                    executarJogo();
                }
            }
        }catch (LeaveException e) {
            System.out.println("Bye !");
        } finally {
            throw new LeaveException();

        }
    }

    private void cicloDoJogo() {
        try{

            while(!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();
                digitado = capturarValorDigitado("1 - Abrir ou 2 -(Des)Marcar");
                if("1".equals(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                }else if ("2".equals(digitado)){
                    tabuleiro.alterarMarcacao(xy.next(),xy.next());
                }
                System.out.println();
            }
            System.out.println(tabuleiro);
            System.out.println("You Won");
        }catch (ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("You Lost");
        }
    }
    private String capturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = scan.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new LeaveException();
        }
        return digitado;
    }
}
