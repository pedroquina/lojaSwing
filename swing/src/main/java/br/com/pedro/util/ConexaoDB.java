package br.com.pedro.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDB {
 
    
    // faz conexão com o banco de dados
     public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver"); // Driver

            conn = DriverManager.getConnection( 
                "jdbc:h2:tcp://localhost/c:/Projeto/banco/loja", // string de conexão
                 "sa",  // usuário 
                  "") ; // senha
        } catch (Exception erro) {
            System.out.println("Erro (getConnection) : " + erro.getMessage());
        }

        return conn;
     } 


     // executa comandos INSERT, DELETE, UPDATE
     public void executeUpdate(String sql ){
        Connection conn = getConnection();

        try {
            int i = conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }
     }


     // execute SELECT
     public ResultSet executeSelect(String sql ){
        Connection conn = getConnection();
        ResultSet ret = null;
        try {
            ret= conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return ret;
    }


    // executa Insert e retorna novo ID gerado
    public int executeInsertGetId(String sqlInsert ){
        Connection conn = getConnection();
        int id = -1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
            int linhasAfetadas = pstmt.executeUpdate();
            ResultSet idGerado = pstmt.getGeneratedKeys();
            if ( idGerado.next()){
                id = idGerado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    public static void main(String a[]){
        ConexaoDB c = new ConexaoDB();
        String sql = "insert into usuario (nome, email, senha) values('nome','email','senha');";
        int id = c.executeInsertGetId(sql);
        System.out.println("ID = " + id);
    }

}
