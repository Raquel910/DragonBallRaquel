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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raqueldelosrios.dragonballraquel.DataBase.DragonBallCharacter
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
                    .background(color = colorResource(id = R.color.fire))
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
                        modifier= Modifier
                            .fillMaxSize()
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
                                Text(text = "INFORMACIÓN",
                                    modifier=Modifier
                                        .align(Alignment.CenterHorizontally),
                                    fontWeight = FontWeight.Bold
                                )
                                Column (
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(16.dp)
                                )
                                {
                                    if(character.spanishName.isBlank() || character.spanishName.isEmpty()){
                                        Row {
                                            Text(text = "Nombre Español: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "No tiene nombre en español")
                                        }


                                    }else{
                                        Row {
                                            Text(text = "Nombre Español: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "${character.spanishName}")
                                        }
                                    }

                                    if(character.otherName.isBlank() || character.spanishName.isEmpty()){
                                        Row {
                                            Text(text = "Otro nombre: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "No tiene otro nombre")
                                        }

                                    }else{
                                        Row {
                                            Text(text = "Otro nombre: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "${character.otherName}")
                                        }
                                    }
                                    Row {
                                        Text(text = "Nombre Japonés: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = character.japaneseName)
                                    }

                                    if(character.birthdayYear== 0){
                                        Row {
                                            Text(text = "Cumpleaños: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "Dato desconocido")
                                        }

                                    }else{
                                        Row {
                                            Text(text = "Cumpleaños: ",
                                                fontWeight = FontWeight.Bold)
                                            Text(text = "${character.birthdayYear}")
                                        }
                                    }



                                    Row {
                                        Text(text = "Género: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = character.gender)
                                    }

                                    Row {
                                        Text(text = "ID: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = "${character.id}")
                                    }

                                    Row {
                                        Text(text = "Photo: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = "${character.photo}")
                                    }


                                    Row {
                                        Text(text = "Info personaje: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = "${character.information}")
                                    }
                                    Row {
                                        Text(text = "Species: ",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = "${character.species}")
                                    }

                                }



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

