MineCar
=======
####Current version: 0.6.8
This plugin will allow players to ride in a minecart that they can control using WASD as a control scheme.


*TO COLLABORATORS
PLEASE ALTER THE TO DO AND IMPLEMENTED LIST AS YOU MAKE CHANGES.
PLEASE ADD ANYTHING THAT ISN'T ALREADY ON THE TO DO OR IMPLEMENTED LIST TO SAID LIST.
IF YOU WANT, YOU CAN MAKE A FORK OF THIS TO MAKE YOUR CHANGES AND THEN SEND A PULL REQUEST
TO GET THEM ADDED TO THIS MAIN REPOSITORY. I HIGHLY RECOMMEND YOU DO THIS.*

TO DO:
------
The first two points should be programmed together as changing speed involves the phyisics

 - MAJOR OVERHAUL OF MINECART MOVEMENT PHYSICS (aim for v 0.7 - 1.0)
 - Make MineCar speed configurable in a config using setVelocity (or similar, refer to API) (config Option already exists) (aim for v 0.7 - 1.0)

KNOWN ISSUES / BUGS / ERRORS:
-----------------------------
None that i am aware of at the moment :)

-----------

- Needs to be tested:
    - more than one player online and using cars
    - UUID persistance and usage ok?
    - all the return code!

IMPLEMENTED:
------------
 - Spawning of MineCar when M is pressed in normal view (ie when not crafting or looking at inventory)
 - Deletion of previous MineCar if applicable (Currently the player must be in the same world as previous MineCar)
 - Basic movement of MineCar (Somehow, still acts a little weird)
 - Check that user has permission to create a MineCar
 - Check that user can drive a MineCar
 - Only control a MineCar if it is in the HashMap, mineCar
 - Make MineCar controlling only possible when the cart is derailed
 - MineCar will only spawn if player has a minecart in their inventory OR if they already have a MineCar
 - On destruction of a MineCar remove it from the HashMap mineCars in MCMain
 - Load and save the list of cars between server restarts/reloads/stops/starts
 - Creation of the config file(with default values) and cars file
 - Admin commands:
    - Reload config
    - Remove commands: Return the minecart to the player's inventory
      (if online else save the name and return it when he comes back, if possible)
 - Support for Permissions 2/3, SuperPerms(PermissionsBukkit) and PermissionsEx
 - Functions for AutoUpdating the config.yml
 - Multi-world support
    - Added a list in the config.yml on which worlds MineCar should be active
 - Added scheduler for constant movement (e.g.: W pressed -> move as long as the key is pressed now just once)

How I think this will work
--------------------------

Players can only have one MineCar at a time. This will be tracked in a HashMap with the Player name as a key and
the EntityID as the value. This will be stored in a flatfile on the server as well so data is kept between
restarts/reloads/stops/starts.

### A MineCar is created by:
 - Pressing M on their keyboard whilst looking at the ground where they want to place their MineCar

The first option is the one I think we should aim for

### Here is the entire process:

- Server start
- Plugin start
- Load flatfile if it exists
- Register listener for keypress events
- Regiser listener for EntityDamage (or destroy?) event
- Register listener for BlockDamage events
- Register listener for VehicleEnter and VehicleExit events
- Player presses key (M?)
- Player hit's ground
- First check if the player already has a MineCar, if they do then follow the indented code, else skip indented code
	- Either delete the MineCar they already have or set it's location to the punched block
- Spawn a MineCar at punched location (FYI spawn location will have to be 1 higher than punched block)
- Player get's in MineCar
- Player presses either W, A, S or D
- If key was W or S then move cart forward or backward. Suggested method for this is MineCar.setVelocityX(...) (need to refer to API for this part)
- If key was A or D then rotate MineCar left or right. I think this would be done using setYaw?
- Player gets out of MineCar
- If player decides to destroy it then make sure to remove it from the HashMap