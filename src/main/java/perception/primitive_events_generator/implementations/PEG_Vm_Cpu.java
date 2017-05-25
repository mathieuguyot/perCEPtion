package perception.primitive_events_generator.implementations;

import graph.CloudResource;
import graph.CloudResourceType;
import graph.VM;
import perception.events.PrimitiveEvent;
import perception.events.primitive_events.PE_Cpu;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Vm_Cpu extends PrimitiveEventGenerator {

    public PEG_Vm_Cpu(long msRefreshingRate) {
        super(msRefreshingRate);
    }

    @Override
    protected Optional<PrimitiveEvent>  processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.VM) {
            PE_Cpu pe_cpu = new PE_Cpu(cr.getName(), cr.getType(), ((VM)cr).getCpu_consumption());
            return Optional.of(pe_cpu);
        }
        return Optional.empty();
    }

}
