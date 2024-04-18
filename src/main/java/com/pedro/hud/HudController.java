package com.pedro.hud;

import com.pedro.controller.FileController;

import java.util.List;

public class HudController {

    private HudService hudService = new HudService();

    private FileController fileController = new FileController();

    public HudController() {
    }

    public HudController(HudService hudService) {
        this.hudService = hudService;
    }

    public void startHudProgram() {
        fileController.prepareFilesToRename();
        List<String> allRenameds = fileController.startRenameWithData();

        if (allRenameds.isEmpty()) {
            hudService.showDirectoryCreated();
        } else {
            hudService.setListOfAllRenameds(allRenameds);
            showReport();
        }

    }

    public boolean askIfCanStartProgram() {
        return hudService.askIfCanStart() == 0;
    }

    public void showReport() {
        hudService.showReport();
    }

}
