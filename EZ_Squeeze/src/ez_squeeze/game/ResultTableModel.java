package ez_squeeze.game;

import javax.swing.table.DefaultTableModel;

public class ResultTableModel extends DefaultTableModel {

  private static final long serialVersionUID = -5303973530285167196L;

  public ResultTableModel(Object[][] tableData, Object[] colNames) {
    super(tableData, colNames);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }

}
