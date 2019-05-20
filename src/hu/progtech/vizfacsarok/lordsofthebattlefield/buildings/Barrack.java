package hu.progtech.vizfacsarok.lordsofthebattlefield.buildings;

import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.buildingenums.Infantry;
import hu.progtech.vizfacsarok.lordsofthebattlefield.Map;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Unit;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Attacker;

public class Barrack extends UnProdBuilding {

    private Infantry currentType = Infantry.valueOf("ATTACKER");

    public Barrack(int[] buildCost, int id, int positionX, int positionY, int maxHealth, int owner) {
        super(buildCost, 1, id, positionX, positionY, maxHealth, owner);
    }

    public Infantry getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = Infantry.valueOf(currentType);
    }

    @Override
    public void releaseUnit(Map map) {
		
		if(currentType == Infantry.valueOf("ATTACKER") && map.getPossess(this.getPositionX(), this.getPositionY() - 1) == 0){
            int[] position = {this.getPositionX(), this.getPositionY() - 1};
            int[] cost = {0, 0};
            Unit attacker = new Attacker(1, 1,5,1, position, cost, 3,3, 3, 3);
            map.setUnit(this.getPositionX(), this.getPositionY() -1, attacker);
            map.setPossess(this.getPositionX(), this.getPositionY() -1, 1);
            System.out.println("valami234");
        }
		/*boolean isFree = false;
		int[] free = freeSpaces(map);
		int i = 0;
		int freePos;
		while(!isFree && i < free.length){
			isFree = (free[i] == 0);
			freePos = i;
			++i;
		}
        if(currentType == Infantry.valueOf("ATTACKER") && isFree == true){
			int[] position = new int[2];
			switch (freePos) {
				case 0:
					
			}
            int[] position = {this.getPositionX(), this.getPositionY() - 1};
            int[] cost = {0, 0};
            Unit attacker = new Attacker(1, 1,5,1, position, cost, 3,3, 3, 3);
            map.setUnit(this.getPositionX(), this.getPositionY() -1, attacker);
        }*/

    }

    @Override
    public int[] getStats() {
        int[] stats = new int[3];
        stats[0] = this.getCurrentHealth();
        stats[1] = this.getMaxHealth();
        if(this.currentType == Infantry.valueOf("ATTACKER")){
            stats[2] = 1;
        } else{
            stats[2] = 0;
        }
        return stats;
    }

    /*private int[] freeSpaces(Map map){
        int[] free = new int[4];
       		//osszegyujti, mi van az epület kozvetlenn kornyezeteben
		free[0] = map.getPossess(this.getPositionX(), this.getPositionY() - 1);
		free[1] = map.getPossess(this.getPositionX() - 1, this.getPositionY());
		free[2] = map.getPossess(this.getPositionX(), this.getPositionY() + 1);
		free[3] = map.getPossess(this.getPositionX() + 1, this.getPositionY());
		
		return free;
    }*/
}
