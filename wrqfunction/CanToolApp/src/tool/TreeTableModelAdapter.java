package tool;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class TreeTableModelAdapter extends AbstractTableModel
{
    JTree tree;
    TreeTableModel treeTableModel;

    public TreeTableModelAdapter(TreeTableModel treeTableModel, JTree tree) {
        this.tree = tree;
        this.treeTableModel = treeTableModel;

    tree.addTreeExpansionListener(new TreeExpansionListener() {
        // Don't use fireTableRowsInserted() here; the selection model
        // would get updated twice.
        public void treeExpanded(TreeExpansionEvent event) {
          fireTableDataChanged();
        }
            public void treeCollapsed(TreeExpansionEvent event) {
          fireTableDataChanged();
        }
    });

    // Install a TreeModelListener that can update the table when
    // tree changes. We use delayedFireTableDataChanged as we can
    // not be guaranteed the tree will have finished processing
    // the event before us.
    treeTableModel.addTreeModelListener(new TreeModelListener() {
        public void treeNodesChanged(TreeModelEvent e) {
        delayedFireTableDataChanged();
        }

        public void treeNodesInserted(TreeModelEvent e) {
        delayedFireTableDataChanged();
        }

        public void treeNodesRemoved(TreeModelEvent e) {
        delayedFireTableDataChanged();
        }

        public void treeStructureChanged(TreeModelEvent e) {
        delayedFireTableDataChanged();
        }
    });
    }

    // Wrappers, implementing TableModel interface.

    public int getColumnCount() {
    return treeTableModel.getColumnCount();
    }

    public String getColumnName(int column) {
    return treeTableModel.getColumnName(column);
    }

    public Class getColumnClass(int column) {
    return treeTableModel.getColumnClass(column);
    }

    public int getRowCount() {
    return tree.getRowCount();
    }

    protected Object nodeForRow(int row) {
    TreePath treePath = tree.getPathForRow(row);
    return treePath.getLastPathComponent();
    }

    public Object getValueAt(int row, int column) {
    return treeTableModel.getValueAt(nodeForRow(row), column);
    }

    public boolean isCellEditable(int row, int column) {
         return treeTableModel.isCellEditable(nodeForRow(row), column);
    }

    public void setValueAt(Object value, int row, int column) {
    treeTableModel.setValueAt(value, nodeForRow(row), column);
    }

    /**
     * Invokes fireTableDataChanged after all the pending events have been
     * processed. SwingUtilities.invokeLater is used to handle this.
     */
    protected void delayedFireTableDataChanged() {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        fireTableDataChanged();
        }
    });
    }
}
