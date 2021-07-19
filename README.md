# NoFootstepSound
### It prevents footstep sounds

This is a raw version of the plugin and needs to be improved but,
no one's stopping you to try it on your server ✨!

## Features

- You can set a radius of effectiveness were to prevent the footstep sounds.
- Always-enabled or per world selection.

## Prerequisites

- Spigot 1.8.8+
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) plugin.

## Installation

- Put the **No-Footstep-Sound-1.0.jar** file in your plugins folder.
- Start/Restart the server or load it with the Plugman plugin.

## Commands and permissions

| Main command | Aliases | description | permission |
| ------ | ------ | ------ | ------ |
| /NoFootstepSound <arg> | /nfs, /nfps, /nfsp | list of plugin sub-commands | ``nfps`` |

| sub-command | description | permission |
| ------ | ------ | ------ |
| /nfps add <world-name> | Add a world to the world list | ``nfps.add`` |
| /nfps remove <world-name> | Remove a world from the world list | ``nfps.remove`` |
| /nfps list | List of config worlds | ``nfps.list`` |
| /nfps reload | Reload the config file | ``nfps.reload`` |

## License
GNU General Public License v3.0
