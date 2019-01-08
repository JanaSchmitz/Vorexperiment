import java.io.StringReader;

public class EnumBlock{

    private final String name;
    private final String[] instances;
    private final String stringRepresentation;
    private final String[] identifier;


    public EnumBlock(String name, String[] instances, String stringRepresentation, String[] identifier) {
        this.name = name;
        this.instances = instances;
        this.stringRepresentation = stringRepresentation;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public int getInstancesLength(){
        return instances.length;
    }

    public String getInstances(int i){
        String instance ="";
        instance = instances[i];
        return instance;

    }

    public String getIdentifier(int i){
        return identifier[i];
    }
    public CodeType getType(){
        return CodeType.ENUM;
    }
    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public String[] getInstances() {
        return instances;

    }
}
