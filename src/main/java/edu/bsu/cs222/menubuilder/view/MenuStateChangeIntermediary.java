package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Schedule;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

/**
 * Manages application state.
 * Each view class is given a reference to an instance of this class
 * and all requests to change state are routed through here.
 */
public class MenuStateChangeIntermediary {

    private final Stage stage;
    private Schedule schedule;
    private Schedule initialSchedule;

    public MenuStateChangeIntermediary(Stage stage, Schedule schedule) {
        this.schedule = schedule;
        updateInitialSchedule();
        this.stage = stage;
        showScheduleViewer();
        this.stage.setOnCloseRequest(this::handleCloseRequest);
        this.stage.show();
    }

    private void showScheduleViewer() {
        ScheduleViewBox viewBox = new ScheduleViewBox(schedule);
        viewBox.setOnEdit(this::showScheduleEditor);
        viewBox.setOnLoadRequest(this::showLoadDialog);
        stage.getScene().setRoot(viewBox);
    }

    private void showScheduleEditor() {
        ScheduleEditorBox scheduleEditorBox = new ScheduleEditorBox(schedule);
        scheduleEditorBox.setOnSaveAndClose(schedule -> {
            setSchedule(schedule);
            showScheduleViewer();
        });
        scheduleEditorBox.setOnCancel(this::showScheduleViewer);
        stage.getScene().setRoot(scheduleEditorBox);
    }

    private void showLoadDialog() {
        Optional<Schedule> optionalSchedule = new SaveLoadDialog().loadFile(stage.getScene().getWindow());
        optionalSchedule.ifPresent(loadedSchedule -> {
            setSchedule(loadedSchedule);
            updateInitialSchedule();
        });
        showScheduleViewer();
    }

    private void showSaveDialog() {
        new SaveLoadDialog().saveFile(stage.getOwner(), schedule);
    }

    private void handleCloseRequest(WindowEvent windowEvent) {
        if (windowEvent.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST &&
                !schedule.equals(initialSchedule)) {
            showSaveDialog();
            Platform.exit();
        }
    }

    private void updateInitialSchedule () {
        initialSchedule = schedule.deepCopy();
    }

    private void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
