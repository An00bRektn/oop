/*
 * Author: Ryan Syed
 *      Mosaic
 * This program generates a random 12 x 12 array of tiles
 */

// Runs program
public class Mosaic{
    public static void main(String[] args){
        System.out.println("Mosaic starting...");

        MosaicFrame mosaic = new MosaicFrame();
        mosaic.setTitle("Mosaic");
        mosaic.setVisible(true);
    }
}