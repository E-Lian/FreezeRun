# 210 Personal Project - Freeze Runner

## Description

This will be a 2D platformer game which the hero
has a superpower: chronostasis, the power to ***freeze the time***. 

Unlike most main characters, their power is so weak that they can only stop time for 3 seconds.
The player needs to use their new found superpower and intelligence to get pass 2 levels to finish the game.

## User Stories

- As a user, I want to be able to move up, down, left, and right in the game.
- As a user, I want to be able to shoot a fireball and make my enemies disappear.
- As a user, I want to be able to stop time (in the game) for 3 seconds.
- As a user, I want to be able to pause the game and resume.
- As a user, I want to be able to save my progress in game to file(if I so choose).
- As a user, I want to be able to load my progress from a file (if I so choose).


## Phase 4 Task 2
Some events that may occur in the program:
- Wed Apr 03 01:05:49 PDT 2024  
Fireball created at (208,267)
- Wed Apr 03 01:05:50 PDT 2024  
Player HP - 1
- Wed Apr 03 01:05:51 PDT 2024  
Fireball created at (212,321)
- Wed Apr 03 01:05:51 PDT 2024  
Fireball hits an enemy
- Wed Apr 03 01:05:54 PDT 2024  
Player has entered Level 2
- Wed Apr 03 01:05:57 PDT 2024  
Fireball created at (200,449)
- Wed Apr 03 01:05:58 PDT 2024  
Fireball hits an enemy
- Wed Apr 03 01:06:04 PDT 2024  
Window closed


## Phase 4 Task 3
- refactor the association between Game and Level so that the direction of the arrow is reversed,
from Game to Level. That way none of the classes in model will have a field of Game.
- redesign Fireball's fields and methods so that it extends the abstract class Character.
Initially Character class is meant to represent the living beings in the game: Player and Enemy.
As the game gets bigger and more objects are added, Character came to represent objects
in game that may change coordinates, which means it should also include Fireball.
- refactor so that each object has a detectCollision method to simplify the process of checking collision between 
different objects in game. Additionally overload detectCollision methods in some objects so that it behaves 
differently to collision with different objects. This way the CollisionChecker class will no longer be needed and 
the checking collision method in Game class will be simpler.
