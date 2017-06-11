package perception.primitive_events_generator;

import graph.CloudResource;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.core.CloudResourcesAccess;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.PrimitiveEvent;

import java.util.Optional;

/**
 * Classe abstraite représentant un {@link PrimitiveEventGenerator}.
 * Un PEG génère des évènements primitifs et les ajoute à un {@link perception.core.PrimitiveEventStream}.
 * L'exécution d'un PEG est appelée de façon périodique, le temps (en millisecondes) entre chaque exécution
 * étant défini par l'utilisateur.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\CEG
 * Attention, un PEG est {@link perception.services.PerceptionRunResource}, cela signifie que les modifications
 * effectuées pendant l'exécution sur lui n'aura aucun effet avant le redémarrage du {@link perception.core.PerceptionCore}.
 *  !\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class PrimitiveEventGenerator extends EventGenerator
        implements SourceFunction<PrimitiveEvent>
{

    private boolean isRunning; //Indique si le PEG est exécuté ou non
    private long msRefreshingRate; //Période d'exécution du PEG (appel toutes les msRefreshingRate millisecondes)

    /**
     * Constructeur de la classe {@link PrimitiveEventGenerator}
     * @param name - Nom du PrimitiveEventGenerator
     * @param msRefreshingRate - Période d'exécution PrimitiveEventGenerator (appel toutes les msRefreshingRate millisecondes)
     */
    public PrimitiveEventGenerator(String name, long msRefreshingRate) {
        super(name);
        this.msRefreshingRate = msRefreshingRate;
        this.isRunning = true;
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        while(isRunning) {
            if(isHasToGenerateEvents()) {
                long startTime = System.currentTimeMillis();
                this.exec(ctx);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                //TODO if elapsedTime > msRefreshingRate, up a warning, the monitoring system is to low
                Thread.sleep(msRefreshingRate);
            }
        }
    }

    /**
     * Boucle d'exécution du PEG. Pour chaque ressource, la méthode processResource() sera appelée
     * @param ctx - Le {@link org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext} qui collecte les PE générés
     */
    private void exec(SourceContext ctx) {
        //TODO Improve doc of this function !
        for(CloudResource cr : CloudResourcesAccess.getInstance().getResources().values()) {
            Optional<PrimitiveEvent> optEvent = processResource(cr);
            if(optEvent.isPresent()) {
                ctx.collect(optEvent.get());
                if(this.isLogGeneratedEvents() && PerceptionRunContext.getPerceptionLogger() != null) {
                    PerceptionRunContext.getPerceptionLogger().logPrimitiveEvent(optEvent.get(), getName());
                }
            }
        }
    }

    @Override
    public void cancel() {}

    /**
     * Méthode abstraite à définir par les PEG.
     * Définit si le PEG doit retourner un {@link PrimitiveEvent} ou rien, en fonction de la ressource donnée.
     * @param cr - La ressource à traiter
     * @return Un évènement primitif à ajouter à la {@link perception.core.PrimitiveEventStream} ou un Optional.empty
     */
    protected abstract Optional<PrimitiveEvent> processResource(CloudResource cr);
}