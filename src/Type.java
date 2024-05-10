public class Type {
    private String[] type;
    private final double[][] counterMatrix = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 0, 1, 1, 0.5, 1 },
            { 1, 0.5, 0.5, 2, 1, 2, 1, 1, 1, 1, 1, 2, 0.5, 1, 0.5, 1, 2, 1 },
            { 1, 2, 0.5, 0.5, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0.5, 1, 1, 1 },
            { 1, 0.5, 2, 0.5, 1, 1, 1, 0.5, 2, 0.5, 1, 0.5, 2, 1, 0.5, 1, 0.5, 1},
            { 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0, 2, 1, 1, 1, 1, 0.5, 1, 1, 1 },
            { 1, 0.5, 0.5, 2, 1, 0.5, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 0.5, 1 },
            { 2, 1, 1, 1, 1, 2, 1, 0.5, 1, 0.5, 0.5, 0.5, 2, 0, 1, 2, 2, 0.5 },
            { 1, 1, 1, 2, 1, 1, 1, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 1, 1, 0, 2 },
            { 1, 2, 1, 0.5, 2, 1, 1, 2, 1, 0, 1, 0.5, 2, 1, 1, 1, 2, 1 },
            { 1, 1, 1, 2, 0.5, 1, 2, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 0.5, 1},
            { 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0.5, 1, 1, 1, 1, 0, 0.5, 1 },
            { 1, 0.5, 1, 2, 1, 1, 0.5, 0.5, 1, 0.5, 2, 1, 1, 0.5, 1, 2, 0.5, 0.5 },
            { 1, 2, 1, 1, 1, 2, 0.5, 1, 0.5, 2, 1, 2, 1, 1, 1, 1, 0.5, 1 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0.5, 0 },
            { 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 0.5},
            { 1, 0.5, 0.5, 1, 0.5, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0.5, 2},
            { 1, 0.5, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 2, 2, 0.5, 1}
    };
    private final String[] pokemonType = {"Normal", "Fire", "Water", "Grass", "Electric", "Ice",
                                          "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug",
                                          "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"};
    public Type(String[] typeName) {
        this.type = typeName;
    }
    public String[] getTypeName() {
        return this.type;
    }

    public double typeCounter(String[] enemy) {
        double total = 1;
        int trainerTypeIndex = 0;
        int enemyTypeIndex = 0;

        for (int i=0; i< this.type.length; i++) {
            // Get the Trainer First Type Index
                for (int j=0; j< pokemonType.length; j++) {
                    if (this.type[i].equalsIgnoreCase(pokemonType[j])) {
                        trainerTypeIndex = j;
                        break;
                    }
                }
                for (int k=0; k<enemy.length; k++) {
                    for (int j=0; j<pokemonType.length; j++) {
                        if (enemy[k].equalsIgnoreCase(pokemonType[j])) {
                            enemyTypeIndex = j;
                            break;
                        }
                    }
                    total *= counterMatrix[trainerTypeIndex][enemyTypeIndex];
                }
        }
        return total;
    }
    public double moveCounter(String own, String[] opposite) {
        int trainerMoveIndex = 0;
        double total = 1;
        int oppositeTypeIndex = 0;

        for (int i=0; i< pokemonType.length; i++) {
            if (pokemonType[i].equalsIgnoreCase(own)) {
                trainerMoveIndex = i;
                break;
            }
        }

        for (int j=0; j<opposite.length; j++) {
            for (int k=0; k< pokemonType.length; k++) {
                if (opposite[j].equalsIgnoreCase(pokemonType[k])) {
                    oppositeTypeIndex = k;
                    break;
                }
                total *= counterMatrix[trainerMoveIndex][oppositeTypeIndex];
            }
        }
        // float, double , long and short are prohibited in switch()
        if (total == 4) {
            System.out.println("Doubly Effective!!!!");
            return 2.56;
        }
        else if (total == 2) {
            System.out.println("Super Effective!!");
            return 1.6;
        }
        else if (total == 1) {
            return 1;
        }
        else if (total == 0.5 ) {
            System.out.println("Not very effective!");
            return 0.625;
        }
        else if (total == 0.25){
            System.out.println("Not effective.");
            return 0.39;
        }
        else {
            System.out.println("Immunity");
            return 0;
        }
    }

}
