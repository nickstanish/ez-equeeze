package ez_squeeze.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ResultPanel extends JPanel {

  private static final long serialVersionUID = 6431030015477700513L;


  private String[] columnNames = {"Visitors Today", "Sales", "Total Sales", "Revenue",
      "Total Revenue", "Avg Satisfaction", "Days"};
  private Object[][] data = {{0, 0, 0, 0, 0, 50, 0}};
  private JTable table;

  public ResultPanel() {
    super();
    table = new JTable();
    table.setModel(new ResultTableModel(data, columnNames));
    setLayout(new BorderLayout());
    add(table.getTableHeader(), BorderLayout.PAGE_START);
    add(table, BorderLayout.CENTER);
  }

  public void updateTable(State state) {
    if (state == null) {
      return;
    }
    Stats stats = state.stats;
    table.getModel().setValueAt(stats.visitorsToday, 0, 0);
    table.getModel().setValueAt(stats.salesToday, 0, 1);
    table.getModel().setValueAt(stats.sales, 0, 2);
    table.getModel().setValueAt(Constants.priceFormat.format(stats.revenueToday), 0, 3);
    table.getModel().setValueAt(Constants.priceFormat.format(stats.revenue), 0, 4);
    table.getModel().setValueAt(Constants.satisfactionFormat.format(stats.averageSatisfaction), 0,
        5);
    table.getModel().setValueAt(stats.days, 0, 6);
  }

  public static void main(String[] args) {
    JFrame window = new JFrame("Result");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ResultPanel rp = new ResultPanel();
    window.getContentPane().add(rp);
    window.pack();
    window.setVisible(true);
    window.setSize(400, 200);
  }
}
