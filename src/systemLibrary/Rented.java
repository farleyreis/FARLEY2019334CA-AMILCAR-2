/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @Farleyreis
 * www.farleyr.com
 * www.getitdonewebs.com
 * Cct Colege Dublin
 * sn: 2019334
 */
public class Rented {

    private int id;
    private int idLeitor;
    private int idLivro;
    private int status;//1=rented 0=return

    public Rented() {
        this.id = 0;
        this.idLeitor = 0;
        this.idLivro = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(int idLeitor) {
        this.idLeitor = idLeitor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int lista(Rented[] emps){
        int total=0;
        System.out.println("Listando os Empréstimos");
        for (Rented emprestimo : emps) {
            total++;
            //if(emprestimo.getStatus()==1){
            System.out.println("Id......:"+emprestimo.getId());
            System.out.println("Leitor..:"+emprestimo.getIdLeitor());
            System.out.println("Livro...:"+emprestimo.getIdLivro());
            System.out.println("Status...:"+emprestimo.getStatus());
            System.out.println("----------------");
            //}
        }
        return total;
    }

    public Rented buscarLivroEmprestado(Rented[] emps, int idLivro){
        Rented emprestimo=new Rented();
        for (Rented e : emps) {
            if(e.getStatus()==1){
                if(e.getIdLivro()==idLivro){
                    emprestimo.setId(e.getId());
                    emprestimo.setIdLeitor(e.getIdLeitor());
                    emprestimo.setIdLivro(e.getIdLivro());

                }
            }

        }

        return emprestimo;

    }

    public Rented[] carregaEmprestimos(String fileName){
        File file = new File(fileName);
        Rented emprestimo;
        Rented[] emprestimos;
        Activities operacoes = new Activities();
        emprestimos = new Rented[operacoes.contaLinhas(file)];
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
                String[] dados=linha.split(";");
                emprestimo=new Rented();
                emprestimo.setId(Integer.parseInt(dados[0]));
                emprestimo.setIdLeitor(Integer.parseInt(dados[1]));
                emprestimo.setIdLivro(Integer.parseInt(dados[2]));
                emprestimo.setStatus(Integer.parseInt(dados[3]));
                emprestimos[linhas]=emprestimo;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public Rented buscarEmprestimoPorIdDeLivro(Rented[] emprestimos, int idBuscar){
        Rented emp = new Rented();
        for (Rented emprestimo : emprestimos) {
            if(emprestimo.getStatus()==1){//this is for show only the rented ones
                if(emprestimo.getIdLivro()==idBuscar){ //here is if the book is rented
                    id = emprestimo.getId();
                    idLeitor = emprestimo.getIdLeitor();
                    idLivro = emprestimo.getIdLivro();
                    status = emprestimo.getStatus();
                    emp.setId(id);
                    emp.setIdLeitor(idLeitor);
                    emp.setIdLivro(idLivro);
                    emp.setStatus(status);
                    break;
                }
            }
        }
        return emp;
    }

    public Rented[] adicionaEmprestimo(Rented[] emps, Rented emp){
        Rented[] emprestimos = new Rented[emps.length+1];
        for (int i = 0; i < emps.length; i++) {
            emprestimos[i]=emps[i];
            System.out.println("ee "+emprestimos[i].getId());
        }
        emprestimos[emps.length]=emp;
        //this is for make a record on emprestimos.txt
        this.gravaEmprestimos("emprestimos.txt", emp);//all in the array .txt
        return emprestimos;//all in the array.
    }

    public void gravaEmprestimos(String filename, Rented emprestimo){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file,true);
            id=emprestimo.getId();
            idLeitor=emprestimo.getIdLeitor();
            idLivro=emprestimo.getIdLivro();
            status=emprestimo.getStatus();
            fw.write("\n"+id+";"+idLeitor+";"+idLivro+";"+status);
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizaEmprestimos(String filename, Rented[] emprestimos, Rented emp){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            fw.write("id;idLeitor;idLivro;status");
            for (int i = 0; i < emprestimos.length; i++) {
                id=emprestimos[i].getId();
                idLeitor=emprestimos[i].getIdLeitor();
                idLivro=emprestimos[i].getIdLivro();
                if(emprestimos[i].getId()!=emp.getId()){
                    status=emprestimos[i].getStatus();
                }else{
                    status=0;
                }
                fw.write("\n"+id+";"+idLeitor+";"+idLivro+";"+status);
            }
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void realizarEmprestimo(Rented[] emprestimos, systemLibrary.Books[] livros, systemLibrary.Reader[] leitores, systemLibrary.Queue[] filas){
        int idLeitor,idLivro;

        Scanner teclado = new Scanner(System.in);
        System.out.println("Enter with book id");
        idLivro = teclado.nextInt();
        System.out.println("Enter with reader id");
        idLeitor = teclado.nextInt();

        //verefy if the book exist in the systems
        systemLibrary.Books livro = new systemLibrary.Books();
        livro = livro.buscarPorId(livros, idLivro);

        //verefy if the custumer exist in the systems
        systemLibrary.Reader leitor = new systemLibrary.Reader();
        leitor = leitor.buscarPorId(leitores, idLeitor);

        if(livro.getId()==null){
            System.out.println("Book is not in the list");
            return;
        }
        if(leitor.getId()==0){
            System.out.println("Reader is not in the system");
            return;
        }

        ////verefy if the book is avaliable
        Rented empTeste;
        empTeste = new Rented();
        empTeste = empTeste.buscarEmprestimoPorIdDeLivro(emprestimos, idLivro);

        if(empTeste.getIdLeitor()!=0){
            System.out.println("Rented Book");
            System.out.println("Are you wish to get in the queue for this book? (s/n)");

            //this is in case the book is already rented, you can go to the queue for get it later
            //then
            //add tho the queue
            teclado = new Scanner(System.in);
            String confirma;
            confirma = teclado.nextLine();
            if(confirma.equals("s")){
                systemLibrary.Queue fi = new systemLibrary.Queue();
                fi.setId(filas.length+1);
                fi.setIdLeitor(idLeitor);
                fi.setIdLivro(idLivro);
                fi.gravaFilas("fila.txt", fi);
            }


        }else{
            //teclado.close();
            System.out.println("Confirm rented? (s/n)");
            teclado = new Scanner(System.in);
            String confirma;
            confirma = teclado.nextLine();
            if(confirma.equals("s")){
                //add tho the emprestimo lists
                System.out.println("Book rented! Have fun!");
                Rented emp = new Rented();
                emp.setId(emprestimos.length+1);
                emp.setIdLeitor(idLeitor);
                emp.setIdLivro(idLivro);
                emp.setStatus(1);
                //adicionaEmprestimo(emprestimos,emp);
                gravaEmprestimos("emprestimos.txt", emp);//vamos ter tudo no arquivo
            }
            //teclado.close();
        }

    }

    public void devolverLivro(Rented[] emprestimos, systemLibrary.Books[] livros, systemLibrary.Reader[] leitores, systemLibrary.Queue[] filas){
        int idLeitor,idLivro;
        String opcao;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Enter with book id");
        idLivro = teclado.nextInt();

        //verefy if the book is avaliable
        Rented emprestimo = new Rented();
        emprestimo = buscarLivroEmprestado(emprestimos,idLivro);

        if(emprestimo.getIdLivro()==0){
            System.out.println("Book is not rented");
            return;//sai do método devolverLivro
        }
        teclado = new Scanner(System.in);

        System.out.println("Confirm return? (s/n)");
        teclado = new Scanner(System.in);
        String confirma;
        confirma = teclado.nextLine();
        if(confirma.equals("s")){
            //set status 0 no empréstimo
            atualizaEmprestimos("emprestimos.txt", emprestimos, emprestimo);
            System.out.println("Thanks for return the book");
        }

        //basic alert if the book is in the queue list
        systemLibrary.Queue fila= new systemLibrary.Queue();
        int[] tem = fila.verificarSeTemLeitorEsperando(filas,idLivro);
        if(tem.length>0){
            System.out.println("Temos "+tem.length+" na fila");
            for (int i = 0; i < tem.length; i++) {
                systemLibrary.Reader l=new systemLibrary.Reader();
                l=l.buscarPorId(leitores, tem[i]);
                System.out.println("O leitor "+l.getNome()+" está na fila");
            }
            //remove from the queue list
            fila.removeDaFila("fila.txt",filas,idLivro,tem[0]);

            //rented book if  idLivro e idLeitor
            emprestimo.realizarEmprestimo(emprestimos,livros,leitores,filas);
            emprestimos=emprestimo.carregaEmprestimos("emprestimos.txt");
            //filas=fila.carregaFila("fila.txt");
        }

    }

}
