package net.xway.code.utils;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.model.Component;

public class ComponentUtils {

	public static List<Component> getComponentAndAllSubComponents(Component component) {
		ArrayList<Component> components = new ArrayList<>();
		components.add(component);
		components.addAll(getSubComponent(component));
		return components;
	}
	
	public static List<Component> getSubComponent(Component component) {
		ArrayList<Component> components = new ArrayList<>(component.getSubcomponent());
		
		List<Component> cs = component.getSubcomponent();
		for (Component c : cs ) {
			components.addAll(getSubComponent(c));
		}
		
		
		return components;
	}

}
