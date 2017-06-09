import perception.pluginManager.PerceptionPlugin;
import primitive_events_generator.*;
import simple_events_generator.SEG_Cpu_Drop;

public class MainPerceptionPlugin extends PerceptionPlugin {

    private static PerceptionPlugin plugin = new MainPerceptionPlugin("MainPerceptionPlugin", 1);

    public static PerceptionPlugin getPlugin() {
        return plugin;
    }

    protected MainPerceptionPlugin(String pluginName, int version) {
        super(pluginName, version);
    }

    @Override
    protected void initPlugin() {
        //Registering PEGs
        this.registerPEG(PEG_Co_ResponseTime.class);
        this.registerPEG(PEG_Pm_Cpu.class);
        this.registerPEG(PEG_Pm_Disk.class);
        this.registerPEG(PEG_Pm_Ram.class);
        this.registerPEG(PEG_Vm_Cpu.class);
        this.registerPEG(PEG_Vm_Disk.class);
        this.registerPEG(PEG_Vm_Ram.class);

        //Register SEGs
        this.registerSEG(SEG_Cpu_Drop.class);

    }

}
