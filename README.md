# Undertale Battle Engine

***

A simple engine for making and playing custom [_Undertale_](https://undertale.com)-style RPG battles.

## Downloads

***

You can download UBE [here]() for Windows operating systems.

## Setup

***

Simply unzip the folder above and launch `UBE.jar` to play. If the game doesn't launch, ensure you have 
[Java](https://download.oracle.com/java/19/archive/jdk-19.0.2_windows-x64_bin.exe) installed.  
Included with the game is [Sha's Battle Pack 1](), a few custom battles of mine made to show off and test *UBE*.
To add the battles to your game, take the folder titled `Shas-Battle-Pack-1`, navigate to your `appdata`
folder, find the folder titled `UTB` (will only appear after booting *UBE* at least once), place the folder in `UTB`,
and rename it to 'battles' (replacing the preexisting folder of the same name).

## What Is UBE?

***

_Undertale Battle Engine_, or _UBE_ is a game engine used to play custom-made _Undertale_ battles.

### Gameplay

For those of you unfamiliar with _Undertale_, battles are a mix between JRPG inspired turn-based combat and casual bullet hell elements. 
The goal of each battle is to defeat a given enemy, either by skillfully dodging their attacks and killing them or by finding creative ways to defuse the situation and spare them.

#### The Main Menu

Upon booting _UBE_, you will be greeted by a list of battles read from your `battles` folder.
Once selected from this menu, you will be entered into a battle.

#### Battles

Each battle is split into two major phases, the player's turn and enemy's turn.  
During the player's turn, select one of the four action commands:  
> **Fight** - Attacks the enemy.  
> *Once selected, the player must land the fight needle on the target. 
> Landing closer to the center of the target will deal more damage.
> After the enemy's health reaches 0, they will die.*

> **Act** - Interacts with the enemy.  
> *Once selected, the player must further select one of the available actions. 
> Check is always present and will display enemy stats, but other actions are battle dependant. 
> Some actions will fail until another action has been preformed. Once all required actions have been completed, 
> the player can spare their foe.*

> **Item** - Uses an item.  
> *Once selected, the player must choose an item to use. In UBE there aren't actually any items 
> as I am lazy. Due to this, when selecting an item to utilize, you're merely selecting the amount 
> of health you wish to regain (each option an increment of 5). Think of it as a difficulty select! ;)*

> **Mercy** - Shows your adversary mercy.  
> *Once selected, the player must elect to spare their enemy or to flee the battle. 
> Fleeing ends the battle and returns the player to the battle select menu, while sparing 
> the enemy will bring a peaceful end to the dispute.*  
>>NOTE: sparing is only effective if the enemy does not wish to, or is unable to, continue the battle.
> In order to achieve this, use of the Act option may be necessary.

Once the player has concluded their turn, the enemy will speak a piece of dialogue 
and then attack the player with a barrage of bullets. In order to avoid the damaging bullets,
the player must maneuver their soul around (and sometimes in between) the enemy's onslaught.
After the attack has concluded, the battle will return to the player's turn.

#### Controls

Throughout _UBE_, the controls remain the same. This is because, from battles to menus, the player is always controlling
their soul (the red heart you see everywhere). As such, actions are contextual, but unchanging in nature. Said actions are:  
> **Move Up** - moves the player upwards.  
> *Press the `up-arrow` to preform.*

> **Move Down** - moves the player downwards.  
> *Press the `down-arrow` to preform.*

> **Move Left** - moves the player left.  
> *Press the `left-arrow` to preform.*

> **Move Right** - moves the player right.  
> *Press the `right-arrow` to preform.*

> **Select** - selects the current option.  
> *Press `Z` to preform.*

> **Deselect** - deselects the current option.  
> *Press `X` to preform.*  
>> NOTE: deselection generally returns the player form a selected menu 
> (i.e. returning the player to action command selection from item selection), but will
> slow the player's soul when being attacked if held (allowing for finer movement). Text can also be completed by 
> pressing `X` while it is progressing.

#### Misc Info
 * If player health reaches 0, they will die and be returned to the battle select menu. 
 * Health is replenished between battles and is displayed above the row of action commands (denoted by the letters 'HP').
 * If a player wins a battle, they will be awarded 'EXP' and 'gold', this does not actually do anything in *UBE*.
 * If a battle present in your `battles` folder is incorrectly formatted, the game will crash upon launch.
Furthermore, if specific data is incorrectly formatted, it can cause the game to crash in battle.

### Custom Battles

Battles in *UBE* are simply folders of data that anyone can make and play. Battles are stored in a folder named `battles`
found within a folder called `UTB` that is generated in the user's `appdata` folder upon booting *UBE* for the first time.  
This section will detail how to make your own battles.

#### Setting up your battle folder.

The name of the folder is what will show in the battle select menu. Inside every folder must be:
 1. A folder named `bullet_sprites`.
2. A folder named `enemy_sprites`.
3. A .txt file named `battle_data`.
4. A .wav file named `bgm`.

##### bullet_sprites

This folder houses all .png files used for bullets in custom battles.
As many sprites as the battle creator wants can be hosted here, and named as the creator sees fit.
Sprites can be any size as bullets will be sized to the sprite in-game.

##### enemy_sprites

This folder houses all .png files used to display enemies in custom battles.
Sprites can be any size, but I recommend working out of [this]() which spans two of the green tiles the sit 
behind the enemy in battle, along with extending from the top of the screen to the bottom of the tiles.
There are 6 sprites that should be present here:
* **attacking.png** - the sprite drawn while an enemy is attacking the player.
* **hit.png** - the sprite drawn while an enemy is taking damage.
* **killed.png** - the sprite drawn once an enemy has been killed.
* **spareable.png** - the sprite drawn once an enemy is spareable.
* **talking.png** - the sprite drawn while an enemy is speaking.
* **default.png** - the sprite drawn at all other times.

> Sprites should be named as listed above in order to be recognised by the game, and all 6 sprites are required.
> However, if `default.png` is present but one or all other sprites are missing, `default.png` will be used for all enemy states.

##### battle_data.txt

This is the trickiest part as the formatting is quite specific, but I assure you it isn't too hard to get a hang of.
Instead of listing all the details here, you can download this [sample file]() for formatting info.
It includes descriptions of what each value is, but you can also use [this]() if you just need an easy to reference list.  
These, however, are all the bullet patterns in the game:

###### Bullet Rain

###### Closing Walls

###### Crusher

###### Double Walls o' Bullet

###### Ladder Drill

###### Walls o' Bullet

##### bgm.wav

This .wav file is the track that will be played on loop throughout the duration of a battle. It **must** be named 
`bgm.wav` in order to be recognised by the game.

> If you have any questions regarding creating your own battles, please refer to the battles included in 
> [Sha's Battle Pack 1]() for examples. Best of luck!

## Known Issues
 * The game **will** crash if even just one battle's data is incorrectly formatted or missing.
 * Lack of UX polish such as volume adjustment or window resizing.
 * Really weird audio bug where a battle will take ages to load if you select it right after you enter the battle select
menu. This can be remedied by waiting just a few second before selecting a battle. I wish I could fix it, but it's an 
issue with the Java audio player (to my knowledge).

## Creator Corner
Hey, this is Sha (the guy who made this thing)!  
Firstly, thank you for checking out *UBE*. I had a lot of fun making it
and hope that you can find some fun in creating and playing levels. I've decided to include this little section in all my 
repos going forward, so I can talk candidly about some of my projects. Seeing as this is the first one, we're making 
history right now (you know, with me typing and you reading)!  
Back to *UBE*, I consider this project a great success. It was definitely a step-down from the UX polish
seen in [Sha's Tetris](https://github.com/ShaShaMan26/Tetris), but I went in expecting that. I made this project
entirely over winter break 2023 and really crunched to get it done within that time. Because of that, focus was
mainly placed on getting the game feature complete and a lot of cuts had to be made on polish. Despite that,
I'm very pleased with the final result.  
Many better versions of this concept exist online already, but it was an amazing learning exercise and 
a great portfolio piece (hopefully). Each project I make has a loose focus on flexing, developing, and refining 
various programming skills, and this one acted as my introduction to state machines. With finally implementing
state machines, I was able to achieve a scope far beyond that which I could previously. Seriously, before this
point my programs' menus were held together by duct tape and kisses, but now I can make more stable programs
that require less work :D  
That's about all I have to say on developing *UBE* tbh... I've been writing this massive readme for like a day
now and am sick of typing. BUT I will list some fun cut content...
* Enemies were planned to have two types: 'regular monsters' and 'boss monsters' with bosses having more scripting,
mirroring the destination as seen in *Undertale*.
* Bullet patterns originally could be stacked, intended to let creators make their own attacks.
* Animated enemy sprites were considered, but cut due to scoping issues.
* A bullet pattern called 'Bullet Tunnel' was cut because, at the time, the code for bullets was a mess, and I was
losing my mind trying to work with it. After I fixed up the code, I probably could have brought 'Bullet Tunnel'
to fruition, but it just gave me bad vibes at that point lol.
