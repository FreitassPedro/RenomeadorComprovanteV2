package com.pedro.service;

import com.pedro.model.Comprovante;
import com.pedro.model.FileModel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private DirectoryService directoryService = new DirectoryService();

    private String getPathToSave = directoryService.pathToSaveRenameds;
    private String getPathMain = directoryService.pathMainFolder;

    private List<String> allSucessRenames = new ArrayList<>();


    public FileService() {
    }

    public List<String> getFile(boolean isRenamed) {
        File directoryPath = new File(getPathMain);
        File[] filesList = directoryPath.listFiles();
        int i = 0;
        if (filesList != null) {

            for (File file : filesList) {
                //Verifica se o arquivo não é uma pasta
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    if (isRenamed) {
                        prepareFileToNewData(file);
                    } else {
                        prepareToRenameFile(file, i);
                    }
                    i++;
                }
            }
        }
        return getAllSucessRenames();

    }

    private void prepareFileToNewData(File file) {
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

    public boolean renameFileToTemporaryName(File fileToRename, int counter) {
        FileModel fileModel = new FileModel();
        File fileTemporary = new File(fileToRename.getParent(), fileModel.getTemporaryName(counter));

        return fileToRename.renameTo(fileTemporary);
    }

    public void renameUsingNewDatas(File file, Comprovante comprovante) {
        File finalFile = new File(getPathToSave, comprovante.toString());

        int avoidDuplicatedFile = 0;
        do {
            String prefix = "";
            if (avoidDuplicatedFile > 0) {
                prefix = "(" + avoidDuplicatedFile + ") ";
            }
            finalFile = new File(getPathToSave, prefix + comprovante);
            avoidDuplicatedFile++;
        } while (finalFile.exists());


        if (file.renameTo(finalFile)) {
            allSucessRenames.add("Sucessful! Renamed to " + comprovante);
        } else { allSucessRenames.add("Something went wrong..." + file.getName()); }
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

    public List<String> getAllSucessRenames() {
        return allSucessRenames;
    }
}

