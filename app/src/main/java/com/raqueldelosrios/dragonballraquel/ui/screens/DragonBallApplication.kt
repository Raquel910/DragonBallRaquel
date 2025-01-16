package com.raqueldelosrios.dragonballraquel.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raqueldelosrios.dragonballraquel.DataBase.DragonBallCharacter
import com.raqueldelosrios.dragonballraquel.DataBase.DragonBallCharacter.Companion.getCharacterById
import com.raqueldelosrios.dragonballraquel.DataBase.DragonBallCharacter.Companion.sorted
import com.raqueldelosrios.dragonballraquel.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragonBallApplication(modifier: Modifier = Modifier) {
    //Estado para mostrar la foto con el nombre
    var showImageText by rememberSaveable { mutableStateOf(false) }

    //Estado para mostrar datos del personaje
    var showCharacter by rememberSaveable { mutableStateOf(false) }

    //Variable para saber que personaje has pinchado
    var selectedCharacter by rememberSaveable { mutableStateOf<DragonBallCharacter?>(null) }


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dragon_ball_una_estrella_con_color_oro_serie_vector_de_colorgood_para_icono_balls_fans_en_el_mundo_214457342),
                    contentDescription = "Bola de dragon",
                    modifier = Modifier.height(100.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable._16px_dragon_ball_z_logo),
                    contentDescription = "Logo DragonBall"
                )

                Text(text = "Akira Toriyama")

                    FloatingActionButton(onClick = {
                        showImageText = !showImageText

                     }) {
                        Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "button")
                    }
                if (showImageText) {
                    imageText()
                }



            }

        },
        content = { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)

            ) {
                Column {
                    Text(text = "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")

                    Row (
                        modifier=Modifier.fillMaxSize()
                            .background(color = colorResource(id = R.color.fire))

                    ){
                        LazyColumn (
                            modifier= Modifier
                                .weight(0.2f) // Ocupa la mitad del ancho
                                .fillMaxHeight()

                        ){

                            val groupedCharacters: Map<Char, List<DragonBallCharacter>> = sorted().groupBy { it.spanishName[0] }
                            groupedCharacters.forEach { (header, names) ->
                                stickyHeader {
                                    Text(
                                        text ="$header",
                                        fontSize = 25.sp,
                                        color = Color.Cyan,
                                        textAlign=TextAlign.Center,
                                        fontWeight= FontWeight.Bold,
                                        modifier= Modifier
                                            .background(Color.Magenta)

                                    )
                                    HorizontalDivider(
                                        color=Color.Yellow,
                                        thickness = 1.dp
                                    )

                                }


                                items(names) { character ->
                                    Row {
                                        Text(
                                            text = character.spanishName,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(start = 40.dp)
                                                .padding(vertical = 8.dp)
                                        )
                                        IconButton(
                                            onClick = {
                                                selectedCharacter=character
                                                showCharacter=!showCharacter
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.AccountBox,
                                                contentDescription = "button"
                                            )
                                        }
                                    }

                                }

                            }
                        }
                        Column (
                            modifier= Modifier
                                .weight(0.5f)
                                .fillMaxHeight()
                            .background(color = colorResource(id = R.color.red))

                        ){
                            selectedCharacter?.let { character ->
                                Text(text = """Información de ${character.spanishName+ "\n"
                                        +character.otherName+ "\n"
                                        +character.japaneseName+ "\n"
                                        +character.gender+ "\n"
                                        + character.id+ "\n"
                                        +character.photo+ "\n"
                                        + character.birthdayYear+ "\n"
                                        + character.information+ "\n"
                                        +character.species
                                  }""")
                            }
                        }
                    }


                }
            }
        }
    )
}


@Composable
fun imageText(){
    Box(
        modifier = Modifier
            .padding(15.dp)
            .border(
                BorderStroke(2.dp, Color.Black), // Borde con grosor de 2dp y color negro
                shape = RoundedCornerShape(8.dp) // Bordes redondeados
            )
    ){
        Row {
            Image(
                painter = painterResource(id = R.drawable.fotorachel),
                contentDescription = "Photo")
            Text(text = "Raquel ")
        }
        }


}

