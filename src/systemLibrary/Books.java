/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @Farleyreis
 * www.farleyr.com
 * www.getitdonewebs.com
 * Cct Colege Dublin
 * sn: 2019334
 */

//Contructor, get and setters. ID, Author name, Author last name, genere, Booktitle
public class Books {
    private String id;
    private String author_first_name;
    private String author_last_name;
    private String book_title;
    private String genre;

    public Books() {
        this.id = null;
        this.author_first_name = "";
        this.author_last_name = "";
        this.genre = "";
        this.book_title = "";
    }

    public Books(String id, String titulo, String autor, String editora) {
        this.id = id;
        this.author_first_name = titulo;
        this.author_last_name = autor;
        this.genre = editora;
        this.book_title = book_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_first_name() {
        return author_first_name;
    }

    public void setAuthor_first_name(String titulo) {
        this.author_first_name = titulo;
    }

    public String getAuthor_last_name() {
        return author_last_name;
    }

    public void setAuthor_last_name(String autor) {
        this.author_last_name = autor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String autorLastN) {
        this.book_title = autorLastN;
    }

    public Books[] carregaLivros(String fileName){
        File file = new File(fileName);
        Books livro;
        Books[] livros;
        Activities operacoes = new Activities();
        livros = new Books[operacoes.contaLinhas(file)];
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);
            String linha;
            linha=bf.readLine();
            int linhas=0;
            while(true){
                linha=bf.readLine();
                if(linha==null)
                    break;
                String[] dados=linha.split(",");
                livro=new Books();
                livro.setId(dados[0]);
                livro.setAuthor_first_name(dados[1]);
                livro.setAuthor_last_name(dados[2]);
                livro.setBook_title(dados[3]);
                livro.setGenre(dados[4]);
                livros[linhas]=livro;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    public int listaLivros(Books[] livros){
        int total=0;
        System.out.println("All the store books :");
        for (Books livro : livros) {
            System.out.println("Id......:"+livro.getId());
            System.out.println("Author First Name..:"+livro.getAuthor_first_name());
            System.out.println("Author Laast Name...:"+livro.getAuthor_last_name());
            System.out.println("Book Title...:"+livro.getBook_title());
            System.out.println("Genre...:"+livro.getGenre());
            System.out.println("----------------");
            total++;
        }
        return total;
    }
    //public Books[] listaOrdenado(Books[] livros){
    public void listaOrdenado(Books[] livros){
        Books livroTemp;
        String a1,a2;
        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < livros.length-1-i; j++) {
                a1=livros[j].getBook_title().trim();
                a2=livros[j+1].getBook_title().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    livroTemp=livros[j];
                    livros[j]=livros[j+1];
                    livros[j+1]=livroTemp;
                }
            }
        }
        int t = this.listaLivros(livros);
        // return livros;
    }

    public void listaOrdenadoAutor(Books[] livros){
        Books livroTemp;
        String a1,a2;
        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < livros.length-1-i; j++) {
                a1=livros[j].getAuthor_last_name().trim();
                a2=livros[j+1].getAuthor_last_name().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    livroTemp=livros[j];
                    livros[j]=livros[j+1];
                    livros[j+1]=livroTemp;
                }
            }
        }
        int t = this.listaLivros(livros);
    }

    public void buscar(Books[] livros, String nome){
        for (Books livro : livros) {
            //seach for the titles using input sistem.in/ buffreader
            if(livro.getAuthor_first_name().toLowerCase().contains(nome.toLowerCase())){
                System.out.println("Id......:"+livro.getId());
                System.out.println("title..:"+livro.getBook_title());
                System.out.println("Author firs name...:"+livro.getAuthor_first_name());
                System.out.println("Author last name....:"+livro.getAuthor_last_name());
                System.out.println("Genre...:"+livro.getGenre());
                System.out.println("----------------");
            }
            //seach for the author using input sistem.in/ buffreader
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

    public Books buscarPorId(Books[] livros, int idBuscar){
        Books l = new Books();
        for (Books livro : livros) {
        }
        return l;
    }

}
