package com.pedro.model;

public class FileModel {
    private final String temporaryName = "comprovanteTemporario";

    public FileModel() {}

    public String getTemporaryName(int counter) {
        return temporaryName
                + " (" + counter + ").pdf"  ;
    }
}
