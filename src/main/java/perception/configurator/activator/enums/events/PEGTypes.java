package perception.configurator.activator.enums.events;

import perception.primitive_events_generator.implementations.*;

import java.util.Arrays;
import java.util.List;

/**
 * Enumération des types de PEG couplés à leur classe d'implémentation.
 * Permet de lier le système de validation et de parse à l'activation
 * des primitives events.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public enum PEGTypes {

    PEG_BLANK("PEG_Blank", PEG_Blank.class),
    PEG_CO_RESPONSETIME("PEG_Co_ResponseTime", PEG_Co_ResponseTime.class),

    // PM
    PEG_PM_CPU("PEG_Pm_Cpu", PEG_Pm_Cpu.class),
    PEG_PM_DISK("PEG_Pm_Disk", PEG_Pm_Disk.class),
    PEG_PM_RAM("PEG_Pm_Ram", PEG_Pm_Ram.class),

    // VM
    PEG_VM_CPU("PEG_Vm_Cpu", PEG_Vm_Cpu.class),
    PEG_VM_DISK("PEG_Vm_Disk", PEG_Vm_Disk.class),
    PEG_VM_RAM("PEG_Vm_Ram", PEG_Vm_Ram.class);


    // Attributs
    private String PEG_name;
    private Class<?> implementationClasse;

    // Constructeur

    /**
     * Constructeur de la classe {@link PEGTypes}.
     *
     * @param PEG_name             nom du PEG
     * @param implementationClasse classe d'implémentation du PEG
     */
    PEGTypes(String PEG_name, Class<?> implementationClasse) {
        this.PEG_name = PEG_name;
        this.implementationClasse = implementationClasse;
    }

    // Services

    /**
     * Permet la récupération du {@link PEGTypes} à partir de son libellé.
     * Note : la recherche du libellé se fait en ignorant la case
     *
     * @param lab le libellé de l'objet recherché
     * @return l'objet de l'énumération correspondant au libellé fournit ou null
     * si le libellé est inconnu
     */
    public static PEGTypes fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getPEG_name().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    /**
     * Permet de récupérer la classe d'implémentation pour le nom de PEG fournit.
     * @param pegName nom du PEG dont on souhaite récupérer le classe d'implémentation
     * @return la classe d'implémentation correspondant au type de PEG fournit
     */
    public static Class<?> getClassForPEGName(String pegName) throws ClassNotFoundException {
        Class<?> implementationClass = PEGTypes.fromLabel(pegName).getClass();
        if (implementationClass == null) {
            throw new ClassNotFoundException();
        }
        return implementationClass;
    }

    /**
     * Permet d'obtenir une liste des valeurs de l'énumération
     * {@link PEGTypes}.
     *
     * @return la liste des valeur de l'énumération {@link PEGTypes}
     */
    public static List<PEGTypes> valuesAsList() {
        return Arrays.asList(values());
    }

    // Accesseurs

    public String getPEG_name() {
        return PEG_name;
    }

    public Class<?> getImplementationClasse() {
        return implementationClasse;
    }

}
