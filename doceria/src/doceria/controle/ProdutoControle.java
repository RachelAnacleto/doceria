/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doceria.controle;

import doceria.dao.CategoriaDAO;
import doceria.dao.ProdutoDAO;
import doceria.modelo.Produto;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anamm
 */
public class ProdutoControle {
    public void gravar(Produto i)throws SQLException{
        
        ProdutoDAO dao = new ProdutoDAO();
        try{
        if(i.getId()==null || i.getId()<=0)
            dao.adicionar(i);
        else
            dao.alterar(i);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao salvar as informações: \n"+ex.getMessage());
            
        }
    }
    public void excluir(Produto i)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este Produto?")==JOptionPane.YES_OPTION){
            ProdutoDAO dao = new ProdutoDAO();    
            dao.excluir(i);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este produto?")==JOptionPane.YES_OPTION){
            ProdutoDAO dao = new ProdutoDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //limpa as linhas da tabela.
        for(int i=0; i<model.getRowCount(); i++)
            model.removeRow(i);
        
        ProdutoDAO dao = new ProdutoDAO();
        List categorias = dao.listarTodos();
        //Adiciona as linhas
        for (Object o : categorias){
            Produto r = (Produto) o;
            model.addRow(new Object[]{r.getId(),r.getNome(),r.getCategoria().getNome(),r.getDescricao()});
        }
        
    }

    public Produto getProdutoPorId(Long id) throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        Produto r = (Produto)dao.listarPorId(id);
        return r;       
    }

    public List listarCategorias() throws SQLException {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.listarTodos();
    }

}
