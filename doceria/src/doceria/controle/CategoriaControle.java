/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doceria.controle;

import doceria.dao.CategoriaDAO;
import doceria.modelo.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author anamm
 */
public class CategoriaControle{
    public void gravar(Categoria c)throws SQLException{
        CategoriaDAO dao = new CategoriaDAO();
        try{
        if(c.getId()==null || c.getId()<=0)
            dao.adicionar(c);
        else
            dao.alterar(c);
        }catch(SQLException ex){
            throw new SQLException("Erro ao salvar as informações: \n"+ex.getMessage());
        }
    }
    public void excluir(Categoria c)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta categoria?")==JOptionPane.YES_OPTION){
            CategoriaDAO dao = new CategoriaDAO();    
            dao.excluir(c);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta categoria?")==JOptionPane.YES_OPTION){
            CategoriaDAO dao = new CategoriaDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //limpa as linhas da tabela.
        for(int i=0; i<model.getRowCount(); i++)
            model.removeRow(i);
        
        CategoriaDAO dao = new CategoriaDAO();
        List categorias = dao.listarTodos();
        //Adiciona as linhas
        for (Object o : categorias){
            Categoria c = (Categoria) o;
            model.addRow(new Object[]{c.getId(),c.getNome(),c.getDescricao()});
        }
        
    }

    public Categoria getCategoriaPorId(Long id) throws SQLException {
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = (Categoria)dao.listarPorId(id);
        return c;       
    }
}
