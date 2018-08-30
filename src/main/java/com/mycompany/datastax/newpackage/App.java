/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datastax.newpackage;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.mycompany.datastax.newpackage.modelo.Pessoa;
import java.util.List;
import com.datastax.driver.core.Row;


/**
 *
 * @author caique
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        
        Session session = cluster.connect("aula");
        
        /* inserir
        PreparedStatement stmt = session.prepare("INSERT INTO pessoa (cpf, nome, idade) VALUES(?, ?, ?)");
        
        Pessoa p = new Pessoa("111.111.111-11", "Caique", 21);
                
        BoundStatement bound = stmt.bind(p.getCpf(), p.getNome(), p.getIdade());
        
        ResultSet rs = session.execute(bound);
        
        System.out.println(rs.wasApplied());
        */
        
        ResultSet rs = session.execute("SELECT * FROM pessoa");

        List<Row> linhas = rs.all();
        
        for(Row r: linhas){
          
            String cpf = r.getString("cpf");
            String nome = r.getString("nome");
            int idade = r.getInt("idade");
            
            Pessoa p = new Pessoa(cpf,nome,idade);
            
            System.out.println(p);
            
        }
        
        session.close();
        cluster.close();;
        
    }
    
}
