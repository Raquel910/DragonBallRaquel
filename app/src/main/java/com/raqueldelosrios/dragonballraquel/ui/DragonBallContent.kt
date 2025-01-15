package com.raqueldelosrios.dragonballraquel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raqueldelosrios.dragonballraquel.R
import com.raqueldelosrios.dragonballraquel.ui.theme.DragonBallRaquelTheme


@Composable
fun DragonBallContent (content: @Composable () -> Unit){
    DragonBallRaquelTheme{
        content()
//        Scaffold (
//            topBar={
//                        Image(
//                            painter = painterResource(id = R.drawable.dragon_ball_una_estrella_con_color_oro_serie_vector_de_colorgood_para_icono_balls_fans_en_el_mundo_214457342),
//                            contentDescription="Bola de dragon",
//                            modifier = Modifier.height(100.dp)
//
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable._16px_dragon_ball_z_logo),
//                            contentDescription = "Logo DragonBall"
//                        )
//                Text(text = "Akira Toriyama")
//            },
//
//            modifier=Modifier.padding(30.dp),
//        )
//        { innerPadding ->
//            content(Modifier.padding(innerPadding))
//        }
    }
}

