public class Equipment {
    private String name;
    private int price;
    private int pilotAbilityIncrement;
    private int fighterAbilityIncrement;
    private int merchantAbilityIncrement;
    private int engineerAbilityIncrement;
    private String description;
    public Equipment(String name, int price, int pilotAbilityIncrement, int fighterAbilityIncrement, int merchantAbilityIncrement, int engineerAbilityIncrement) {
        this.name = name;
        this.price = price;
        this.pilotAbilityIncrement = pilotAbilityIncrement;
        this.fighterAbilityIncrement = fighterAbilityIncrement;
        this.merchantAbilityIncrement = merchantAbilityIncrement;
        this.engineerAbilityIncrement = engineerAbilityIncrement;
        this.description= "";
        if(pilotAbilityIncrement!=0){
            description+= "pilot+"+pilotAbilityIncrement;
        }
        if(fighterAbilityIncrement!=0){
            description+= "fighter+"+fighterAbilityIncrement;
        }
        if(merchantAbilityIncrement!=0){
            description+= "merchant+"+merchantAbilityIncrement;
        }
        if(engineerAbilityIncrement!=0){
            description+= "engineer+"+engineerAbilityIncrement;
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPilotAbilityIncrement() {
        return pilotAbilityIncrement;
    }

    public int getFighterAbilityIncrement() {
        return fighterAbilityIncrement;
    }

    public int getMerchantAbilityIncrement() {
        return merchantAbilityIncrement;
    }

    public int getEngineerAbilityIncrement() {
        return engineerAbilityIncrement;
    }

    public String getDescription() {
        return description;
    }
}
