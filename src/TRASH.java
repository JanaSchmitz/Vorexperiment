import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TRASH extends Statement {


    private String trash = null;
    private String trashCode = null;
    private String[] trashCase = null;
    private String trashSwitch = null;
    private boolean isSwitchTrash = false;
    protected Statement parent;
    protected Words words;
    GenerationParameter param;

    public TRASH(int level, String enumName, String enumInstance, String enumIdentifier, int maxDepth, Statement parent, Words words, GenerationParameter param) {
        super(level, enumName, enumInstance, enumIdentifier, maxDepth);
        this.type = CodeType.TRASH;
        this.parent = parent;
        this.words = words;
        this.param = param;

    }

    @Override
    protected int getLineCount() {
        return 1;
    }

    public Statement getParent() {
        return this.parent;
    }

    //ArrayList<String> names = new ArrayList<>(Arrays.asList( "Devil", "Angel", "Vegetables", "Animals", "Profession", "Drink", "Nature", "Feeling", "Place", "Galaxy"));
    //ArrayList<String>  words = new ArrayList<>(Arrays.asList("Apple", "Citrus", "Zuccini", "Tomato","Giraffe", "Lion", "Unicorn","Pinguin", "Seestar", "Cat", "Mouse", "Horse", "Craftsman", "Professor", "Clerk", "Employee" ));
    ArrayList<String> statementWords = new ArrayList<>(Arrays.asList("z", "x", "y", "number", "random", "value", "speed", "level", "nb", "rd", "axis", "point", "i", "a", "b", "nb1", "first", "third"));

    public void setTrashIf(String trashIf) {
        this.trash = trashIf;
    }



    public void setTrashCode(String trashCode) {
        this.trashCode = trashCode;
    }

    public void setTrashCase(String[] trashCase) {
        this.trashCase = trashCase;
    }

    private void setIsSwitchTrash(boolean value) {
        this.isSwitchTrash = value;
    }

    private void setTrashSwitch(String trashSwitch) {
        this.trashSwitch = trashSwitch;
    }

    public void generateTrash() {
        Random random = new Random();
        boolean r = random.nextBoolean();
        if (r == true) {
            GenerateTrashSwitch();
            setIsSwitchTrash(true);
        } else {
            generateTrashif();
            setIsSwitchTrash(false);

        }
    }

    public void GenerateTrashSwitch() {
        String trashSwitch;
        String name = words.getFreeName();
        words.usedidentifierNames.add(name);
        trashSwitch = "switch(" + name + "){";
        setTrashSwitch(trashSwitch);
        generateTrashCase();

    }

    public String[] generateTrashCase() {
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        System.out.println("random " + random);
        String[] trashCase = new String[random];

        for (int i = 0; i < trashCase.length; i++) {
            String word = getStatementWord();
            trashCase[i] = "case " + word + ":";
            words.usedidentifierNames.add(word);
        }
        setTrashCase(trashCase);
        return trashCase;
    }

    public String getTrashIf() {
        return this.trash;
    }

    public String getTrashCode() {
        return this.trashCode;
    }

    public String[] getTrashCase() {
        return this.trashCase;
    }

    public String getTrashSwitch() {
        return this.trashSwitch;
    }

    public void generateTrashif() {
        setTrashIf(createIf());
        setTrashCode(generateTrashCode());
    }

    public String createIf() {
        String trashif;
        String[] names;
        names = getName();

        trashif = "if(" + names[0].toLowerCase() + " == " + names[1] + "." + names[2].toLowerCase() + "){";
        return trashif;
    }

    public String generateTrashCode() {
        String[] words = getStatementWords();
        String trashCode = words[0] + " = " + words[1] + getOperator() + words[2] + ";";
        setTrashCode(trashCode);
        return trashCode;
    }


    private String[] getName() {
        String[] word = new String[3];
        word[0] = words.getFreeWord();
        word[1] = words.getFreeName();
        word[2] = words.getFreeWord();

/*
        int random = ThreadLocalRandom.current().nextInt(0, words.freeWords.size());
        word[0] = words.freeWords.get(random);

        random = ThreadLocalRandom.current().nextInt(0, words.freeNames.size());
        word[1] = words.freeNames.get(random);

        random = ThreadLocalRandom.current().nextInt(0, words.freeWords.size());
        while (words.freeWords.get(random) == word[0]) {
            random = ThreadLocalRandom.current().nextInt(0, words.freeWords.size());
        }
        word[2] = words.freeWords.get(random);

        */
        return word;
    }


    private String getStatementWord() {

        String word = words.getFreeWord();
        return word;
    }

    private String[] getStatementWords() {
        String[] words = new String[3];

        int random = ThreadLocalRandom.current().nextInt(0, statementWords.size());
        words[0] = statementWords.get(random);

        random = ThreadLocalRandom.current().nextInt(0, statementWords.size());
        while (statementWords.get(random) == words[0]) {
            random = ThreadLocalRandom.current().nextInt(0, statementWords.size());
        }
        words[1] = statementWords.get(random);
        while (statementWords.get(random) == words[0] || statementWords.get(random) == words[1]) {
            random = ThreadLocalRandom.current().nextInt(0, statementWords.size());
        }
        words[2] = statementWords.get(random);
        return words;
    }

    private String getOperator() {
        int random = ThreadLocalRandom.current().nextInt(0, 3);
        if (random == 0) {
            return " + ";
        }
        if (random == 1) {
            return " - ";
        }
        if (random == 2) {
            return " * ";
        }
        return " + ";
    }


    @Override
    public CodeType getType() {
        return CodeType.TRASH;
    }

    @Override
    public void setToLast() {

    }

    @Override
    public void setToFirst() {

    }

    @Override
    public void deletLastAndFirst() {

    }

    public String toString() {

        if (isSwitchTrash == false) {
            if (param.getCodeType() == CodeType.SWITCH) {
                if (getTrashIf() == null) {
                    return "     " + getSpaces() + getSpaces(1) + getTrashCode() + "\n";
                } else {
                    return "     " + getSpaces() + getSpaces(1) + getTrashIf() + "\n" + " " + getSpaces() + " " + getSpaces(2) + getTrashCode() + "\n" + getSpaces() + getSpaces(1) + "     " + "}\n";
                }
            } else {
                if (getTrashIf() == null) {
                    return " " + getSpaces() + getSpaces(1) + getTrashCode() + "\n";
                } else {
                    return " " + getSpaces() + getSpaces(1) + getTrashIf() + "\n" + " " + getSpaces() + " " + getSpaces(2) + getTrashCode() + "\n" + getSpaces() + getSpaces(1) + "   " + "}\n";
                }

            }
        } else {
            String[] cases = getTrashCase();
            String trashCode = "";
            if (param.getCodeType() == CodeType.IF) {
                trashCode = getSpaces() + getSpaces(1)  + getTrashSwitch() + "\n";
                for (int i = 0; i < cases.length; i++) {
                    if(i == cases.length-1){
                        trashCode += getSpaces() + getSpaces(1) + "  " + cases[i] + "\n" + "  " + getSpaces() + getSpaces(1) + "  " +generateTrashCode() + "\n" + getSpaces() + getSpaces(1) +  getSpaces(1)+ "  " + "break; } \n";
                    }else{
                        trashCode += getSpaces() + getSpaces(1) + "  " + cases[i] + "\n" + "  " + getSpaces() + getSpaces(1) + "  " +generateTrashCode() + "\n" + "  " + getSpaces() + getSpaces(1) + getSpaces(1)+ "  " + "break; \n";
                    }
                }

                return trashCode;

            } else {
                trashCode = getSpaces() + getSpaces(1)  + getSpaces(1)+ "  " + getTrashSwitch() + "\n";
                for (int i = 0; i < cases.length; i++) {
                    System.out.println("i " + i + " " + cases.length);
                    if(i == cases.length-1){
                        System.out.println("hier");
                        trashCode +=  getSpaces() + getSpaces(1)+ getSpaces(1 )+ "  " + "  "+ cases[i] + "\n" + "  " + getSpaces() + getSpaces(1) + getSpaces(1 ) + "  " +  "  " +generateTrashCode() + "\n" + getSpaces() + getSpaces(1) + getSpaces(1 ) + "  " +  "  "+ "break; } \n" ;
                    }else{
                        trashCode +=  getSpaces() + getSpaces(1)+ getSpaces(1 )+ "  " + "  "+ cases[i] + "\n" + "  " + getSpaces() + getSpaces(1) + getSpaces(1 ) + "  " +  "  " +generateTrashCode() + "\n" + getSpaces() + getSpaces(1) + getSpaces(1 ) + "  " +  "  "+ "break; \n" ;
                    }
                }

                return trashCode;
            }


        }
/*
    public String toString() {
        return "  " + getSpaces() + getSpaces(1) + generateTrashif() + "\n" + getSpaces() +  " " + getSpaces(2) + generateTrashStatement() + " }\n";
    }

    */
    }

}}