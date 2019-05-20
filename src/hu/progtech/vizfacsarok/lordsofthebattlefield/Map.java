package hu.progtech.vizfacsarok.lordsofthebattlefield;

import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Barrack;
import java.util.Random;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Building;
import hu.progtech.vizfacsarok.lordsofthebattlefield.buildings.Farm;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Attacker;
import hu.progtech.vizfacsarok.lordsofthebattlefield.units.Unit;

public class Map {
    private int size;
    private int[][] possess;
    private int[][] terrain;
    private Unit[][] units;
    private Building[][] buildings;
    
    public Map(int size) {
        this.size = size;
        Random rand = new Random();
        terrain = new int[size][size];
        possess = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                possess[i][j] = 0;
            }
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                terrain[i][j] = rand.nextInt(6) + 1;
                if(terrain[i][j] == 2)possess[i][j] = 3;
                if(terrain[i][j] == 3)terrain[i][j] = 1;
                if(terrain[i][j] == 4)terrain[i][j] = 1;
                if(terrain[i][j] == 5)terrain[i][j] = 1;
                if(terrain[i][j] == 6)terrain[i][j] = 1;
            }
        }
        int[] fakedata = {0,0};
        units = new Unit[size][size];
        units[0][0] = new Attacker(1, 1, 5, 1, fakedata, fakedata, 1,1,3,1);
        possess[0][0] = 1;
        int[] fakedata2 = {3,3};
        units[3][3] = new Attacker(1, 1, 5, 1, fakedata2, fakedata2, 1,1,3,1);
        possess[3][3] = 1;
        int[] fakedata3 = {3,4};
        units[3][4] = new Attacker(1, 1, 5, 1, fakedata3, fakedata3, 1,1,3,1);
        possess[3][4] = 1;
        int[] fakedata4 = {3,5};
        units[3][5] = new Attacker(1, 1, 5, 1, fakedata4, fakedata4, 1,8,3,1);
        possess[3][5] = 1;
        int[] fakedata5 = {3,6};
        units[3][6] = new Attacker(3, 10, 5, 2, fakedata5, fakedata5, 1,1,3,1);
        possess[3][6] = 2;
        
        buildings = new Building[size][size];
        int[] fakedata6 = {3, 11};
        buildings[3][11] = new Barrack(fakedata, 1, fakedata6[0], fakedata6[1], 5, 1);
        possess[3][11] = 1;
        int[] fakedata7 = {2, 10};
        buildings[2][10] = new Farm(fakedata, 2, fakedata7[0], fakedata7[1], 1, 1, 5, 5);
        possess[2][10] = 1;
        
    }
    
    public Building getBuilding(int i, int j){
        return buildings[i][j];
    }
    
    public int getPossess(int i, int j){
        return possess[i][j];
    }
    
    public Unit getUnit(int i, int j){
        return units[i][j];
    }
    
    public int getTerrain(int i, int j){
        return terrain[i][j];
    }
    
    public int getSize(){
        return size;
    }
    
    public void setPossess(int i, int j, int x){
        possess[i][j] = x;
    }
    
    public void setUnit(int i, int j, Unit unit){
        units[i][j] = unit;
    }
    
    public int marking(int row, int column) {
        if(units[row][column] != null){
            return 1;
        }else{
            if(units[row][column] != null){
                return 2;
            }else{
                return 3;
            }
        }
    }
    
    public void action(int n) {
        
    }
    
    
}
