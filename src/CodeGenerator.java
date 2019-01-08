import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class CodeGenerator {

    protected final GenerationParameter param;
    public Words words;

    public CodeGenerator(GenerationParameter param){
        this.param = param;
    }

    public String generateCode(){

        Random rnd = ThreadLocalRandom.current();

        int randomEnums = ((ThreadLocalRandom) rnd).nextInt(2,5);
        System.out.println("random " + randomEnums);
        String code = "";
        EnumGenerator enumGenerator = new EnumGenerator();
        EnumBlock[] allEnums = enumGenerator.generateEnums(param, randomEnums);
        EnumBlock[] enums = new EnumBlock[param.getHorizontalDepth()];
        for(int j = 0; j < enums.length; j++){
            enums[j] = allEnums[j];
        }

        Words words = enumGenerator.generateWords();
        StatementFactory factory = new StatementFactory(enums, param, words);
        StatementGenerator g = new StatementGenerator(param, factory);

        RootStatement root = new RootStatement(0, "root", "root" , "root", param.getHorizontalDepth());
        Statement statement = g.generateTree();

        EnumBlock[] shuffeldEnums = shuffle(allEnums);
        for(int i = 0; i < shuffeldEnums.length; i++) {
            code += shuffeldEnums[i].getStringRepresentation() + "\n";
        }
/*
        for(int i = 0; i < enums.length; i++) {
            code += enums[i].getStringRepresentation() + "\n";
        }

        code += additionalEnums[0].getStringRepresentation() + "\n";
        code += additionalEnums[1].getStringRepresentation();
        code += "\n";
        code += "public SwitchStatements() {" + "\n";
*/
        code += "public class CodeGenerator{ " + "\n";
        code += "   " + "public void ChoooseStatements() {" + "\n";
        code+="     ";
        for(int i = 0; i < words.usedidentifierNames.size(); i++){
            code += "String " + words.usedidentifierNames.get(i) + "; ";
            if(i == words.usedidentifierNames.size()-1){
                code += "\n";
            }
            System.out.println(" identifier " + words.usedidentifierNames.get(i) );

        }
        for(Statement c : statement.children){
            root.addChild(c);
        }
        System.out.println("Gesamtcode zeilen " + root.getAllLines());
        code += root.getCode();
        code +=  "      } \n";
        code +=  "}";
        System.out.println(code);
        System.out.println("------code generation complete --------");

        return code;

    }

    private EnumBlock[] shuffle( EnumBlock[] enums){
        int count = 0;
        int index = 0;
        Random rnd = ThreadLocalRandom.current();
        EnumBlock[] shuffledEnums = new EnumBlock[enums.length-1];
        for(int i = 0; i < enums.length; i++){
            if(i == enums.length-1){
                return shuffledEnums;
            }else {
                while (shuffledEnums[index] != null) {
                    index = ((ThreadLocalRandom) rnd).nextInt(0, enums.length - 1);
                }
            }
            shuffledEnums[index]= enums[i];
            //System.out.println("index " + index);
            // System.out.println("shuffled " + shuffledEnums[index].getStringRepresentation());
        }

        return shuffledEnums;

    }

}
