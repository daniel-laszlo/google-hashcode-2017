/**
 * Created by Thomas on 2017. 02. 21..
 */
public class Pizza {
    private int[][] pizza;
    private int[][] scores;

    public Pizza(int[][] pizza) {
        this.pizza = pizza;
        this.scores = initScores();

        updateScores();
    }

    public void updateScores() {}

    public int[][] initScores(){

    	int[][] tempScores = new int[pizza.length][pizza[0].length];
        for (int i = 0; i < pizza.length; i++){
            for (int j = 0; j < pizza[0].length; j++){
				for(int k = -1; k < 2; k++){
					for(int l = -1; l < 2; l++){
						if(!(k == 0 && l == 0)){
							tempScores[i][j] += getCellValue(i + k, j + l, pizza[i][j]);
						}
					}
				}
            }
        }
        return  tempScores;
    }

    private int getCellValue(int i, int j, int defaultValue){
    	if(i >= 0 && i < pizza.length && j >= 0 && j < pizza[0].length){
    		return pizza[i][j];
		}
		else{
    		return defaultValue;
		}
	}
}
