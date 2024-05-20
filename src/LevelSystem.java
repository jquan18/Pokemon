public class LevelSystem {
    int[] RequiredEXP = new int[50];
    int currentEXP = 0,previousLevel=0, currentLevel =0;
    Pokemon pokemon;
    public LevelSystem(Pokemon pokemon) {
        RequieredEXP();
        this.pokemon = pokemon;
    }
    public void RequieredEXP() {
        for (int i=0, j= 0; i<RequiredEXP.length; i++) {
            if (i>=30) { //20 EXP point in 31-35 level
                j = 300;
                RequiredEXP[i] = j;
            }
            else if (i>=20) {// 15 EXP ponit in 21-30 level
                j = 200;
                RequiredEXP[i] = j;
            }
            else { // only need 5 EXP point to upgrade level in 1-10 level
                j = 100;
                RequiredEXP[i] = j;
            }
        }
    }

    public void addEXP(int EXP) {
        System.out.println("You earn "+ EXP + " EXP points");
        currentEXP += EXP;
        LevelUp();
    }
    public void LevelUp() {
        int i=0;
        previousLevel = currentLevel;
        while (currentEXP>RequiredEXP[i]) {
            currentLevel = i+1;
            i++;
        }
        System.out.println("You are Level " + (i+1) + " now");
    }

    //Use for test Level System
    public void getCurrentEXP() {
        System.out.println("Current EXP: " + currentEXP);
    }
    public void setCurrentEXP(int EXP) {
        this.currentEXP = EXP;
    }
}
