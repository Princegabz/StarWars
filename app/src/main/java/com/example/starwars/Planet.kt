package com.example.starwars

var arrName = ArrayList<String>()
var arrClimate = ArrayList<String>()
var arrGravity = ArrayList<String>()
var arrTerrain = ArrayList<String>()
var arrSurfaceWater = ArrayList<String>()
var arrPopulation = ArrayList<String>()

data class Planet(
    val name: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surface_water: String,
    val population: String,
)

data class Result(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
)