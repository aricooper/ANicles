package gavehicles.classes;

import gavehicles.interfaces.Evaluable;
import gavehicles.vehicles.PreyVehicle;

/*
 Written by Paul Schot
 Each int in traitValue represents one of the traits of a PredVehicle
 traitValue[0] = 
 traitValue[1] = 
 traitValue[2] = 
 */
public class PredTraitDeterminer {

    private static final int GENE_LENGTH = 8;
    private static final int NUM_GENES = 5;

    private static final int[] traitValue = new int[NUM_GENES];

    public static void determine(PreyVehicle v) {
        findGenes(v);
        for (int i = 0; i < NUM_GENES; i++) {
            System.out.println("TraitValue: " + traitValue[i]);
        }
    }

    private static int evaluateGene(Evaluable chromo, int whichGene) {
        byte[] bits = chromo.getDNA();
        int length = bits.length;
        int distance = length / NUM_GENES;
        int start = whichGene * distance;

        int returnMe = 0;

        for (int i = start; i < start + GENE_LENGTH; i++) {
            returnMe += bits[i];
        }

        return returnMe;
    }

    private static void findGenes(Evaluable chromo) {
        for (int i = 0; i < NUM_GENES; i++) {
            traitValue[i] = evaluateGene(chromo, i);
        }
    }

    @Override
    public String toString() {
        return "Trait Determiner";
    }

}
