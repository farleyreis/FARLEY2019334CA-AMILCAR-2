/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary; 

import java.util.Scanner;

/**
 *
 * @Farleyreis
 * www.farleyr.com
 * www.getitdonewebs.com
 * Cct Colege Dublin
 * sn: 2019334
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        systemLibrary.Books[] livros;
        systemLibrary.Books livro=new systemLibrary.Books();
        livros = livro.carregaLivros("livros.txt");

        Reader[] leitores;
        Reader leitor=new Reader();
        leitores = leitor.carregaLeitores("leitores.txt");

        Rented[] emprestimos;
        Rented emprestimo=new Rented();
        emprestimos = emprestimo.carregaEmprestimos("emprestimos.txt");

        Queue[] filas;
        Queue fila=new Queue();
        filas = fila.carregaFila("fila.txt");

        Activities operacoes;
        operacoes = new Activities();

        Scanner teclado;
        teclado = new Scanner(System.in);

        String opcao="";

        for(;;){
            System.out.println("=============================================================");
            System.out.println("Wellcome to bookstore system, please choose one service below ");
            System.out.println("=============================================================");

            System.out.println("1 - See all the books ");
            System.out.println("2 - See all the books by title order ");
            System.out.println("3 - See all the books by autor order ");
            System.out.println("4 - Search for books ");

            System.out.println("5 - Search author ");
            System.out.println("6 - See all the clients by name order ");
            System.out.println("7 - See all the clients by the id number");

            System.out.println("8 - Renter a book ");
            System.out.println("9 - Return book ");

            System.out.println("a - See all the rented books ");
            System.out.println("b - See all the waiting clients ");

            System.out.println("0 - exit ");
            opcao = teclado.nextLine();

            switch(opcao){
                case "1":
                    livro.listaLivros(livros);
                    break;
                case "2":
                    livro.listaOrdenado(livros);
                    break;
                case "3":
                    livro.listaOrdenadoAutor(livros);
                    break;
                case "4":
                    operacoes.buscarLivros(livros);
                    //livro.buscar(livros);
                    break;
                case "5":
                    operacoes.buscarLivros(livros);
                    //livro.buscar(livros);
                    break;
                case "8":
                    emprestimo.realizarEmprestimo(emprestimos,livros,leitores,filas);
                    emprestimos=emprestimo.carregaEmprestimos("emprestimos.txt");
                    filas=fila.carregaFila("fila.txt");
                    //teclado = new Scanner(System.in);
                    break;
                case "a":
                    emprestimo.lista(emprestimos);
                    break;
                case "b":
                    fila.lista(filas);
                    break;
                case "9":
                    emprestimo.devolverLivro(emprestimos,livros,leitores,filas);
                    emprestimos=emprestimo.carregaEmprestimos("emprestimos.txt");
                    break;
                case "0":
                    System.out.println("END");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Try another one");
            }

        }

    }

}