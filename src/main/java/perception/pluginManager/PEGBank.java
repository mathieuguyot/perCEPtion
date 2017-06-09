package perception.pluginManager;

import perception.primitive_events_generator.PrimitiveEventGenerator;
import utils.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

public class PEGBank {

    private static Deque<Pair<String, Class<? extends PrimitiveEventGenerator>>> pegs
            = new LinkedList<>();

    public static boolean registerPlugin(PerceptionPlugin plugin) {
        for(Pair<String, Class<? extends PrimitiveEventGenerator>> p : plugin.getPegs()) {
            pegs.push(p);
        }
        return true;
    }

    /**
     * Permet de récupérer la classe d'implémentation pour le nom de PEG fournit.
     * @param pegName nom du PEG dont on souhaite récupérer le classe d'implémentation
     * @return la classe d'implémentation correspondant au type de PEG fournit
     */
    public static Optional<Class<? extends PrimitiveEventGenerator>> getClassForPEGName(String pegName) {
        for(Pair<String, Class<? extends PrimitiveEventGenerator>> p : pegs) {
            if(p.getFirst().equals(pegName)) {
                return Optional.of(p.getSecond());
            }
        }
        return Optional.empty();
    }

}
