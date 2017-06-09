package perception.pluginManager;

public class PluginManager {

    public static boolean registerPlugin(PerceptionPlugin plugin) {
        boolean isRegistrationOk = true;
        isRegistrationOk = PEGBank.registerPlugin(plugin);
        return isRegistrationOk;
    }



}
