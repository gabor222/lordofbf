package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

public abstract class Building {

    private int[] buildCost;
    private final int size;
    private final int id;
    private final int positionX;
    private final int positionY;
    private int currentHealth;
    private int maxHealth;
    private final int owner;

    public Building(int[] buildCost, int size, int id, int positionX, int positionY, int maxHealth, int owner){
        this.buildCost = buildCost;
        this.size = size;
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.owner = owner;
    }

    public int[] getBuildCost(){
        return this.buildCost;
    }

    public int getSize(){
        return this.size;
    }

    public int getId(){
        return this.id;
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public void setMaxHealth(int maxHealth){
		int boost = maxHealth - this.maxHealth;
        this.maxHealth = maxHealth;
		this.currentHealth += boost;
		
    }

    public int getOwner(){
        return this.owner;
    }
	
	protected abstract int[] getStats();

}
