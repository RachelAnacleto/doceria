/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doceria.dao;

import doceria.modelo.Categoria;
import doceria.modelo.Produto;
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
public class ProdutoDAO implements IDAO{

    @Override
    public void adicionar(Object o) throws SQLException {
        Produto produto(Produto) o;
        Connection conn = null;
        //cadastra uma nova receita
        try {
            conn = FabricaConexao.getConexao();            
            String sql = "INSERT INTO public.receita(\n" +
"	nome, preparo, modoservir, categoria)\n" +
"	VALUES (?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                    
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getdescricao());
            stmt.setLong(3,produto.getCategoria().getId());
            stmt.executeUpdate();            
           //recupera o id da nova receita gerado pelo banco
           ResultSet rs = stmt.getGeneratedKeys();  
           rs.next();
           Long id = rs.getLong(1);
           produto.setId(id);
           rs.close();            
           stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Eroo ao tentar cadastrar a produto. \n" + e.getMessage());
        }
        //cadastra os ingredientes da nova receita
        
        
            
        }
            }
            stmt.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                throw new SQLException
        
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Produto produto = (Produto) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "UPDATE public.produto\n" +
"	SET nome=?, descricao=?, categoria=?\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getdescricao());
            stmt.setLong(3, receita.getCategoria().getId());
            stmt.setLong(4, produto.getId());
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar alterar a produto.\n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Produto produto = (Produto) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.produto\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setLong(1, produto.getId());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover a produto. \n" + e.getMessage());
        }
    }
    
    @Override
    public void excluir(Long id) throws SQLException {
        
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.produto\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setLong(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover a produto. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException{
        List lista = new ArrayList();
        Connection conn = null;
        CategoriaDAO dao = new CategoriaDAO();
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT *\n" +
"	FROM public.receita;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto r = new produto();
                r.setNome(rs.getString("nome"));
                r.setdescricao(rs.getString("descricao "));                
                r.setCategoria((Categoria)dao.listarPorId(rs.getLong("categoria")));
                r.setId(rs.getLong("id"));
                lista.add(r);
            }

            rs.close();
            stmt.close();
            
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a lista de produtos. \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(Long id) throws SQLException {
        Connection conn = null;
        CategoriaDAO dao = new CategoriaDAO();
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, nome, descricao, categoria\n" +
"	FROM public.produto WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Produto r = new Produto();
            r.setNome(rs.getString("nome"));
            r.setdescricao(rs.getString("descricao"));                
            r.setCategoria((Categoria)dao.listarPorId(rs.getLong("categoria")));
            r.setId(rs.getLong("id"));
            

            rs.close();
            stmt.close();
            
            return r;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a descricao. \n" + e.getMessage());
        }
    }

            
           
    }
    
    
}
