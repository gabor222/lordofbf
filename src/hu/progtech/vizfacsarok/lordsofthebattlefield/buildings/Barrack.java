package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.buildingenums.Infantry;
import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.Player;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Archer;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Unit;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Soldier;

public class Barrack extends UnProdBuilding {
    
    private static final int[] BUILD_COST = new int[]{0, 50, 40, 50};
    private Infantry currentType = Infantry.valueOf("SOLDIER");

    public Barrack(int positionX, int positionY, int maxHealth, int owner) {
        super(positionX, positionY, maxHealth, owner);
    }

    public Infantry getCurrentType() {
        return currentType;
    }
    //amikor rakattintunk a gyartson x tipust parancsra, eloszor a setCurrentType-ot hívja meg, aztan a setRoundcountert
    //mert a setRoundcounternek tudnia kell az uj tipusrol, hogy jot vizsgaljon
    public void setCurrentType(String currentType) {

        this.currentType = Infantry.valueOf(currentType);
        
    }
    
    @Override
    public void setRoundcounter(Player player){
        boolean lacks = false;
        int[] materials = new int[4];
        materials[0] = player.getFood();
        materials[1] = player.getWood();
        materials[2] = player.getStone();
        materials[3] = player.getGold();
        switch (this. currentType) {
            case SOLDIER :
                int i = 0;
                while(!lacks && i < 4){
                    lacks = Soldier.getProductionCost()[i] > materials[i];
                    ++i;
                }
                break;
            case ARCHER :
                int j = 0;
                while(!lacks && j < 4){
                    lacks = Archer.getProductionCost()[j] > materials[j];
                    ++j;
                }
                break;
                
        }
        
        if(!lacks){
            switch (this.currentType){
                case SOLDIER :
                    this.roundcounter = Soldier.getProductionTime();
                    break;
                case ARCHER :
                    this.roundcounter = Archer.getProductionTime();
                    break;
            }
                
        }
    }

    @Override
    public boolean releaseUnit(Map map) {
            if(this.getPositionY() - 1 >= 0 && map.getPossess(this.getPositionX(), this.getPositionY() - 1) == 0){
                        int[] position = {this.getPositionX(), this.getPositionY() - 1};
                switch (currentType) {
                    case SOLDIER :
                        //Unit soldier = new Soldier....
                        Unit soldier = new Soldier(this.getOwner(), position);
                        map.setUnit(this.getPositionX(), this.getPositionY() -1, soldier);
                        map.setPossess(this.getPositionX(), this.getPositionY() -1, 1);
                        break;
                    case ARCHER :
                        //Unit archer = new Archer....
                        Unit archer = new Archer(this.getOwner(), position);
                        map.setUnit(this.getPositionX(), this.getPositionY() -1, archer);
                        map.setPossess(this.getPositionX(), this.getPositionY() -1, 1);
                        break;
                }
                return true;
            } else {
                return false;
            }
        
		
		
        }
		

    

    @Override
    public int[] getStats() {
        int[] stats = new int[18];
        if(this.getBuildTimeLeft() > 0){
            stats[0] = 0;
            stats[1] = 0;
        } else {
            if(this.getRoundcounter() == 0){
            stats[0] = 2; //start soldier
            stats[1] = 3; //start archer
            
            } else {
                stats[0] = 0; //nem elérhetőek a parancsok
                stats[1] = 0;
            }
        }
        
        
        for(int i = 2; i < 11; ++i){
            stats[i] = 0;
        }
        stats[11] = 1; //demolish
        stats[12] = this.getCurrentHealth(); //hp
        stats[13] = this.getMaxHealth(); //maxhp
        if(this.currentType == Infantry.valueOf("SOLDIER")){
            stats[14] = 2; //soldier
        } else{
            stats[14] = 3; //archer
        }
        stats[15] = this.getRoundcounter();
        stats[16] = -1; //pea
        stats[17] = -1;
        return stats;
    }
    
    public static int[] getBUILD_COST() {
        return BUILD_COST;
    }

    
}
