/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doceria.dao;

import doceria.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anamm
 */
public class CategoriaDAO implements IDAO{

    @Override
    public void adicionar(Object o) throws SQLException {
        Categoria categoria = (Categoria) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "INSERT INTO public.categoria(\n" +
"	nome, descricao)\n" +
"	VALUES (?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar cadastrar a categoria. \n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Categoria categoria = (Categoria) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "UPDATE public.categoria\n" +
"	SET nome=?, descricao=?\n" +
"	WHERE id = ?";
           /* UPDATE public.categoria
	SET id=?, nome=?, descricao
	WHERE <condition>;
        */
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setLong(3, categoria.getId());
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar alterar a categoria. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Categoria categoria = (Categoria) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.categoria\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setLong(1, categoria.getId());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover a categoria. \n" + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Long id) throws SQLException {
        
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.categoria\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setLong(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover a categoria. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException{
        List lista = new ArrayList();
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, nome, descricao\n" +
"	FROM public.categoria;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));                
                c.setId(rs.getLong("id"));
                lista.add(c);
            }

            rs.close();
            stmt.close();
            
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a lista de categorias. \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(Long id) throws SQLException {
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, nome, descricao\n" +
"	FROM public.categoria WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Categoria c = new Categoria();
            c.setNome(rs.getString("nome"));
            c.setDescricao(rs.getString("descricao"));
            c.setId(rs.getLong("id"));
            

            rs.close();
            stmt.close();
            
            return c;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a categoria. \n" + e.getMessage());
        }
    }
    
}
