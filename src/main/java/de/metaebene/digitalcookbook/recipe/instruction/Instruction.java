package de.metaebene.digitalcookbook.recipe.instruction;

public class Instruction {

    private final String instructionDescription;

    /**
     * Konstructor der Klasse Instruction
     * @param instructionDescription Übergabe der eigentlichen Anweisung
     */
    public Instruction(String instructionDescription) {
        this.instructionDescription = instructionDescription;
    }

    public String getInstructionDescription() {
        return instructionDescription;
    }
}
