package com.pedro.service;

import com.pedro.model.FileModel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class FileService {

    private DirectoryService directoryService = new DirectoryService();

    private FileModel fileModel = new FileModel();
    private String getPathToSave = directoryService.pathToSaveRenameds;
    private String getPathMain = directoryService.pathMainFolder;

    public FileService() {
    }

    public void getFile(boolean isRenamed) {
        File directoryPath = new File(getPathMain);
        File[] filesList = directoryPath.listFiles();

        if (filesList != null) {
            int i = 0;

            for (File file : filesList) {
                if (isRenamed) {
                    renameFileToNewDatas(file);
                } else {
                    prepareToRenameFile(file, i);
                }

            }
        }
    }

    private void renameFileToNewDatas(File file) {
        String text = getTextFromPDF(file);
        int type = identifyType(text);

       ComprovanteService comprovanteService = new ComprovanteService(text, type);

       comprovanteService.splitLines();
       var newFile = comprovanteService.startCollectDatas();


    }




    public Integer prepareToRenameFile(File file, int i) {
        int numberOfRenamed = 0;
        if (file.isFile()) {
            if (renameFileToTemporaryName(file, i)) {
                numberOfRenamed++;
            }
        }
        return numberOfRenamed;
    }


    public boolean renameFileToTemporaryName(File fileToRename, int counter) {
        FileModel fileModel = new FileModel();
        File fileTemporary = new File(fileToRename.getParent(), fileModel.getTemporaryName(counter));

        return fileToRename.renameTo(fileTemporary);
    }

    public String getTextFromPDF(File file) {
        try {
            PDDocument pdfLoad = Loader.loadPDF(file);
            PDFTextStripper textStripper = new PDFTextStripper();

            return textStripper.getText(pdfLoad);
        } catch (IOException e) {
            System.err.println("Error collecting text");
        }
        return null;
    }

    public int identifyType(String text) {
        if (text.startsWith("Ass")) return 1;
        if (text.startsWith("Comprovante de Pagamento")) return 2;
        return 3;
    }

}

