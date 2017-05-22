package perception.primitive_events_generator.implementations;

import graph.CloudResource;
import graph.CloudResourceType;
import graph.VM;
import perception.events.PrimitiveEvent;
import perception.events.primitive_events.PE_Ram;
import perception.primitive_events_generator.PrimitiveEventsGenerator;

import java.util.Optional;

public class PEG_Vm_Ram extends PrimitiveEventsGenerator {

    public PEG_Vm_Ram(long msRefreshingRate) {
        super(msRefreshingRate);
    }

    @Override
    protected Optional<PrimitiveEvent>  processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.VM) {
            PE_Ram pe_Ram = new PE_Ram(cr.getName(), cr.getType(), ((VM)cr).getRamConsumption());
            return Optional.of(pe_Ram);
        }
        return Optional.empty();
    }

}
