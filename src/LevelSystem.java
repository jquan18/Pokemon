import java.util.Random;
import java.util.logging.Level;

public class LevelSystem {
    int[] RequiredEXP = new int[50];
    int currentEXP = 0,previousLevel=1, currentLevel=1;

    public LevelSystem() {
        initializeRequiredEXP();
    }
    public void initializeRequiredEXP() {
        for (int i=1; i<RequiredEXP.length; i++) {
            RequiredEXP[0] = 100;
            if (i>=30) {
                RequiredEXP[i] = RequiredEXP[i-1] + 300;
            }
            else if (i>=20) {
                RequiredEXP[i] = RequiredEXP[i-1] + 200;
            }
            else {
                RequiredEXP[i] = RequiredEXP[i-1] + 100;
            }
        }
    }

    public void addEXP(Pokemon pokemon, int EXP) {
        System.out.println(pokemon.getName() + " earn "+ EXP + " EXP points");
        currentEXP += EXP;
        LevelUp(pokemon);
    }
    public void LevelUp(Pokemon pokemon) {
        previousLevel = currentLevel;
        //Set level 50 stop growth
        for (int i=currentLevel; i<RequiredEXP.length; i++) {
            if (currentEXP >= RequiredEXP[i]) {
                currentLevel = i + 1;
                break;
            }
        }
        if (currentLevel > previousLevel) {
            System.out.printf("%s [ Level.%d ] --> [ Level.%d ]\n",pokemon.getName(),previousLevel,currentLevel);
            attributesIncrease(pokemon);
        }
    }

    //Use for test Level System
    public void getCurrentEXP() {
        System.out.println("Current EXP: " + currentEXP);
    }
    public void setCurrentEXP(int EXP) {
        this.currentEXP = EXP;
    }

    public void setLevelManual(Pokemon pokemon, int level) {
        currentLevel = level;
        currentEXP = RequiredEXP[currentLevel - 1];
    }

    public void setDefaultLevel(String location, Pokemon pokemon) {
        switch (location) {
            case "Pallet Town": {
                currentLevel = 5;
                break;
            }
            case "Pewter City": {
                currentLevel = new Random().nextInt(2)+4;
                break;
            }
            case "Brock": {
                currentLevel = 10;
                break;
            }
            case "Cerulean City": {
                currentLevel = new Random().nextInt(5)+8;
                break;
            }
            case "Misty": {
                currentLevel = 15;
                break;
            }
            case "Vermilion City": {
                currentLevel = new Random().nextInt(5)+13;
                break;
            }
            case "Lt. Surge": {
                currentLevel = 20;
                break;
            }
            case "Celadon City": {
                currentLevel = new Random().nextInt(5)+18;
                break;
            }
            case "Erika": {
                currentLevel = 25;
                break;
            }
            case "Fuschia City": {
                currentLevel = new Random().nextInt(5)+23;
                break;
            }case "Koga": {
                currentLevel = 30;
                break;
            }
            case "Saffron City": {
                currentLevel = new Random().nextInt(5)+28;
                break;
            }case "Sabrina": {
                currentLevel = 35;
                break;
            }case "Cinnabar Island": {
                currentLevel = new Random().nextInt(5)+33;
                break;
            }case "Blaine": {
                currentLevel = 40;
                break;
            }
            case "Viridian City": {
                currentLevel = new Random().nextInt(5)+38;
                break;
            }
            case "Giovanni": {
                currentLevel = 45;
                break;
            }
            case "Gary": {
                currentLevel = new Random().nextInt(5)+45;
                break;
            }
            case "Lavender Town": {
                currentLevel = new Random().nextInt(10)+28;
                break;
            }
            case "Cheating": {
                currentLevel = 50;
                break;
            }
            default:
                System.out.println("Error on Pokemon Set Level!");
        }
        this.currentEXP = RequiredEXP[currentLevel-1];
        attributesIncrease(pokemon);
    }
    public void attributesIncrease(Pokemon pokemon) {
        int evPoint = currentLevel - previousLevel;
        pokemon.HP += (evPoint*2) ;
        pokemon.maxHP += (evPoint*2);
        pokemon.speed += (evPoint);
        pokemon.attack += evPoint;
        pokemon.defense += evPoint;
    }

}