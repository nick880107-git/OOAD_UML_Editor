package Factory;

import java.util.ArrayList;

import Listener.CanvasHandler;
import UML.AssociationLine;
import UML.BasicObject;
import UML.Composite;
import UML.CompositionLine;
import UML.GeneralizationLine;
import UML.UMLclass;
import UML.UMLobject;
import UML.UseCase;

public class ObjectFactory {
	CanvasHandler ch;

	public ObjectFactory(CanvasHandler ch) {
		this.ch = ch;
	}

	public UMLobject factory(String state) {
		UMLobject obj = null;
		switch (state) {
		case "association line":
			obj = new AssociationLine((BasicObject) ch.getObj1(), (BasicObject) ch.getObj2(), ch.getX2(), ch.getY2(),
					ch.getX1(), ch.getY1(), ch.getDepth());
			break;
		case "generalization line":
			obj = new GeneralizationLine((BasicObject) ch.getObj1(), (BasicObject) ch.getObj2(), ch.getX2(), ch.getY2(),
					ch.getX1(), ch.getY1(), ch.getDepth());
			break;
		case "composition line":
			obj = new CompositionLine((BasicObject) ch.getObj1(), (BasicObject) ch.getObj2(), ch.getX2(), ch.getY2(),
					ch.getX1(), ch.getY1(), ch.getDepth());
			break;
		case "use case":
			obj = new UseCase(ch.getX1(), ch.getY1(), ch.getDepth());
			break;
		case "class":
			obj = new UMLclass(ch.getX1(), ch.getY1(), ch.getDepth());
			break;
		case "composite":
			ArrayList<UMLobject> objlist = ch.getSelectionList();
			if(objlist.size() > 1) {				
				obj = new Composite(objlist);
				ch.removeDuplicate(objlist);
				break;
			}
		}

		return obj;
	}
}
