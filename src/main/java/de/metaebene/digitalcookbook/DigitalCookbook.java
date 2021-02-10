package de.metaebene.digitalcookbook;

import de.metaebene.digitalcookbook.gui.FrameHandler;

public class DigitalCookbook {

    private FrameHandler frameHandler;

    public static void main(String[] args) {
        new DigitalCookbook();
    }

    public DigitalCookbook() {
        this.frameHandler = new FrameHandler();
    }

}
