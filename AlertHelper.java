package FST;

// Project name: ComSciFST
// Program name: AlertHelper.java
// Purpose: Show alerts in pop up windows
// Created by Natan Parker on Wednesday April 17 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {

	public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) { //Creates a popup alert with the given parameters
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
