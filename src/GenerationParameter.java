public class GenerationParameter {

    // private final int codeLength;
    private final int verticalDepth;
    private final int horizontalDepth;
    private final CodeType codeType;
    private final int maxLines;
    private final EnumModus modus;
    private final IdentifierArt art;



    public GenerationParameter(int verticalDepth,
                               int horizontalDepth,
                               CodeType codeType,
                               int maxLines,
                               EnumModus modus,
                               IdentifierArt art
    )
    {
        this.verticalDepth = verticalDepth;
        this.horizontalDepth = horizontalDepth;
        this.codeType = codeType;
        this.maxLines = maxLines;
        this.modus = modus;
        this.art = IdentifierArt.sameIdentifier;
    }

    public IdentifierArt getArt(){
        return this.art;
    }

    public int getVerticalDepth() {
        return verticalDepth;
    }

    public int getHorizontalDepth() {
        return horizontalDepth;
    }


    public CodeType getCodeType() {
        return codeType;
    }

    public int getMaxLines(){ return maxLines; }

    public EnumModus getModus(){
        return this.modus;

    }
}
