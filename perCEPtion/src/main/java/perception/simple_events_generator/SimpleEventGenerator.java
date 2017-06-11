package perception.simple_events_generator;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import perception.simple_events_generator.SimpleEventGenerator;
import perception.core.EventGenerator;
import perception.core.PerceptionRunContext;
import perception.events.Event;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;

import java.util.List;
import java.util.Map;

/**
 * Classe abstraite représentant un {@link SimpleEventGenerator}.
 * Un SEG génère des évènement simples et les ajoute à un {@link perception.core.SACEventStream}.
 * Un SEG récupère ses données des {@link perception.events.PrimitiveEvent} générés.
 * L'exécution d'un SEG est appelée quand un schéma est reconnu (ce schéma est défini par la méthode getPattern()).
 * Après une détection (en utilisant le schéma), la méthode getPatternSelectFunction() est appelé pour
 * appliquer le traitement adéquat sur le {@link perception.core.SACEventStream}.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\CEG
 * Attention, un SEG est {@link perception.services.PerceptionRunResource}, cela signifie que les modifications
 * effectuées pendant l'exécution sur lui n'aura aucun effet avant le redémarrage du {@link perception.core.PerceptionCore}.
 *  !\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public abstract class SimpleEventGenerator extends EventGenerator {

    /**
     * Constructeur de la classe {@link SimpleEventGenerator}
     * @param name - le nom du SimpleEventGenerator
     */
    public SimpleEventGenerator(String name) {
        super(name);
    }

    /**
     * Schéma de détection du SEG
     * @return Schéma du SEG
     */
    public abstract Pattern<PrimitiveEvent, ?> getPattern();

    /**
     * La méthode de traitement du SEG
     * @return La méthode de traitement du SEG
     */
    public abstract PatternSelectFunction<PrimitiveEvent, Event> getPatternSelectFunction();

    @Override
    public boolean beforeRun(PerceptionRunContext ctx) {
        //TODO Improve doc of this function !
        boolean initOk = super.beforeRun(ctx);
        if(initOk) {
            Pattern<PrimitiveEvent, ?> pattern = this.getPattern();
            PatternStream<PrimitiveEvent> pStream = CEP.pattern(ctx.getPrimitiveEventStream().getKeyedStream(), pattern);
            DataStream<Event> outStream = pStream.select(new PatternSelectFunction<PrimitiveEvent, Event>() {
                @Override
                public Event select(Map<String, List<PrimitiveEvent>> map) throws Exception {
                    Event e = getPatternSelectFunction().select(map);
                    if(isLogGeneratedEvents() && e != null) {
                        PerceptionRunContext.getPerceptionLogger().logSimpleEvent((SimpleEvent)e, getName());
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
