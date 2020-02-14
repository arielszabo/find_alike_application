package com.szabgab.findalike

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.szabgab.findalike.databinding.ActivityMainBinding


const val CHOSEN_TITLE = "chosenTitle"

class MainActivity : AppCompatActivity() {
    // todo: load this from a json file, maybe do a dict style ?
    private val countries = arrayOf("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "AzerbaijanThe Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Advertisement", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Cyprus", "Czech RepublicDenmark", "Djibouti", "Dominica", "Dominican Republic", "Advertisement", "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "EthiopiaFiji", "Finland", "FranceGabon", "The Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "GuyanaHaiti", "Honduras", "HungaryIceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "ItalyJamaica", "Japan", "JordanKazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait", "KyrgyzstanLaos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "LuxembourgMadagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "NorwayOmanPakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "PortugalQatarRomania", "Russia", "RwandaSaint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Sudan, South", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "UzbekistanVanuatu", "Vatican City", "Venezuela", "VietnamYemenZambia", "Zimbabwe")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, countries
        )

        binding.searchTitleAutocomplete.setAdapter(adapter)


        // Hide seen movies Logic:
        // TODO implement this if login is needed
//        binding.hideAlreadySeenTitlesCheckbox.setOnClickListener { view: View ->
//            if (view.isClickable)  {
//                Log.d("TODO" , "change a value and pass it to the API request")
//                // todo: change a value and pass it to the API request
//            }
//            else {
//                Toast.makeText(this,
//                    "You should be logged in to do this",
//                    Toast.LENGTH_LONG).show()
//            }
//        }


        // Missing Title Logic:
        binding.cantFindTitleButton.setOnClickListener {
            val intent = Intent(this, ReportMissingTitles::class.java)
            startActivity(intent)

            }


        // Search Logic:

        // Set an item click listener for auto complete text view
        binding.searchTitleAutocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                parent, _, position, _ ->

            val selectedItem = parent.getItemAtPosition(position).toString()
            val selectedIMDBbID = countries.indexOf(selectedItem).toString()
            val searchedTitle = SearchedTitle(selectedItem, selectedIMDBbID)
            searchForTitle(searchedTitle)

        }


        binding.searchTitleAutocomplete.setOnEditorActionListener { v, actionId, _ ->
            val selectedItem: String = v.text.toString()

            if ((countries.contains(selectedItem)) and (actionId == EditorInfo.IME_ACTION_SEARCH)) {

                val selectedIMDBbID = countries.indexOf(selectedItem).toString()
                val searchedTitle = SearchedTitle(selectedItem, selectedIMDBbID)
                searchForTitle(searchedTitle)
                true
            } else {
                Toast.makeText(this,
                    "Wrong Value ! can't find this title",
                    Toast.LENGTH_LONG).show()
                false
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.about_and_privacy -> {
                val intent = Intent(this, AboutAndPrivacy::class.java)
                this.startActivity(intent)
            }

        }
        return true
    }

    private fun searchForTitle(searchedTitle: SearchedTitle) {
        val intent = Intent(this, SearchResults::class.java).apply {
            putExtra(CHOSEN_TITLE, searchedTitle.title) // TODO change this ?
        }
        startActivity(intent)
    }
}
