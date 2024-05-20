import java.util.Random;
import java.util.logging.Level;

public class LevelSystem {
    int[] RequiredEXP = new int[50];
    int currentEXP = 0,previousLevel=0, currentLevel = 5;

    public LevelSystem() {
        RequiredEXP();
    }
    public void RequiredEXP() {
        for (int i=0, j= 0; i<RequiredEXP.length; i++) {
            if (i>=30) {
                j = 300;
                RequiredEXP[i] = j;
            }
            else if (i>=20) {
                j = 200;
                RequiredEXP[i] = j;
            }
            else {
                j = 100;
                RequiredEXP[i] = j;
            }
        }
    }

    public void addEXP(Pokemon pokemon, int EXP) {
        System.out.println(pokemon.getName() + " earn "+ EXP + " EXP points");
        currentEXP += EXP;
        LevelUp(pokemon);
        attributesIncrease(pokemon);
    }
    public void LevelUp(Pokemon pokemon) {
        int i=0;
        previousLevel = currentLevel;
        while (currentEXP>RequiredEXP[i]) {
            currentLevel = i+1;
            i++;
        }
        if (currentLevel > previousLevel) {
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
    public void setDefaultLevel(String location) {
        switch (location) {
            case "Pallet Town": {
                currentLevel = 5;
                break;
            }
            case "Pewter_Wild": {
                currentLevel = new Random().nextInt(2)+4;
                break;
            }
            case "Pewter_Gym": {
                currentLevel = 10;
                break;
            }
            case "Cerulean_Wild": {
                currentLevel = new Random().nextInt(5)+8;
                break;
            }
            case "Cerulean_Gym": {
                currentLevel = 15;
                break;
            }
            case "Vermilion_Wild": {
                currentLevel = new Random().nextInt(5)+13;
                break;
            }
            case "Vermilion_Gym": {
                currentLevel = 20;
                break;
            }
            case "Celadon_Wild": {
                currentLevel = new Random().nextInt(5)+18;
                break;
            }
            case "Celadon_Gym": {
                currentLevel = 25;
                break;
            }
            case "Fuchsia_Wild": {
                currentLevel = new Random().nextInt(5)+23;
                break;
            }case "Fuchsia_Gym": {
                currentLevel = 30;
                break;
            }
            case "Saffron_Wild": {
                currentLevel = new Random().nextInt(5)+28;
                break;
            }case "Saffron_Gym": {
                currentLevel = 35;
                break;
            }case "Cinnabar_Wild": {
                currentLevel = new Random().nextInt(5)+33;
                break;
            }case "Cinnabar_Gym": {
                currentLevel = 40;
                break;
            }
            case "Viridian_Wild": {
                currentLevel = new Random().nextInt(5)+38;
                break;
            }
            case "Viridian_Gym": {
                currentLevel = 45;
                break;
            }
            case "Gary": {
                currentLevel = new Random().nextInt(5)+45;
                break;
            }
            default:
                System.out.println("Error on Pokemon Set Level!");
        }
    }
    public void attributesIncrease(Pokemon pokemon) {
        int evPoint = currentLevel - previousLevel;
        pokemon.HP += (evPoint*2);
        pokemon.speed += (evPoint);
        pokemon.attack += evPoint;
        pokemon.defense += evPoint;
    }
}
