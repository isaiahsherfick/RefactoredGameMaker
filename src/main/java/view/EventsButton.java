package view;

import java.util.ArrayList;
import java.util.Iterator;

public class EventsButton extends GameButton {

	public EventsButton(String title, String backgroundColorHex, ArrayList<EventMenuItem> menuItems) {
		super(backgroundColorHex, menuItems); 
		this.setText(title);
	}
	
	public ArrayList<EventMenuItem> getSelectedButtonItems() {
		ArrayList<EventMenuItem> selectedItems = new ArrayList<EventMenuItem>();
		Iterator<EventMenuItem> it = this.menuItems.iterator();
		while (it.hasNext()) {
			if (it.next().isSelected()) {
				selectedItems.add(it.next());
			}
		}
		return selectedItems;
	}
	
}
