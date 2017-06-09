package primitive_events_generator;

import events.primitive_events.PE_Cpu;
import graph.CloudResource;
import graph.CloudResourceType;
import graph.PM;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Pm_Cpu extends PrimitiveEventGenerator {

    public PEG_Pm_Cpu(String name, long msRefreshingRate) {
        super(name, msRefreshingRate);
    }

    public PEG_Pm_Cpu(long msRefreshingRate) {
        super("PEG_Pm_Cpu", msRefreshingRate);
    }

    @Override
    protected Optional<PrimitiveEvent> processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.PM) {
            PE_Cpu pe_cpu = new PE_Cpu(cr.getName(), cr.getType(), cr.getScore(), ((PM)cr).getCpu_consumption());
            return Optional.of(pe_cpu);
        }
        return Optional.empty();
    }

}
