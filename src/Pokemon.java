import java.io.*;
import java.util.*;

public class Pokemon {
    // contains 50 pokemon

    private String name;
    private int HP;
    private int attack;
    private int defense;
    private int evLevel;
    private int nextEvLevel;
    private double catchRate;
    private int appearanceRate;
    private String type1;
    private String type2;
    private int evolution;
    private Attack[] attacks;
    private Attack[] learnset;
    private int[] thresholds;
    private String[] strengths;
    private String[] weakness;

    public Pokemon(String name, int HP, int attack, int defense, int evLevel, int nextEvLevel, double catchRate, int appearanceRate, String type1, String type2, int evolution, Attack[] attacks, Attack[] learnset, int[] thresholds, String[] strengths, String[] weakness) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.evLevel = evLevel;
        this.nextEvLevel = nextEvLevel;
        this.catchRate = catchRate;
        this.appearanceRate = appearanceRate;
        this.type1 = type1;
        this.type2 = type2;
        this.evolution = evolution;
        this.attacks = attacks;
        this.learnset = learnset;
        this.thresholds = thresholds;
        this.strengths = strengths;
        this.weakness = weakness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAppearanceRate() {
        return appearanceRate;
    }

    public void setAppearanceRate(int appearanceRate) {
        this.appearanceRate = appearanceRate;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    public double getCatchRate() {
        return catchRate;
    }

    public void setCatchRate(double catchRate) {
        this.catchRate = catchRate;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEvLevel() {
        return evLevel;
    }

    public void setEvLevel(int evLevel) {
        this.evLevel = evLevel;
    }

    public int getEvolution() {
        return evolution;
    }

    public void setEvolution(int evolution) {
        this.evolution = evolution;
    }

    public Attack[] getLearnset() {
        return learnset;
    }

    public void setLearnset(Attack[] learnset) {
        this.learnset = learnset;
    }

    public int getNextEvLevel() {
        return nextEvLevel;
    }

    public void setNextEvLevel(int nextEvLevel) {
        this.nextEvLevel = nextEvLevel;
    }

    public String[] getStrengths() {
        return strengths;
    }

    public void setStrengths(String[] strengths) {
        this.strengths = strengths;
    }

    public int[] getThresholds() {
        return thresholds;
    }

    public void setThresholds(int[] thresholds) {
        this.thresholds = thresholds;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String[] getWeakness() {
        return weakness;
    }

    public void setWeakness(String[] weakness) {
        this.weakness = weakness;
    }

    public double calculateAttackBonus(String opponentType) {
        double attackBonus = 1.0; // Default attack bonus is 1.0 (no bonus)

        // Check if the opponent's type is in the Pokemon's strength or weakness array
        for (String type : strengths) {
            if (type.equalsIgnoreCase(opponentType)) {
                attackBonus += 0.2; // Add 20% attack bonus if strong against opponent type
                break; // Exit the loop since bonus is already applied
            }
        }

        for (String type : weakness) {
            if (type.equalsIgnoreCase(opponentType)) {
                attackBonus -= 0.2; // Subtract 20% attack bonus if weak against opponent type
                break; // Exit the loop since bonus is already applied
            }
        }

        return attackBonus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("HP: ").append(HP).append("\n");
        sb.append("Attack: ").append(attack).append("\n");
        sb.append("Defense: ").append(defense).append("\n");
        sb.append("Evolution Level: ").append(evLevel).append("\n");
        sb.append("Next Evolution Level: ").append(nextEvLevel).append("\n");
        sb.append("Catch Rate: ").append(catchRate).append("\n");
        sb.append("Appearance Rate: ").append(appearanceRate).append("\n");
        sb.append("Type 1: ").append(type1).append("\n");
        sb.append("Type 2: ").append(type2).append("\n");
        sb.append("Evolution: ").append(evolution).append("\n");
        sb.append("Attacks: ").append(Arrays.toString(attacks)).append("\n");
        sb.append("Learnset: ").append(Arrays.toString(learnset)).append("\n");
        sb.append("Thresholds: ").append(Arrays.toString(thresholds)).append("\n");
        sb.append("Strengths: ").append(Arrays.toString(strengths)).append("\n");
        sb.append("Weaknesses: ").append(Arrays.toString(weakness)).append("\n");
        return sb.toString();
    }
}