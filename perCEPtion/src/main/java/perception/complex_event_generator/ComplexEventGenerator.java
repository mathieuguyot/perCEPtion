package perception.complex_event_generator;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.ComplexEvent;
import perception.events.Event;

import java.util.List;
import java.util.Map;

/**
 * Classe abstraite représentant un {@link ComplexEventGenerator}.
 * Un CEG génère des évènement complexes et les ajoute à un {@link perception.core.SACEventStream}.
 * Un CEG récupère ses données des {@link perception.events.PrimitiveEvent}, {@link perception.events.SimpleEvent} et
 * {@link perception.events.ComplexEvent} générés.
 * L'exécution d'un CEG est appelée quand un schéma est reconnu (ce schéma est défini par la méthode getPattern()).
 * Après une détection (en utilisant le schéma), la méthode getPatternSelectFunction() est appelé pour
 * appliquer le traitement adéquat sur le {@link perception.core.SACEventStream}.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\CEG
 * Attention, un CEG est {@link perception.services.PerceptionRunResource}, cela signifie que les modifications
 * effectuées pendant l'exécution sur lui n'aura aucun effet avant le redémarrage du {@link perception.core.PerceptionCore}.
 *  !\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class ComplexEventGenerator extends EventGenerator {

    public ComplexEventGenerator(String name) {
        super(name);
    }

    /**
     * Schéma de détection de ce CEG
     * @return le schéma de détection du CEG
     */
    public abstract Pattern<Event, ?> getPattern();

    /**
     * Traitement de la fonction de CEG
     * @return Le traitement à opérer sur ce CEG
     */
    public abstract PatternSelectFunction<Event, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //TODO Improve doc of this function !
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<Event, ?> pattern = this.getPattern();
            PatternStream<Event> pStream = CEP.pattern(ctx.getPasacEventStream().getStream(), pattern);
            DataStream<Event> outStream = pStream.select(new PatternSelectFunction<Event, Event>() {
                @Override
                public Event select(Map<String, List<Event>> map) throws Exception {
                    Event e = getPatternSelectFunction().select(map);
                    if (isLogGeneratedEvents() && e != null) {
                        PerceptionRunContext.getPerceptionLogger().logComplexEvent((ComplexEvent) e, getName());
                    }
                    return e;
                }
            });
            ctx.getSacEventStream().mergeStream(outStream);
        }
        return initOk;
    }

    @Override
    public void endRun() {
        super.endRun();
    }

}
