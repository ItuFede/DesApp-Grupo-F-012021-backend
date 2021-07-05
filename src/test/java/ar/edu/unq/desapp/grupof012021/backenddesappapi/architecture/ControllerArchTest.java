package ar.edu.unq.desapp.grupof012021.backenddesappapi.architecture;//package ar.edu.unq.desapp.grupof012021.backenddesappapi.architecture;//package ar.edu.unq.desapp.grupof012021.backenddesappapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

public class ControllerArchTest {
    @Test
    public void controllers_Have_RestControllerAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupof012021.backenddesappapi");

        ArchRule rule = classes().that().resideInAPackage("..webservice..")
                .should().beAnnotatedWith(RestController.class);

        rule.check(importedClasses);
    }

    @Test
    public void controllerMethods_ShouldHave_RequestMappingAnnotation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupof012021.backenddesappapi");

        ArchRule rule =
                methods().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                        .should().beAnnotatedWith(RequestMapping.class);

        rule.check(importedClasses);
    }

    @Test
    public void controllerMethods_ShouldNot_ThrowException() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupof012021.backenddesappapi");

        ArchRule rule =
                methods().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                        .should().notDeclareThrowableOfType(Exception.class);

        rule.check(importedClasses);
    }

    @Test
    public void controllerMethods_ShouldReturn_ResponseEntityType() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("ar.edu.unq.desapp.grupof012021.backenddesappapi");

        ArchRule rule =
                methods().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                        .should().haveRawReturnType(ResponseEntity.class);

        rule.check(importedClasses);
    }
}
