package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.List;

// Java program to read JSON from a file

public class MyWiki {


    private WikiElement[][] elements;
    private EWikiKathegorie[] lastKategorieIndex = null;

    public MyWiki() {
        this.elements = new WikiElement[EWikiKathegorie.values().length][0];
        System.out.print(new File(".").getAbsolutePath());
        loadWiki();
    }

    /**
     * @return JSON alle Kategorien als Array
     */
    public String getWikiKategorie() {
        List<Enum> enumValues = Arrays.asList(EWikiKathegorie.values());
        lastKategorieIndex = new EWikiKathegorie[EWikiKathegorie.values().length];

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();

        String nextKaegorie;
        for (int i = 0; i < EWikiKathegorie.values().length; i++) {
            lastKategorieIndex[i] = (EWikiKathegorie) enumValues.get(i);
            nextKaegorie = enumValues.get(i).toString().substring(0, 1).toUpperCase() + enumValues.get(i).toString().substring(1).toLowerCase();
            arrayNode.add(nextKaegorie);
        }
        objectNode.set("Kategorien", arrayNode);

        return objectNode.toString();
        // return StatusToString.notReady("Kategorien wurden noch nicht eingebaut");
    }

    /**
     * @param kategorieIndex
     * @return JSON alle Fragen einer kategorie
     */
    public String getWikiElement(int kategorieIndex) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        ObjectNode objectNode = mapper.createObjectNode();

        try {
            if (lastKategorieIndex[kategorieIndex] == elements[kategorieIndex][0].getCategory()) {
                for (int i = 0; i < elements[kategorieIndex].length; i++) {
                    ObjectNode newFrage = mapper.createObjectNode();

                    newFrage.put("question", elements[kategorieIndex][i].getQuestion());
                    newFrage.put("id", elements[kategorieIndex][i].getId());
                    arrayNode.add(newFrage);
                }
                objectNode.put("typ", lastKategorieIndex[kategorieIndex].toString());
                objectNode.set("response", arrayNode);
            } else{
                gebeAus();
                return StatusToString.fehler("Elemente konnten nicht gefunden werden");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            gebeAus();
            return StatusToString.fehler("Elemente konnten nicht gefunden werden");
        }

        return objectNode.toString();
    }

    /**
     * @param kategorieIndex
     * @param frageID
     * @return JSON ein Element mit Inhalt
     */
    public String getWikiElement(int kategorieIndex, int frageID) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        ObjectNode response = mapper.createObjectNode();

        objectNode.put("typ", "Antwort");
        objectNode.set("response", response);

        try {
            if (lastKategorieIndex[kategorieIndex] == elements[kategorieIndex][0].getCategory()) {
                for (int i = 0; i < elements[kategorieIndex].length; i++) {
                    if (elements[kategorieIndex][i].getId() == frageID) {
                        response.put("question", elements[kategorieIndex][i].getQuestion());
                        response.put("content", elements[kategorieIndex][i].getContent());
                        response.put("id", elements[kategorieIndex][i].getId());
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return StatusToString.fehler("Element konnten nicht gefunden werden");
        }
        return objectNode.toString();
    }


    /**
     * reset the counter in every Wiki Element
     */
    private void resetCounter() {
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[i].length; j++) {
                this.elements[i][j].resetCount();
            }
        }
    }

    /**
     * save the counter in a text file
     */
    private void saveCounter() {

        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));
            pWriter.println("Wiki Counter Backup");

            for (int i = 0; i < this.elements.length; i++) {
                for (int j = 0; j < this.elements[i].length; j++) {
                    pWriter.print(this.elements[i][j].getCount());
                }
                pWriter.println();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }
    }


    private void loadWiki() {
        JSONParser parser = new JSONParser();
        try {
            //System.out.println(new File(".").getAbsolutePath());
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("WikiBackup.json"));

            for (EWikiKathegorie enumKategorie : EWikiKathegorie.values()) {

                // loop array
                JSONArray jsonKrankheiten = (JSONArray) jsonObject.get(enumKategorie.toString());
                if (jsonKrankheiten != null) {
                    for (Object c : jsonKrankheiten) {
                        JSONObject jsonInnerObject = (JSONObject) c;

                        String question = jsonInnerObject.get("question").toString();
                        String content = jsonInnerObject.get("content").toString();
                        String idObject = jsonInnerObject.get("id").toString();
                        int id = Integer.parseInt(idObject);
                        String countObject = jsonInnerObject.get("count").toString();
                        int count = Integer.parseInt(countObject);
                        JSONArray jsonTags = (JSONArray) jsonInnerObject.get("tags");

                        int arraySize = jsonTags.size();
                        String[] tags = new String[arraySize];

                        for (int i = 0; i < arraySize; i++) {
                            tags[i] = (String) jsonTags.get(i);
                        }
                        addElement(new WikiElement(question, content, enumKategorie, tags, id, count));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addElement(WikiElement element) {
        boolean freiePlaetze = false;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].length > 0) {
                if (elements[i][0].getCategory() == element.getCategory()) {
                    WikiElement[] array = makeArrayLonger(elements[i]);
                    array[array.length - 1] = element;
                    elements[i] = array;
                    System.out.println("Element eingefügt1: " + element.getQuestion() + ", pos: " + i + ", j: " + (array.length - 1));
                    return;
                }
            } else {
                freiePlaetze = true;
            }
        }
        if (freiePlaetze) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].length == 0) {
                    elements[i] = new WikiElement[1];
                    elements[i][0] = element;
                    System.out.println("Element eingefügt2: " + element.getQuestion() + ", pos: " + i + ", j: 0");
                    return;
                }
            }
        }

    }

    private WikiElement[] makeArrayLonger(WikiElement[] array) {
        WikiElement[] newArray = new WikiElement[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public static void main(String[] args) {
        MyWiki wiki = new MyWiki();

        wiki.getWikiKategorie();
        wiki.loadWiki();
        //System.out.println(wiki.getWikiElement(0,0));
        wiki.addElement(new WikiElement("titel1", "inhalt", EWikiKathegorie.KRANKHEITEN, null, 100, 0));
        //wiki.saveCounter();

    wiki.gebeAus();

    }

    private void gebeAus(){
        System.out.println("\n\n");
        for (int i = 0; i < elements.length; i++) {
            System.out.println("Kategorie: " + lastKategorieIndex[i]);
            for (int j = 0; j < elements[i].length; j++) {
                System.out.println((j + 1) + ": id: " + elements[i][j].getId() + ", frage: " + elements[i][j].getQuestion() + " cat: " + elements[i][j].getCategory());
            }
            System.out.println("__________________________________");
        }
    }


}
