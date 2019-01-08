import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Words{


    public List<String> usedEnumNames =  new ArrayList<>();
    public List<String> usedEnumInstance = new ArrayList<>();
    public List<String> words = new ArrayList<>();
    public List<String> EnumNames = new ArrayList<>();
    public List<String> freeWords = new ArrayList<>();
    public List<String> freeNames = new ArrayList<>();
    public List<String>  usedidentifierNames =  new ArrayList<>();




    public String getFreeWord(){
        String word;

        int random = ThreadLocalRandom.current().nextInt(0, freeWords.size());
        word = freeWords.get(random);
        freeWords.remove(random);
        usedEnumInstance.add(word);
        return word;

    }

    public String getFreeName(){
        String word;
        int random = ThreadLocalRandom.current().nextInt(0, freeNames.size());
        word = freeNames.get(random);

        freeNames.remove(random);
        usedEnumNames.add(word);

        return word;
    }

    public void fillUsedEnumNames(List<String> EnumNames){
        for(int i = 0; i < EnumNames.size(); i++){
            usedEnumNames.add(EnumNames.get(i));
        }
    }

    public void fillUsedEnumInstance(List<String> EnumInstances){
        for(int i = 0; i < EnumInstances.size(); i++){
            usedEnumInstance.add(EnumInstances.get(i));

        }
    }
    public void setEnumNames(List<String> Names){
        for(int i = 0; i < Names.size(); i++){
            EnumNames.add(Names.get(i));

        }
    }
    public void setWordList(List<String>allWords){
        for(int i = 0; i < allWords.size(); i++){
            words.add(allWords.get(i));
        }

    }

    public void generateFreeWordList(){
        for(int i = 0; i < words.size(); i++){
            boolean is = false;
            for(int j = 0; j < usedEnumInstance.size(); j++) {
                if(words.get(i).equals(usedEnumInstance.get(j))){
                    is = true;
                }
            }
            if(is == false){
                freeWords.add(words.get(i));
            }
        }

    }
    public void generateFreeNamesList(){
        for(int i = 0; i < EnumNames.size(); i++){
            boolean is = false;
            for(int j = 0; j < usedEnumNames.size(); j++){
                if(EnumNames.get(i).equals(usedEnumNames.get(j))){
                    is = true;

                }
            }
            if(is == false){
                freeNames.add(EnumNames.get(i));
            }
        }


    }

}
