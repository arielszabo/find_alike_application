package com.szabgab.findalike

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*

const val CHOSEN_TITLE = "chosenTitle"

class MainActivity : AppCompatActivity() {
    // todo: load this from a json file, maybe do a dict style ?
    private val countries = arrayOf("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "AzerbaijanThe Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Advertisement", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Cyprus", "Czech RepublicDenmark", "Djibouti", "Dominica", "Dominican Republic", "Advertisement", "East Timor (Timor-Leste)", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "EthiopiaFiji", "Finland", "FranceGabon", "The Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "GuyanaHaiti", "Honduras", "HungaryIceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "ItalyJamaica", "Japan", "JordanKazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait", "KyrgyzstanLaos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "LuxembourgMadagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "NorwayOmanPakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "PortugalQatarRomania", "Russia", "RwandaSaint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Sudan, South", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "UzbekistanVanuatu", "Vatican City", "Venezuela", "VietnamYemenZambia", "Zimbabwe")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, countries
        )
        val searchTitleAutocomplete: AutoCompleteTextView = findViewById(R.id.search_title_autocomplete)
        searchTitleAutocomplete.setAdapter(adapter)

        val cantFindTitleButton: Button = findViewById(R.id.cant_find_title_button)
        val alreadySeenTitlesCheckbox: CheckBox = findViewById(R.id.already_seen_titles_checkbox)


        // Hide seen movies Logic:
        alreadySeenTitlesCheckbox.isClickable = false  // todo: CHANGE THIS if logged in
        alreadySeenTitlesCheckbox.setOnClickListener { view: View ->
            if (view.isClickable) {
                null
                // todo: change a value and pass it to the API request
            }
            else {
                Toast.makeText(this,
                    "You should be logged in to do this",
                    Toast.LENGTH_LONG).show()
            }
        }

        // Missing Title Logic:
        cantFindTitleButton.setOnClickListener {
            // TODO move to the add missing titles page
        }


        // Search Logic:

        // Set an item click listener for auto complete text view
        searchTitleAutocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                parent, _, position, _ ->

            val selectedItem = parent.getItemAtPosition(position).toString()
            searchForTitle(selectedItem) // TODO move to the next page like a search

        }


        searchTitleAutocomplete.setOnEditorActionListener { v, actionId, event ->
            val selectedItem: String = v.text.toString()

            if ((countries.contains(selectedItem)) and (actionId == EditorInfo.IME_ACTION_SEARCH)) {
                Log.d("TAG", selectedItem)
                searchForTitle(selectedItem) // TODO move to the next page like a search
                true
            } else {
                Toast.makeText(this,
                    "Wrong Value ! can't find this title",
                    Toast.LENGTH_LONG).show()
                false
            }
        }


    }


    private fun searchForTitle(title: String) {
        val intent = Intent(this, SearchResults::class.java).apply {
            putExtra(CHOSEN_TITLE, title)
        }
        startActivity(intent)
    }
}
