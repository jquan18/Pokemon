import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
QM = quick move (low damage, but more change )
MM = Main move (High damage, but less change)

Create a Pokemon class to store each Pokemon as an independent object.
Type of Pokemon also just a new object type, the effectiveness of pokemon will automaticcally cal in every battle
LevelSystem used for set pokemon level based on their living area
master is use for catch pokemon, trainer only can catch the pokemon without master.
Moves will have further explain in its class
 */
public class Pokemon{
    int maxHP, HP, speed, attack, defense;
    String name;
    Type type;
    QuickMove quickMove;
    MainMove mainMove;
    LevelSystem levelSystem;
    String master;
    String[] typeString;

// Load pokemon from Pokemon database
    public Pokemon() {
        this(null, null, 0, 0,0, 0, null, null, 0, null, null, 0 );
    }
    public Pokemon(String name, String[] type, int HP,int attack, int defense, int speed, String QMName,String QMType, int QMDamage, String MMName, String MMType, int MMDamage) {
        this.name = name;
        this.type = new Type(type);
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;     // Detect the priority of pokemon to attack
        this.maxHP = HP;
        this.HP = HP;
        this.quickMove = new QuickMove(QMName, QMType, QMDamage);
        this.mainMove = new MainMove(MMName, MMType, MMDamage);
        this.levelSystem = new LevelSystem();
        this.master = "";
        this.typeString = type;
    }

	//for savegame loadgame
	public Pokemon(String name, String[] type,int maxHP, int HP,int attack, int defense, int speed, String QMName,String QMType, int QMDamage, String MMName, String MMType, int MMDamage, int level) {
        this.name = name;
        this.type = new Type(type);
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxHP = maxHP;
        this.HP = HP;
        this.quickMove = new QuickMove(QMName, QMType, QMDamage);
        this.mainMove = new MainMove(MMName, MMType, MMDamage);
        this.levelSystem = new LevelSystem();
        this.master = "Trainer";
        this.typeString = type;

		//Set level
		this.levelSystem.setLevelManual(this, level);
    }

    // For pokemon to use QuickMove or MainMove based on their selection in battlesystem, damage is calculated in this method
    public void useMove(int index, Pokemon enemy) {
        int value = 0;
        switch (index) {
            case 1:
                quickMove.QMPoint -= 1;
                System.out.printf("%s used %s\n", this.name, this.quickMove.getMovesName());
                //Check toString()
                value = (int)((((2*getLevel())/2 + 2) * quickMove.getDPR() * (getAttack()/enemy.getDefense())/ 50) * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), enemy.getType().getMovesTypeString()));
                break;
            case 2:
                mainMove.MMPoint -= 1;
                System.out.printf("%s used %s", this.name, this.mainMove.getMovesName());
                value = (int)((((2*getLevel())/2 + 2) * quickMove.getDPR() * (getAttack()/enemy.getDefense())/ 50) * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), enemy.getType().getMovesTypeString()));
                break;
        }

        if (value < 5) {
            value = new Random().nextInt(5)+1;
        }

        receiveDamage(value, enemy);
    }

    // Receive damage from opposite pokemon, for trainer, opposite is WildPokemon/Gary/GymLeader. for them, opposite is Trainer
    public void receiveDamage(int value, Pokemon enemy) {
        enemy.HP -= value;
        System.out.printf("%s received %d points damage!", enemy.getName(), value);
        if (enemy.HP < 0)
            enemy.HP = 0;
    }
    // Display basic info of current pokemon
    public void displayPokemonStatus() {
        System.out.print(this.name);
        System.out.printf("--HP: [ %s ](%s/%s)\n", getHpBar(), this.HP, this.maxHP);
    }

    public String getHpBar() {
        int barLength = 10;
        int bar = Math.abs((int) Math.round((double) this.HP / maxHP * barLength));
         return "=".repeat(bar) + " ".repeat(barLength - bar);
    }
    public String getMoveName(int index) {
		if (index == 0)
		    return quickMove.getMovesName();
        else
            return mainMove.getMovesName();
    }
    public String getMoveType(int index) {
		ArrayList<String> arr = new ArrayList<>();
        if (index == 0)
		arr.addAll(Arrays.asList(quickMove.getMovesTypeString()));
        else if (index == 1)
		arr.addAll(Arrays.asList(mainMove.getMovesTypeString()));

        return arr.get(0);
    }
	public String getName() {
		return this.name;
	}
	public int getLevel() {
		return levelSystem.currentLevel;
	}
	public Type getType() {
		return type;
	}

    public void setLevel(String location) {
		this.levelSystem.setDefaultLevel(location, this);
    }

    // Use for savegame to set the level
	public void setLevelManual(int level) {
		this.levelSystem.setLevelManual(this, level);
	}

    public int getAttack() {
        return this.attack;
    }
    public int getDefense() { return this.defense; }
    public void gainEXP(Pokemon enemy) {
        this.levelSystem.addEXP(this, enemy.levelSystem.currentLevel * 4);
    }
    public void setMaster(String master) {
        this.master = master;
    }
	public int getHP(){
		return this.HP;
	}
	public int getMaxHP(){
		return this.maxHP;
	}
	public int getSpeed(){
		return this.speed;
	}
}
