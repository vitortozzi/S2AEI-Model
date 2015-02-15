/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import Model.Tabelas.Avaliador;
import Model.Tabelas.Projeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vítor
 */
public class ProjetoDAO {

    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pstm;
    Projeto p;

    public ProjetoDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public boolean insertProjeto(Projeto projeto) {

        String sql = "INSERT INTO projeto (titulo, descricao, data_criacao, ultima_modificacao, status, area, email_orientador"
                + ", email_lider) VALUES (?, ?, curdate(), curdate(), 'Novo', ?, (SELECT email FROM usuario WHERE usuario.nome = (?)), "
                + "(SELECT email FROM usuario WHERE usuario.nome = (?)))";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, projeto.getTitulo());
            pstm.setString(2, projeto.getDescricao());
            pstm.setString(3, projeto.getArea());
            pstm.setString(4, projeto.getOrientador());
            pstm.setString(5, projeto.getLider());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        addParticipantes(projeto);
        populaRespostas();

        return true;
    }

    public void addLiderParticipante(String nome) {

        String sql = "INSERT INTO participa (id_projeto, email_aluno) VALUES ((SELECT MAX(id) FROM projeto), "
                + "(SELECT email FROM usuario WHERE usuario.nome = (?));";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nome);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void populaRespostas() {

        String sql = "INSERT INTO respostas (id_projeto, id_pergunta) values ((SELECT MAX(id) FROM projeto) , ?)";

        try {
            pstm = connection.prepareStatement(sql);
            for (int i = 0; i < 9; i++) {
                pstm.setInt(1, i + 1);
                pstm.execute();
            }
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean addParticipantes(Projeto projeto) {

        String sql = "INSERT INTO participa (id_projeto, email_aluno) VALUES ((SELECT id FROM projeto WHERE email_lider = "
                + " (SELECT email FROM usuario WHERE usuario.nome = (?))), (SELECT email FROM usuario WHERE usuario.nome = (?))) ";

        try {
            pstm = connection.prepareStatement(sql);
            for (String s : projeto.getMembros()) {
                pstm.setString(1, projeto.getLider());
                pstm.setString(2, s);
                pstm.execute();

            }
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public ArrayList getProjetos() {

        ArrayList<Projeto> projetos = new ArrayList<>();

        String sql = "SELECT p.id, p.titulo, lider.nome, professor.nome, p.data_criacao, p.ultima_modificacao, "
                + "p.status FROM projeto p, usuario lider, usuario professor WHERE lider.email = p.email_lider and "
                + "p.email_orientador = professor.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();

            rs = pstm.getResultSet();
            while (rs.next()) {
                p = new Projeto();
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setLider(rs.getString(3));
                p.setOrientador(rs.getString(4));
                p.setDataCriacao(rs.getString(5));
                p.setUltimaModificacao(rs.getString(6));
                p.setStatus(rs.getString(7));
                projetos.add(p);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projetos;
    }

    public ArrayList getProjetosParticipante(String nome) {

        ArrayList<Projeto> projetos = new ArrayList<>();

        String sql = "SELECT p.id, p.titulo, lider.nome, professor.nome, p.data_criacao, p.ultima_modificacao, "
                + "p.status FROM projeto p, usuario lider, usuario professor WHERE p.id in (SELECT id_projeto FROM"
                + " participa WHERE email_aluno in (SELECT email FROM usuario WHERE nome = (?))) and lider.email = p.email_lider and "
                + "p.email_orientador = professor.email";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nome);

            pstm.execute();

            rs = pstm.getResultSet();
            while (rs.next()) {
                p = new Projeto();
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setLider(rs.getString(3));
                p.setOrientador(rs.getString(4));
                p.setDataCriacao(rs.getString(5));
                p.setUltimaModificacao(rs.getString(6));
                p.setStatus(rs.getString(7));
                projetos.add(p);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projetos;
    }

    public ArrayList getProjetosOrientador(String nome) {

        ArrayList<Projeto> projetos = new ArrayList<>();

        String sql = "SELECT p.id, p.titulo, lider.nome, professor.nome, p.data_criacao, p.ultima_modificacao, "
                + "p.status FROM projeto p, usuario lider, usuario professor WHERE lider.email = p.email_lider and "
                + "p.email_orientador = professor.email and professor.email in (SELECT email FROM usuario WHERE nome = (?))";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nome);

            pstm.execute();

            rs = pstm.getResultSet();
            while (rs.next()) {
                p = new Projeto();
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setLider(rs.getString(3));
                p.setOrientador(rs.getString(4));
                p.setDataCriacao(rs.getString(5));
                p.setUltimaModificacao(rs.getString(6));
                p.setStatus(rs.getString(7));
                projetos.add(p);
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projetos;
    }

    public ArrayList<String> getRespostas(int id) {

        ArrayList<String> respostas = new ArrayList<>();
        String resposta;

        String sql = "SELECT texto_resposta FROM respostas WHERE id_projeto = " + id + "";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            rs = pstm.getResultSet();
            while (rs.next()) {
                resposta = rs.getString(1);
                respostas.add(resposta);
            }
            pstm.close();

            return respostas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Projeto getProjetoPorLider(String nome) {

        p = new Projeto();

        String sql = "SELECT p.id, p.titulo, p.descricao, p.data_criacao, p.ultima_modificacao, p.status, p.area, (SELECT nome FROM usuario WHERE email = p.email_orientador)"
                + " FROM projeto p WHERE email_lider in (SELECT u.email FROM usuario u WHERE u.nome = (?))";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, nome);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setDescricao(rs.getString(3));
                p.setDataCriacao(rs.getString(4));
                p.setUltimaModificacao(rs.getString(5));
                p.setStatus(rs.getString(6));
                p.setArea(rs.getString(7));
                p.setOrientador(rs.getString(8));
                p.setLider(nome);
            }
            pstm.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Projeto getProjetoPorID(int id) {

        String sql = "SELECT titulo, descricao, status, area, email_orientador, email_lider FROM projeto WHERE id = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                p = new Projeto();
                p.setTitulo(rs.getString(1));
                p.setDescricao(rs.getString(2));
                p.setStatus(rs.getString(3));
                p.setArea(rs.getString(4));
                p.setOrientador(rs.getString(5));
                p.setLider(rs.getString(6));
            }
            pstm.close();
            return p;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateRespostas(Projeto projeto) {

        String sql = "UPDATE respostas r, projeto p SET r.texto_resposta = (?), status = 'Em Preenchimento' ,p.ultima_modificacao = curdate() WHERE r.id_projeto = (?) and r.id_pergunta = (?) "
                + "and p.id = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            for (int i = 0; i < 9; i++) {
                pstm.setString(1, projeto.getRespostas().get(i));
                pstm.setInt(2, projeto.getId());
                pstm.setInt(3, i + 1);
                pstm.setInt(4, projeto.getId());
                pstm.execute();
            }
            pstm.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean finalizaProjeto(int id_projeto) {

        if (checkRespostas(id_projeto).size() == 9) {
            String sql = "UPDATE projeto SET status = 'Em avaliação' WHERE id = (?)";

            try {
                pstm = connection.prepareStatement(sql);
                pstm.setInt(1, id_projeto);
                pstm.execute();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

    public boolean alteraStatusProjetoAprovado(String emailLider, String novoStatus) {
        String sql = "UPDATE projeto SET status = (?) WHERE email_lider = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, novoStatus);
            pstm.setString(2, emailLider);
            pstm.execute();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<String> checkRespostas(int id_projeto) {

        ArrayList<String> respostas = new ArrayList<>();
        String resposta;

        String sql = "SELECT texto_resposta FROM respostas WHERE texto_resposta != '' AND id_projeto = (?)";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id_projeto);
            pstm.execute();

            rs = pstm.getResultSet();
            while (rs.next()) {
                resposta = rs.getString(1);
                respostas.add(resposta);
            }

            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return respostas;
    }

    public boolean setAvaliadorProjeto(Avaliador avaliador, Projeto projeto) {

        String sql = "INSERT INTO avalia (email_avaliador, id_projeto) VALUES ((SELECT email FROM usuario"
                + " WHERE nome = (?)), ?)  ";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, avaliador.getNome());
            pstm.setInt(2, projeto.getId());

            pstm.execute();
            pstm.close();

            populaAvalicacao(avaliador, projeto);

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void populaAvalicacao(Avaliador avaliador, Projeto projeto) {

        String sql = "INSERT INTO avaliacao (id_projeto, id_pergunta, email_avaliador) VALUES"
                + " (?, ?, (SELECT email FROM usuario WHERE nome = (?)))";

        try {
            for (int i = 1; i < 10; i++) {
                pstm = connection.prepareStatement(sql);
                pstm.setInt(1, projeto.getId());
                pstm.setInt(2, i);
                pstm.setString(3, avaliador.getNome());
                pstm.execute();
            }
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteAvaliadorProjeto(Projeto projeto, String nomeAvaliador) {

        if (deleteAvaliacao(projeto, nomeAvaliador)) {
            String sql = "DELETE FROM avalia WHERE id_projeto = (?) and email_avaliador = (SELECT email "
                    + "FROM usuario WHERE usuario.nome = (?))";
            try {
                pstm = connection.prepareStatement(sql);
                pstm.setInt(1, projeto.getId());
                pstm.setString(2, nomeAvaliador);
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean deleteAvaliacao(Projeto projeto, String nomeAvaliador) {

        String sql = "DELETE FROM avaliacao WHERE id_projeto = (?) and email_avaliador = "
                + "(SELECT email FROM usuario WHERE usuario.nome = (?))";

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, projeto.getId());
            pstm.setString(2, nomeAvaliador);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
