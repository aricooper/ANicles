/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gavehicles.classes;

import gavehicles.interfaces.Evaluable;
import gavehicles.vehicles.PreyVehicle;

/*
 *  Written by Paul Schot
 *  Each int in traitValue represents one of the traits of a PreyVehicle
 *  traitValue[0] = dangerSense
 *  traitValue[1] = herding
 *  traitValue[2] = poisonous
 */
public class PreyTraitDeterminer {

    private static final int GENE_LENGTH = 3;
    private static final int NUM_GENES = 3;

    private static final int [] traitValue = new int [NUM_GENES];

    public static void determine(PreyVehicle v) {
        findGenes(v);
        for (int i = 0; i < NUM_GENES; i++) {
            System.out.println("TraitValue: " + traitValue[i]);
        }
    }

    private static void findGenes(Evaluable chromo) {
        for (int i = 0; i < NUM_GENES; i++) {
            traitValue[i] = evaluateGene(chromo, i);
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
    
     public static int[] getValue(Evaluable chromo) {
        findGenes(chromo);
        return traitValue;
    }


    @Override
    public String toString() {
        return "Trait Determiner";
    }
    
    public static void main(String[] args) {
    }

}
