package util;

import javafx.scene.control.Label;

public class Utils {

	/**
	 * @param labels
	 * @return set all the label invisible (isVisible(false))
	 */
	public static void settingLabelsInvisible(Label...labels ){
		for (Label l : labels ){
			l.setVisible(false);
		}

	}
}
