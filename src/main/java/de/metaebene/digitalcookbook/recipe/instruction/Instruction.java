package de.metaebene.digitalcookbook.recipe.instruction;

public class Instruction {

    private final String instructionDescription;

    public Instruction(String instructionDescription) {
        this.instructionDescription = instructionDescription;
    }

    public String getInstructionDescription() {
        return instructionDescription;
    }
}
