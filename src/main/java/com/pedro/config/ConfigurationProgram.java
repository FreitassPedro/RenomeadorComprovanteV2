package com.pedro.config;

import com.pedro.controller.DirectoryController;
import com.pedro.controller.HudController;

public class ConfigurationProgram {
    private DirectoryController directoryController;
    private HudController hudController;

    public void setHudController(HudController hudController) {
        this.hudController = hudController;
    }

    public void setDirectoryController(DirectoryController directoryController) {this.directoryController = directoryController;
    }

    public ConfigurationProgram() {
    }

    public void startProgram() {
        if (hudController.askIfCanStartProgram()) {
            if (directoryController.createDirectory()) {
                hudController.startHudProgram();

                directoryController.openFolder();
            }
        }

    }
}

