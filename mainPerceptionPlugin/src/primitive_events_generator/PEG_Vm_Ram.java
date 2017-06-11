package primitive_events_generator;

import events.primitive_events.PE_Ram;
import graph.CloudResource;
import graph.CloudResourceType;
import graph.VM;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Vm_Ram extends PrimitiveEventGenerator {

    public PEG_Vm_Ram(String name, long msRefreshingRate) {
        super(name, msRefreshingRate);
    }

    public PEG_Vm_Ram(long msRefreshingRate) {
        super("PEG_Vm_Ram", msRefreshingRate);
    }

    @Override
    protected Optional<PrimitiveEvent>  processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.VM) {
            PE_Ram pe_Ram = new PE_Ram(cr.getName(), cr.getType(), cr.getScore(), ((VM)cr).getRamConsumption());
            return Optional.of(pe_Ram);
        }
        return Optional.empty();
    }

}
