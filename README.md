
# Skyblock Reborn

Experience the classic Hypixel SkyBlock on your own Spigot 1.8 server! This plugin is a remake of SkyBlock game mode on the popular server Hypixel.


## Build

Build Skyblock Reborn with IntelliJ IDEA

1) Open this project folder in IntelliJ IDEA as project
2) Go to File -> Project Structure 
3) Configure project settings as follows:
```
Project Settings 
  Project 
    SDK: 1.8
  Modules 
    SkyblockReborn 
      Dependencies: Click Add (the + sign), and select spigot-1.8.8.jar. 
      [IMPORTANT!] If spigot-1.8.8.jar is already added, Ignore the above step.
  Artifacts
    SkyblockReborn.jar
      Output directory: <Path to your server plugin folder>
```
4) Press Ctrl+Shift+A. Type `Build Artifact` and Enter. Click `Build`.

## Installation

To deploy this project run, put the plugin.jar file inside your spigot server plugin folder. 

Run `./start.sh` if you are on Linux or macOS.

Run `./start.bat` if you are on Windows.

