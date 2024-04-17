package com.pedro.service;

import com.pedro.model.Comprovante;
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
        int i = 0;
        if (filesList != null) {

            for (File file : filesList) {
                //Verifica se o arquivo não é uma pasta
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    if (isRenamed) {
                        renameFileToNewDatas(file);
                    } else {
                        prepareToRenameFile(file, i);
                    }
                    i++;
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
        renameUsingNewDatas(file, newFile);

    }


    public Integer prepareToRenameFile(File file, int i) {
        int numberOfRenamed = 0;
            if (renameFileToTemporaryName(file, i)) {
                numberOfRenamed++;

        }
        return numberOfRenamed;
    }

    public String renameUsingNewDatas(File file, Comprovante comprovante) {
        File finalFile = new File(getPathToSave, comprovante.toString());

        int counter = 0;
        do {
            String prefixo = "";
            if (counter > 0) {
                prefixo = "(" + counter + ") ";
            }
            finalFile = new File(getPathToSave, prefixo + comprovante.toString());
            counter++;
        } while (finalFile.exists());

        String message = "";
        if (file.renameTo(finalFile)) {
            message = "File sucessful! " + comprovante.toString();
            System.out.println(message);
            return message;
        }

        message = "Error! TODO";
        System.out.println(message);
        return message;
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
        if (text.startsWith("Comprovante de Pagamento")) return 3;
        return 2;
    }

}

