package com.pedro.service;

import com.pedro.model.FileModel;

import java.io.File;

public class FileService {

    private DirectoryService directoryService = new DirectoryService();

    private String getPathToSave = directoryService.pathToSaveRenameds;
    private String getPathMain = directoryService.pathMainFolder;

    public FileService() {

    }

    public Integer prepareFileToRename() {
        File directoryFiles = new File(getPathMain);
        File[] fileInList = directoryFiles.listFiles();

        int numberOfRenamed = 0;

        int i = 0;
        if (fileInList != null) {
            for (File file : fileInList) {
                if (file.isFile()) {
                    if (prepareFileToRename(file, i)) {
                        numberOfRenamed++;
                    }
                }
            }

        }
        return numberOfRenamed;
    }


    public boolean prepareFileToRename(File fileToRename, int counter) {
        FileModel fileModel = new FileModel();
        File fileTemporary = new File(fileToRename.getParent(), fileModel.getTemporaryName(counter));

        return fileToRename.renameTo(fileTemporary);
    }
}

