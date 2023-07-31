package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  FIELDS
        var name = findViewById(R.id.txtName) as TextView
        var climate = findViewById(R.id.txtClimate) as TextView
        var gravity = findViewById(R.id.txtGravity) as TextView
        var terrain = findViewById(R.id.txtTerrain) as TextView
        var surface_water = findViewById(R.id.txtSurfaceWater) as TextView
        var population = findViewById(R.id.txtPopulation) as TextView
        val btnSearch = findViewById(R.id.btnSearch) as Button
        // dropdown -> planets
        val spinner = findViewById<Spinner>(R.id.spinnerPlanetList)
        if(arrName.size<1) { arrName.add("select a planet") }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrName)
        spinner.adapter = adapter


        // FETCH FROM API
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val url = URL("https://swapi.dev/api/planets")
            val json = url.readText()

            // json(object with planet array) -> retrieve planets
            val result: Result = Gson().fromJson(json, Result::class.java)
            val planets: List<Planet> = result.results

            // loop -> store data in arrays
            for (planet in planets) {
                arrName.add(planet.name)
                arrClimate.add(planet.climate)
                arrGravity.add(planet.gravity)
                arrTerrain.add(planet.terrain)
                arrSurfaceWater.add(planet.surface_water)
                arrPopulation.add(planet.population)
            }
        }


        // ON-CLICK -> SEARCH
        btnSearch.setOnClickListener()
        {
            val selectedPlanet = spinner.getSelectedItem().toString()
            // get index of searched planet
            for ((num, planet) in arrName.withIndex()) {
                if(planet == selectedPlanet){
                    // bind data to view
                    name.setText("Name: " + arrName[num])
                    climate.setText("Climate: " + arrClimate[num])
                    gravity.setText("Gravity: " + arrGravity[num])
                    terrain.setText("Terrain: " + arrTerrain[num])
                    surface_water.setText("Surface Water: " + arrSurfaceWater[num])
                    population.setText("Population: " + arrPopulation[num])
                }
            }
        }
    }
}