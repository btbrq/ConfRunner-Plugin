package brq.intellij.plugins.confrunner.common.utils;

import java.awt.event.KeyEvent;
import java.util.stream.IntStream;

public class FocusUtil {
    public static boolean keyEquals(KeyEvent e, int... vkDown) {
        return IntStream.of(vkDown).anyMatch(k -> e.getKeyCode() == k);
    }
}
