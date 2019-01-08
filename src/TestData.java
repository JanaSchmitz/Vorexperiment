import java.util.concurrent.TimeUnit;

public class TestData {

    public final long startTime;
    public long endTime;
    public final String code;
    public final GenerationParameter param;
    public String input = "";
    public EnumModus modus;
    public int typeCoded;
    public int modusCoded;

    public TestData(long startTime, String code, GenerationParameter param, EnumModus modus){
        this.startTime = startTime;
        this.param = param;
        this.code = code;
        this.modus = modus;
    }


    public void setCodedType(){
        if(param.getCodeType() == CodeType.SWITCH) {
            typeCoded = 0;
        }
        else{ typeCoded = 1;
        }
    }
    public void setCodedModus(){
        if(modus == EnumModus.UPPERCASE) {
            modusCoded = 1;
        }else{ modusCoded = 0;
        }
    }
    public void setEndTime(long time){
        this.endTime = time;
    }

    public long getDuration(){
        long diffSec = 0;
        if(endTime != 0) {
            long difference = endTime - startTime;
            diffSec = TimeUnit.MILLISECONDS.toSeconds(difference);
        } else{
            diffSec = -1;
        }
        return diffSec;
    }

    public int getTypeCoded(){
        return typeCoded;
    }
    public int getModusCoded(){
        return modusCoded;
    }
    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public String getCode(){
        return code;
    }

    public CodeType getType(){
        return param.getCodeType();
    }

    public int getHorizontalDepth(){
        return param.getHorizontalDepth();
    }

    public int getVerticalDepth(){
        return param.getVerticalDepth();
    }

    public long getStartTime() {
        return startTime;
    }

    public String toString(){
        return "";
    }

    public int getDepth(){
        return param.getHorizontalDepth();
    }

    public EnumModus getModus() {
        return modus;
    }

    public void addInput(String input) {
        this.input +=  input + ", ";
        System.out.println("add input : " + this.input);

    }

}
