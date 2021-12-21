package brq.intellij.plugins.confrunner.builders

import brq.intellij.plugins.confrunner.BaseSpec
import brq.intellij.plugins.confrunner.settings.Settings
import org.skyscreamer.jsonassert.JSONAssert

class ConfigurationTreeBuilderTest extends BaseSpec {
    def testFilesDir = "domain/configuration/builder/output"

    def "should build configuration tree with only non foldered configurations"() {
        given:
        def expected = jsonFile("${testFilesDir}/expected-non-folders")
        def configurations = buildOnlyNonFolderedConfigurations()

        when:
        def actual = ConfigurationTreeBuilder.buildConfigurationTree(configurations, new Settings())

        then:
        JSONAssert.assertEquals(expected, toJson(actual), true)
    }

    def "should build configuration tree with only folders configurations"() {
        given:
        def expected = jsonFile("${testFilesDir}/expected-only-folders")
        def configurations = buildOnlyFolderedConfigurations()

        when:
        def actual = ConfigurationTreeBuilder.buildConfigurationTree(configurations, new Settings())

        then:
        JSONAssert.assertEquals(expected, toJson(actual), true)
    }

    def "should build configuration tree mixed configurations - folders / single configurations"() {
        given:
        def expected = jsonFile("${testFilesDir}/expected-mixed")
        def configurations = buildMixedConfigurations()

        when:
        def actual = ConfigurationTreeBuilder.buildConfigurationTree(configurations, new Settings())

        then:
        JSONAssert.assertEquals(expected, toJson(actual), true)
    }

    def buildMixedConfigurations() {
        return buildOnlyFolderedConfigurations() + buildOnlyNonFolderedConfigurations()
    }

    def buildOnlyFolderedConfigurations() {
        return [
                buildSingleRunConfiguration("u1", "junit", "integration"),
                buildSingleRunConfiguration("u2", "junit", "integration"),
                buildSingleRunConfiguration("u3", "junit", "integration"),
                buildSingleRunConfiguration("u4", "junit", "unit"),
                buildSingleRunConfiguration("u5", "junit", "unit"),
                buildSingleRunConfiguration("u6", "junit", "test-dir"),
                buildSingleRunConfiguration("r1", "remote", "test-dir"),
                buildSingleRunConfiguration("r2", "remote", "remote-dir"),
                buildSingleRunConfiguration("r3", "remote", "unit")
        ]
    }

    def buildOnlyNonFolderedConfigurations() {
        return [
                buildSingleRunConfiguration("u1", "junit", null),
                buildSingleRunConfiguration("u2", "junit", null),
                buildSingleRunConfiguration("r1", "remote", null),
        ]
    }
}
