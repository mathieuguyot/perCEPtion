package perception.primitive_events_generator.implementations;

import graph.CloudResource;
import graph.CloudResourceType;
import graph.VM;
import perception.events.PrimitiveEvent;
import perception.events.primitive_events.PE_Disk;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Vm_Disk extends PrimitiveEventGenerator {

    public PEG_Vm_Disk(long msRefreshingRate) {
        super(msRefreshingRate);
    }

    @Override
    protected Optional<PrimitiveEvent>  processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.VM) {
            PE_Disk pe_Disk = new PE_Disk(cr.getName(), cr.getType(), ((VM)cr).getDiskConsumption());
            return Optional.of(pe_Disk);
        }
        return Optional.empty();
    }

}
