package ea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;

public class Keybinder extends KeyAdapter {

	private final Object impl;

	public Keybinder(Object impl) {
		this.impl = impl;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			for (Method m : this.impl.getClass().getDeclaredMethods()) {
				ShortcutKey anno = m.getAnnotation(ShortcutKey.class);

				if (anno != null) {
					if (e.getKeyCode() == anno.value()) {
						m.invoke(this.impl);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
