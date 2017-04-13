package mine.fanjh;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({"mine.fanjh.Test"})
public class DemoProcessor extends AbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<Element> elementSet = (Set<Element>) roundEnv.getElementsAnnotatedWith(Test.class);
        for(Element element:elementSet){
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Element:\n"
                    + "simpleName:" + element.getSimpleName() + "\n"
                    + "modifier:" + ((Modifier)element.getModifiers().toArray()[0]).name() + "\n"
                    + "kindName:" + element.asType().getKind().name() + "\n"
                    + "TypeMirror:" + element.asType().toString() + "\n"
                    + "kindString:" + element.getKind().toString() + "\n"
            );
            Element enclosingElement = element.getEnclosingElement();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "enclosingElement:\n"
                    + "simpleName:" + enclosingElement.getSimpleName() + "\n"
                    + "modifier:" + ((Modifier)enclosingElement.getModifiers().toArray()[0]).name() + "\n"
                    + "kindName:" + enclosingElement.asType().getKind().name() + "\n"
                    + "qualifiedName:" + ((TypeElement)enclosingElement).getQualifiedName() + "\n"
                    + "TypeMirror:" + enclosingElement.asType().toString() + "\n"
                    + "kindString:" + enclosingElement.getKind().toString() + "\n"
            );
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Printing: Field");
        Set<Element> elementSet1 = (Set<Element>) roundEnv.getElementsAnnotatedWith(TestField.class);
        for(Element element:elementSet1){
            DeclaredType mirror = (DeclaredType) element.asType();
            List<? extends TypeMirror> arguments = mirror.getTypeArguments();
            if(arguments.size() > 0){
                StringBuilder typeString = new StringBuilder(mirror.asElement().toString());
                typeString.append('<');
                for (int i = 0; i < arguments.size(); i++) {
                    if (i > 0) {
                        typeString.append(',');
                    }
                    typeString.append('?');
                }
                typeString.append('>');
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,"arguments:"+typeString.toString());
            }
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Element:\n"
                    + "simpleName:" + element.getSimpleName() + "\n"
                    + "modifier:" + ((Modifier)element.getModifiers().toArray()[0]).name() + "\n"
                    + "kindName:" + element.asType().getKind().name() + "\n"
                    + "TypeMirror:" + element.asType().toString() + "\n"
                    + "kindString:" + element.getKind().toString() + "\n"
            );
            Element enclosingElement = element.getEnclosingElement();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "enclosingElement:\n"
                    + "simpleName:" + enclosingElement.getSimpleName() + "\n"
                    + "modifier:" + ((Modifier)enclosingElement.getModifiers().toArray()[0]).name() + "\n"
                    + "kindName:" + enclosingElement.asType().getKind().name() + "\n"
                    + "qualifiedName:" + ((TypeElement)enclosingElement).getQualifiedName() + "\n"
                    + "TypeMirror:" + enclosingElement.asType().toString() + "\n"
                    + "kindString:" + enclosingElement.getKind().toString() + "\n"
            );
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Printing: full");
        return true;
    }
}
