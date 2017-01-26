package main;

import hunter.HunterMain;
import java.io.IOException;
import particules.ParticulesMain;
import wator.WatorMain;

public class Simulation {

   public static String[] empty = null;
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length < 1) {
            usage();
        }
        
        int simulationToRun = Integer.parseInt(args[0]);
        
       switch (simulationToRun) {
           case 1:
               HunterMain.main(empty);
               break;
           case 2:
               WatorMain.main(empty);
               break;
           case 3:
               ParticulesMain.main(empty);
               break;
           default:
               usage();
               break;
       }
        
  
    }
    private static void usage() {
        System.err.println("Please select the correct number of the simulation you want to run : 1- PACMAN, 2- WATOR or 3- PARTICULES : ");
        System.exit(-1);
    }
}
   
