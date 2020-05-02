package assignment2019;

public class WineSampleBrowser {
	
	

	

	public static void main(String[] args) {
		
		if (args.length ==0) {
			args = new String[] {
					"D:\\workspace\\assignment2019_complited\\src\\assignment2019\\winequality-red.csv",
					"D:\\workspace\\assignment2019_complited\\src\\assignment2019\\winequality-white.csv",
					"D:\\workspace\\assignment2019_complited\\src\\assignment2019\\queries.txt"};
		}
		
		//instance variables
		String redWineFile = args[0];
		String whiteWineFile = args[1];
		String queriesFile = args[2];
		
		
		
		WineSampleCellar wineSample = new WineSampleCellar(redWineFile, whiteWineFile, queriesFile);
		wineSample.answerQuestions();
		
		WineSampleBrowserPanel panel = new WineSampleBrowserPanel(wineSample);
	}
}
