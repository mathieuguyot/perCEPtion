package perception.configurator.activator.enums.events;

import perception.simple_events_generator.implementations.SEG_Blank;
import perception.simple_events_generator.implementations.SEG_Cpu_Drop;
import perception.simple_events_generator.implementations.SEG_Cpu_Overload;
import perception.simple_events_generator.implementations.SEG_Ram_Drop;

import java.util.Arrays;
import java.util.List;

/**
 * Created by asus on 06/06/2017.
 */
public enum SEGTypes {

    SEG_BLANK("SEG_Blank",SEG_Blank.class),
    SEG_CPU_DROP("SEG_Cpu_Drop",SEG_Cpu_Drop.class),
    SEG_CPU_OVERLOAD("SEG_Cpu_Overload",SEG_Cpu_Overload.class),
    SEG_RAM_DROP("SEG_Ram_Drop",SEG_Ram_Drop.class);

    // Attributs
    private String SEG_name;
    private Class<?> implementationClasse;

    // Constructeur

    /**
     * Constructeur de la classe {@link SEGTypes}.
     *
     * @param SEG_name             nom du SEG
     * @param implementationClasse classe d'implémentation du SEG
     */
    SEGTypes(String SEG_name, Class<?> implementationClasse) {
        this.SEG_name = SEG_name;
        this.implementationClasse = implementationClasse;
    }

    // Services

    /**
     * Permet la récupération du {@link SEGTypes} à partir de son libellé.
     * Note : la recherche du libellé se fait en ignorant la case
     *
     * @param lab le libellé de l'objet recherché
     * @return l'objet de l'énumération correspondant au libellé fournit ou null
     * si le libellé est inconnu
     */
    public static SEGTypes fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getSEG_name().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    /**
     * Permet de récupérer la classe d'implémentation pour le nom de SEG fournit.
     * @param segName nom du SEG dont on souhaite récupérer le classe d'implémentation
     * @return la classe d'implémentation correspondant au type de SEG fournit
     */
    public static Class<?> getClassForSEGName(String segName) throws ClassNotFoundException {
        Class<?> implementationClass = SEGTypes.fromLabel(segName).getClass();
        if (implementationClass == null) {
            throw new ClassNotFoundException();
        }
        return implementationClass;
    }

    /**
     * Permet d'obtenir une liste des valeurs de l'énumération
     * {@link SEGTypes}.
     *
     * @return la liste des valeur de l'énumération {@link SEGTypes}
     */
    public static List<SEGTypes> valuesAsList() {
        return Arrays.asList(values());
    }

    // Accesseurs

    public String getSEG_name() {
        return SEG_name;
    }

    public Class<?> getImplementationClasse() {
        return implementationClasse;
    }


}
