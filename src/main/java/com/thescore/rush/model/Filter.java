package com.thescore.rush.model;

public enum Filter {
    
    TOTAL_YARDS("totalYards"), LONGEST("longest"), TOUCH_DOWNS("touchDowns"), NONE("none");

    private final String label;

    private Filter(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
}
