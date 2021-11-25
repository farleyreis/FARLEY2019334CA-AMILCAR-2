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
public class Reader {

    private int id;
    private String nome;

    public Reader() {
        this.id = 0;
        this.nome = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Reader[] carregaLeitores(String fileName){
        File file = new File(fileName);
        Reader leitor=new Reader();
        Reader[] leitores;
        Activities operacoes;
        operacoes=new Activities();
        leitores = new Reader[operacoes.contaLinhas(file)];
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
                leitor=new Reader();
                leitor.setId(Integer.parseInt(dados[0]));
                leitor.setNome(dados[1]);
                leitores[linhas]=leitor;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return leitores;
    }

    public Reader buscarPorId(Reader[] leitores, int idBuscar){
        Reader l = new Reader();
        for (Reader leitor : leitores) {
            if(leitor.getId()==idBuscar){
                id = leitor.getId();
                nome = leitor.getNome();
                System.out.println("Id......:"+id);
                System.out.println("Nome...:"+nome);
                System.out.println("----------------");
                l.setId(id);
                l.setNome(nome);
            }
        }
        return l;
    }

}
