package perception.primitive_events_generator.implementations;

import graph.CloudResource;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Blank extends PrimitiveEventGenerator {

    public PEG_Blank() {
        super(Long.MAX_VALUE);
    }

    @Override
    protected Optional<PrimitiveEvent> processResource(CloudResource cr) {
        return Optional.empty();
    }

}
