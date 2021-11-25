/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* importe... 
Hello jeny, as I work using inteliJ, when I finished the program and went to transfer to netbens,
 I couldn't put the automatic main class, because I must have a compatibility problem with my operating system, macbook.
 So to run the program, it must be run in the NewMain class.*/

package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @Farleyreis
 * www.farleyr.com
 * www.getitdonewebs.com
 * Cct Colege Dublin
 * sn: 2019334
 */



public class Activities {

    Books livro;


    public int contaLinhas(File file){
        System.out.println("counting lines");
        int total=0;
        try {
            FileReader isr = new FileReader(file);
            BufferedReader bf = new BufferedReader(isr);
            String linha;
            linha=bf.readLine();
            int linhas=0;
            while(true){
                linha=bf.readLine();
                if(linha==null)
                    break;
                linhas++;
            }
            total=linhas;
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("t"+total);
        return total;
    }

    public void buscarLivros(Books[] livros){
        livro = new Books();
        String nome;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Enter with movie title or author last name");
        nome = teclado.nextLine();

        livro.buscar(livros, nome);

        for (Books livro : livros) {
            //search for movie title
            if(livro.getBook_title().toLowerCase().contains(nome.toLowerCase())){
                System.out.println("Id......:"+livro.getId());
                System.out.println("title..:"+livro.getBook_title());
                System.out.println("Author firs name...:"+livro.getAuthor_first_name());
                System.out.println("Author last name....:"+livro.getAuthor_last_name());
                System.out.println("Genre...:"+livro.getGenre());
                System.out.println("----------------");
            }
            //searchg for movie author last name
            if(livro.getAuthor_last_name().toLowerCase().contains(nome.toLowerCase())){
                System.out.println("Id......:"+livro.getId());
                System.out.println("title..:"+livro.getBook_title());
                System.out.println("Author firs name...:"+livro.getAuthor_first_name());
                System.out.println("Author last name....:"+livro.getAuthor_last_name());
                System.out.println("Genre...:"+livro.getGenre());
                System.out.println("----------------");
            }

        }
    }

}
