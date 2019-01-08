import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.io.*;


public class EnumGenerator {

    private ArrayList<String> enumName = new ArrayList<>(Arrays.asList("Category","Sort", "Part","Type", "Entity", "Things", "Name", "Parameter", "Quality","Person", "Art", "Tree", "Plants","Flowers", "Color","Probability", "Rate", "Grade", "Animals", "Group", "Vegetables", "Animals", "Profession", "Drink", "Nature", "Feeling", "Place", "Galaxy", "Music", "Melody", "Pastries"));
    private ArrayList<String> usedNames = new ArrayList<>();
    private ArrayList<String> usedInstances = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();


    public Words generateWords(){
        String instance[] = new String[0];
        Words used = new Words();
        used.fillUsedEnumNames(usedNames);
        used.fillUsedEnumInstance(usedInstances);
        used.setEnumNames(enumName);
        used.setWordList(words);
        used.generateFreeNamesList();
        used.generateFreeWordList();

        return used;
    }
    public EnumBlock[] generateEnums(GenerationParameter param, int AdditionalEnums) {
        int Hdepth = param.getHorizontalDepth();
        int Vdepth = param.getVerticalDepth();
        int before = 1;
        EnumBlock[] EnumBlock = new EnumBlock[Hdepth+AdditionalEnums+1];
        for (int i = 0; i < Hdepth+AdditionalEnums; i++) {
            if (i == 0) {
                EnumBlock[i] = generateEnum((Vdepth), param);
                before = Vdepth;
            } else {
                EnumBlock[i] = generateEnum((Vdepth), param);
                before = before * Vdepth;
                //System.out.println("I: " + i);
            }
        }

        System.out.println("Enums erstellt");
        return EnumBlock;
    }



    public EnumBlock generateEnum(int nbWords, GenerationParameter param){
        EnumModus modus = param.getModus();
        String name = enumName();
        String stringRepresentation ="";
        String[] instances = new String[nbWords];
        String[] identifier = new String[nbWords];
        //EnumGenerator generator = new EnumGenerator();



        for(int i = 0; i < nbWords; i++){
            if(modus == EnumModus.UPPERCASE) {
                instances[i] = getRandomEnumWord().toUpperCase();
                identifier[i] = getRandomEnumWord();
            }
            else {
                instances[i] = getRandomEnumWord().toLowerCase();
                identifier[i] = getRandomEnumWord();
            }

            //System.out.println("instance: " + instances[i]);
        }

        stringRepresentation = "enum " + name + " {";
        for(int i = 0; i < instances.length; i++){
            if(i != instances.length-1) {
                stringRepresentation += instances[i] + ", ";

            }
            else{
                stringRepresentation += instances[i] + ";}";

            }

        }
        // System.out.println("StringRepresentation: " + stringRepresentation);

        EnumBlock generatedEnum = new EnumBlock(name, instances, stringRepresentation, identifier);
        return generatedEnum;
    }


    public String enumName() {
        String name = "";

        int rdNb = ThreadLocalRandom.current().nextInt(0, enumName.size() - 1);

        while (usedNames.contains(enumName.get(rdNb))) {
            rdNb = ThreadLocalRandom.current().nextInt(0, enumName.size() - 1);
        }
        if (!usedNames.contains(enumName.get(rdNb))) {
            name = enumName.get(rdNb);
            usedNames.add(name);
        }
        return name;
    }

    public String getRandomEnumWord(){
        String rdWord ="";
        int rdNb = 0;

        Scanner input = new Scanner(System.in);

        String fileName = "./words.txt";
        File wordList = new File(fileName);
        Scanner reader = null;

        try {
            reader = new Scanner(wordList);
        } catch (FileNotFoundException e) {
            System.out.println("file \"" + fileName + "\" not found");
            System.exit(0);
        }

        while(reader.hasNextLine()) {
            String word = reader.nextLine();
            words.add(word);
        }

        rdNb = ThreadLocalRandom.current().nextInt(0, words.size()-1);

        while(usedInstances.contains(words.get(rdNb))){
            rdNb = ThreadLocalRandom.current().nextInt(0, words.size()-1);
        }

        rdWord = words.get(rdNb);
        usedInstances.add(rdWord);


        return rdWord;

    }


}

