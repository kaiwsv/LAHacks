package com.example.diningnotifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Environment
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
//import androidx.compose.ui.unit.dp
//import it.skrape.core.*
//import it.skrape.fetcher.*
//import it.skrape.selects.html5.*

data class MenuItem(
    var name: String = "",
    var url: String = "",
    var description: String = ""
)

class MainActivity : ComponentActivity() {
    //manually create menuItems due to dependency problems
    val menuItems = arrayListOf(
        MenuItem(name="Tomorrow's Menu", url="Tomorrow's Menu", description="Tomorrow's Menu"),
        MenuItem(name="Korean Style Fried Chicken Wings", url="http://menu.dining.ucla.edu/Recipes/112035/2", description="Tempura battered chicken wings, crunchy-fried to perfection. Served with 4 different sauces to choose from like Gochujang Chili, Honey Citrus, Green Sriracha and Soy Garlic Sauces. Served with Kimchi fried rice."),
        MenuItem(name="Vegetable Kimchi Fried Rice", url="http://menu.dining.ucla.edu/Recipes/167122/10", description="Spicy Korean kimchi stir-fried with lightly fried tofu, chili paste, and steamed rice, then tossed with fresh chopped scallions."),
        MenuItem(name="Korean Crispy Tofu", url="http://menu.dining.ucla.edu/Recipes/145078/3", description="Soft tofu coated with pepper and garlic then fried to a light crisp. Wok-tossed with a spicy Korean sauce and mixed vegetables and served with steamed rice."),
        MenuItem(name="Vegetable Dumplings", url="http://menu.dining.ucla.edu/Recipes/145193/2", description="Plump and juicy dumplings filled with assorted chopped vegetables. Served with our homemade dipping sauce."),
        MenuItem(name="Tuesday", url="Tuesday", description="Tuesday"),
        MenuItem(name="Teriyaki Chicken", url="http://menu.dining.ucla.edu/Recipes/111730/3", description="Tender chicken thigh marinated in a sweet and salty teriyaki sauce, then grilled and topped with fresh chopped scallions."),
        MenuItem(name="Miso-Glazed Tofu Bowl", url="http://menu.dining.ucla.edu/Recipes/145044/2", description="Slices of fresh tofu marinated in a blend of miso, mirin, and low sodium soy sauce, then lightly baked and served with sushi rice lightly seasoned with sesame oil."),
        MenuItem(name="Wednesday", url="Wednesday", description="Wednesday"),
        MenuItem(name="Honey-Glazed Walnut Shrimp", url="http://menu.dining.ucla.edu/Recipes/127069/3", description="Juicy fried shrimp tossed with caramelized walnuts and a creamy sauce of mayonnaise, honey, and lemon juice. Served with steamed rice."),
        MenuItem(name="Spicy Garlic Eggplant & Tofu Stir Fry", url="http://menu.dining.ucla.edu/Recipes/146050/3", description="Tofu and silky eggplant stir-fried together in a spicy Szechuan sauce."),
        MenuItem(name="Thursday", url="Thursday", description="Thursday"),
        MenuItem(name="Korean Style Beef Bowl w/ Mixed Vegetables ( Bibimbap )", url="http://menu.dining.ucla.edu/Recipes/071131/12", description=""),
        MenuItem(name="Vegetarian Bibimbap", url="http://menu.dining.ucla.edu/Recipes/145083/12", description="Fresh leafy spinach, sliced shiitake mushrooms, cucumbers, bean sprouts, and julienned carrots served over steamed rice and topped with a fried egg. Drizzled with red pepper sauce."),
        MenuItem(name="Friday", url="Friday", description="Friday"),
        MenuItem(name="Chinese Char Siu Noodle Soup", url="http://menu.dining.ucla.edu/Recipes/029065/6", description="Roasted BBQ pork served with fresh noodles in a hearty chicken broth simmered with mushrooms, baby bok choy, snow peas, bamboo, spinach, and chopped scallions."),
        MenuItem(name="Vegetarian Miso Ramen", url="http://menu.dining.ucla.edu/Recipes/027148/6", description="Vegetarian ramen broth served with shitake mushrooms,bean sprout, napa cabbage and ramen noodles. Garnished with green onions.")
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF003B5C) //darkest of the UCLA blues
            ) {
                MenuHandler(menuItems)
//                Menu("Vegetable Kimchi Fried Rice", "http://menu.dining.ucla.edu/Recipes/167122/10")
//                Menu("Korean Crispy Tofu", "http://menu.dining.ucla.edu/Recipes/145078/3")
//                Menu("Vegetable Dumplings", "http://menu.dining.ucla.edu/Recipes/145193/2")
            }
        }
    }
}

@Composable
fun Menu(name: String, url: String, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = AnnotatedString(name),
        style = TextStyle(
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        ),
        onClick = {uriHandler.openUri(url)},
        modifier = modifier
    )
}

@Composable
fun MenuHandler(menu: ArrayList<MenuItem>, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Column {
        menu.forEachIndexed { index, item ->
            if (index != 0 && index != 5 && index < 8) { //exclude dates, go to next day only
                if (index > 0) {
//                    Spacer(modifier = Modifier.height(16.dp))
                }
                ClickableText(
                    text = AnnotatedString(item.name),
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    onClick = { uriHandler.openUri(item.url) },
                    modifier = modifier
                )
            }
        }
    }
}

//fun FeastParser(): ArrayList<MenuItem> {
//    val menuItems: ArrayList<MenuItem> = skrape(HttpFetcher) {
//        // perform a GET request to the specified URL
//        request {
//            url = "http://menu.dining.ucla.edu/Menus/FeastAtRieber"
//        }
//
//        extractIt<ArrayList<MenuItem>> {
//            htmlDocument {
//                var duplicate = false
//                "div.menu-item,div.menu-block,div.menu-block-fullwidth" { // CSS selector for menu items
//                    findAll {
//                        forEach {menuItemElement ->
//                            try {
//                                val day =
//                                    menuItemElement.h2 { findFirst { text } }
//                                val menuItem = MenuItem(name=day, url=day, description=day)
//                                it.add(menuItem)
//                                duplicate = true
//                            } catch(e: Throwable) {
//
//                            }
//                            try {
//                                val menuItem = MenuItem(
//                                    url = menuItemElement.a { findFirst { attribute("href") } },
//                                    name = menuItemElement.a { findFirst { text } },
//                                    description = try {
//                                        menuItemElement.span(".menu-item-description") { findFirst { text } }
//                                    } catch (e: Throwable) {
//                                        ""
//                                    }
//                                )
//                                if (!duplicate) {
//                                    it.add(menuItem)
//                                } else {
//                                    duplicate = false
//                                }
//                            } catch (e: Throwable) {
//
//                            }
//
//                        }
//                    }
//                }
//
//            }
//        }
//    }
//
//    //separate spices from menuitems
//    var extras = ArrayList<MenuItem>()
//    val daysOfWeek = arrayListOf("Tomorrow", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
//    var dayIndex = 0
//    var firstIndex = -1
//    var secondIndex = -1
//    //get first day of this week
//    while (firstIndex == -1 && dayIndex < 6) {
//        firstIndex = menuItems.indexOfFirst { it.name.contains(daysOfWeek.get(dayIndex++), ignoreCase = true) }
//        if(firstIndex == 0) {
//            dayIndex++
//        }
//    }
//    //if not friday, get second day of week and split lists accordingly
//    if (dayIndex != 5) {
//        secondIndex = menuItems.indexOfFirst { it.name.contains(daysOfWeek.get(dayIndex)) }
//        extras = ArrayList<MenuItem>(menuItems.subList(firstIndex + 5 + 1, secondIndex).toList())
//    } else { //remove everything after four menu items
//        extras = ArrayList(menuItems.subList(firstIndex + 5 + 1, menuItems.size).toList())
//    }
//    menuItems.removeAll(extras)
//
////    print each scraped item
//    menuItems.forEach {
//        if (it.name.contains(daysOfWeek.get(0)) || it.name.contains(daysOfWeek.get(1)) || it.name.contains(daysOfWeek.get(2)) || it.name.contains(daysOfWeek.get(3)) || it.name.contains(daysOfWeek.get(4)) || it.name.contains(daysOfWeek.get(5)) {
//            print("Day: ")
//            println(it.name)
//        }
//        else {
//            println("MenuItem:")
//            println(it)
//        }
//    }
//
//    return menuItems
//}
