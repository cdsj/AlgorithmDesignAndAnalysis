import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class SudokuAlgorithmGUI extends JPanel implements ActionListener
{

	JButton runButton;
	JPanel jp1;
	JLabel jl1,jl2;
	JTextPane resultPane;
	String[] algorithms = {"Las Vegas SVS", "Las Vegas GS", "Las Vegas RO", "Las Vegas RORI", "Las Vegas RVL"};
	Integer[] boardSizes = {2,3};
	JComboBox<String> algorithmList;
	JComboBox<Integer> sudokuBoardSize;
	public SudokuAlgorithmGUI()
	{
		
		jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.setPreferredSize(new Dimension(200, 280));
		
		runButton = new JButton("Run Algorithm");
		runButton.setVerticalAlignment(AbstractButton.CENTER);
		runButton.setPreferredSize(new Dimension(180,20));
		runButton.setActionCommand("runAlgorithm");
		runButton.addActionListener(this);
		
		algorithmList = new JComboBox<String>(algorithms);
		algorithmList.setPreferredSize(new Dimension(180,20));
		
		sudokuBoardSize = new JComboBox<Integer>(boardSizes);
		sudokuBoardSize.setPreferredSize(new Dimension(180,20));
		
		resultPane = new JTextPane();
		resultPane.setPreferredSize(new Dimension(180,180));
		
		jl1 = new JLabel("Select Board Size");
		jl2 = new JLabel("Select Algorithm");
		
		jp1.add(jl1);
		jp1.add(sudokuBoardSize);
		jp1.add(jl2);
		jp1.add(algorithmList);
		jp1.add(runButton);
		jp1.add(resultPane);
		
		add(jp1, BorderLayout.WEST);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ("runAlgorithm".equals(e.getActionCommand()))
		{	
			int algorithmIndex=algorithmList.getSelectedIndex();
			int boardSize = sudokuBoardSize.getSelectedIndex()+2;
			SudokuBoard sb = new SudokuBoard(boardSize);
			
			switch (algorithmIndex)
			{
			case 0:
				LasVegasSVS lvsvs = new LasVegasSVS(boardSize, sb);
				lvsvs.solveSudoku();
				break;
			case 1:
				LasVegasGS lvgs = new LasVegasGS(boardSize, sb);
				lvgs.solveSudoku();
				break;
			
			case 2:
				LasVegasRO lvr = new LasVegasRO(boardSize, sb);
				lvr.solveSudoku();
				break;
			
			case 3:
				LasVegasRORI lvrori = new LasVegasRORI(boardSize, sb);
				lvrori.solveSudoku();
				break;
				
			case 4:
				LasVegasRVL lvrvl = new LasVegasRVL(boardSize, sb);
				lvrvl.solveSudoku();
				break;

			default:
				break;
			}
			
		}		
	}	
	
	private static void createAndShowGUI()
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Algorithm GUI");
		frame.setPreferredSize(new Dimension(210, 390));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		SudokuAlgorithmGUI saGUI = new SudokuAlgorithmGUI();
		saGUI.setOpaque(true); // content panes must be opaque
		frame.setContentPane(saGUI);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((dim.width / 2 - frame.getSize().width / 2),
				(dim.height / 2 - frame.getSize().height / 2));
	}
	
	public static void main(String[] args)
	{
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
}

