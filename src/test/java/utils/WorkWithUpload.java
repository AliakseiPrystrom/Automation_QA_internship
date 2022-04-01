package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class WorkWithUpload {
    private final static ISettingsFile configData = new JsonSettingsFile("config.json");


    public static void uploadFile(String nameFile) {
        StringSelection stringSelection = new StringSelection(nameFile);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        try {
            Robot robot = new Robot();
            robot.delay(Integer.parseInt(configData.getValue("/robotDelay").toString()));

            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.mouseMove(Integer.parseInt(configData.getValue("/robotMouseMoveToApplyX").toString()),
                    Integer.parseInt(configData.getValue("/robotMouseMoveToApplyY").toString()));
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
