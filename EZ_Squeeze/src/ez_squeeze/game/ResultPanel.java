package ez_squeeze.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ResultPanel extends JPanel{
	private String[] columnNames = {"Visitors Today","Sales", "Total Sales", "Profit", "Total Profit", "Avg Satisfaction", "Days"};
	private Object[][] data = {{0,0,0,0,0,50, 0}};
	private JTable table;
	public ResultPanel(){
		super();
		table = new JTable(data, columnNames);
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
	}
	public void updateTable(State state){
		if(state == null) return;
		Stats stats = state.stats;
		table.getModel().setValueAt(stats.visitorsToday, 0, 0);
		table.getModel().setValueAt(stats.cupsSoldToday, 0, 1);
		table.getModel().setValueAt(stats.cupsSold, 0, 2);
		table.getModel().setValueAt( stats.moneyEarnedToday, 0, 3);
		table.getModel().setValueAt(stats.moneyEarned, 0, 4);
		table.getModel().setValueAt(stats.averageSatisfaction, 0, 5);
		table.getModel().setValueAt(stats.days, 0, 6);
	}
	public static void main(String[] args){
		JFrame window = new JFrame("Result");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ResultPanel rp = new ResultPanel();
		window.getContentPane().add(rp);
		window.pack();
		window.setVisible(true);
		window.setSize(400, 200);
	}
}
