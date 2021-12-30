package brq.intellij.plugins.confrunner.common.utils

import spock.lang.Specification
import spock.lang.Unroll

import java.awt.event.KeyEvent

import static java.awt.event.KeyEvent.VK_TAB
import static java.awt.event.KeyEvent.VK_T
import static java.awt.event.KeyEvent.VK_F


class FocusUtilTest extends Specification {

    @Unroll
    def "should return #expected when detecting if keystroke matches"() {
        when:
        def result = FocusUtil.keyEquals(eventKey, comparedKeys as int[])

        then:
        result == expected

        where:
        eventKey             | comparedKeys   || expected
        mockKeyEvent(VK_TAB) | [VK_TAB, VK_F] || true
        mockKeyEvent(VK_TAB) | [VK_TAB]       || true
        mockKeyEvent(VK_TAB) | [VK_T, VK_F]   || false
    }

    def mockKeyEvent(int key) {
        return Mock(KeyEvent) {
            getKeyCode() >> key
        }
    }
}
