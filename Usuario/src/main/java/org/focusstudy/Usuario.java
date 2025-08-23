package org.focusstudy;


import java.sql.*;
import java.util.Scanner;

public class Usuario {

        private String user;
        private String password;
        private Connection connection;

        public Usuario(String user, String password) {
            this.user = user;
            this.password = password;
            this.connection = conectarBanco();
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private Connection conectarBanco() {
            String url = "jdbc:mysql://3306/FocusStudy?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String senha = "";

            try {
                return DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco: " + e.getMessage());
                return null;
            }
        }

        public boolean autenticar() {
            if (connection == null) return false;

            String sql = "SELECT * FROM usuario WHERE nome=? AND senha=?";

            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, this.user);
                pst.setString(2, this.password);

                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next(); // true se encontrou o usuário
                }

            } catch (SQLException e) {
                System.err.println("Erro ao autenticar: " + e.getMessage());
                return false;
            } finally {
                try {
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();

            Usuario usuario = new Usuario(nome,senha);
            scanner.close();
            if (usuario.autenticar()) {
                System.out.println("Usuário autenticado!");
            } else {
                System.out.println("Usuário ou senha incorretos!");
            }
            }

        }



