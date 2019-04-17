package FST;

// Project name: ComSciFST
// Program name: AlertHelper.java
// Purpose: 
// Created by Natan Parker on Wednesday April 17 2019.
// Copyright © 2019 Natan Parker. All rights reserved.

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {

	public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
