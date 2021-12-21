package brq.intellij.plugins.confrunner.actions

import brq.intellij.plugins.confrunner.BaseSpec
import org.skyscreamer.jsonassert.JSONAssert
import spock.lang.Unroll

class ConfigurationsLookupFilterTest extends BaseSpec {
    def testFilesDir = "ui/configuration/filter/"

    @Unroll
    def "should filter configurations based on input text: #caseName"() {
        given:
        def expected = jsonFile("${testFilesDir}/output/${expectedFile}")
        def input = jsonFile("${testFilesDir}/input/${inputFile}")
        def inputTree = buildUIConfigurationTree("${testFilesDir}/input/${inputFile}")

        when: "filtering by text"
        ConfigurationsLookupFilter.filterConfigurations(inputTree.getConfigurationTypeBoxes(), text)

        then: "configurations should be filtered"
        def actual = jsonUIConfigurationTree(inputTree)
        JSONAssert.assertEquals(expected, actual, true)

        when: "removing text from filtering"
        ConfigurationsLookupFilter.filterConfigurations(inputTree.getConfigurationTypeBoxes(), "")

        then: "all configurations should be visible"
        def actualRm = jsonUIConfigurationTree(inputTree)
        JSONAssert.assertEquals(input, actualRm, true)

        where:
        inputFile | text          || expectedFile                           | caseName
        "conf_1"  | "junit"       || "${inputFile}_junit_type_all_children" | "whole type matched by text"
        "conf_1"  | "integration" || "${inputFile}_integration"             | "whole folder matched by text"
        "conf_1"  | "gration"     || "${inputFile}_integration"             | "whole folder matched by text"
        "conf_1"  | "u1"          || "${inputFile}_u1"                      | "exact 'u1' configurations - foldered/non-foldered"
        "conf_1"  | "u"           || "${inputFile}_u"                       | "configurations containing 'u'"
        "conf_2"  | "unit"        || "${inputFile}_unit"                    | "configurations containing 'unit'"
        "conf_2"  | "unit"        || "${inputFile}_unit"                    | "configurations containing 'unit'"
        "conf_2"  | "appTest"     || "${inputFile}_appTest"                 | "split words search 'app', 'test'"
        "conf_2"  | "apptest"     || "${inputFile}_app_test_no_match"       | "nothing matches exact 'apptest' text"
        "conf_2"  | "teBu"        || "${inputFile}_teBu"                    | "split words search 'te', 'Bu'"
        "conf_2"  | "RU"          || "${inputFile}_RU"                      | "split words search 'R', 'U'"
        "conf_2"  | "RUT"         || "${inputFile}_RUT"                     | "split words search 'R', 'U', 'T'"
        "conf_2"  | "Ru"          || "${inputFile}_Ru_exact_match"          | "configurations containing 'ru'"
    }
}
