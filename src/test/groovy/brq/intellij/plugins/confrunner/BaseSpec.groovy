package brq.intellij.plugins.confrunner

import brq.intellij.plugins.confrunner.builders.JPanelConfigurationTreeBuilder
import brq.intellij.plugins.confrunner.domain.ConfigurationTree
import brq.intellij.plugins.confrunner.test.utils.ConfigurationMarshaller
import com.fasterxml.jackson.databind.ObjectMapper
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.icons.AllIcons
import spock.lang.Specification

class BaseSpec extends Specification {
    def BASE_RESOURCE_PATH = "src/test/resources/"
    def JSON = ".json"

    def objectMapper = new ObjectMapper()

    def toJson(def obj) {
        return objectMapper.writeValueAsString(obj)
    }

    def jsonFile(def file) {
        return objectMapper.readTree(new File("${BASE_RESOURCE_PATH}${file}${JSON}")).toString()
    }

    def buildDomainConfigurationTree(def file) {
        return objectMapper.readValue(new File("${BASE_RESOURCE_PATH}${file}${JSON}"), ConfigurationTree.class)
    }

    def buildUIConfigurationTree(def file) {
        return JPanelConfigurationTreeBuilder.transformConfigurationTreeIntoJPanelTree(buildDomainConfigurationTree(file))
    }

    def jsonUIConfigurationTree(def tree) {
        return toJson(uiTreeToDomain(tree))
    }

    def uiTreeToDomain(def tree) {
        return ConfigurationMarshaller.mapVisibleUIComponentsToDomain(tree)
    }

    def buildSingleRunConfiguration(name, type, folderName) {
        return Mock(RunnerAndConfigurationSettings) {
            getFolderName() >> folderName
            getName() >> name
            getType() >> Mock(ConfigurationType) {
                getDisplayName() >> type
            }
            getFactory() >> Mock(ConfigurationFactory) {
                getIcon() >> AllIcons.Idea_logo_welcome
            }
        }
    }
}
