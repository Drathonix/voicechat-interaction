This is the unofficial forge port of Voicechat Interaction. I ported this purely for use on my own servers.

A public download link for forge is not available. I do not own this mod. I will not be uploading a jar file. If you do want to download you can contact me on discord by joining here [ViciousDevelopment](https://discord.gg/rsYYBgwnRJ) sending me a friend request is unnecessary.

# Voice Chat Interaction

This server side Forge mod allows Simple Voice Chat to interact with your Minecraft world.

## Features

- A custom, configurable vibration frequency for voice
- Talking in voice chat activates sculk sensors
- Talking in voice chat is detected by the warden
- Optional support for whisper and group chat vibrations
- Talking while sneaking doesn't trigger vibrations (Configurable)
- Configurable volume threshold

## Config Values

*config/vcinteraction/vcinteraction.properties*

|Name| Default Value | Description                                                  |
|---|---------------|--------------------------------------------------------------|
|`group_interaction`| `false`       | If talking in groups should trigger vibrations               |
|`whisper_interaction`| `false`       | If whispering should trigger vibrations                      |
|`sneak_interaction`| `false`       | If talking while sneaking should trigger vibrations          |
|`voice_sculk_frequency`| `7`           | The frequency of the voice vibration                         |
|`minimum_activation_threshold`| `-50`         | The audio level threshold to activate the sculk sensor in dB |

## Credits

- [Fabric API](https://github.com/FabricMC/fabric)
- [Polymer](https://github.com/Patbox/polymer)
