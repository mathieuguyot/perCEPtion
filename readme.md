# Framework perCEPtion
Bienvenu sur le dépot officiel du framework perCEPtion !

***
PerCEPTion est un framework de monitoring basé sur le traitement des événements complexes (Complex Event Processing - CEP) permettant de mettre en place une surveillance avancée d'une architecture matérielle et logicielle (Cloud par exemple). En outre, il permet de détecter des signes d'incohérence au sein d'un ensemble de composants d'une architecture. Si des incohérences sont détectées par le framework, celui-ci fournit un ensemble d'informations permettant d'aider à la reconfiguration des composants affectés.
***
Ce Framework à été redesigné à partir d'un framework développé par Simon Dupont et des chercheurs et étudiants de l'Institut Mines-Télécom Atlantique. Apache Flink est utilisé dans la nouvelle version du framework.
Il suit assez fidèlement les concepts de la thèse de Simon Dupont : ["Gestion autonomique de l'élasticité multi-couche des applications dans le Cloud : vers une utilisation efficiente des ressources et des services du Cloud"](https://tel.archives-ouvertes.fr/tel-01344377/)

>La dernière version de framework a été conçue par 4 étudiants de l'Institut Mines-Télécom Atlantique :
>- Chloé GUILBAUD
>- Kendall FOREST
>- Mathieu GUYOT
>- Léo PARIS

![alt text](https://www.imt-atlantique.fr/sites/default/files/logo_mt_0_0.png)

## Fonctionnement général

L'image ci-dessous décrit le fonctionnement global du framework perception:


Le framework à pour objectif de monitorer un ensemble de composants d'une architecture. Pour attendre cet objectif, perCEPtion utilise le CEP (complex event processing) d'une manière ingénieuse:
1. La première étape consiste à générer des évènements dis primitifs. Ceux-ci sont générés constaments. Ils sont donc très nombreux mais peu significatifs.
2. La seconde étape consiste a étudier le flux constant d'évènements primitifs pour essayer d'extraire des informations significatives. De cette étape, sont générés des évènements dis simples et complexes (le premier contient des informations significatives d'une resources, le second d'une ou plusieurs resources).
3. La troisième étape consiste a étudier le flux constant d'évènements simples et complexes afin de sélectionner les plus significatifs et de les considérer comme des symtomes porteurs d'une information sur une anomalie d'une partie du système surveillé.
4. Enfin la pile des symptomes est triée et permet de faire remonter les anomalies du système les plus grave.

> Tous les évènements générés par le système de monitoring embarquent avec eux un
score qui permet de faire remoter les maladies du systèmes en fonction de leur
gravité. En outre, les scores des évènements permettent de trier la pile des symptomes.

Tous les composants de cette image sont décrits en détail ci-dessous.

### Évènements

Voici les différents types d’événements qui sont utilisés dans perCEPtion:

|Type d'évènement| Description |
|---|---|
| Primitif event(PE) |  Un événement primitif contient une information sur un seul composant de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés primitive events generator (PEG). |
| Simple event (SE) | Un événement simple contient une ou plusieurs informations sur un seul composant de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés simple events generator (SEG). |
| Complex event (CE) | Un événement complexe contient une ou plusieurs informations sur un ou plusieurs composants de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés complex events generator (CEG). |
| Symptom (Sy) | Tout comme un évènement complexe, un symptome contient  une ou plusieurs informations sur un ou plusieurs composants de l'architecture qui est surveillée. Il est obtenu par les SEGs, CEGs, ou bien les symtoms extractors.|

### Générateurs d'évènements primitifs

Pour générer des évènements primitifs, nous utilisons des générateurs d'évènements primtifs ! (PEG pour Primitive event generator).

Chaque PEG observe le graphe de resources à une cadence x donnée (en millisecondes), et passe en revue chaque resources de ce graphe.
Lors de ce passage en revue, un évènement primitif peut être généré ou non.

Si un évènement primitif est créé, il est envoyé vers la primitive event stream.

Voici un exemple de PEG pouvant être créé par un admistrateur réseau.
Comme vous pouvez le voir, créer son propre PEG est très simple, il suffit
d'hériter de la classe PrimitiveEventGenerator et d'implémenter la méthode
processResource.
```java
/**
 * PEG used to monitor Cpu usage of PM cloud resources
 */
public class PEG_Pm_Cpu extends PrimitiveEventGenerator {

    /**
     * Constructor of the PEG
     * @param name Name of the PEG
     * @param msRefreshingRate Refreshing rate in milliseconds
     */
    public PEG_Pm_Cpu(String name, long msRefreshingRate) {
        super(name, msRefreshingRate);
    }

    /*
     * Processing function of the PEG, Call for each Cloud resources
     * every msRefreshingRate milliseconds
     */
    @Override
    protected Optional<PrimitiveEvent> processResource(CloudResource cr) {
        //If the resource is a PM, we want to generate a primitive event
        if(cr.getType() == CloudResourceType.PM) {
            String crName = cr.getName();
            CloudResourceType crType = cr.getType();
            int score = cr.getScore();
            int cpuConsumption = ((PM)cr).getCpuConsumption();
            PE_Cpu pe_Cpu = new PE_Cpu(crName, crType, score, cpuConsumption);
            return Optional.of(pe_Cpu);
        }
        //Else, generate nothing
        return Optional.empty();
    }

}
```

### Générateurs d'évènements simples

Pour générer des évènements simples, nous utilisons des générateurs d'évènements simples ! (SEG pour Simple event generator).

Chaque SEG observe en continue la primitive event stream, et essaye de détecter un pattern (une liste de PE) défini par le concepteur du SEG.
Si un pattern est reconu, il est traité par le SEG et un Simple event peut être créé. (Voir même un symptome si l'information récupérée est très significative).

Voici un exemple de SEG pouvant être créé par un admistrateur réseau.
Comme vous pouvez le voir, créer son propre SEG est très simple, il suffit
d'hériter de la classe SimpleEventGenerator et d'implémenter les méthodes
getPattern (pattern de reconnaissance du SEG) et getPatternSelectFunction (fonction de traitement des patterns détectés).

```java
/**
 * SEG used to detect cpu consumption drop from a cloud resource.
 */
public class SEG_Cpu_Drop extends SimpleEventGenerator {

    int normalCpuCadency;
    int anormalCpuCadency;
    int msDetectionWindown;

    //Constructor of the SEG
    public SEG_Cpu_Drop(String name, int normalCpuCadency,
                        int anormalCpuCadency, int msDetectionWindown)
    {
        super(name);
        this.normalCpuCadency = normalCpuCadency;
        this.anormalCpuCadency = anormalCpuCadency;
        this.msDetectionWindown = msDetectionWindown;
    }

    @Override
    public Pattern<PrimitiveEvent, ?> getPattern() {
        return Pattern
                //We looking for a PE which contains a normal cpu consumption of the cloud resource.
                .<PrimitiveEvent>begin("IDLE")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() > normalCpuCadency) {
                            return true;
                        }
                        return false;
                    }
                })
                //We ignore all PEs that are not PE_Cpu PEs
                .next("IGNORE")
                .oneOrMore()
                .optional()
                .where(new IterativeCondition<PrimitiveEvent>() {
                    @Override
                    public boolean filter(PrimitiveEvent pe, Context<PrimitiveEvent> context) throws Exception {
                        if(pe.getClass() == PE_Cpu.class) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                //We now looking to a PE which contains an anormal cpu consumption
                .next("CPU DROP")
                .subtype(PE_Cpu.class)
                .where(new IterativeCondition<PE_Cpu>() {
                    @Override
                    public boolean filter(PE_Cpu pe_cpu, Context<PE_Cpu> context) throws Exception {
                        if(pe_cpu.getCpuValue() < anormalCpuCadency) {
                            return true;
                        }
                        return false;
                    }
                })
                //All of this in a ms interval specified
                .within(Time.milliseconds(msDetectionWindown));
    }

    @Override
    public PatternSelectFunction<PrimitiveEvent, Event> getPatternSelectFunction() {
        return new PatternSelectFunction<PrimitiveEvent, Event>() {
            @Override
            public Event select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                boolean isSecondPE = false;
                int cpuHigh = 0, cpuLow = 0;
                //Iterate all the list of primitive event stored in the pattern
                for (List<PrimitiveEvent> p : map.values()) {
                    for (PrimitiveEvent pe : p) {
                        //If the event is the first PE_Cpu monitored (with high cpu)
                        if(pe.getClass() == PE_Cpu.class && !isSecondPE) {
                            cpuHigh = ((PE_Cpu) pe).getCpuValue();
                            isSecondPE = true;
                        }
                        //If the event is the second PE_Cpu monitored (with low cpu)
                        else if(pe.getClass() == PE_Cpu.class && isSecondPE) {
                            cpuLow = ((PE_Cpu) pe).getCpuValue();
                            SE_Cpu_Drop seCpuDrop = new SE_Cpu_Drop(pe.getCloudResourceName(),
                                                                     pe.getCloudResourceType(),
                                                                     pe.getScore(),
                                                                     cpuHigh,
                                                                     cpuLow);
                            //Create a simple event
                            return seCpuDrop;
                        }
                    }
                }
                //In case of bad pattern definition by operator... Should never happen
                return null;
            }
        };
    }
}
```

### Générateurs d'évènements compexes

### Extracteurs de symptomes

## Utilisation du Framework

Nous avons fait en sorte que l'utilisation du framework soit le plus simple possible !
Les sections ci-dessous détaillent comment utiliser perCEPtion.

# En travaux !
