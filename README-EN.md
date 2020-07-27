# DanmakuVote

![](https://img.shields.io/badge/Bukkit-1.13-blue)
![](https://img.shields.io/badge/Minecraft-1.15.2-yellowgreen)
![](https://img.shields.io/badge/version-1.0-red)

## Introduction

This is a plug-in that enables the Bilibili live room to interact with the Minecraft server

You can set a random or fixed time to vote for the audience, receive the barrage of the designated live broadcast room for statistics, and finally realize the triggering of some events

## Commands

`/bvote setid [直播间room_id号]`:Set listening bilibili living room id

`/bvote setworld`: Set the current world as the world where the event occurred

`/bvote switch`: Set plugin`s status, turn off or on

`/bvote time [random|static] [mintime maxtime | statictime]`: Set the next interval time as fixed or random

## Problems

* Some events may not take effect. For example, after voting to set the weather to sunny, it cannot be set to cloudy

* Part of the code may have a null pointer problem, whether it is an error to be tested

* There is no countdown display, and the plug-in time in the game is not very accurate, and there is a delay problem

## About

The idea of this plug-in comes from Ubisoft’s Hyper Space. The game can be voted on the Twitch platform to affect the game process

At the same time, I saw Bilibili's barrage monitor wheel at this time, and then I conceived and implemented this plug-in

Related development information can be found in [Decision`s blog](https://decision01.cn/)

