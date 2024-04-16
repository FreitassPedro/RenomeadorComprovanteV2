package com.pedro.model;

import javax.swing.filechooser.FileSystemView;

public class DirectoryModel {
    public String pathToSaveRenameds() {
        FileSystemView path = FileSystemView.getFileSystemView();
        return path.getHomeDirectory().getPath() + "\\Comprovantes\\Renomeados\\";
    }

    public String pathMainFolderDesktop() {
        FileSystemView path = FileSystemView.getFileSystemView();
        return path.getHomeDirectory().getPath() + "\\Comprovantes\\";
    }


}
