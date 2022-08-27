package de.maxhenkel.vcinteraction;

import de.maxhenkel.configbuilder.ConfigBuilder;
import de.maxhenkel.vcinteraction.config.ServerConfig;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(VoicechatInteraction.MODID)
public class VoicechatInteraction {

    public static final String MODID = "vcinteraction";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ServerConfig SERVER_CONFIG;
    public static final DeferredRegister<GameEvent> EVENTS = DeferredRegister.create(Registry.GAME_EVENT_REGISTRY,MODID);

    public static RegistryObject<GameEvent> VOICE_GAME_EVENT;

    public VoicechatInteraction() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EVENTS.register(modEventBus);
        modEventBus.addListener(this::onInit);
        Path cfgPath = Paths.get(".").resolve("config").resolve(MODID).resolve(MODID + ".properties");
        SERVER_CONFIG = ConfigBuilder.build(cfgPath, ServerConfig::new);
        VOICE_GAME_EVENT = EVENTS.register("voice", ()->new GameEvent("voice", 16));
        //Might be unneccessary in forge, I'm not exactly sure.
        //RegistrySyncUtils.setServerEntry(Registry.GAME_EVENT, VOICE_GAME_EVENT);
        //Keep within the valid range of frequencies.



    }
    public void onInit(FMLCommonSetupEvent event){
        LOGGER.info("VCIForge-Initializing");
        //Make the sculk map modifiable.
        Object2IntOpenHashMap<GameEvent> map = new Object2IntOpenHashMap<>();
        map.putAll(SculkSensorBlock.VIBRATION_FREQUENCY_FOR_EVENT);
        SculkSensorBlock.VIBRATION_FREQUENCY_FOR_EVENT=map;
        //Add the Voicechat event.
        int frequency = Math.max(SERVER_CONFIG.voiceSculkFrequency.get(),0);
        frequency = Math.min(frequency,16);
        int replaced = map.put(VOICE_GAME_EVENT.get(),frequency);
        if(replaced != 0){
            LOGGER.warn("You apparently have two mods with voicechat sculk interaction capability. VoicechatInteraction-Forge has taken priority.");
        }
    }
}
