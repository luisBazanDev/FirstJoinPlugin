# First Join Plugin v1.0.0
A minecraft plugin to be able to easily configure basic things that are executed when a player logs in
for the first time

## License [MIT](./LICENSE.md)

## Install
1. Download Plugin from [Spigot](https://www.spigotmc.org/resources/first-join-plugin.103544/) or [Github releases](https://github.com/luisBazanDev/FirstJoinPlugin/releases)
2. Move the jar file to the plugins folder
3. Start your server and configure in `plugins/FirstJoinPlugin/config.yml`

## Default Config File
```yml
# Plugin replace Placeholders? (development)
placeholder-api: false

# Is used in variable %server%
server-name: "Luis's server"

# This message is always sent to all players when a player joins
msg-join:
  - "&e%player% joined the game"

############ Messages and Commands ##############
# %player% - Is replaced by the player's name   #
# %server% - Is replaced by the server's name   #
#################################################
messages:
  - "&f#############################################"
  - "&a     Hi &2%player%&a welcome to the %server%!"
  - "&6       We hope you have a good time!"
  - "&f#############################################"
commands:
  - "say Hey everyone, %player% is new here!"

# Here save serialized items
items: []
```

## Set-up development
1. First add the JitPack repository to your build file.
```xml
<repositories>
	<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
    </repository>
</repositories>
```
2. Add the dependency
```xml
<dependency>
	<groupId>com.github.luisBazanDev</groupId>
	<artifactId>FirstJoinPlugin</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```