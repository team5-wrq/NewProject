package dataprocess;
/*import tool.AbstractTreeTableModel;
import tool.TreeTableModel;

import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
import java.util.Vector;  
import java.util.List;  
  

import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.tree.DefaultMutableTreeNode;  

public class SubjectTableModel extends AbstractTreeTableModel implements TreeTableModel{
	//列名  
    public Vector<String> colNames;  
    //数据  
    public Vector<Item> data;  
    public SubjectTableModel(Object root) {  
        super(root);  
        // TODO Auto-generated constructor stub  
    }  
      
    //列的类型  
    static protected Class[] colTypes = { TreeTableModel.class, String.class,  
        String.class, String.class};  
      
    //  
    public SubjectTableModel(Vector<String> columnNames,  
            Vector<Item> dataVector) {  
        this.colNames = columnNames;  
        this.data = dataVector;  
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("收支项目类型");  
        createNodes(top);  
        this.setRoot(top);  
  
    }  
    //设置数据  
    public Vector<Item> getData(){  
        return this.data;  
    }  
    public void setData(Vector<Item> entityVector) {  
        data = entityVector;  
    }  
    //创建节点   
    private void createNodes(DefaultMutableTreeNode top) {  
        DefaultMutableTreeNode out = new DefaultMutableTreeNode("支出项目");  
        DefaultMutableTreeNode income = new DefaultMutableTreeNode("收入项目");  
        top.add(out);  
        top.add(income);    
          
        DefaultMutableTreeNode subjectNode = null;  
        for (int i = 0; i < data.size(); i++) {  
            //添加子节点以及子节点的子节点  
            subjectNode = new DefaultMutableTreeNode(data.elementAt(i).getName());  
            //子节点集合  
            List<Item> childs =ItemService.getInstance().findChildByParentId(data.elementAt(i).getId());  
            if(data.elementAt(i).getItemType()==1){ //0=支出   1=收入  
                income.add(subjectNode);          
                for (int m = 0; m < childs.size(); m++) {  
                    DefaultMutableTreeNode  childItem = new DefaultMutableTreeNode(childs.get(m).getName());  
                    subjectNode.add(childItem);  
                }  
            }  
            else{  
                out.add(subjectNode);  
                for (int m = 0; m <childs.size(); m++) {  
                    DefaultMutableTreeNode  childItem = new DefaultMutableTreeNode(childs.get(m).getName());  
                    //添加子节点  
                    subjectNode.add(childItem);  
                    //查看子节点是否还有子节点，有则继续添加  
                    addChild(childItem,childs.get(m).getId());  
                }  
            }     
        }  
    }  */
    /** 
     * 子节点添加子节点 
     * @param parent 
     * @param item 
     */  

/*
    public void addChild(DefaultMutableTreeNode parent,int parentItemId){  
        List<Item> lst = ItemService.getInstance().findChildByParentId(parentItemId);  
        for (int i = 0; i < lst.size(); i++) {  
            DefaultMutableTreeNode  childItem = new DefaultMutableTreeNode(lst.get(i).getName());  
            parent.add(childItem);  
            addChild(childItem,lst.get(i).getId()); //子节点添加子节点  
        }  
    }  
    */
 
/** 
     * @return 获得节点对象 
     * @param rowIndex 
     *  
     * */  

/*
    public Object getObjectByNode(Object node) {  
        Object obj = null;  
        if (node instanceof DefaultMutableTreeNode) {  
            obj = ((DefaultMutableTreeNode) node).getUserObject();  
        } else {  
            obj = node;  
        }  
  
        return obj;  
    }  
    public Class<?> getColumnClass(int column) {  
        return colTypes[column];  
    }
	@Override
	public Object getChild(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getChildCount(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getValueAt(Object node, int column) {
		// TODO Auto-generated method stub
		return null;
	}  
}
*/
