package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindowListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(new Event("Window closed"));
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }
}
