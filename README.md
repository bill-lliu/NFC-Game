﻿# NFC-Game
Inspiration
There will always be an initial nervousness when first engaging with others in a large group. This project aims to subdue the worries, and enhance the fun of networking.

What It Does
The app itself has a tutorial to explain the game, but here is a quick rundown of how to play:

One host will start the game by tapping HOST on the landing frame of the app
The host will answer a few questions that will guide the discussion of the group (usually related to breaking the ice and talking about oneself/own experiences, networking/ job-related questions, large group discussions on specific topics, brainstorming ideas, etc.)
Each player will then turn on NFC, choose JOIN on the app, then tap the backs of their phones to the host to join the group
Each player will then answer the questions asked by the host; short and concise, but still detailed enough to distinguish them from others in the group (be opinionated)
The host will start the game
Each player will receive a call to "hunt" another (anonymous) user, and the answer to one of the questions (chosen randomly) will be shown; players are to then disperse and engage in conversation with one another in pairs, attempting to discretely but quickly direct their topic to the theme of the question and try to get their answer; both players trying to figure out if the other player is their target
When the pair feels they have gleaned enough information to make a decisive conclusion, they will then mutually decide to end the conversation, and tap on either HACK or BEFRIEND on their app before tapping their phones together
The following is a guideline for how scoring will work for each case:
- Player 1 and Player 2 both befriend each other: both players gain 50 points
- Player 1 hacks player 2 correctly as their target, player 2 befriending player 1: player 1 gains 300 points, player 2 loses 150 points
- Player 1 hacks player 2 correctly as their target, player 2 hacking back at player 1: player 1 gains 50 points, player 2 nothing happens
- Player 1 hacks player 2 incorrectly as a neutral player, player 2 befriending player 1: player 1 loses 50 points, player 2 loses 50 points
- Player 1 hacks player 2 incorrectly as a neutral player, player 2 hacking back at player 1: player 1 loses 50 points, player 2 nothing happens
- *Balancing of the scores could be made accordingly for different sized groups so that the execution of play is always fair, and players not being allowed to discuss the choice they will make to the other player
Any interaction involving a player and their target will reset the player to receive another call and have a new target after the interaction, with a new answer randomly chosen from their target
All other regular interactions will result in a text update based on their performance, either praising them for their decision or discouraging them for making a faulty decision
The game will continue until the host decides that enough conversation has happened, and end the game
The host will then choose whether to end the game, or whether to ask another 3 questions; if the host chooses the ask more questions, the game process repeats
If the host chooses to end the game, the score of the player's own score will be replaced with a scoreboard of all players (and usernames), ranked from highest to lowest
The game then ends, and the winner(s) will be listed in descending order based on their score
How we built it & Behind The Scenes
The Android App communicates with the Firebase API for user authentication and realtime database throughout the game. Use of Twilio API for the making of calls and texts in the game, adding a more realistic feeling to the game, better immersing the player. The intent is also for players to be more focused on the game, and lowering the levels of stress that may still linger from meeting new people.

Neat Aspects Of Our Game
Research related to psychology and decision making could be recorded based on our Firebase Database, and analyzing the data could lead to some interesting discoveries. An aspect of psychology is already used, as there is the incentive to always befriend other countries so both countries could benefit, but an impending pressure that the other player could think of you as your target would keep the decision to hack back always on your mind. Players will have to act accordingly and unsuspiciously so that they may earn the most points themselves.

Challenges We Ran Into
Working with Android is hard. Gradle is very prone to randomly breaking for absolutely no reason and needing some time for debugging. Many, many, many iterations in order to work out logistics and balancing reward/punishment so players will always be playing a fair game and discouraged from lying. The inexperience of using Git for groupwork proved to be a reoccurring challenge. NFC application is limited to Android phones, and making an iPhone version with the same game mechanics is virtually impossible (but connections between phones could be made through WiFi or other connective means). In addition, we were pretty much completely learning as we went, as we were all relatively new to Android development, NFC, Twilio, and Firebase.

Accomplishments That We Are Proud Of
We learned :)

What's Next For Hacker Hunter
Balancing of scorings, expansions for new questions/roles, support for extremely large groups, support for iOS.

Built With
android
android-studio
java
nfc
node.js
python
xml
