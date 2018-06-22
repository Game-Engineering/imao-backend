package de.hsmannheim.ss18.gae.imao.model;

import de.hsmannheim.ss18.gae.imao.model.EWikiKathegorie;
import de.hsmannheim.ss18.gae.imao.model.WikiElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyWiki {

   private  WikiElement[][] elements;

    public MyWiki(){

        this.elements = new WikiElement[EWikiKathegorie.values().length][0];
    }

    public String getWikiKategorie(){

        return StatusToString.notReady("Kategorien wurden noch nicht eingebaut");
    }

    public String getWikiElement(int kategorieIndex){

        return StatusToString.notReady("Fragen wurden noch nicht eingebaut. Ihr Kategorie Index: " + kategorieIndex);
    }

    public String getWikiElement(int kategorieIndex, int frageID){

        return StatusToString.notReady("Antworten wurden noch nicht eingebaut. Ihr Kategorie Index: " + kategorieIndex + ". Ihre Frage ID: " + frageID);
    }


    /**
     * reset the counter in every Wiki Element
     */
    private void resetCounter(){
        for (int i=0;i<this.elements.length;i++){
            for (int j=0;j<this.elements[i].length;j++){
                this.elements[i][j].resetCount();
            }
        }
    }

    /**
     * save the counter in a text file
     */
    private void saveCounter(){

        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));
            pWriter.println("Wiki Counter Backup");

            for (int i=0;i<this.elements.length;i++){
                for (int j=0;j<this.elements[i].length;j++){
                    pWriter.print(this.elements[i][j].getCount());
                }
                pWriter.println();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }
    }

    /**
     * load the counter from text file
     */
    private void loadCounter(){

    }

    public static void main(String[] args){
        MyWiki wiki = new MyWiki();


        wiki.saveCounter();
    }




}
