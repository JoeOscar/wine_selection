package assignment2019;

import javax.swing.JFrame;

import assignment2019.codeprovided.AbstractWineSampleBrowserPanel;
import assignment2019.codeprovided.AbstractWineSampleCellar;

public class WineSampleBrowserPanel extends AbstractWineSampleBrowserPanel{

	private JFrame jf;
	
	
	public WineSampleBrowserPanel(AbstractWineSampleCellar cellar) {
		super(cellar);
		
		value.setText("0.0"); //set default value
		addListeners(); //add event listener
		jf = new JFrame();
		jf.setBounds(100,50,1200,800);
		jf.setContentPane(this);
		
		jf.setVisible(true);
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearFilters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWineList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeQuery() {
		// TODO Auto-generated method stub
		
	}

}
