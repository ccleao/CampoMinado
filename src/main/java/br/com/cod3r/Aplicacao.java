package br.com.cod3r;

import br.com.cod3r.modelo.Tabuleiro;
import br.com.cod3r.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 4);
        new TabuleiroConsole(tabuleiro);



    }
}
