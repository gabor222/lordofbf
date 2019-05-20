package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;


public abstract class Building {

 
 
   
    private final int positionX;
    private final int positionY;
    private int currentHealth;
    private int maxHealth;
    private final int owner;

    public Building(int positionX, int positionY, int maxHealth, int owner){
        
        
    
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.owner = owner;
  
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
	
    public void demolish(Map map){
        map.setPossess(this.getPositionX(), this.getPositionY(), 0);
        map.setBuilding(this.getPositionX(), this.getPositionY(), null);
    }
    
	protected abstract int[] getStats();

}
