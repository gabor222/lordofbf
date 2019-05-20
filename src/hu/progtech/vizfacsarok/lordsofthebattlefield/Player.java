package hu.progtech.vizfacsarok.lordsofthebattlefield;

public class Player {
    private int ID;
    private String name;
    private int food;
    private int wood;
    private int stone;
    private int gold;
    private int population;
    private int maxpopulation;
    
    
    public Player(int ID, String name, int food, int wood, int stone, int gold, int population, int maxpopulation){
        this.ID = ID;
        this.name = name;
        this.food = food;
        this.wood = wood;
        this.stone = stone;
        this.gold = gold;
        this.population = population;
        this.maxpopulation = maxpopulation;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
	
	public int getWood(){
		return wood;
	}
	
	public void setWood(int wood){
		this.wood = wood;
	}
	
	public int getStone(){
		return stone;
	}
	
	public void setStone(int stone){
		this.stone = stone;
	}
	
	public int getGold(){
		return gold;
	}
	
	public void setGold(int gold){
		this.gold = gold;
	}
	
	public int getPopulation(){
		return population;
	}
	
	public void setPopulation(int population){
		this.population = population;
	}
	
	public int getMaxPopulation(){
		return maxpopulation;
	}
	
	public void setMaxPopulation(int maxpopulation){
		this.maxpopulation = maxpopulation;
	}
}