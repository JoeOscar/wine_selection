package assignment2019;

import java.util.List;

import assignment2019.codeprovided.AbstractWineSampleCellar;
import assignment2019.codeprovided.Query;
import assignment2019.codeprovided.WineSample;
import assignment2019.codeprovided.WineType;
import assignment2019.codeprovided.QueryCondition;
import assignment2019.codeprovided.WineProperty;

import java.util.ArrayList;

public class WineSampleCellar extends AbstractWineSampleCellar{

	public List<String> queryLine;
	public List<Query> queryList;
	
	public WineSampleCellar(String redWineFilename, String whiteWineFilename, String queryFilename) {
		super(redWineFilename, whiteWineFilename, queryFilename);
		queryLine = readQueryFile(queryFilename);
		queryList = readQueries(queryLine);
	}


		
	
	
	@Override
	//read query imfor
	public List<Query> readQueries(List<String> queryList) {
		
		List<Query> finalQueryList = new ArrayList<>();
		List<String> wineTypeTemp = new ArrayList<>();
		List<String> conditionTemp = new ArrayList<>();
		List<QueryCondition> conditions = new ArrayList<>();
		
		
		WineType iniWineType = null;
		String catchWord;
		for(int i=0; i<queryList.size();) {
			catchWord = queryList.get(i);
			if(catchWord=="select"&& i<=queryList.size()) {
				i++;
				catchWord = queryList.get(i);
				if(catchWord=="where") {
					wineTypeTemp.add(catchWord);
					i++;
					catchWord = queryList.get(i);				
				}
				if(wineTypeTemp.size()==3) {
					iniWineType = WineType.ALL;
				}else if(wineTypeTemp.get(0)=="red") {
					iniWineType = WineType.RED;
				}else if(wineTypeTemp.get(0)=="white"){
					iniWineType = WineType.WHITE;
				}
				wineTypeTemp.clear();
				i++;
				while(i<queryList.size()) {
					catchWord = queryList.get(i);
					if(catchWord=="and") {
						WineProperty property = WineProperty.fromFileIdentifier(conditionTemp.get(0));
						String ope = conditionTemp.get(1);
						Double value = Double.parseDouble(conditionTemp.get(2));
						//here need add something
					}
				}
			}
			i++;

		}
		return null;
	}

	@Override
	public void updateCellar() {
		List<WineSample> TotalList = new ArrayList<>();
		TotalList.addAll(wineSampleRacks.get(WineType.RED));
		TotalList.addAll(wineSampleRacks.get(WineType.WHITE));
		wineSampleRacks.put(WineType.ALL, TotalList);
		
	}

	@Override
	public void displayQueryResults(Query query) {
		
		
	}

	@Override
	public List<WineSample> bestQualityWine(WineType wineType) {
		double bestQuality = 0.0;
        for (WineSample wine: wineSampleRacks.get(wineType)) {
        	bestQuality = Math.max(bestQuality, wine.getQuality());
		}
        
        QueryCondition cons = new QueryCondition(WineProperty.Quality, ">=", bestQuality);
        List<QueryCondition> conList = new ArrayList<>();
        conList.add(cons);
        Query bestQuery = new Query(wineSampleRacks.get(WineType.ALL), conList, WineType.ALL);
        return bestQuery.solveQuery();
	}

	@Override
	public List<WineSample> worstQualityWine(WineType wineType) {
		double worstQuality = 999.0;
		for (WineSample wine: wineSampleRacks.get(wineType)) {
			worstQuality = Math.min(worstQuality, wine.getQuality());
		}
		QueryCondition cons = new QueryCondition(WineProperty.Quality, "<=", worstQuality);
		List<QueryCondition> conList = new ArrayList<>();
		conList.add(cons);
		Query worstQuery = new Query(wineSampleRacks.get(WineType.ALL), conList, WineType.ALL);
		return worstQuery.solveQuery();
	}

	@Override
	public List<WineSample> highestPH(WineType wineType) {
		double highestPH = 0.0;
		for (WineSample ws: wineSampleRacks.get(wineType)) {
			highestPH = Math.max(highestPH, ws.getpH());
		}
		QueryCondition cons = new QueryCondition(WineProperty.PH, ">=", highestPH);
		List<QueryCondition> conList = new ArrayList<>();
		conList.add(cons);
		Query highQuery = new Query(wineSampleRacks.get(WineType.ALL), conList, WineType.ALL);
		return highQuery.solveQuery();
	}

	@Override
	public List<WineSample> lowestPH(WineType wineType) {
				double lowestPH = 0.0;
				for (WineSample ws: wineSampleRacks.get(wineType)) {
					lowestPH = Math.min(lowestPH, ws.getpH());
				}
				QueryCondition cons = new QueryCondition(WineProperty.PH, "<=", lowestPH);
				List<QueryCondition> conList = new ArrayList<>();
				conList.add(cons);
				Query lowQuery = new Query(wineSampleRacks.get(WineType.ALL), conList, WineType.ALL);
				return lowQuery.solveQuery();
	}

	@Override
	public double highestAlcoholContent(WineType wineType) {
		double highestAlcol= 0.0;
		for (WineSample ws: wineSampleRacks.get(wineType)) {
			highestAlcol = Math.max(highestAlcol, ws.getAlcohol());
		}
		return highestAlcol;
	}

	@Override
	public double lowestCitricAcid(WineType wineType) {
		double lowestCitricAcid = 999.0;
		for (WineSample ws: wineSampleRacks.get(wineType)) {
			lowestCitricAcid = Math.min(lowestCitricAcid, ws.getCitricAcid());
		}
		return lowestCitricAcid;
	}

	@Override
	public double averageAlcoholContent(WineType wineType) {
		double averageAlcol = 0.0;
		int cnt = 0;
		for (WineSample ws: wineSampleRacks.get(wineType)) {
			averageAlcol += ws.getAlcohol();
			cnt++;
		}
		return averageAlcol / cnt;
	}
	
	private void displayWineSamples(List<WineSample> queriedWineSample) {
		for (WineSample s: queriedWineSample) {

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(String.format("%s wine, ", s.getType()));
			sb.append(String.format("sample #%d, ", s.getId()));
			sb.append(String.format("f_acid: %.1f, ", s.getFixedAcidity()));
			sb.append(String.format("v_acid:  %.2f, ", s.getVolatileAcidity()));
			sb.append(String.format("c_acid:  %.2f, ", s.getCitricAcid()));
			sb.append(String.format("r_sugar: %.1f, ", s.getResidualSugar()));
			sb.append(String.format("chlorid: %.3f, ",  s.getChlorides()));
			sb.append(String.format("f_sulf: %.1f, ", s.getFreeSulfurDioxide()));
			sb.append(String.format("t_sulf: %.1f, ", s.getTotalSulfurDioxide()) );
			sb.append(String.format("dens: %.5f, ", s.getDensity()));
			sb.append(String.format("pH: %.2f, ", s.getpH()));
			sb.append(String.format("alc: %.1f, ", s.getAlcohol()));
			sb.append(String.format("f_sulf: %.1f, ", s.getSulphates()));
			sb.append(String.format("qual: %.1f]", s.getQuality()));
			System.out.println(sb.toString());
		}
	}
	
	public void answerQuestions() {
		System.out.println("Q1. How many wine samples are there?");
		System.out.println(wineSampleRacks.get(WineType.ALL).size());
		System.out.println("Q2. How many red wine samples are there?");
		System.out.println(wineSampleRacks.get(WineType.RED).size());
		System.out.println("Q3. How many white wine samples are there?");
		System.out.println(wineSampleRacks.get(WineType.WHITE).size());
		System.out.println("Q4. Which wine samples were graded with the best quality?*");
		displayWineSamples(bestQualityWine(WineType.ALL));
		System.out.println("Q5. Which wine samples were graded with the worst quality?*");
		displayWineSamples(worstQualityWine(WineType.ALL));
		System.out.println("Q6. Which wine samples have the highest PH?* ");
		displayWineSamples(highestPH(WineType.ALL));
		System.out.println("Q7. Which wine samples have the lowest PH?* ");
		displayWineSamples(lowestPH(WineType.ALL));
		System.out.println("Q8. What is the highest value of alcohol grade for the whole sample of red wines?");
		System.out.println(highestAlcoholContent(WineType.ALL));
		System.out.println("Q9. What is the lowest value of citric acid for the whole sample of white wines?");
		System.out.println(lowestCitricAcid(WineType.ALL));
		System.out.println("Q10. What is the average value of alcohol grade for the whole sample of white wines?");
		System.out.println(averageAlcoholContent(WineType.ALL));

	}


}
